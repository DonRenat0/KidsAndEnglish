package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
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

        // Muestra puntos en XML
        mostrarPuntosEn(R.id.txtVwPoints18, nombreJugador)

        btnCheck36 = findViewById(R.id.btnCheck36)
        btnCheck36.setOnClickListener {

            val strRespuestaUsuario = txtRespuestaUsuario.text.toString().trim().toLowerCase()
            esCorrecta = verificarRespuesta(puntosJugador, strRespuestaUsuario)

            if (esCorrecta == true) {
                Toast.makeText(this, "Congratulations!! \n You won this game!", Toast.LENGTH_LONG).show()


                val puntosActualizados = sumarPunto(nombreJugador)

                // Puedes redirigir al siguiente nivel o mostrar una pantalla de finalización
                val intent = Intent(this, MainActivity::class.java)
                //intent.putExtra("nombreJugador", nombreJugador)
                //intent.putExtra("puntosJugador", puntosActualizados)
                val mediaPlayer = MediaPlayer.create(this, R.raw.cheers_1)
                mediaPlayer.start()
                showVictoryDialog()

               // startActivity(intent)
                // finish()
                Log.d("Miapp", "$nombreJugador")
            } else {
                Toast.makeText(this, "Invalid answer, \nhint: it starts with ...", Toast.LENGTH_LONG).show()
                Log.d("MiappFailed", "$nombreJugador")
                val mediaPlayer = MediaPlayer.create(this, R.raw.error_sound)
                mediaPlayer.start()

            }
        }

        btnSvExit36 = findViewById(R.id.btnSvExit36)
        btnSvExit36.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    fun showVictoryDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_victory, null)
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.show()

        // Cierra automáticamente después de 5 segundos (opcional)
        Handler(Looper.getMainLooper()).postDelayed({
            dialog.dismiss()
            // Tal vez ir al MainMenu o Reiniciar juego
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 20000)
        startActivity(intent)
         finish()

    }

}
