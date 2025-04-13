package com.example.kidsandenglish

import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

 //class Funciones {


   // private var puntos: Int = 0

    fun AppCompatActivity.mostrarPuntosEn(textViewId: Int, nombreJugador: String?) {
        val dbHelper = SQLite(this, "player", null, 1)
        val puntos = dbHelper.obtenerPuntos(nombreJugador)
        val textView = findViewById<TextView>(textViewId)
        textView.text = "Points: $puntos"
    }

    fun AppCompatActivity.verificarRespuesta(respuestaUsuario: String?) {
        val dbHelper = SQLite(this, "respuestas", null, 1)
        var loQueDiositoQuiera = dbHelper.esCorrecta(respuestaUsuario)
        //ahora al lite
        //Deberiamos pasar esta var
    // var BoolCorrecta = verificarRegistroSQLite(respuestaUsuario)

    }

//  Funcion para verificar la respuesta del editText






//}