package com.example.tp2.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CapitalDatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "capitales.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "capitales"
        private const val COLUMN_ID = "_id"
        private const val COLUMN_PAIS = "nombrePais"
        private const val COLUMN_CAPITAL = "nombreCapital"
        private const val COLUMN_HABITANTES = "habitantesPromedio"

        private const val TABLE_CREATE =
            "CREATE TABLE $TABLE_NAME (" +
                    "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$COLUMN_PAIS TEXT, " +
                    "$COLUMN_CAPITAL TEXT, " +
                    "$COLUMN_HABITANTES INTEGER);"
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(TABLE_CREATE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }
}
