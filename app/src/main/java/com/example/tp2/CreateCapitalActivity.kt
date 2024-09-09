@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tp2.ui.theme.TP2Theme

class CreateCapitalActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                CreateCapitalScreen()
            }
        }
    }
}

@Composable
fun CreateCapitalScreen() {
    var countryName by remember { mutableStateOf("") }
    var capitalName by remember { mutableStateOf("") }
    var population by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear Capital") }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                OutlinedTextField(
                    value = countryName,
                    onValueChange = { countryName = it },
                    label = { Text("Nombre del País") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = capitalName,
                    onValueChange = { capitalName = it },
                    label = { Text("Nombre de la Ciudad Capital") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    value = population,
                    onValueChange = { population = it },
                    label = { Text("Población Aproximada") },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        Capital(
                            countryName = countryName,
                            capitalName = capitalName,
                            population = population.toIntOrNull() ?: 0
                        )
                        // Handle the created capital object
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Crear Capital")
                }
            }
        }
    )
}