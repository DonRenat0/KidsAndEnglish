package com.example.kidsandenglish

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class lvl3page4  : AppCompatActivity() {
    private var nombreJugador: String? = null
    private lateinit var btnSvExit34:Button
    private lateinit var btnCheck34: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl3page4)
        nombreJugador = intent.getStringExtra("nombreJugador")
        btnCheck34= findViewById(R.id.btnCheck34)
        btnCheck34.setOnClickListener {
            verificarRespuesta()
        }
        btnSvExit34= findViewById(R.id.btnSvExit34)
        btnSvExit34.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    /////
    private fun verificarRespuesta() {
        val editText: EditText = findViewById(R.id.editText34)
        val respuestaUsuario = editText.text.toString().trim().toLowerCase()
        if (respuestaUsuario == "pineapple") {
            sumarPunto()
            Toast.makeText(this, "well done you got 1 point!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, lvl3page5::class.java)
            intent.putExtra("nombreJugador", nombreJugador)

            startActivity(intent)

        } else {
            Toast.makeText(this, "Try again it starts for pin...", Toast.LENGTH_SHORT).show()
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