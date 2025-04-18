package com.example.kidsandenglish

import android.content.ContentValues
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


    fun AppCompatActivity.mostrarPuntosEn(textViewId: Int, nombreJugador: String?) {
        val dbHelper = SQLite(this, "player", null, 1)
        val puntos = dbHelper.mostrarPuntosSQLite(nombreJugador)
        val textView = findViewById<TextView>(textViewId)
        textView.text = "Points: $puntos"
    }

    fun AppCompatActivity.verificarRespuesta(puntosJugador: Int?, strRespuestaUsuario: String?): Boolean {
        val dbHelper = SQLite(this, "respuestas", null, 1)
        var existe = dbHelper.esRegistrada(puntosJugador, strRespuestaUsuario)
        return existe
    }

    fun AppCompatActivity.sumarPunto(nombreJugador: String?){
        val conexion = SQLite(this, "player", null, 1)
        val writebbdd = conexion.writableDatabase
        val readbbdd = conexion.readableDatabase
        val contentValues = ContentValues()
        val cursor = readbbdd.rawQuery("SELECT points FROM player WHERE name=?", arrayOf(nombreJugador))

        var puntos = 0
        if (cursor.moveToFirst()){
            puntos = cursor.getInt(0) + 1
        }
        contentValues.put("points", puntos)
        Log.d("Miapp", "$contentValues")

        writebbdd.update( "player", contentValues, "name=?", arrayOf(nombreJugador))
        writebbdd.close()
        readbbdd.close()
    }
