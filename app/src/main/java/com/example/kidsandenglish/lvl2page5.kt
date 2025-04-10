package com.example.kidsandenglish

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class lvl2page5  : AppCompatActivity() {
    private var nombreJugador: String? = null
    private lateinit var btnSvExit25:Button
    private lateinit var btnCheck25: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl2page5)
        nombreJugador = intent.getStringExtra("nombreJugador")
        btnCheck25= findViewById(R.id.btnCheck25)
        btnCheck25.setOnClickListener {
            verificarRespuesta()
        }
        btnSvExit25= findViewById(R.id.btnSvExit25)
        btnSvExit25.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
    /////
    private fun verificarRespuesta() {
        val editText: EditText = findViewById(R.id.editText25)
        val respuestaUsuario = editText.text.toString().trim().toLowerCase()
        if (respuestaUsuario == "ham") {
            sumarPunto()
            Toast.makeText(this, "well done you got 1 point!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, lvl2page6::class.java)
            intent.putExtra("nombreJugador", nombreJugador)

            startActivity(intent)

        } else {
            Toast.makeText(this, "Try again it starts for h...", Toast.LENGTH_SHORT).show()
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