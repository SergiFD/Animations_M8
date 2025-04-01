package com.example.grficsianimacions

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MecanicoView : AppCompatActivity(){

    private lateinit var sun: ImageView
    private lateinit var sunSet: AnimatorSet
    private lateinit var faceOut: AnimatorSet
    private lateinit var paisaje : View;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_atardecer)

        // reconocemos el image view
        sun = findViewById<View>(R.id.sun) as ImageView
        paisaje = findViewById(R.id.view)

// inflamos el XML de la animación
        sunSet = AnimatorInflater.loadAnimator(this, R.animator.sun_moving) as AnimatorSet
// seleccionamos el objetivo de la animación inflada
        sunSet.setTarget(sun)
// ejecutamos la animación con el objetivo ya establecido

        sunSet.start()

        // comenzamos con el objeto visible y lo vamos haciendo invisible
        val fadeAnim: ValueAnimator = ObjectAnimator.ofFloat(paisaje, "alpha", 0f, 1f)
        fadeAnim.duration = 5000
        fadeAnim.repeatCount = ValueAnimator.INFINITE
        fadeAnim.repeatMode = ValueAnimator.REVERSE

        fadeAnim.start()
    }
}