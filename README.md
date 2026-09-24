# Mobile Treasure Hunt

A GPS-based Android treasure hunt game built with Kotlin and Jetpack Compose. Players solve riddles that hint at real-world locations, navigate there on foot, and verify they've arrived using live GPS positioning and Haversine distance calculation.

<!-- If you have a screenshot or screen recording, add it here: -->
<!-- ![Mobile Treasure Hunt gameplay](screenshot.png) -->

## How It Works

The game presents players with a series of riddles. Each riddle describes a real-world location. The player reads the clue, figures out where it's pointing, physically travels there, and presses "Found It!" to check their position.

When the player taps "Found It!", the app:

1. Requests the device's current GPS coordinates using `FusedLocationProviderClient` with high-accuracy priority
2. Calculates the great-circle distance between the player's position and the target location using the **Haversine formula**
3. Compares that distance against a configurable proximity threshold (default: 100 meters)
4. If the player is close enough, the clue is marked as solved and the next one loads. If not, they're prompted to keep looking.

A running timer tracks total time across all clues. When every clue is solved, the game shows the player's total completion time.

## Architecture

The app follows a **ViewModel + Unidirectional Data Flow (UDF)** pattern:

- A single `AppViewModel` holds all game state in a `StateFlow<AppUiState>`
- Screens are stateless composables that read from the state flow and call ViewModel functions to trigger updates
- Navigation is managed through `NavHost` and `NavController` with five distinct screens
- State changes flow in one direction: user action → ViewModel function → state update → UI recomposition

This architecture keeps the UI layer clean and testable, with all game logic centralized in the ViewModel.

## Screens

| Screen | Purpose |
|--------|---------|
| **Permission** | Requests fine location access; explains why GPS is needed |
| **Start** | Displays game rules and starts the timer |
| **Clue** | Shows the current riddle, optional hint toggle, timer, and "Found It!" button |
| **Clue Solved** | Confirms the location was found; displays the location name and description |
| **Game Complete** | Shows total completion time and option to play again |

## Geospatial Implementation

The distance calculation lives in `model/haversine.kt` as a `Geo` class:

- Takes latitude and longitude as constructor parameters
- Implements the Haversine formula to compute great-circle distance in kilometers between two `Geo` points
- Uses Earth's mean radius (6,372.8 km) for the calculation
- The result is converted to meters in the ViewModel and compared against each clue's `winCondition` threshold

Location data comes from Google's `FusedLocationProviderClient`, initialized in `MainActivity` and passed down to composables. The app uses `getCurrentLocation()` with `PRIORITY_HIGH_ACCURACY` rather than continuous location tracking, so GPS is only accessed at the moment the player checks their position.

## Project Structure

```
├── MainActivity.kt                  # Entry point; initializes FusedLocationProviderClient
├── ui/theme/
│   ├── AppScreen.kt                 # NavHost setup, screen routing, location verification logic
│   ├── AppViewModel.kt              # Game state management, timer, location checking
│   ├── AppUiState.kt                # Data class defining all UI state
│   ├── PermissionScreen.kt          # Location permission request screen
│   ├── StartScreen.kt               # Game rules and start button
│   ├── ClueScreen.kt                # Riddle display, hint toggle, Found It button
│   ├── ClueSolved.kt                # Location found confirmation
│   ├── GameCompleteScreen.kt        # Final time and play again option
│   ├── Theme.kt                     # Material3 theming
│   ├── Color.kt                     # Color definitions
│   └── Type.kt                      # Typography definitions
├── model/
│   ├── haversine.kt                 # Geo class with Haversine distance calculation
│   └── clue_model.kt                # Clue data source with coordinates and riddles
└── data/
    └── clue_datasource.kt           # TreasureHuntClue data class definition
```

## Built With

- **Kotlin** with **Jetpack Compose** for declarative UI
- **ViewModel** + **StateFlow** for state management (UDF pattern)
- **NavHost / NavController** for screen navigation
- **FusedLocationProviderClient** for GPS positioning
- **Coroutines** for the game timer (launch, delay, cancel)
- **Material3** components and theming

## Running It

1. Clone the repo and open in Android Studio
2. Connect an Android device or start an emulator with location services enabled
3. Build and run
4. Grant location permission when prompted
5. Start the hunt and head to the locations described in the clues

Note: The included clues reference locations in the Castle Rock, WA area. To play elsewhere, update the coordinates and riddles in `clue_model.kt` and the corresponding string resources.

## What I Learned

This project taught me how to work with real-world sensor data in a mobile application. GPS coordinates aren't precise the way database records are. They have accuracy margins, they can drift, and the same physical spot can return slightly different values each time you check. Designing the win condition system around configurable proximity thresholds rather than exact coordinate matching was a key design decision that came from testing the app outdoors and seeing how GPS actually behaves in practice.
