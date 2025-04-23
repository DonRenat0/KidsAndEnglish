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

       /* db?.execSQL("create table IF NOT EXISTS player (idPlayer INTEGER primary key AUTOINCREMENT, name TEXT, points INTEGER)")

        db?.execSQL("CREATE TABLE IF NOT EXISTS respuestas (id_respuesta INTEGER PRIMARY KEY AUTOINCREMENT, respuesta TEXT, puntos_necesarios INTEGER, img_respuesta TEXT)")

        db?.execSQL("INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta) VALUES ('tiger', 0, 'img11')")
*/

        db?.execSQL("""
    CREATE TABLE IF NOT EXISTS player (
        idPlayer INTEGER PRIMARY KEY AUTOINCREMENT,
        name TEXT,
        points INTEGER
    )
""")

        db?.execSQL("""
    CREATE TABLE IF NOT EXISTS respuestas (
        id_respuesta INTEGER PRIMARY KEY AUTOINCREMENT,
        respuesta TEXT,
        puntos_necesarios INTEGER,
        img_respuesta TEXT
    )
""")

// Inserción de registro inicial correctamente
        db?.execSQL("""
    INSERT INTO respuestas (respuesta, puntos_necesarios, img_respuesta)
    VALUES ('tiger', 0, 'img11')
""")

        /* val datos1 = ContentValues().apply {
             put("respuesta", "tiger")
             put("puntos_necesarios", 0)
             put("img_respuesta", "img11")
         }
         db?.insert("respuestas", null, datos1)

         val datos2 = ContentValues().apply {
             put("respuesta", "dog")
             put("puntos_necesarios", 1)
             put("img_respuesta", "img12")
         }
         db?.insert("respuestas", null, datos2)

         val datos3 = ContentValues().apply {
             put("respuesta", "cow")
             put("puntos_necesarios", 2)
             put("img_respuesta", "img13")
         }
         db?.insert("respuestas", null, datos3)

         val datos4 = ContentValues().apply {
             put("respuesta", "monkey")
             put("puntos_necesarios", 3)
             put("img_respuesta", "img14")
         }
         db?.insert("respuestas", null, datos4)

         val datos5 = ContentValues().apply {
             put("respuesta", "parrot")
             put("puntos_necesarios", 4)
             put("img_respuesta", "img15")
         }
         db?.insert("respuestas", null, datos5)

         val datos6 = ContentValues().apply {
             put("respuesta", "elephant")
             put("puntos_necesarios", 5)
             put("img_respuesta", "img16")
         }
         db?.insert("respuestas", null, datos6)

         val datos7 = ContentValues().apply {
             put("respuesta", "chicken")
             put("puntos_necesarios", 6)
             put("img_respuesta", "img21")
         }
         db?.insert("respuestas", null, datos7)

         val datos8 = ContentValues().apply {
             put("respuesta", "milk")
             put("puntos_necesarios", 7)
             put("img_respuesta", "img22")
         }
         db?.insert("respuestas", null, datos8)

         val datos9 = ContentValues().apply {
             put("respuesta", "cheese")
             put("puntos_necesarios", 8)
             put("img_respuesta", "img23")
         }
         db?.insert("respuestas", null, datos9)

         val datos10 = ContentValues().apply {
             put("respuesta", "sushi")
             put("puntos_necesarios", 9)
             put("img_respuesta", "img24")
         }
         db?.insert("respuestas", null, datos10)

         val datos11 = ContentValues().apply {
             put("respuesta", "ham")
             put("puntos_necesarios", 10)
             put("img_respuesta", "img25")
         }
         db?.insert("respuestas", null, datos11)

         val datos12 = ContentValues().apply {
             put("respuesta", "bread")
             put("puntos_necesarios", 11)
             put("img_respuesta", "img26")
         }
         db?.insert("respuestas", null, datos12)

         val datos13 = ContentValues().apply {
             put("respuesta", "apple")
             put("puntos_necesarios", 12)
             put("img_respuesta", "img31")
         }
         db?.insert("respuestas", null, datos13)

         val datos14 = ContentValues().apply {
             put("respuesta", "orange")
             put("puntos_necesarios", 13)
             put("img_respuesta", "img32")
         }
         db?.insert("respuestas", null, datos14)

         val datos15 = ContentValues().apply {
             put("respuesta", "strawberry")
             put("puntos_necesarios", 14)
             put("img_respuesta", "img33")
         }
         db?.insert("respuestas", null, datos15)

         val datos16 = ContentValues().apply {
             put("respuesta", "pineapple")
             put("puntos_necesarios", 15)
             put("img_respuesta", "img34")
         }
         db?.insert("respuestas", null, datos16)

         val datos17 = ContentValues().apply {
             put("respuesta", "watermelon")
             put("puntos_necesarios", 16)
             put("img_respuesta", "img35")
         }
         db?.insert("respuestas", null, datos17)

         val datos18 = ContentValues().apply {
             put("respuesta", "blackberry")
             put("puntos_necesarios", 17)
             put("img_respuesta", "img36")
         }
         db?.insert("respuestas", null, datos18)*/

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

          Log.d("sqlito", "$puntosJugador y $strRespuestaUsuario")

          if (puntosJugador == null || strRespuestaUsuario.isNullOrBlank()) return false

          val db = this.readableDatabase

          val cursor = db.rawQuery(
              "SELECT 1 FROM respuestas WHERE puntos_necesarios = ? AND LOWER(respuesta) = ? LIMIT 1",
              arrayOf(puntosJugador.toString(), strRespuestaUsuario.lowercase())

          )
          Log.d("sqlito", "$cursor")

          val existe = cursor.moveToFirst()
          Log.d("sqlito", "$existe")

          cursor.close()
          db.close()

          return existe
      }

    // Inserta datos en tabla respuestas

    fun insertarDatos(){
        val db = this.writableDatabase
        val datos1 = ContentValues().apply {
            put("respuesta", "tiger")
            put("puntos_necesarios", 0)
            put("img_respuesta", "img11")
        }
        db.insert("respuestas", null, datos1)

        val datos2 = ContentValues().apply {
            put("respuesta", "dog")
            put("puntos_necesarios", 1)
            put("img_respuesta", "img12")
        }
        db.insert("respuestas", null, datos2)

        val datos3 = ContentValues().apply {
            put("respuesta", "cow")
            put("puntos_necesarios", 2)
            put("img_respuesta", "img13")
        }
        db.insert("respuestas", null, datos3)

        val datos4 = ContentValues().apply {
            put("respuesta", "monkey")
            put("puntos_necesarios", 3)
            put("img_respuesta", "img14")
        }
        db.insert("respuestas", null, datos4)

        val datos5 = ContentValues().apply {
            put("respuesta", "parrot")
            put("puntos_necesarios", 4)
            put("img_respuesta", "img15")
        }
        db.insert("respuestas", null, datos5)

        val datos6 = ContentValues().apply {
            put("respuesta", "elephant")
            put("puntos_necesarios", 5)
            put("img_respuesta", "img16")
        }
        db.insert("respuestas", null, datos6)

        val datos7 = ContentValues().apply {
            put("respuesta", "chicken")
            put("puntos_necesarios", 6)
            put("img_respuesta", "img21")
        }
        db.insert("respuestas", null, datos7)

        val datos8 = ContentValues().apply {
            put("respuesta", "milk")
            put("puntos_necesarios", 7)
            put("img_respuesta", "img22")
        }
        db.insert("respuestas", null, datos8)

        val datos9 = ContentValues().apply {
            put("respuesta", "cheese")
            put("puntos_necesarios", 8)
            put("img_respuesta", "img23")
        }
        db.insert("respuestas", null, datos9)

        val datos10 = ContentValues().apply {
            put("respuesta", "sushi")
            put("puntos_necesarios", 9)
            put("img_respuesta", "img24")
        }
        db.insert("respuestas", null, datos10)

        val datos11 = ContentValues().apply {
            put("respuesta", "ham")
            put("puntos_necesarios", 10)
            put("img_respuesta", "img25")
        }
        db.insert("respuestas", null, datos11)

        val datos12 = ContentValues().apply {
            put("respuesta", "bread")
            put("puntos_necesarios", 11)
            put("img_respuesta", "img26")
        }
        db.insert("respuestas", null, datos12)

        val datos13 = ContentValues().apply {
            put("respuesta", "apple")
            put("puntos_necesarios", 12)
            put("img_respuesta", "img31")
        }
        db.insert("respuestas", null, datos13)

        val datos14 = ContentValues().apply {
            put("respuesta", "orange")
            put("puntos_necesarios", 13)
            put("img_respuesta", "img32")
        }
        db.insert("respuestas", null, datos14)

        val datos15 = ContentValues().apply {
            put("respuesta", "strawberry")
            put("puntos_necesarios", 14)
            put("img_respuesta", "img33")
        }
        db.insert("respuestas", null, datos15)

        val datos16 = ContentValues().apply {
            put("respuesta", "pineapple")
            put("puntos_necesarios", 15)
            put("img_respuesta", "img34")
        }
        db.insert("respuestas", null, datos16)

        val datos17 = ContentValues().apply {
            put("respuesta", "watermelon")
            put("puntos_necesarios", 16)
            put("img_respuesta", "img35")
        }
        db.insert("respuestas", null, datos17)

        val datos18 = ContentValues().apply {
            put("respuesta", "blackberry")
            put("puntos_necesarios", 17)
            put("img_respuesta", "img36")
        }
        db.insert("respuestas", null, datos18)

        db.close()
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS respuestas")
        //db?.execSQL("DROP TABLE IF EXISTS respuestas")

        onCreate(db)
    }

}