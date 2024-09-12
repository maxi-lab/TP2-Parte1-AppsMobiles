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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.tp2.data.Capital
import com.example.tp2.data.CapitalRepository
import com.example.tp2.ui.theme.TP2Theme

class CreateCapitalActivity : ComponentActivity() {
    private lateinit var repository: CapitalRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = CapitalRepository(this)
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
        var shouldShowCapitals by remember { mutableStateOf(false) } // Estado para controlar si se muestran las capitales

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
                            shouldShowCapitals = true // Activar el estado para mostrar las capitales
                        } else {
                            Toast.makeText(this@CreateCapitalActivity, "Error al registrar ciudad", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this@CreateCapitalActivity, "Por favor, complete todos los campos correctamente", Toast.LENGTH_SHORT).show()
                    }
                }
            )

            // Mostrar todas las capitales registradas si el estado es verdadero
            if (shouldShowCapitals) {
                ShowAllCapitals()
            }
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

            // Aquí se mostrará la lista de capitales registrada
            ShowAllCapitals()
        }
    }

    // Nueva función para mostrar todas las capitales guardadas
    @Composable
    fun ShowAllCapitals() {
        // Obtener todas las ciudades guardadas
        val capitalsList by remember { mutableStateOf(repository.getAllCapitals()) }

        LazyColumn(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            if (capitalsList.isNotEmpty()) {
                items(capitalsList) { capital ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(text = "País: ${capital.nombrePais}")
                            Text(text = "Capital: ${capital.nombreCapital}")
                            Text(text = "Habitantes: ${capital.habitantesPromedio}")
                        }
                    }
                }
            } else {
                item {
                    Text("No hay ciudades registradas")
                }
            }
        }
    }
}
