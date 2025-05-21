package com.example.kidsandenglish

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity() {

    private lateinit var btnGoToCreate: Button
    private lateinit var btnRanking: Button
    private lateinit var btnSelect: Button
    private lateinit var btnClose:   Button

//  Situación Declaracion de variables

    //-   No para tipos primitivos. Vas a inicializar después pero estás seguro que se usará antes de acceder	lateinit var
    //-   El valor puede ser nulo o no estar aún	var nombre: Tipo? = null
    //-   Tienes un valor inicial claro	var puntos: Int = 0
    //-   No se va a reasignar	val nombre: String = "Juan"

    @SuppressLint("SuspiciousIndentation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        btnGoToCreate = findViewById(R.id.btnGoToCreate)
        btnGoToCreate.setOnClickListener {
            val intent = Intent(this, createNewPlayer::class.java)
            startActivity(intent)
            finish()
        }

        btnRanking = findViewById(R.id.btnRanking)
        btnRanking.setOnClickListener {
            val intent = Intent(this, ranking::class.java)
            startActivity(intent)
            finish()

        }

        btnSelect = findViewById(R.id.btnSelect)
        btnSelect.setOnClickListener {
            val intent = Intent(this, selectPlayer::class.java)
            startActivity(intent)
            finish()

        }

        btnClose=findViewById(R.id.btnCloseApp)
        btnClose.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Exit Game")
            builder.setMessage("Are you sure you want to quit the game?")
            builder.setPositiveButton("Yes") { _, _ ->
                finishAffinity()
            }
            builder.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            builder.setCancelable(false)
            builder.show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_about -> {
                mostrarAboutItem()
                true
            }

            R.id.menu_help -> {
                mostrarHelpItem()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun mostrarAboutItem() {

        val message = SpannableString("App Project Kids and English was developed by Alejandro C.M.\n" +
                "Please visit my GitHub Account to watch all the process:\nhttps://github.com/DonRenat0\n\n" +
                "Thanks for playing! 🎉")

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/DonRenat0"))
                widget.context.startActivity(intent)
            }
        }

        val start = message.indexOf("https://github.com")
        val end = start + "https://github.com/DonRenat0".length
        message.setSpan(clickableSpan, start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        val dialog = AlertDialog.Builder(this)
            .setTitle("About Us")
            .setMessage(message)
            .setPositiveButton("Close", null)
            .create()

        dialog.show()

// Habilitar enlaces clicables
        (dialog.findViewById<TextView>(android.R.id.message))?.movementMethod = LinkMovementMethod.getInstance()


        }

    private fun mostrarHelpItem() {

        AlertDialog.Builder(this)
            .setTitle("Help")
            .setMessage("Any problem you have while playing the game please send us an Email to: user@example.com. " +
                    "\nWe will response as fast as possible." +
                    "\nThanks for playing! 🎉")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

}