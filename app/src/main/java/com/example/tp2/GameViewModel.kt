package com.example.tp2

import android.app.Application
import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.random.Random
import kotlin.random.nextInt

class GameViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences = application.getSharedPreferences("game_prefs", Context.MODE_PRIVATE)
    var gameState by mutableStateOf(GameState(bestScore = sharedPreferences.getInt("best_score", 0)))
        private set

    init {
        viewModelScope.launch {  }
    }

    fun onClickNumber(number: Int) {
        if (number == Random.nextInt(1..5)) {
            val prevScore: Int = gameState.score
            gameState = gameState.copy(score = prevScore + 10)
        } else {
            manageAttempts()
        }
        if (gameState.bestScore < gameState.score) {
            gameState = gameState.copy(bestScore = gameState.score)
            sharedPreferences.edit().putInt("best_score", gameState.bestScore).apply()
        }
    }

    private fun manageAttempts() {
        val prevAttempts: Int = gameState.attempts
        gameState = gameState.copy(attempts = prevAttempts - 1)
        if (gameState.attempts < 0) {
            gameState = gameState.copy(score = 0, attempts = 5)
        }
    }
}