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
import java.util.Locale

class lvl1page2  : AppCompatActivity() {
    private lateinit var btnCheck12: Button
    private lateinit var btnSvExit12:Button
    private lateinit var txtRespuestaUsuario: EditText

    private var nombreJugador: String? = null
    private var puntosJugador: Int? = null
    private var esCorrecta: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl1page2)

        txtRespuestaUsuario = findViewById(R.id.editText12)
        nombreJugador = intent.getStringExtra("nombreJugador")
        puntosJugador = intent.getIntExtra("puntosJugador", 1)


        //  Muestra puntos en XML
        mostrarPuntosEn(R.id.txtVwPoints2, nombreJugador)

        btnCheck12= findViewById(R.id.btnCheck12)
        btnCheck12.setOnClickListener {
//      Aqui cambiamos el to lower case
            var strRespuestaUsuario = txtRespuestaUsuario.text.toString().trim().lowercase(Locale.ROOT)
             esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)


            if(esCorrecta == true){
                Toast.makeText(this, "Correct! You got 1 point", Toast.LENGTH_LONG).show()
                val puntosActualizados = sumarPunto(nombreJugador)
                val intent = Intent(this, lvl1page3::class.java)
                intent.putExtra("nombreJugador", nombreJugador)
                intent.putExtra("puntosJugador", puntosActualizados)
                val mediaPlayer = MediaPlayer.create(this, R.raw.levelup_sound)
                mediaPlayer.start()
                startActivity(intent)
                finish()
                Log.d("Miapp", "$nombreJugador")
            }else{
                Toast.makeText(this, "Invalid answer, \nhint: it starts with d...", Toast.LENGTH_LONG).show()
                val mediaPlayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaPlayer.start()
                Log.d("MiappFailed", "$nombreJugador")
            }
        }
        btnSvExit12= findViewById(R.id.btnSvExit12)
        btnSvExit12.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }



}