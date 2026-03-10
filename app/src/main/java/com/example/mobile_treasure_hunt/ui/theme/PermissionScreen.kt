package com.example.mobile_treasure_hunt.ui.theme

import android.Manifest
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
fun PermissionScreen(
    permissionDenied: Boolean,
    permissionResult: (Boolean) -> Unit,
    proceed: () -> Unit
) {
    val permissionRequest = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) {
        permissionGranted: Boolean -> permissionResult(permissionGranted)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Mobile Treasure Hunt",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Can you solve the riddle?!",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.padding(20.dp)
            ) {
                Text(
                    text = "Location Access Required",
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "This game requires the use of GPS to verify " +
                            "that you have arrived at the correct location " +
                            "for the given riddle. We will only utilize " +
                            "your device's location to check if you have " +
                            "arrived at the correct location when you press " +
                            "the \"Found It!\" button."
                )

                Spacer(Modifier.height(8.dp))

            }
        }

        Spacer(Modifier.height(24.dp))

        if (permissionDenied) {
            Text(
                text = "Location permission is required to verify. ",
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.height(16.dp))

        }

        Button(
            onClick = {
                permissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Enable Location Access")
        }
    }
}