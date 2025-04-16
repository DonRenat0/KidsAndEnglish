package com.example.kidsandenglish

import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


    fun AppCompatActivity.mostrarPuntosEn(textViewId: Int, nombreJugador: String?) {
        val dbHelper = SQLite(this, "player", null, 1)
        val puntos = dbHelper.obtenerPuntos(nombreJugador)
        val textView = findViewById<TextView>(textViewId)
        textView.text = "Points: $puntos"
    }

    fun AppCompatActivity.verificarRespuesta(puntosJugador: Int?, strRespuestaUsuario: String?): Boolean {
        val dbHelper = SQLite(this, "respuestas", null, 1)
        var existe = dbHelper.esRegistrada(puntosJugador, strRespuestaUsuario)
        return existe
    }
