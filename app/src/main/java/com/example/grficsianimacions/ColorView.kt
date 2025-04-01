import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.grficsianimacions.R

class ColorView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)

        val imagen = findViewById<ImageView>(R.id.imageView3)
        val boton = findViewById<Button>(R.id.button)

        boton.setOnClickListener {
            // Animación para cambiar el color de fondo de la imagen
            val imageAnim = ObjectAnimator.ofInt(
                imagen, "backgroundColor",
                Color.rgb(0x66, 0xcc, 0xff), // Color inicial
                Color.rgb(0x00, 0x66, 0x99)  // Color final
            ).apply {
                duration = 3000
                repeatCount = ValueAnimator.INFINITE
                repeatMode = ValueAnimator.REVERSE
                setEvaluator(ArgbEvaluator())
            }

            // Animación para cambiar el color de fondo del botón
            val buttonAnim = ObjectAnimator.ofInt(
                boton, "backgroundColor",
                Color.rgb(0xff, 0xa5, 0x00), // Naranja
                Color.rgb(0xff, 0x00, 0x00)  // Rojo
            ).apply {
                duration = 3000
                repeatCount = ValueAnimator.INFINITE
                repeatMode = ValueAnimator.REVERSE
                setEvaluator(ArgbEvaluator())
            }

            imageAnim.start()
            buttonAnim.start()
        }
    }
}
