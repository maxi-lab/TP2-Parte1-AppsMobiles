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
import com.example.tp2.data.CapitalRepository
import com.example.tp2.ui.theme.TP2Theme

class CapitalsActivity : ComponentActivity() {
    private lateinit var repository: CapitalRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        repository = CapitalRepository(this)
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
                        context.startActivity(Intent(context, MainActivity::class.java))
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
            item {
                MenuCard(
                    title = "Cargar una ciudad capital",
                    description = "Permite al usuario cargar una ciudad capital",
                    onButtonClick = {
                        context.startActivity(Intent(context, CreateCapitalActivity::class.java))
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item {
                MenuCard(
                    title = "Consultar una ciudad por su nombre",
                    description = "Permite al usuario buscar una ciudad por su nombre",
                    onButtonClick = {
                        // Aquí pones la acción para consultar una ciudad por su nombre
                        // context.startActivity(Intent(context, ConsultaCiudadActivity::class.java))
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item {
                MenuCard(
                    title = "Borrar una ciudad ingresando su nombre",
                    description = "Permite al usuario borrar una ciudad ingresando su nombre",
                    onButtonClick = {
                        // Aquí pones la acción para borrar una ciudad ingresando su nombre
                        // context.startActivity(Intent(context, BorrarCiudadActivity::class.java))
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item {
                MenuCard(
                    title = "Borrar todas las ciudades",
                    description = "Permite al usuario borrar todas las ciudades del listado",
                    onButtonClick = {
                        // Aquí pones la acción para borrar todas las ciudades
                        // context.startActivity(Intent(context, BorrarTodasCiudadesActivity::class.java))
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(10.dp)) }
            item {
                MenuCard(
                    title = "Modificar la población de una ciudad",
                    description = "Permite al usuario modificar la población de una ciudad",
                    onButtonClick = {
                        // Aquí pones la acción para modificar la población de una ciudad
                        // context.startActivity(Intent(context, ModificarPoblacionActivity::class.java))
                    }
                )
            }
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
