package com.example.kidsandenglish

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class lvl3page3  : AppCompatActivity() {
    private var nombreJugador: String? = null
    private lateinit var btnSvExit33:Button
    private lateinit var btnCheck33: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl3page3)
        nombreJugador = intent.getStringExtra("nombreJugador")
        btnCheck33= findViewById(R.id.btnCheck33)
        btnCheck33.setOnClickListener {
            verificarRespuesta()
        }
        btnSvExit33= findViewById(R.id.btnSvExit33)
        btnSvExit33.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    /////
    private fun verificarRespuesta() {
        val editText: EditText = findViewById(R.id.editText33)
        val respuestaUsuario = editText.text.toString().trim().toLowerCase()
        if (respuestaUsuario == "strawberry") {
            sumarPunto()
            Toast.makeText(this, "well done you got 1 point!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, lvl3page4::class.java)
            intent.putExtra("nombreJugador", nombreJugador)

            startActivity(intent)

        } else {
            Toast.makeText(this, "Try again it starts for str...", Toast.LENGTH_SHORT).show()
        }
    }

    private fun sumarPunto() {
        val conexion = SQLite(this, "player", null, 1)
        val baseDeDatos = conexion.writableDatabase
        //txtNewPlayer=findViewById(R.id.txtNewPlayer)
        nombreJugador = intent.getStringExtra("nombreJugador")
        val name = nombreJugador
        val contentValues = ContentValues()
        contentValues.put("points", obtenerPuntosActuales() + 1)
        // Actualiza la columna "points" en la base de datos
        baseDeDatos.update("player", contentValues, "name=?", arrayOf(name))
        baseDeDatos.close()
    }

    
    private fun obtenerPuntosActuales(): Int {
        val conexion = SQLite(this, "player", null, 1)
        val baseDeDatos = conexion.readableDatabase
        nombreJugador = intent.getStringExtra("nombreJugador")
        val name = nombreJugador
        val cursor = baseDeDatos.rawQuery("SELECT points FROM player WHERE name=?", arrayOf(name))
        var puntos = 0
        if (cursor.moveToFirst()) {
            puntos = cursor.getInt(0)
        }
        cursor.close()
        baseDeDatos.close()
        return puntos
    }
}