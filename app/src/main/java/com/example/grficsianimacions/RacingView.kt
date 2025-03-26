package com.example.grficsianimacions

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

class RacingView : AppCompatActivity() {
    private lateinit var wheel: ImageView
    private lateinit var wheel_2: ImageView
    private lateinit var wheelSet: AnimatorSet
    private lateinit var speedButton: Button
    private var currentSpeed: Long = 3000 // Velocidad inicial en milisegundos (3 segundos)
    private val minSpeed: Long = 1000 // Velocidad mínima (1 segundo)
    private val maxSpeed: Long = 200 // Velocidad máxima (0.5 segundos)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_racing)

        // Encuentra las vistas
        wheel = findViewById(R.id.wheel_1)
        wheel_2 = findViewById(R.id.wheel_2)
        speedButton = findViewById(R.id.speedButton)

        // Inicializa el AnimatorSet
        wheelSet = AnimatorSet()

        // Interpolador para la animación
        val inter: TimeInterpolator = LinearInterpolator()

        // Crea la animación de rotación
        val rotateWheel: ObjectAnimator = ObjectAnimator.ofFloat(wheel, "rotation", 0f, 360f)
        val rotateWheel_2: ObjectAnimator = ObjectAnimator.ofFloat(wheel_2, "rotation", 0f, 360f)
        rotateWheel.duration = currentSpeed
        rotateWheel.repeatCount = ValueAnimator.INFINITE // animación infinita
        rotateWheel.interpolator = inter // Establecemos el interpolador
        rotateWheel_2.duration = currentSpeed
        rotateWheel_2.repeatCount = ValueAnimator.INFINITE // animación infinita
        rotateWheel_2.interpolator = inter // Establecemos el interpolador

        // Comienza la animación
        wheelSet.play(rotateWheel)
        wheelSet.play(rotateWheel_2)
        wheelSet.start()

        // Cargar el GIF de fondo
        val gifBackground: ImageView = findViewById(R.id.gifBackground)
        Glide.with(this)
            .asGif()
            .load(R.drawable.caranimation) // Reemplaza con tu GIF
            .into(gifBackground)

        // Establecer el comportamiento del botón
        speedButton.setOnClickListener {
            // Aumenta la velocidad, pero no supere el límite máximo
            if (currentSpeed > minSpeed) {
                currentSpeed -= 200 // Reducir la duración (aumentar la velocidad)
                rotateWheel.duration = currentSpeed // Actualizar la duración de la animación
                rotateWheel_2.duration = currentSpeed // Actualizar la duración de la animación
            }

            // Si la velocidad alcanza el límite mínimo, el botón puede deshabilitarse
            if (currentSpeed == minSpeed) {
                speedButton.isEnabled = false // Deshabilitar el botón cuando se alcanza la velocidad máxima
            }
        }
    }
}
