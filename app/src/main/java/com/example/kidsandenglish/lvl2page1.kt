package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

    class lvl2page1 : AppCompatActivity() {

        private lateinit var btnCheck21: Button
        private lateinit var btnSvExit21: Button
        private lateinit var txtRespuestaUsuario: EditText
        private var nombreJugador: String? = null
        private var puntosJugador: Int? = null
        private var esCorrecta: Boolean? = false

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.lvl2page1)

            txtRespuestaUsuario = findViewById(R.id.editText21)
            nombreJugador = intent.getStringExtra("nombreJugador")
            puntosJugador = intent.getIntExtra("puntosJugador", 6)

            // Muestra puntos en XML
            mostrarPuntosEn(R.id.txtVwPoints7, nombreJugador)

            btnCheck21 = findViewById(R.id.btnCheck21)
            btnCheck21.setOnClickListener {

                val strRespuestaUsuario = txtRespuestaUsuario.text.toString().trim().toLowerCase()
                esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)

                if (esCorrecta == true) {
                    Toast.makeText(this, "Correct! You got 1 point", Toast.LENGTH_LONG).show()
                    sumarPunto(nombreJugador)
                    val intent = Intent(this, lvl2page2::class.java)
                    intent.putExtra("nombreJugador", nombreJugador)
                    intent.putExtra("puntosJugador", puntosJugador)

                    startActivity(intent)
                    finish()
                    Log.d("Miapp", "$nombreJugador")
                } else {
                    Toast.makeText(this, "Invalid answer, \nhint: it starts with ...", Toast.LENGTH_LONG).show()
                    Log.d("MiappFailed", "$nombreJugador")
                }
            }

            btnSvExit21 = findViewById(R.id.btnSvExit21)
            btnSvExit21.setOnClickListener {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }

