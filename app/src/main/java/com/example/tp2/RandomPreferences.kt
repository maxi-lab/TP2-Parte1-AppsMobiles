package com.example.tp2

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException

import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

private const val BEST_SCORE="game_settings"
 val Context.dataStore:DataStore<Preferences> by preferencesDataStore(
    name = BEST_SCORE
)
class SettingsDataStore(context: Context){
    private val SCORE= intPreferencesKey("best_score")
    val bestScoreFlow:Flow<Int> = context.dataStore.data.catch {
            if (it is IOException){
                it.printStackTrace()
                emit(emptyPreferences())
            }else{
                throw  it
            }
        }.map { pref->pref[SCORE]?:0 }

    suspend fun saveBestScore(newBestScore:Int,context: Context){
        context.dataStore.edit { preference->preference[SCORE]=newBestScore }
    }
}
