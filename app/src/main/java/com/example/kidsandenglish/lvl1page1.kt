package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
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
   // private var nombreJugador: String? = null

   // private var puntos: Int? = null
    private lateinit var btnCheck11:Button
    private lateinit var btnSvExit:Button
   // private var respuestaUsuario = findViewById<EditText>(R.id.editText11)
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
                    sumarPunto(nombreJugador)
                    Log.d("Miapp", "$nombreJugador")
            }else{
                Toast.makeText(this, "Invalid answer, \nhint: it starts with ti...", Toast.LENGTH_LONG).show()
                Log.d("Miapp", "$nombreJugador")
            }
        }
        btnSvExit= findViewById(R.id.btnSvExit12)
        btnSvExit.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


     /* private fun sumarPunto() {
        val conexion = SQLite(this, "player", null, 1)
        val writebbdd = conexion.writableDatabase
        val readbbdd = conexion.readableDatabase
        //txtNewPlayer=findViewById(R.id.txtNewPlayer)
        //nombreJugador = intent.getStringExtra("nombreJugador")
       // val name = nombreJugador
        val contentValues = ContentValues()
        contentValues.put("points", obtenerPuntosActuales() + 1)
        // Actualiza la columna "points" en la base de datos
     //   baseDeDatos.update("player", contentValues, "name=?", arrayOf(nombreJugador))
        baseDeDatos.close()
    }*/

    /*private fun obtenerPuntosActuales(): Int {
        val conexion = SQLite(this, "player", null, 1)
        val baseDeDatos = conexion.readableDatabase
      //  nombreJugador = intent.getStringExtra("nombreJugador")
        //val name = nombreJugador

     //   val cursor = baseDeDatos.rawQuery("SELECT points FROM player WHERE name=?", arrayOf(nombreJugador))
        var puntos = 0
     /*   if (cursor.moveToFirst()) {
            puntos = cursor.getInt(0)
        }
        cursor.close()*/
        baseDeDatos.close()
        return puntos
    }*/
}