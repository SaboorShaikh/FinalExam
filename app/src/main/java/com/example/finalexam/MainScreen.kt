package com.example.finalexam

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

@Composable
fun MainScreen() {
    val context = LocalContext.current
    var text by remember { mutableStateOf("Loading...") }
    var score by remember { mutableStateOf(0) }

    LaunchedEffect(Unit) {
        try {
            val inputStream = context.assets.open("questions.txt")
            text = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
        } catch (e: IOException) {
            text = "Error loading file"
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            Button(onClick = { score += 1 }) {
                Text(text = "True")
            }
            Button(onClick = { score -= 1 }) {
                Text(text = "False")
            }
        }

        Text(
            text = "Score: $score",
            fontSize = 20.sp
        )
    }
}
