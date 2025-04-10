package com.example.kidsandenglish

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

private lateinit var btnGoToCreate: Button
private lateinit var btnRanking: Button
private lateinit var btnSelect: Button



    @SuppressLint("SuspiciousIndentation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

     btnGoToCreate=findViewById(R.id.btnGoToCreate)
        btnGoToCreate.setOnClickListener{
            val intent = Intent(this, createNewPlayer::class.java)
            startActivity(intent)
        }

     btnRanking=findViewById(R.id.btnRanking)
        btnRanking.setOnClickListener{
            val intent = Intent(this, ranking::class.java)
            startActivity(intent)
        }

     btnSelect=findViewById(R.id.btnSelect)
        btnSelect.setOnClickListener{
            val intent = Intent(this, selectPlayer::class.java)
            startActivity(intent)
        }
    }

}