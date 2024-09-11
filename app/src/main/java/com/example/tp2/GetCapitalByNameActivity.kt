package com.example.tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import com.example.tp2.ui.theme.TP2Theme
import com.example.tp2.data.CapitalRepository
import com.example.tp2.data.Capital

class GetCapitalByNameActivity : ComponentActivity() {
    private lateinit var repository: CapitalRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = CapitalRepository(this)
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                GetCapitalByNameScreen(repository)
            }
        }
    }
}

@Composable
fun GetCapitalByNameScreen(repository: CapitalRepository) {
    var capitalName by remember { mutableStateOf("") }
    var capitalResult by remember { mutableStateOf<Capital?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Buscar Ciudad Capital", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))
        Text("Por favor indique el nombre de la capital a buscar", fontSize = 16.sp, modifier = Modifier.padding(bottom = 16.dp))

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
                }
            }
        }
    }
}