package com.example.tp2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.tp2.data.Capital
import com.example.tp2.data.CapitalRepository
import com.example.tp2.ui.theme.TP2Theme

class CreateCapitalActivity : ComponentActivity() {
    private val repository by lazy { CapitalRepository.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP2Theme {
                CreateCapitalScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun CreateCapitalScreen() {
        val context = LocalContext.current
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Registrar Ciudad Capital") },
                    navigationIcon = {
                        IconButton(onClick = {
                            context.startActivity(Intent(context, CapitalsActivity::class.java))
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Volver atrás"
                            )
                        }
                    }
                )
            }
        ) { innerPadding ->
            CreateCapitalContent(
                modifier = Modifier.padding(innerPadding),
                onRegisterClick = { nombrePais, nombreCapital, habitantesPromedio ->
                    val habitantes = habitantesPromedio.toIntOrNull()
                    if (nombrePais.isNotEmpty() && nombreCapital.isNotEmpty() && habitantes != null && habitantes > 0) {
                        val capital = Capital(nombrePais = nombrePais, nombreCapital = nombreCapital, habitantesPromedio = habitantes)
                        val result = repository.insertCapital(capital)
                        if (result != -1L) {
                            Toast.makeText(this@CreateCapitalActivity, "Ciudad registrada exitosamente", Toast.LENGTH_SHORT).show()
                            finish() // Close the activity and return to the previous screen
                        } else {
                            Toast.makeText(this@CreateCapitalActivity, "Error al registrar ciudad", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@CreateCapitalActivity, "Por favor, complete todos los campos correctamente", Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
    }

    @Composable
    fun CreateCapitalContent(modifier: Modifier = Modifier, onRegisterClick: (String, String, String) -> Unit) {
        var nombrePais by remember { mutableStateOf("") }
        var nombreCapital by remember { mutableStateOf("") }
        var habitantesPromedio by remember { mutableStateOf("") }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(200.dp))
            OutlinedTextField(
                value = nombrePais,
                onValueChange = { nombrePais = it },
                label = { Text("Nombre del País") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = nombreCapital,
                onValueChange = { nombreCapital = it },
                label = { Text("Nombre de la Ciudad Capital") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = habitantesPromedio,
                onValueChange = { habitantesPromedio = it },
                label = { Text("Habitantes Promedio") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {
                onRegisterClick(nombrePais, nombreCapital, habitantesPromedio)
            }) {
                Text("Registrar")
            }
        }
    }
}
