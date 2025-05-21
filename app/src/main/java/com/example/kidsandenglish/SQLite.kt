package com.example.kidsandenglish

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class SQLite(
    context: AppCompatActivity,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version){

    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL("PRAGMA foreign_keys = ON")

        db?.execSQL("""
    CREATE TABLE IF NOT EXISTS respuestas (
        id_respuesta INTEGER PRIMARY KEY AUTOINCREMENT,
        respuesta TEXT NOT NULL,
        puntos_necesarios INTEGER NOT NULL,
        img_respuesta TEXT NOT NULL
    )
""")

        db?.execSQL("""
    CREATE TABLE IF NOT EXISTS player (
        idPlayer INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT NOT NULL,
        points INTEGER NOT NULL,
        FOREIGN KEY (points) REFERENCES respuestas(puntos_necesarios)
    )
""")

// Inserciones en la tabla respuestas
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('tiger', 0, 'img11')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('dog', 1, 'img12')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('cow', 2, 'img13')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('monkey', 3, 'img14')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('parrot', 4, 'img15')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('elephant', 5, 'img16')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('chicken', 6, 'img21')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('milk', 7, 'img22')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('cheese', 8, 'img23')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('sushi', 9, 'img24')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('ham', 10, 'img25')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('bread', 11, 'img26')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('apple', 12, 'img31')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('orange', 13, 'img32')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('strawberry', 14, 'img33')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('pineapple', 15, 'img34')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('watermelon', 16, 'img35')""")
        db?.execSQL("""INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('blackberry', 17, 'img36')""")

    }

    //  Mostrar puntos en cada XML

    fun mostrarPuntosSQLite(nombreJugador: String?): Int {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT points FROM player WHERE name = ?", arrayOf(nombreJugador))
        var puntos = 0
        if (cursor.moveToFirst()) {
            puntos = cursor.getInt(0)
        }
        cursor.close()
        db.close()
        return puntos
    }

    //  Comprobar si es la respuesta correcta PASANDOLE LA RESPUESTA

      fun esRegistrada(puntosJugador: Int?, strRespuestaUsuario: String?): Boolean {

          Log.d("esRegistrada", "$puntosJugador y $strRespuestaUsuario")

          if (puntosJugador == null || strRespuestaUsuario.isNullOrBlank()) return false

          val db = this.readableDatabase

          val cursor = db.rawQuery(
              "SELECT 1 FROM respuestas WHERE puntos_necesarios = ? AND LOWER(respuesta) = ? LIMIT 1",
              arrayOf(puntosJugador.toString(), strRespuestaUsuario.lowercase())

          )
          Log.d("esRegistrada", "$cursor")

          val existe = cursor.moveToFirst()
          Log.d("esRegistrada", "$existe")

          cursor.close()
          db.close()

          return existe
      }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS respuestas")
        db?.execSQL("DROP TABLE IF EXISTS player")

        onCreate(db)
    }

}