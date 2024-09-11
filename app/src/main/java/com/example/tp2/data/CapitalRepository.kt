package com.example.tp2.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class CapitalRepository(context: Context) {

    private val dbHelper = CapitalDatabaseHelper(context)
    private val database: SQLiteDatabase = dbHelper.writableDatabase

    fun insertCapital(capital: Capital): Long {
        val values = ContentValues().apply {
            put("nombrePais", capital.nombrePais)
            put("nombreCapital", capital.nombreCapital)
            put("habitantesPromedio", capital.habitantesPromedio)
        }
        return database.insert("capitales", null, values)
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
        cursor.use {
            while (it.moveToNext()) {
                val id = it.getLong(it.getColumnIndexOrThrow("_id"))
                val nombrePais = it.getString(it.getColumnIndexOrThrow("nombrePais"))
                val nombreCapital = it.getString(it.getColumnIndexOrThrow("nombreCapital"))
                val habitantesPromedio = it.getInt(it.getColumnIndexOrThrow("habitantesPromedio"))
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
            put("nombrePais", capital.nombrePais)
            put("nombreCapital", capital.nombreCapital)
            put("habitantesPromedio", capital.habitantesPromedio)
        }
        return database.update("capitales", values, "_id = ?", arrayOf(capital.id.toString())) > 0
    }
}
