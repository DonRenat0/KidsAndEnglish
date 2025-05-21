package com.example.kidsandenglish

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle

import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
        puntosJugador = intent.getIntExtra("puntosJugador", 17)

        mostrarPuntosEn(R.id.txtVwPoints18, nombreJugador)

        btnCheck36 = findViewById(R.id.btnCheck36)
        btnCheck36.setOnClickListener {

            val strRespuestaUsuario = txtRespuestaUsuario.text.toString().trim().toLowerCase()
            esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)

            if (esCorrecta == true) {
                Toast.makeText(this, "Congratulations!! \n You won this game!", Toast.LENGTH_LONG).show()
                val puntosActualizados = sumarPunto(nombreJugador)

                // Puedes redirigir al siguiente nivel o mostrar una pantalla de finalización
               // val intent = Intent(this, MainActivity::class.java
                val mediaPlayer = MediaPlayer.create(this, R.raw.cheers_1)
                mediaPlayer.start()
                mostrarVictoria()
                Log.d("Miapp", "$nombreJugador")
            } else {
                Toast.makeText(this, "Invalid answer, \nhint: it starts with black...", Toast.LENGTH_LONG).show()
                Log.d("MiappFailed", "$nombreJugador")
                val mediaPlayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaPlayer.start()
                //  mediaPlayer.release()
            }
        }

        btnSvExit36 = findViewById(R.id.btnSvExit36)
        btnSvExit36.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }
    }

    fun mostrarVictoria() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_victory, null)

        val builder = AlertDialog.Builder(this)
        builder.setView(dialogView)
        builder.setCancelable(false) // No permite cerrar tocando fuera

        val dialog = builder.create()
        dialog.show()

        val btnVolver = dialogView.findViewById<Button>(R.id.btnVolverMenu)
        btnVolver.setOnClickListener {
            dialog.dismiss()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    override fun onBackPressed() {
        // No hacer nada para bloquear el botón atrás
    }


}
