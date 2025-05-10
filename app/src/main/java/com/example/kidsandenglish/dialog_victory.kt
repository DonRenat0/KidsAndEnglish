package com.example.kidsandenglish

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView

class dialog_victory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_victory)

        val animacion = findViewById<LottieAnimationView>(R.id.finish_animation)
        animacion.setAnimation("finishanimation.lottie")
        animacion.playAnimation()


    }
}