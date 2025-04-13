package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

//  1.  Se debe hacer una clase de conexion para que no se repita en cada ClickListener

//  2.  Llamada a la bbdd que compare la sql con cada registro de nombre(name en sqlite)

class createNewPlayer : AppCompatActivity() {

    //var conexion = SQLite(requireContext(), "player", null, 1)
    private lateinit var txtNewPlayer: EditText
    private lateinit var btnNewPlayer: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createnewplayer)

        txtNewPlayer=findViewById(R.id.txtNewPlayer)
        btnNewPlayer=findViewById(R.id.btnNewPlayer)

        btnNewPlayer.setOnClickListener{

            //  1.
            var conexion = SQLite(this, "player", null, 1)

            var baseDeDatos = conexion.writableDatabase
            var name = txtNewPlayer.text.toString()

            if (name.isNotEmpty()){
                if (!existeJugador(name, baseDeDatos)) {

                    var registro = ContentValues()
                    registro.put("name", name)
                    registro.put("points", 0)
                    baseDeDatos.insert("player", null, registro)

                    txtNewPlayer.setText("")
                    Toast.makeText(this, "New Player Created", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, lvl1page1::class.java)
                    intent.putExtra("nombreJugador", name)

                    startActivity(intent)

                    baseDeDatos.close()

                } else {
                    Toast.makeText(this, "Player with the same name already exists", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(this, "You need to complete Name Field", Toast.LENGTH_LONG).show()
            }


        }
    }



    // Función para verificar si un jugador ya existe con el mismo nombre
    private fun existeJugador(nombre: String, db: SQLiteDatabase): Boolean {
        val sql = "SELECT * FROM player WHERE name = ?"

        //  2.
        val resultado = db.rawQuery(sql, arrayOf(nombre))
        val existe = resultado.count > 0
        resultado.close()

        return existe
    }
}