package com.example.kidsandenglish

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity




class lvl1page1 : AppCompatActivity() {

   // private var respuestaUsuario = findViewById<EditText>(R.id.editText11)
    private lateinit var btnCheck11:Button
    private lateinit var btnSvExit11:Button
    private lateinit var txtRespuestaUsuario: EditText

    private var nombreJugador: String? = null
    private var puntosJugador: Int? = null
   private var esCorrecta: Boolean? = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl1page1)

        txtRespuestaUsuario = findViewById(R.id.editText11)
        nombreJugador = intent.getStringExtra("nombreJugador")
        puntosJugador = intent.getIntExtra("puntosJugador", 0)


        mostrarPuntosEn(R.id.txtVwPoints, nombreJugador)

        btnCheck11= findViewById(R.id.btnCheck11)
        btnCheck11.setOnClickListener {

            var strRespuestaUsuario =  txtRespuestaUsuario.text.toString().trim().toLowerCase()
             esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)

            if(esCorrecta == true){
                    Toast.makeText(this, "Correct! You got 1 point", Toast.LENGTH_LONG).show()
                    val puntosActualizados =   sumarPunto(nombreJugador)
                    val intent = Intent(this, lvl1page2::class.java)
                    intent.putExtra("nombreJugador", nombreJugador)
                    intent.putExtra("puntosJugador", puntosActualizados)

                    val mediaPlayer = MediaPlayer.create(this, R.raw.levelup_sound)
                    mediaPlayer.start()

                startActivity(intent)
                    finish()
                Log.d("Miapp", "$nombreJugador")
            }else{
                Toast.makeText(this, "Invalid answer, \nhint: it starts with ti...", Toast.LENGTH_LONG).show()
                val mediaPlayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaPlayer.start()

                Log.d("MiappFailed", "$nombreJugador")
            }
        }
        btnSvExit11= findViewById(R.id.btnSvExit12)
        btnSvExit11.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
    }



}