package com.example.tp2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlin.random.Random


class GuessNumber : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {// dentro de estas llaves se pinta en la pantalla del telefono
            //se pueden llamar las funciones que tengan la etiqueta de composable
            //ViewModel - State
            Column(
                Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Adivina")
                for (i in 1..5) {
                    Number(i)
                }
            }
        }
    }

    @Composable
    fun Number(number: Int) {
        Button(onClick = {
            if (number == Random.nextInt(1, 5)) {
                println("trolo")
            }
        }) {
            Text(text = number.toString())
        }
    }
}

