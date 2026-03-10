package com.example.mobile_treasure_hunt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobile_treasure_hunt.ui.theme.Mobile_treasure_huntTheme
import com.example.mobile_treasure_hunt.ui.theme.TreasureAppScreen
import com.example.mobile_treasure_hunt.ui.theme.TreasureHuntApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Mobile_treasure_huntTheme {
                TreasureHuntApp()
            }
        }
    }
}

@Composable
fun AppStatus(title: String, modifier: Modifier = Modifier) {
    Text(
        text = "Mobile Treasure Hunt",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mobile_treasure_huntTheme {
        AppStatus("Mobile Treasure Hunt")
    }
}