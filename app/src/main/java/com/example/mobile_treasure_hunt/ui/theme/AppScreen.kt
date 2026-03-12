package com.example.mobile_treasure_hunt.ui.theme

import android.Manifest
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mobile_treasure_hunt.ui.theme.AppViewModel


enum class TreasureAppScreen {
    Permission,
    GameStart,
    ClueOne,
    ClueTwo,
    ClueThree,
    Complete
}

@Composable
fun TreasureHuntApp(appViewModel: AppViewModel = viewModel()) {
    val navController = rememberNavController()
    val uiState by appViewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Quick check if permission has already been granted for the device
    val startDestination = remember {
        if (ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
            ) {
            TreasureAppScreen.GameStart.name
        } else {
            TreasureAppScreen.Permission.name
        }
    }

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = TreasureAppScreen.Permission.name) {
            PermissionScreen(
                permissionDenied = uiState.permissionDenied,
                permissionResult = {granted ->

                    appViewModel.updatePermission(granted)

                    if (granted) {
                        navController.navigate(TreasureAppScreen.GameStart.name) {

                            // Prevent issue of hitting back and getting permissions screen again
                            popUpTo(TreasureAppScreen.Permission.name) {
                                inclusive = true
                            }
                        }
                    }
                },

                proceed = {
                    navController.navigate(TreasureAppScreen.GameStart.name)
                }
            )
        }

        composable(route = TreasureAppScreen.GameStart.name) {
            StartScreen(
                adventureStart = {

                }
            )
        }
    }
}

//@Composable
//private fun CategoriesScreen(
//    appViewModel: AppViewModel,
//    onCategoryClick: (String) -> Unit
//) {
//    val appUiState by appViewModel.uiState.collectAsState()
//
//    Column(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        Text(
//            text = appUiState.cityName,
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        Spacer(
//            Modifier.height(12.dp)
//        )
//
//        Row {
//            OutlinedTextField(
//                value = appUiState.newCategory,
//                onValueChange = {
//                    appViewModel.updateCategoryList(it)
//                },
//                label = {Text("New Category")},
//                modifier = Modifier.weight(1f)
//            )
//
//            Spacer(
//                Modifier.width(8.dp)
//            )
//
//            Button(onClick = {
//                appViewModel.addCategory()
//            }) {
//                Text(
//                    text ="Add"
//                )
//            }
//        }
//
//        if (appUiState.invalidCategory) {
//            Spacer(
//                Modifier.height(8.dp)
//            )
//
//            Text(
//                text = "That category already exists!",
//                color = MaterialTheme.colorScheme.error
//            )
//        }
//
//        Spacer(
//            Modifier.height(16.dp)
//        )
//
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            items(items = appUiState.categories) {
//                    name ->
//                Card(
//                    modifier = Modifier.fillMaxWidth()
//
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(12.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = name,
//                            modifier = Modifier
//                                .padding(12.dp)
//                                .weight(1f)
//                                .clickable {
//                                    onCategoryClick(name)
//                                }
//                        )
//
//                        IconButton(onClick = { appViewModel.deleteCategory(name) }) {
//                            Icon(
//                                imageVector = Icons.Filled.Delete,
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//@Composable
//private fun RecommendationsScreen(
//    appViewModel: AppViewModel,
//    onRecClick: (String) -> Unit,
//    onBack: () -> Unit
//) {
//    val appUiState by appViewModel.uiState.collectAsState()
//    val category = appUiState.selectedCategory
//    val recommendations = appUiState.recommendations[category].orEmpty()
//
//    Column(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        Text(
//            text = appUiState.selectedCategory,
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        Spacer(
//            Modifier.height(12.dp)
//        )
//
//        Row {
//            OutlinedTextField(
//                value = appUiState.newRecommendation,
//                onValueChange = {
//                    appViewModel.updateRecommendationList(it)
//                },
//                label = {Text("New Recommendation")},
//                modifier = Modifier.weight(1f)
//            )
//
//            Spacer(
//                Modifier.width(8.dp)
//            )
//
//            Button(onClick = {
//                appViewModel.addRecommendation()
//            }) {
//                Text(
//                    text ="Add"
//                )
//            }
//        }
//
//        if (appUiState.invalidRecommendation) {
//            Spacer(
//                Modifier.height(8.dp)
//            )
//
//            Text(
//                text = "That recommendation already exists!",
//                color = MaterialTheme.colorScheme.error
//            )
//        }
//
//        Spacer(
//            Modifier.height(16.dp)
//        )
//
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            items(recommendations) {
//                    name ->
//                Card(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(12.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = name,
//                            modifier = Modifier
//                                .padding(12.dp)
//                                .weight(1f)
//                                .clickable { onRecClick(name) }
//                        )
//
//                        IconButton(onClick = { appViewModel.deleteRecommendation(name) }) {
//                            Icon(
//                                imageVector = Icons.Filled.Delete,
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }
//            }
//        }
//
//        Spacer(Modifier.height(16.dp))
//
//        Button(onClick = onBack) {
//            Text("Back")
//        }
//    }
//}
//
//@Composable
//private fun DetailScreen(
//    appViewModel: AppViewModel,
//    onBack: () -> Unit
//) {
//    val appUiState by appViewModel.uiState.collectAsState()
//    val recName = appUiState.selectedRecommendation
//    val details = appUiState.recDetails[recName].orEmpty()
//
//    Column(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        Text(
//            text = recName,
//            style = MaterialTheme.typography.headlineMedium
//        )
//
//        Spacer(
//            Modifier.height(12.dp)
//        )
//
//        LazyColumn(
//            verticalArrangement = Arrangement.spacedBy(8.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .weight(1f)
//        ) {
//            items(details) { name ->
//                Card(
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    Row(
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .padding(12.dp),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        Text(
//                            text = name,
//                            modifier = Modifier
//                                .padding(12.dp)
//                                .weight(1f),
//                            maxLines = 3,
//                            overflow = TextOverflow.Ellipsis
//                        )
//
//                        IconButton(onClick = { appViewModel.deleteDetail(name) }) {
//                            Icon(
//                                imageVector = Icons.Filled.Delete,
//                                contentDescription = null
//                            )
//                        }
//                    }
//                }
//            }
//
//        }
//
//        Spacer(
//            Modifier.height(12.dp)
//        )
//
//        Row {
//            OutlinedTextField(
//                value = appUiState.newRecDetail,
//                onValueChange = {
//                    appViewModel.updateRecDetail(it)
//                },
//                label = { Text("New Detail") },
//                modifier = Modifier.weight(1f)
//            )
//
//            Spacer(
//                Modifier.width(8.dp)
//            )
//
//            Button(onClick = {
//                appViewModel.addDetail()
//            }) {
//                Text(
//                    text = "Add"
//                )
//            }
//        }
//
//        if (appUiState.invalidDetail) {
//            Spacer(
//                Modifier.height(8.dp)
//            )
//
//            Text(
//                text = "That detail already exists!",
//                color = MaterialTheme.colorScheme.error
//            )
//        }
//
//        Spacer(
//            Modifier.height(12.dp)
//        )
//
//        Button(
//            onClick = onBack,
//            modifier = Modifier.fillMaxWidth()
//        ) {
//            Text("Back")
//        }
//    }
//}