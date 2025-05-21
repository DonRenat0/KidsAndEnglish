package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class createNewPlayer : AppCompatActivity() {

    private lateinit var txtNewPlayer: EditText
    private lateinit var btnNewPlayer: Button
    private lateinit var btnBackMenuC: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.createnewplayer)

        txtNewPlayer=findViewById(R.id.txtNewPlayer)
        btnNewPlayer=findViewById(R.id.btnNewPlayer)
        btnBackMenuC=findViewById(R.id.btnToMenu)

        btnBackMenuC.setOnClickListener{
            val intent  =   Intent(this, MainActivity::class.java)
            txtNewPlayer.setText("")
            startActivity(intent)
            finish()
        }
        btnNewPlayer.setOnClickListener{

            var conexion = SQLite(this, "player", null, 1)

            var baseDeDatos = conexion.writableDatabase
            var name = txtNewPlayer.text.toString().replace("\\s+".toRegex(), "").lowercase()

            var points = 0
            if (name.isNotEmpty()){
                if (!existeJugador(name, baseDeDatos)) {

                    var registro = ContentValues()
                    registro.put("name", name)
                    registro.put("points", points)
                    baseDeDatos.insert("player", null, registro)

                    txtNewPlayer.setText("")
                    Toast.makeText(this, "New Player Created", Toast.LENGTH_LONG).show()

                    val intent = Intent(this, lvl1page1::class.java)
                    intent.putExtra("nombreJugador", name)
                    intent.putExtra("puntosJugador", points)


                    startActivity(intent)
                    finish()
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

        val resultado = db.rawQuery(sql, arrayOf(nombre))
        val existe = resultado.count > 0
        resultado.close()

        return existe
    }
}