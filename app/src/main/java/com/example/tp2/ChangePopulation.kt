package com.example.tp2

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp2.data.Capital
import com.example.tp2.data.CapitalRepository
import com.example.tp2.ui.theme.TP2Theme

class ChangePopulation : ComponentActivity() {

    private val repository by lazy { CapitalRepository.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                ChangePopulation(repository)
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangePopulation(repository: CapitalRepository) {
    val context = LocalContext.current
    var capitalName by remember { mutableStateOf("") }
    var capitalResult by remember { mutableStateOf<Capital?>(null) }
    var newPopulation by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modificar Población de Ciudad Capital") },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, CapitalsActivity::class.java)
                        context.startActivity(intent)
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Subtitle(string = "Ingrese el nombre de la capital", modifier = Modifier.padding(16.dp))
            OutlinedTextField(
                value = capitalName,
                onValueChange = { capitalName = it },
                label = { Text("Nombre de la Ciudad Capital") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                capitalResult = repository.getCapitalByName(capitalName)
            }) {
                Text("Buscar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            capitalResult?.let { capital ->
                Card(modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = "País: ${capital.nombrePais}", fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Capital: ${capital.nombreCapital}", fontSize = 16.sp)
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = "Habitantes Promedio: ${capital.habitantesPromedio}", fontSize = 16.sp)

                        // Campo para actualizar la población
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedTextField(
                            value = newPopulation,
                            onValueChange = { newPopulation = it },
                            label = { Text("Nueva Población") },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(16.dp))
                        Button(onClick = {
                            val population = newPopulation.toIntOrNull()
                            if (population != null && population > 0) {
                                capital.habitantesPromedio = population
                                val success = repository.updateCapital(capital)
                                if (success) {
                                    Toast.makeText(context, "Población actualizada exitosamente", Toast.LENGTH_SHORT).show()
                                } else {
                                    Toast.makeText(context, "Error al actualizar la población", Toast.LENGTH_SHORT).show()
                                }
                            } else {
                                Toast.makeText(context, "Ingrese un número válido", Toast.LENGTH_SHORT).show()
                            }
                        }) {
                            Text("Actualizar Población")
                        }
                    }
                }
            }
        }
    }
}