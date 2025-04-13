package com.example.kidsandenglish

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ranking  : AppCompatActivity() {
    private lateinit var rankingListView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking)
       rankingListView=findViewById(R.id.rankingListView)

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

      rankingListView.text = text
  }
}