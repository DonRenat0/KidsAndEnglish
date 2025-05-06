package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class lvl3page6 : AppCompatActivity() {

    private lateinit var btnCheck36: Button
    private lateinit var btnSvExit36: Button
    private lateinit var txtRespuestaUsuario: EditText
    private var nombreJugador: String? = null
    private var puntosJugador: Int? = null
    private var esCorrecta: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl3page6)

        txtRespuestaUsuario = findViewById(R.id.editText36)
        nombreJugador = intent.getStringExtra("nombreJugador")
        puntosJugador = intent.getIntExtra("puntosJugador", 0)

        // Muestra puntos en XML
        mostrarPuntosEn(R.id.txtVwPoints18, nombreJugador)

        btnCheck36 = findViewById(R.id.btnCheck36)
        btnCheck36.setOnClickListener {

            val strRespuestaUsuario = txtRespuestaUsuario.text.toString().trim().toLowerCase()
            esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)

            if (esCorrecta == true) {
                Toast.makeText(this, "Correct! You got 1 point", Toast.LENGTH_LONG).show()
                sumarPunto(nombreJugador)

                // Puedes redirigir al siguiente nivel o mostrar una pantalla de finalización
              //  val intent = Intent(this, NivelCompletadoActivity::class.java)
              //  intent.putExtra("nombreJugador", nombreJugador)
              //  intent.putExtra("puntosJugador", puntosJugador)

                startActivity(intent)
                Log.d("Miapp", "$nombreJugador")
            } else {
                Toast.makeText(this, "Invalid answer, \nhint: it starts with ...", Toast.LENGTH_LONG).show()
                Log.d("MiappFailed", "$nombreJugador")
            }
        }

        btnSvExit36 = findViewById(R.id.btnSvExit36)
        btnSvExit36.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
