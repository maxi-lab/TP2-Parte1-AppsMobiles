package com.example.tp2

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import kotlin.random.Random


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {// dentro de estas llaves se pinta en la pantalla del telefono
            //se pueden llamar las funciones que tengan la etiqueta de composable
            Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                NumberLabel()
                ButtonGuess()
            }
        }
    }

    @Composable
     fun  NumberLabel(){
         var number by remember {
               mutableStateOf("")
           }
        TextField(value = number,
               onValueChange = { number = it },
               label = { Text(text = ("Un numero del 1 a 5"))},
               maxLines = 1,
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number) )

    }

    @Composable
    fun ButtonGuess(){
        Button(onClick={ println(Random.nextInt(1,5)) }){//ojo con el boton que importas
            Text("Adivina")
        }
    }
}

