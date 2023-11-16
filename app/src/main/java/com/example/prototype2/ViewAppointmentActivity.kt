package com.example.prototype2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ViewAppointmentActivity : AppCompatActivity() {

    private lateinit var db: MydatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_appointment)

        db = MydatabaseHelper(this)
        val appointmentList: List<String> = db.getAppointmentDate(MainActivity.Companion.userRealname)
        val recycleView : RecyclerView = findViewById(R.id.appointmentRecycler)
        recycleView.layoutManager = LinearLayoutManager(this)
        val appointmentadapter = MyAdapter(appointmentList,this)
        recycleView.adapter = appointmentadapter

    }
}