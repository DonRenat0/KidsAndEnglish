package com.example.kidsandenglish

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ranking  : AppCompatActivity() {
    private lateinit var btnGotoMenu: Button
    private lateinit var listViewRanking: ListView


   // @SuppressLint("MissingInflatedId")
   // @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking)

       btnGotoMenu = findViewById(R.id.btnGoToMenu)
       btnGotoMenu.setOnClickListener{
           val intent  =   Intent(this, MainActivity::class.java)
           startActivity(intent)
           finish()
        }
        /////////////////logica listView

       listViewRanking = findViewById(R.id.listViewRanking)
        val conexionTest = SQLite(this, "player", null, 1)
        val baseDeDatosTest = conexionTest.readableDatabase
        val cursor2 = baseDeDatosTest.rawQuery("SELECT * FROM player ORDER BY points DESC", null)

        val playersList2 = mutableListOf<String>()
        with(cursor2) {
            while (moveToNext()) {
                val name = getString(1)
                val points = getInt(2)
                playersList2.add("$name - $points puntos")
            }
        }

        val adapter = ArrayAdapter(this, R.layout.item_player, R.id.playerItemList2, playersList2)
        listViewRanking.adapter = adapter

        // Acción al seleccionar un jugador
        listViewRanking.setOnItemClickListener { _, _, position, _ ->

            val selectedPlayer = playersList2[position]
            val playerName = selectedPlayer.substringBefore(" -").trim()  // Extrae solo el nombre

            val cursor = baseDeDatosTest.rawQuery(
                "SELECT points FROM player WHERE name=?",
                arrayOf(playerName)
            )
            if (cursor.moveToFirst()) {
                var puntos = cursor.getInt(0)

                if (puntos == 18) {
                    Toast.makeText(
                        this,
                        "You already won this game, to keep learning, go to create a New Player!",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    val intent = Intent(
                        this, when (puntos) {
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

                            else -> MainActivity::class.java
                        })
                    intent.putExtra("nombreJugador", playerName)
                    startActivity(intent)


                    Toast.makeText(this, "Seleccionaste: $playerName", Toast.LENGTH_SHORT).show()

                }

            }
        }
    }
}