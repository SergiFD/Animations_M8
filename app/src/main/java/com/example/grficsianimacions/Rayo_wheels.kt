package com.example.grficsianimacions

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.yourapp.CircleView

class Rayo_wheels : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_wheels)
            val circleView = findViewById<CircleView>(R.id.circleView)
            val iwRayo = findViewById<ImageView>(R.id.iw_rayo)
            circleView.setIwRayo(iwRayo)
        }


}