package com.example.tp2

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tp2.ui.theme.TP2Theme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("MainActivity", "onCreate called")
        enableEdgeToEdge()
        setContent {
            TP2Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val context = LocalContext.current

    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Spacer(modifier = Modifier.size(100.dp))
            Title(
                "Trabajo Práctico Nro 2",
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.TopCenter)
            )
            Spacer(modifier = Modifier.size(200.dp))
            Subtitle(
                "¿A qué quiere ingresar?",
                Modifier
                    .fillMaxWidth()
                    .wrapContentSize(align = Alignment.Center)
            )
            Spacer(modifier = Modifier.size(100.dp))
            FilledButton(
                string = "Un poco de Geografía",
                emoji = "\uD83C\uDFD9\uFE0F",
                onClick = { context.startActivity(Intent(context, CapitalsActivity::class.java)) }
            )
            Spacer(modifier = Modifier.height(30.dp))
            FilledButton(
                string = "Adivina el Número",
                emoji = "🎲",
                onClick = { context.startActivity(Intent(context, GuessNumber::class.java)) }
            )
        }
    }
}


@Composable
fun Title(string: String, modifier: Modifier = Modifier) {
    Text(
        text = string,
        fontSize = 30.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
        modifier = modifier,
    )
}

@Composable
fun Subtitle(string: String, modifier: Modifier = Modifier) {
    Text(
        text = string,
        fontSize = 24.sp,
        modifier = modifier,
    )
}

@Composable
fun FilledButton(string: String, emoji: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = emoji, fontSize = 30.sp)
            Spacer(modifier = Modifier.size(8.dp))
            Text(text = string, fontSize = 25.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
        }
    }
}
