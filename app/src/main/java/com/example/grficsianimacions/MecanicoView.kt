package com.example.grficsianimacions

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.annotation.SuppressLint
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
        setContentView(R.layout.activity_mecanico)

        // reconocemos el image view
        sun = findViewById<View>(R.id.sun) as ImageView
        paisaje = findViewById(R.id.view)


// inflamos el XML de la animación
        sunSet = AnimatorInflater.loadAnimator(this, R.animator.sun_moving) as AnimatorSet
        faceOut = AnimatorInflater.loadAnimator(this, R.animator.face_out) as AnimatorSet
// seleccionamos el objetivo de la animación inflada
        sunSet.setTarget(sun)
        faceOut.setTarget(paisaje)
// ejecutamos la animación con el objetivo ya establecido

        sunSet.start()
        faceOut.start()
    }
}