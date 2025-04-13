package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class lvl2page1  : AppCompatActivity() {
    private var nombreJugador: String? = null
    private lateinit var btnSvExit21:Button
    private lateinit var btnCheck21: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl2page1)
        nombreJugador = intent.getStringExtra("nombreJugador")
        btnCheck21= findViewById(R.id.btnCheck21)
        btnCheck21.setOnClickListener {
            verificarRespuesta()
        }
        btnSvExit21= findViewById(R.id.btnSvExit21)
        btnSvExit21.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    /////
    private fun verificarRespuesta() {
        val editText: EditText = findViewById(R.id.editText21)
        val respuestaUsuario = editText.text.toString().trim().toLowerCase()
        if (respuestaUsuario == "chicken") {
            sumarPunto()
            Toast.makeText(this, "well done you got 1 point!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, lvl2page2::class.java)
            intent.putExtra("nombreJugador", nombreJugador)

            startActivity(intent)

        } else {
            Toast.makeText(this, "Try again it starts for chic...", Toast.LENGTH_SHORT).show()
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