package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class lvl2page3  : AppCompatActivity() {
    private lateinit var btnCheck23:Button
    private lateinit var btnSvExit23:Button
    private lateinit var txtRespuestaUsuario: EditText
    private var nombreJugador: String? = null
    private var puntosJugador: Int? = null
    private var esCorrecta: Boolean? = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lvl2page3)

        txtRespuestaUsuario = findViewById(R.id.editText23)
        nombreJugador = intent.getStringExtra("nombreJugador")
        puntosJugador = intent.getIntExtra("puntosJugador", 8)


        //  Muestra puntos en XML
        mostrarPuntosEn(R.id.txtVwPoints9, nombreJugador)

        btnCheck23  = findViewById(R.id.btnCheck23)
        btnCheck23.setOnClickListener {

            var strRespuestaUsuario =  txtRespuestaUsuario.text.toString().trim().toLowerCase()
            esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)

            if(esCorrecta == true){
                Toast.makeText(this, "Correct! You got 1 point", Toast.LENGTH_LONG).show()
                sumarPunto(nombreJugador)
                val intent = Intent(this, lvl1page4::class.java)
                intent.putExtra("nombreJugador", nombreJugador)
                intent.putExtra("puntosJugador", puntosJugador)

                startActivity(intent)
                finish()
                Log.d("Miapp", "$nombreJugador")
            }else{
                Toast.makeText(this, "Invalid answer, \nhint: it starts with ti...", Toast.LENGTH_LONG).show()
                Log.d("MiappFailed", "$nombreJugador")
            }
        }
        btnSvExit23= findViewById(R.id.btnSvExit23)
        btnSvExit23.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}