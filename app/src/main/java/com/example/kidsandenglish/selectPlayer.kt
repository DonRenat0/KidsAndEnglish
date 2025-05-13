package com.example.kidsandenglish

import android.content.ContentValues
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class selectPlayer  : AppCompatActivity() {
//    private lateinit var playerList: TextView
    private lateinit var txtPlayerName: EditText
    private lateinit var btnSelectName: Button
    private lateinit var playerListView: ListView
    private lateinit var btnBackMenu:   Button
    private lateinit var btnChangeName:   Button
    private lateinit var btnDelete:   Button


    //@SuppressLint("Range", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.selectplayer)

        txtPlayerName=findViewById(R.id.txtPlayerName)
        btnSelectName=findViewById(R.id.btnSelectName)
        btnBackMenu=findViewById(R.id.btnBackMenu)
        btnChangeName=findViewById(R.id.btnChangeName)
        btnDelete=findViewById(R.id.btnDelete)

      /*  val conexion = SQLite(this, "player", null, 1)
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
        playerList.text = text*/

///////////////////////////////     LOGICA LIST VIEW
        playerListView = findViewById(R.id.playerListView)
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
        playerListView.adapter = adapter

        // Acción al seleccionar un jugador
        playerListView.setOnItemClickListener { _, _, position, _ ->
            val selectedPlayer = playersList2[position]
            val playerName = selectedPlayer.substringBefore(" -").trim()  // Extrae solo el nombre
            txtPlayerName.setText(playerName)  // Coloca el nombre en el EditText

            Toast.makeText(this, "Seleccionaste: $playerName", Toast.LENGTH_SHORT).show()

        }

/////////////////////////


        btnBackMenu.setOnClickListener{
            val intent  =   Intent(this, MainActivity::class.java)
            txtPlayerName.setText("")
            startActivity(intent)
            finish()
        }

        btnChangeName.setOnClickListener{
            val conexion=SQLite(this, "player", null, 1)
            val baseDeDatos=conexion.writableDatabase
            val oldName= txtPlayerName.text.toString().trim().toLowerCase()

            if (oldName.isNotEmpty() && existePlayer(oldName, baseDeDatos)) {
                val input = EditText(this)
                input.hint = "Enter new name"

                AlertDialog.Builder(this)
                    .setTitle("Change Name")
                    .setMessage("Enter the new name for \"$oldName\":")
                    .setView(input)
                    .setPositiveButton("OK") { dialog, _ ->
                        val newName = input.text.toString().trim().lowercase()

                        if (newName.isNotEmpty() && !existePlayer(newName, baseDeDatos)) {
                            val values = ContentValues().apply {
                                put("name", newName)
                            }
                            val rowsUpdated = baseDeDatos.update("player", values, "name = ?", arrayOf(oldName))

                            if (rowsUpdated > 0) {
                                Toast.makeText(this, "Name updated to $newName", Toast.LENGTH_SHORT).show()
                                txtPlayerName.setText(newName)

                                //Actualizar listView
                                playersList2.clear()
                                val cursor = baseDeDatos.rawQuery("SELECT * FROM player ORDER BY points DESC", null)
                                with(cursor) {
                                    while (moveToNext()) {
                                        val name = getString(1)
                                        val points = getInt(2)
                                        playersList2.add("$name - $points puntos")
                                    }
                                }
                                cursor.close()

                                // 3. Notificar al adapter
                                adapter.notifyDataSetChanged()

                            } else {
                                Toast.makeText(this, "Update failed", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "New name empty or player already exists", Toast.LENGTH_SHORT).show()
                        }

                        // ✅ Cerramos la base de datos después de usarla
                        baseDeDatos.close()
                        dialog.dismiss()
                    }
                    .setNegativeButton("Cancel") { dialog, _ ->
                        baseDeDatos.close() // También cerramos si se cancela
                        dialog.cancel()
                    }
                    .show()
            } else {
                baseDeDatos.close() // También cerramos aquí si no se encuentra
                Toast.makeText(this, "Player not found or field is empty", Toast.LENGTH_SHORT).show()
            }
            }


        btnSelectName.setOnClickListener{
            val conexion= SQLite(this, "player", null, 1)
            val baseDeDatos=conexion.readableDatabase
            var name= txtPlayerName.text.toString().trim().toLowerCase()
            if (name.isNotEmpty() && existePlayer(name, baseDeDatos)) {
                val cursor = baseDeDatos.rawQuery("SELECT points FROM player WHERE name=?", arrayOf(name))
                if (cursor.moveToFirst()) {
                    var puntos = cursor.getInt(0)

                    if (puntos==18){
                        Toast.makeText(this, "You already won this game, to keep learning, go to create a New Player!", Toast.LENGTH_LONG).show()
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

                        else -> MainActivity::class.java
                    })
                    txtPlayerName.setText("")
                    intent.putExtra("nombreJugador", name)
                    startActivity(intent)
                        finish()
                }
                }

                cursor.close()
                baseDeDatos.close()
            }else{
                Toast.makeText(this, "Complete the Name Field or Player does not exist", Toast.LENGTH_LONG).show()

            }
        }
        btnDelete.setOnClickListener{
            val conexion = SQLite(this, "player", null, 1)
            val baseDeDatos = conexion.writableDatabase
            val name = txtPlayerName.text.toString().trim().lowercase()

            if (name.isNotEmpty() && existePlayer(name, baseDeDatos)) {
                val rowsDeleted = baseDeDatos.delete("player", "name = ?", arrayOf(name))
                if (rowsDeleted > 0) {
                    Toast.makeText(this, "Player deleted successfully", Toast.LENGTH_SHORT).show()
                    txtPlayerName.setText("")
                    //Actualizar listView
                    playersList2.clear()
                    val cursor = baseDeDatos.rawQuery("SELECT * FROM player ORDER BY points DESC", null)
                    with(cursor) {
                        while (moveToNext()) {
                            val name = getString(1)
                            val points = getInt(2)
                            playersList2.add("$name - $points puntos")
                        }
                    }
                    cursor.close()

                    // 3. Notificar al adapter
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this, "Error deleting player", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Enter a valid name or player does not exist", Toast.LENGTH_LONG).show()
            }

            baseDeDatos.close()
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

