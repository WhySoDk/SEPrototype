package com.example.prototype2

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewAppointmentActivity : AppCompatActivity() {

    private lateinit var db: MydatabaseHelper
    companion object {
        const val REQUEST_CODE_ACTIVITY_C = 123
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_appointment)
        db = MydatabaseHelper(this)
        val appointmentList: List<String> = db.getAppointmentDate(MainActivity.Companion.userRealname)
        val recycleView : RecyclerView = findViewById(R.id.appointmentRecycler)
        recycleView.layoutManager = LinearLayoutManager(this)
        val appointmentadapter = MyAdapter(appointmentList,this)
        recycleView.adapter = appointmentadapter

        val refrashbut : ImageView = findViewById(R.id.refreash)
        refrashbut.setOnClickListener {
            val intent = Intent(this, ViewAppointmentActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

}