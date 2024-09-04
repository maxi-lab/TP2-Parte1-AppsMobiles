package com.example.tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp2.ui.theme.TP2Theme

class CapitalsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp)
                ) {
                    Spacer(modifier = Modifier.size(100.dp))
                    Title(
                        "Capitales de Países",
                        Modifier
                            .fillMaxWidth()
                            .wrapContentSize(align = Alignment.TopCenter)
                    )
                    Spacer(modifier = Modifier.size(20.dp))
                    MenuScreen()
                }
            }
        }
    }
}

@Composable
fun MenuCard(title: String, description: String, onButtonClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(3.dp)
            .clickable { onButtonClick() },
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = description,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "Ir a la siguiente pantalla"
            )
        }
    }
}

@Composable
fun MenuScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            MenuCard(
                title = "Cargar una ciudad capital",
                description = "Permite al usuario cargar una ciudad capital",
                onButtonClick = {

                }
            )
        }
        item {
            Spacer(modifier = Modifier.size(10.dp))
            MenuCard(
                title = "Consultar una ciudad por su nombre",
                description = "Permite al usuario buscar una ciudad por su nombre",
                onButtonClick = {

                }
            )
        }
        item {
            Spacer(modifier = Modifier.size(10.dp))
            MenuCard(
                title = "Borrar una ciudad ingresando su nombre",
                description = "Permite al usuario borrar una ciudad ingresando su nombre",
                onButtonClick = {

                }
            )
        }
        item {
            Spacer(modifier = Modifier.size(10.dp))
            MenuCard(
                title = "Borrar todas las ciudades",
                description = "Permite al usuario borrar todas las ciudades del listado",
                onButtonClick = {

                }
            )
        }
        item {
            Spacer(modifier = Modifier.size(10.dp))
            MenuCard(
                title = "Modificar la población de una ciudad",
                description = "Permite al usuario modificar la población de una ciudad",
                onButtonClick = {

                }
            )
        }
    }
}
