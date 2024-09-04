package com.example.tp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
                        "Trabajo Practico Nro 2",
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
                        string = "Un poco de geografía",
                        emoji = "\uD83C\uDFD9\uFE0F",
                        destinationActivity = CapitalsActivity::class.java //actividad de capitale
                    )
                    Spacer(modifier = Modifier.height(30.dp))
                    FilledButton(
                        string = "Adivina el Numero",
                        emoji = "🎲",
                        destinationActivity = GuessNumber::class.java //actividad de adivina el numero
                    )
                }
            }
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
fun FilledButton(string: String, emoji: String, modifier: Modifier = Modifier, destinationActivity: Class<*>) {
    val context = LocalContext.current
    Button(
        onClick = {
            val intent = Intent(context, destinationActivity)
            context.startActivity(intent) },
        modifier = modifier
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

