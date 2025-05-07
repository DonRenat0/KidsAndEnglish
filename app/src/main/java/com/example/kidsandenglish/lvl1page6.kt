package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class lvl1page6  : AppCompatActivity() {
    private lateinit var btnCheck16: Button
    private lateinit var btnSvExit16: Button
    private lateinit var txtRespuestaUsuario: EditText
    private var nombreJugador: String? = null
    private var puntosJugador: Int? = null
    private var esCorrecta: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl1page6)

        txtRespuestaUsuario = findViewById(R.id.editText16)
        nombreJugador = intent.getStringExtra("nombreJugador")
        puntosJugador = intent.getIntExtra("puntosJugador", 5)

        // Muestra puntos en XML
        mostrarPuntosEn(R.id.txtVwPoints6, nombreJugador)

        btnCheck16 = findViewById(R.id.btnCheck16)
        btnCheck16.setOnClickListener {

            var strRespuestaUsuario = txtRespuestaUsuario.text.toString().trim().toLowerCase()
            esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)

            if (esCorrecta == true) {
                Toast.makeText(this, "Correct! You got 1 point", Toast.LENGTH_LONG).show()
                sumarPunto(nombreJugador)
                val intent = Intent(this, lvl2page1::class.java)
                intent.putExtra("nombreJugador", nombreJugador)
                intent.putExtra("puntosJugador", puntosJugador)

                startActivity(intent)
                finish()
                Log.d("Miapp", "$nombreJugador")
            } else {
                Toast.makeText(this, "Invalid answer, \nhint: it starts with ti...", Toast.LENGTH_LONG).show()
                Log.d("MiappFailed", "$nombreJugador")
            }
        }

        btnSvExit16 = findViewById(R.id.btnSvExit16)
        btnSvExit16.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

}