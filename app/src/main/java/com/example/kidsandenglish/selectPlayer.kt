package com.example.kidsandenglish

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class selectPlayer  : AppCompatActivity() {
    private lateinit var playerList: TextView
    private lateinit var txtPlayerName: EditText
    private lateinit var btnSelectName: Button

    //@SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectplayer)

        playerList=findViewById(R.id.playerList)
        txtPlayerName=findViewById(R.id.txtPlayerName)
        btnSelectName=findViewById(R.id.btnSelectName)

        val conexion = SQLite(this, "player", null, 1)
        val baseDeDatos = conexion.readableDatabase
        val cursor = baseDeDatos.rawQuery("SELECT * FROM player ORDER BY points DESC", null)
        val playersList = mutableListOf<String>()  // Lista de cadenas para almacenar los resultados
        with(cursor) {
            while (moveToNext()) {
                val name = getString(1)
                val points = getInt(2)
                playersList.add("$name - $points puntos")
            }
        }
        val text = playersList.joinToString("\n")
        playerList.text = text

        btnSelectName.setOnClickListener{
            val conexion= SQLite(this, "player", null, 1)
            val baseDeDatos=conexion.readableDatabase
            var name= txtPlayerName.text.toString().trim().toLowerCase()
            if (name.isNotEmpty() && existePlayer(name, baseDeDatos)) {
                val cursor = baseDeDatos.rawQuery("SELECT points FROM player WHERE name=?", arrayOf(name))
                if (cursor.moveToFirst()) {
                    var puntos = cursor.getInt(0)

                    if (puntos==18){
                        Toast.makeText(this, "You already won this game bro go touch some grass!", Toast.LENGTH_LONG).show()
                    }else{
                    val intent = Intent(this, when (puntos) {
                        0 -> lvl1page1::class.java
                        1 -> lvl1page2::class.java
                        2 -> lvl1page3::class.java
                        3 -> lvl1page4::class.java
                        4 -> lvl1page5::class.java
                        5 -> lvl1page6::class.java
                        6 -> lvl2page1::class.java
                        7 -> lvl2page2::class.java
                        8 -> lvl2page3::class.java
                        9 -> lvl2page4::class.java
                        10 -> lvl2page5::class.java
                        11 -> lvl2page6::class.java
                        12 -> lvl3page1::class.java
                        13 -> lvl3page2::class.java
                        14 -> lvl3page3::class.java
                        15 -> lvl3page4::class.java
                        16 -> lvl3page5::class.java
                        17 -> lvl3page6::class.java

                        else -> MainActivity::class.java // Cambia esto según tus necesidades
                    })
                    txtPlayerName.setText("")
                    intent.putExtra("nombreJugador", name)
                    startActivity(intent)
                }
                }

                cursor.close()
                baseDeDatos.close()
            }else{
                Toast.makeText(this, "Complete the Name Field or Player does not exist", Toast.LENGTH_LONG).show()

            }
        }
        }

    private fun existePlayer(nombre: String, db: SQLiteDatabase): Boolean {
        val sql = "SELECT * FROM player WHERE name = ?"
        val resultado = db.rawQuery(sql, arrayOf(nombre))

        val existe = resultado.count > 0
        resultado.close()

        return existe
    }
}

