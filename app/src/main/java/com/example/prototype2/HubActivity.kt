package com.example.prototype2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.cardview.widget.CardView

class HubActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hub)

        val addView: CardView = findViewById(R.id.addCard)
        val viewView: CardView = findViewById(R.id.viewCard)

        addView.setOnClickListener{
            val intent = Intent(this, AddAppointmentActivity::class.java)
            startActivity(intent)
        }
        viewView.setOnClickListener{
            val intent = Intent(this, ViewAppointmentActivity::class.java)
            startActivity(intent)
        }
    }
}