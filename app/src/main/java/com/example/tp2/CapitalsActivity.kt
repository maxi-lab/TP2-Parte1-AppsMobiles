package com.example.tp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext
import com.example.tp2.ui.theme.TP2Theme


class CapitalsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                CapitalsScreen()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CapitalsScreen() {
    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Capitales de Países") },
                navigationIcon = {
                    IconButton(onClick = {
                        val intent = Intent(context, MainActivity::class.java)
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
        LazyColumn(
            contentPadding = innerPadding,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            item { MenuCard("Cargar una ciudad capital", "Permite al usuario cargar una ciudad capital") {
                val intent = Intent(context, CreateCapitalActivity::class.java)
                context.startActivity(intent)
            } }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { MenuCard("Consultar una ciudad por su nombre", "Permite al usuario buscar una ciudad por su nombre") {
                val intent = Intent(context, GetCapitalByNameActivity::class.java)
                context.startActivity(intent)
            } }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { MenuCard("Borrar una ciudad ingresando su nombre", "Permite al usuario borrar una ciudad ingresando su nombre") { /* Acción */ } }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { MenuCard("Borrar todas las ciudades", "Permite al usuario borrar todas las ciudades del listado") { /* Acción */ } }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item { MenuCard("Modificar la población de una ciudad", "Permite al usuario modificar la población de una ciudad") { /* Acción */ } }
        }
    }
}

@Composable
fun MenuCard(title: String, description: String, onButtonClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onButtonClick() }
            .padding(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = description, fontSize = 14.sp)
            }
            Icon(imageVector = Icons.AutoMirrored.Filled.ArrowForward, contentDescription = "Ir a la siguiente pantalla")
        }
    }
}
