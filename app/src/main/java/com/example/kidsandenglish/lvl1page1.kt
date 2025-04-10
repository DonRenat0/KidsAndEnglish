package com.example.kidsandenglish

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//  1.  El jugador deberia ver sus puntos in game

//  En este kt veras el problema que tiene el resto de niveles

//  2.  REDUNDANCIA VerificarRespuesta, SumarPuntos y ObtenerPuntosActuales
//  Deberian estar en una clase aparte

//  VerificarRespuesta:
//  crear otra tabla con identificador y la respuesta a la imagen
//  pasar el parametro de la entrada de respuesta y segun la cantidad de puntos te de el nombre de la
//  imagen para comparar con la entrada.
//
//  Sumarpunto: Añade lo que esta en ObtenerPunto a esta funcion o mantenlos separados si lo haces
//  funcional para todas los niveles
//
//  database Inspector : View > Tool windows > App Inspector > Database Inspector
// 20 veces mejor probar en movil

//  In game debes matar el intent anterior o sino el player puede sumar puntos sin fin
//

class lvl1page1 : AppCompatActivity() {
    private var nombreJugador: String? = null
    private lateinit var btnCheck11:Button
    private lateinit var btnSvExit:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl1page1)

        //  1.
        nombreJugador = intent.getStringExtra("nombreJugador")
        btnCheck11= findViewById(R.id.btnCheck11)
        btnCheck11.setOnClickListener {
            verificarRespuesta()
        }
        btnSvExit= findViewById(R.id.btnSvExit12)
        btnSvExit.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun verificarRespuesta() {
        val editText: EditText = findViewById(R.id.editText11)
        val respuestaUsuario = editText.text.toString().trim().toLowerCase()
        if (respuestaUsuario == "tiger") {
            sumarPunto()
            Toast.makeText(this, "well done you got 1 point!", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, lvl1page2::class.java)
            intent.putExtra("nombreJugador", nombreJugador)
            startActivity(intent)
        } else {
            Toast.makeText(this, "Try again it starts for Ti...", Toast.LENGTH_SHORT).show()
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