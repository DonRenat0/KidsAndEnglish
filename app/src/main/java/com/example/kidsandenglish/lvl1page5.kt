package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class lvl1page5  : AppCompatActivity() {
    private lateinit var btnCheck15: Button
    private lateinit var btnSvExit15: Button
    private lateinit var txtRespuestaUsuario: EditText
    private var nombreJugador: String? = null
    private var puntosJugador: Int? = null
    private var esCorrecta: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl1page5)

        txtRespuestaUsuario = findViewById(R.id.editText15)
        nombreJugador = intent.getStringExtra("nombreJugador")
        puntosJugador = intent.getIntExtra("puntosJugador", 4)

        //  Muestra puntos en XML
        mostrarPuntosEn(R.id.txtVwPoints5, nombreJugador)

        btnCheck15 = findViewById(R.id.btnCheck15)
        btnCheck15.setOnClickListener {

            var strRespuestaUsuario = txtRespuestaUsuario.text.toString().trim().toLowerCase()
            esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)

            if (esCorrecta == true) {
                Toast.makeText(this, "Correct! You got 1 point", Toast.LENGTH_LONG).show()
                val puntosActualizados = sumarPunto(nombreJugador)
                val intent = Intent(this, lvl1page6::class.java)
                intent.putExtra("nombreJugador", nombreJugador)
                intent.putExtra("puntosJugador", puntosActualizados)
                val mediaPlayer = MediaPlayer.create(this, R.raw.levelup_sound)
                mediaPlayer.start()
                startActivity(intent)
                finish()
                Log.d("Miapp", "$nombreJugador")
            } else {
                Toast.makeText(this, "Invalid answer, \nhint: it starts with ti...", Toast.LENGTH_LONG).show()
                Log.d("MiappFailed", "$nombreJugador")
                val mediaPlayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaPlayer.start()
            }
        }

        btnSvExit15 = findViewById(R.id.btnSvExit15)
        btnSvExit15.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

}