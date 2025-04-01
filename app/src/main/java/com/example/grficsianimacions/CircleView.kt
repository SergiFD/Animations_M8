package com.example.yourapp

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView

class CircleView(context: Context, attrs: AttributeSet?) : View(context, attrs) {

    private var iwRayo: ImageView? = null
    private var rayoX = 0f
    private var rayoY = 0f
    private var rayoWidth = 0f
    private var rayoHeight = 0f
    private var posicionesCalculadas = false

    private val paint = Paint().apply {
        color = Color.BLUE
        isAntiAlias = true
        style = Paint.Style.FILL // Rellenar el círculo
    }

    private val borderPaint = Paint().apply {
        color = Color.WHITE // Color del borde
        isAntiAlias = true
        style = Paint.Style.STROKE // Solo el borde
        strokeWidth = 10f // Ancho del borde
    }

    private val wheels = mutableListOf<Pair<Float, Float>>() // Lista de ruedas
    private val fixedPositions = mutableListOf<Pair<Float, Float>>() // Posiciones fijas de las ruedas
    private val margin = 50f // Margen para la tolerancia al clic

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        this.setBackgroundColor(Color.TRANSPARENT)


        for ((x, y) in wheels) {
            // Dibuja el círculo
            canvas.drawCircle(x, y, 100f, paint)
            canvas.drawCircle(x, y, 100f, borderPaint)

            // Dibuja radios
            for (angle in 0 until 360 step 30) { // Dibuja líneas cada 30 grados
                val radian = Math.toRadians(angle.toDouble())
                val startX = x + 100f * Math.cos(radian).toFloat()
                val startY = y + 100f * Math.sin(radian).toFloat()
                canvas.drawLine(x, y, startX, startY, borderPaint)
            }
        }

        // Calcula posiciones solo una vez después de que la vista se haya medido
        if (!posicionesCalculadas) {
            iwRayo?.let { rayo ->
                val location = IntArray(2)
                rayo.getLocationOnScreen(location)
                rayoX = location[0].toFloat()
                rayoY = location[1].toFloat()
                rayoWidth = rayo.width.toFloat()
                rayoHeight = rayo.height.toFloat()
                posicionesCalculadas = true

                // Agrega las posiciones fijas de las ruedas
                val ruedaIzquierdaX = rayoX + rayoWidth * 0.20f
                val ruedaDerechaX = rayoX + rayoWidth * 0.74f
                fixedPositions.add(Pair(ruedaIzquierdaX, rayoY + rayoHeight * 0.89f))
                fixedPositions.add(Pair(ruedaDerechaX, rayoY + rayoHeight * 0.86f))
            }
        }
    }

    // Método para establecer el ImageView de Rayo McQueen
    fun setIwRayo(imageView: ImageView) {
        this.iwRayo = imageView
        invalidate()  // Redibuja la vista
    }

    // Método para agregar una rueda
    fun addWheel(x: Float, y: Float) {

        if (wheels.size == 2) {
           if (fixedPositions.contains(wheels[0]) && fixedPositions.contains(wheels[1])) return
        }
        // Verifica si el clic está dentro del margen de una posición fija
        for (position in fixedPositions) {
            if (isWithinMargin(x, y, position.first, position.second)) {
                // Si está cerca, agrega la rueda como fija
                if (!wheels.contains(position)) {
                    borrar_wheel()
                    wheels.add(position)
                }
                return
            }
        }
        wheels.add(Pair(x, y))
        borrar_wheel()
        // Elimina la última rueda generada solo si no está en fixedPositions
        invalidate() // Solicita un redibujo
    }

    private fun borrar_wheel(){
        if (wheels.size > 1) {
            if (fixedPositions.contains(wheels[0])){
                wheels.removeAt(1) //eliminar el segundo
            }else
                if (fixedPositions.contains(wheels[1])){
                    wheels.removeAt(0) //eliminar el primero
                }else
                {
                    wheels.removeAt(0) // Elimina todas menos la primera
                }
        }
    }
    // Método para verificar si un punto está cerca de una posición
    private fun isWithinMargin(x: Float, y: Float, targetX: Float, targetY: Float): Boolean {
        return Math.abs(x - targetX) <= margin && Math.abs(y - targetY) <= margin
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            addWheel(event.x, event.y) // Agrega una rueda en la posición del toque
            invalidate()
            return true
        }
        return super.onTouchEvent(event)
    }
}
