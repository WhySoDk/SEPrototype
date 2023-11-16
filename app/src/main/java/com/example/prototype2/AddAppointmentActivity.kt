package com.example.prototype2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddAppointmentActivity : AppCompatActivity() {

    private val datePattern = Regex("^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d\$")
    private lateinit var db: MydatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appointment)

        db = MydatabaseHelper(this)

        val nameNotInput : EditText = findViewById(R.id.nameNotInput)
        nameNotInput.setText(MainActivity.Companion.userRealname)


        val appointSubmitButton : Button = findViewById(R.id.submitAppointmentButton)
        appointSubmitButton.setOnClickListener {

            var flag = true
            val appointmentInput : EditText = findViewById(R.id.AppointdateInput)
            var appointmentDate = appointmentInput.text.toString().trim()
            Log.d("date", appointmentDate)
            if (!appointmentDate.matches(datePattern)) {
                Toast.makeText(this, "invalid appointment date", Toast.LENGTH_SHORT).show();
                flag = false;
            }

            if(flag){
                db.insertAppointment(MainActivity.Companion.userRealname, appointmentDate)
                db.printData()
                Toast.makeText(this, "Appointment Created", Toast.LENGTH_LONG).show();
                finish()
            }
        }
    }
}