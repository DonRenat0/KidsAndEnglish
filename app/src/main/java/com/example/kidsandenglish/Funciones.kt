package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Context.MODE_PRIVATE
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent



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

fun AppCompatActivity.nextPage() {
    // Lista de actividades (niveles)
    val niveles = listOf(
        // Nivel 1
        lvl1page1::class.java,
        lvl1page2::class.java,
        lvl1page3::class.java,
        lvl1page4::class.java,
        lvl1page5::class.java,
        lvl1page6::class.java,

        // Nivel 2
        lvl2page1::class.java,
        lvl2page2::class.java,
        lvl2page3::class.java,
        lvl2page4::class.java,
        lvl2page5::class.java,
        lvl2page6::class.java,

        // Nivel 3
        lvl3page1::class.java,
        lvl3page2::class.java,
        lvl3page3::class.java,
        lvl3page4::class.java,
        lvl3page5::class.java
    )


    // Obtener SharedPreferences para leer el nivel actual
    val prefs = getSharedPreferences("gameData", MODE_PRIVATE)
    val nivelActual = prefs.getInt("nivelActual", 0)

    // Obtener la actividad correspondiente
    val siguienteNivel = niveles.getOrNull(nivelActual)

    if (siguienteNivel != null) {
        //  NO ME PIDE IMPORT INTENT PERO ERA LO QUE FALTABA
        val intent = Intent(this, siguienteNivel)
        startActivity(intent)
    } else {
        Toast.makeText(this, "¡No hay más niveles!", Toast.LENGTH_LONG).show()
    }
}




/*

*
*
*
**/
