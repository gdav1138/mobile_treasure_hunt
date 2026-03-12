package com.example.mobile_treasure_hunt.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun StartScreen(
    adventureStart: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(Modifier.height(48.dp))

        Text(
            text = "Mobile Treasure Hunt",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(24.dp))

        // Card space for displaying game rules
        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Game Rules",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(16.dp))

                // Rule 1
                Text(
                    text = "Solve the Clues",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold

                )

                Spacer(Modifier.height(8.dp))

                // Rule 1 Description
                Text(
                    text = "Each clue is a hint towards finding a location or " +
                            "point of interest. If you need help, don't be " +
                            "afraid to use the hint button!",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(16.dp))

                // Rule 2
                Text(
                    text = "Go for the fastest time!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold

                )

                Spacer(Modifier.height(8.dp))

                // Rule 2 Description
                Text(
                    text = "When you begin your hunt, the clock will start! " +
                            "Complete the total hunt as quickly as you can " +
                            "to get the best time!",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(16.dp))

                // Rule 3
                Text(
                    text = "Find it!",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold

                )

                Spacer(Modifier.height(8.dp))

                // Rule 3 Description
                Text(
                    text = "When you think you have arrived at the correct " +
                            "location, press the \"Found It!\" button! If " +
                            "you are not correct or possibly too far away " +
                            "you'll be prompted to try again.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(Modifier.height(16.dp))

                // Quick note on GPS Permissions
                Text(
                    text = "This game utilizes GPS Location services. " +
                            "Please ensure you have enabled location services " +
                            "either from the first launch of the application or " +
                            "by navigating to your Location settings on your device.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }

        Spacer(Modifier.weight(1f))

        Button(
            onClick = adventureStart,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Start Adventure",
                fontSize = 18.sp,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }

        Spacer(Modifier.height(16.dp))
    }
}

