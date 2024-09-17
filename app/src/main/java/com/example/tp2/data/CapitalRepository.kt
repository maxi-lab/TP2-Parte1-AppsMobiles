package com.example.tp2.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.content.ContentValues
import android.util.Log


class CapitalRepository private constructor(context: Context) {

    private val dbHelper = CapitalDatabaseHelper(context.applicationContext)
    private val database: SQLiteDatabase = dbHelper.writableDatabase

    companion object {
        @Volatile
        private var INSTANCE: CapitalRepository? = null

        fun getInstance(context: Context): CapitalRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = CapitalRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }

    fun insertCapital(capital: Capital): Long {
        val values = ContentValues().apply {
            put("nombrePais", capital.nombrePais.lowercase())
            put("nombreCapital", capital.nombreCapital.lowercase())
            put("habitantesPromedio", capital.habitantesPromedio)
        }
        val result = database.insert("capitales", null, values)
        Log.d("CapitalRepository", "Inserted capital: $capital with result: $result")
        return result
    }



    fun getAllCapitals(): List<Capital> {
        val capitals = mutableListOf<Capital>()
        val cursor = database.query(
            "capitales",
            null,
            null,
            null,
            null,
            null,
            null
        )
        cursor.use { c ->
            while (c.moveToNext()) {
                val id = c.getLong(c.getColumnIndexOrThrow("_id"))
                val nombrePais = c.getString(c.getColumnIndexOrThrow("nombrePais")).replaceFirstChar { it.uppercase() }
                val nombreCapital = c.getString(c.getColumnIndexOrThrow("nombreCapital")).replaceFirstChar { it.uppercase() }
                val habitantesPromedio = c.getInt(c.getColumnIndexOrThrow("habitantesPromedio"))
                capitals.add(Capital(id, nombrePais, nombreCapital, habitantesPromedio))
            }
        }
        return capitals
    }


    fun deleteCapital(id: Long): Boolean {
        return database.delete("capitales", "_id = ?", arrayOf(id.toString())) > 0
    }

    fun updateCapital(capital: Capital): Boolean {
        val values = ContentValues().apply {
            put("nombrePais", capital.nombrePais.lowercase())
            put("nombreCapital", capital.nombreCapital.lowercase())
            put("habitantesPromedio", capital.habitantesPromedio)
        }
        return database.update("capitales", values, "_id = ?", arrayOf(capital.id.toString())) > 0
    }

    fun getCapitalByName(nombreCapital: String): Capital? {
        val cursor = database.query(
            "capitales",
            null,
            "nombreCapital = ?",
            arrayOf(nombreCapital.lowercase()),
            null,
            null,
            null
        )
        cursor.use { c ->
            if (c.moveToNext()) {
                val id = c.getLong(c.getColumnIndexOrThrow("_id"))
                val nombrePais = c.getString(c.getColumnIndexOrThrow("nombrePais")).replaceFirstChar { it.uppercase() }
                val nombreCapitalDb = c.getString(c.getColumnIndexOrThrow("nombreCapital")).replaceFirstChar { it.uppercase() }
                val habitantesPromedio = c.getInt(c.getColumnIndexOrThrow("habitantesPromedio"))
                return Capital(id, nombrePais, nombreCapitalDb, habitantesPromedio)
            }
        }
        return null
    }

    fun deleteAllCapitals(): Boolean {
        return database.delete("capitales", null, null) > 0
    }

}
