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



//  En este kt veras el problema que tiene el resto de niveles



//  VerificarRespuesta:
//  crear otra tabla con identificador y la respuesta a la imagen
//  pasar el parametro de la entrada de respuesta y segun la cantidad de puntos te de el nombre de la
//  imagen para comparar con la entrada.
//
//  Sumarpunto: Añade lo que esta en ObtenerPunto a esta funcion o mantenlos separados si lo haces
//  funcional para todas los niveles

//  database Inspector : View > Tool windows > App Inspector > Database Inspector
// 20 veces mejor probar en movil

//  In game debes matar el intent anterior o sino el player puede sumar puntos sin fin

//  ME QUEDE en
//  La TABLA con insercion de datos preocupa que sea una funcion, solo queremos que se ejecute una vez,incluso si alguien repite el juego
//  tocaria hacer la logica para poner la imagen en cada xml segun se avanza y hacer dinamicas las funciones que estan en todos los niveles

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


        //  Muestra puntos en XML
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
        }
    }



}