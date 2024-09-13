package com.example.tp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.platform.LocalContext
import com.example.tp2.data.Capital
import com.example.tp2.data.CapitalRepository
import androidx.compose.ui.Alignment
import com.example.tp2.ui.theme.TP2Theme

class GetCapitalByNameActivity : ComponentActivity() {
    private val repository by lazy { CapitalRepository.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TP2Theme {
                GetCapitalByNameScreen(repository)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GetCapitalByNameScreen(repository: CapitalRepository) {
    val context = LocalContext.current
    var capitalName by remember { mutableStateOf("") }
    var capitalResult by remember { mutableStateOf<Capital?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Buscar Ciudad Capital") },
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
                    }
                }
            }
        }
    }
}
