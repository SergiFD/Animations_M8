package com.example.grficsianimacions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var btWheels: Button
    private lateinit var btMecanico: Button
    private lateinit var btCarrera: Button
    private lateinit var tv_animation: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btWheels = findViewById(R.id.bt_weels)
        btMecanico = findViewById(R.id.bt_Mecanico)
        btCarrera = findViewById(R.id.bt_Carrera)
        tv_animation = findViewById(R.id.tv_animation)

        // Aplicar la animación al cargar la vista
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        tv_animation.startAnimation(fadeInAnimation)

        btWheels.setOnClickListener {
            val intent = Intent(this, WheelsView::class.java)
            startActivity(intent)
        }

        btCarrera.setOnClickListener {
            val intent = Intent(this, RacingView::class.java)
            startActivity(intent)
        }

        // Aplicar la animación al hacer clic en el botón
        tv_animation.setOnClickListener {
            // Cargar la animación
            val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
            // Aplicar la animación al botón
            tv_animation.startAnimation(fadeInAnimation)
        }

        btMecanico.setOnClickListener {
            val intent = Intent(this, MecanicoView::class.java)
            startActivity(intent)
        }
    }
}
