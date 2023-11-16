package com.example.prototype2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ChangeAppointmentDateActivity : AppCompatActivity() {

    private val datePattern = Regex("^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d\$")
    private lateinit var db: MydatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_appointment_date)

        val receivedValue= intent.getStringExtra("toBeChangeDate")
        Log.d("toBeChangeDate", receivedValue!!)
        db = MydatabaseHelper(this)

        val nameNotInput : EditText = findViewById(R.id.nameNotInput)
        nameNotInput.setText(MainActivity.userRealname)
        val oldAppointDate : EditText = findViewById(R.id.OldAppointdateInput)
        oldAppointDate.setText(receivedValue)

        val appointSubmitButton : Button = findViewById(R.id.changeAppointmentButton)
        appointSubmitButton.setOnClickListener {

            var flag = true
            val appointmentInput : EditText = findViewById(R.id.NewAppointdateInput)
            var appointmentDate = appointmentInput.text.toString().trim()
            Log.d("date", appointmentDate)
            if (!appointmentDate.matches(datePattern)) {
                Toast.makeText(this, "invalid appointment date", Toast.LENGTH_SHORT).show();
                flag = false;
            }
            else if(db.checkAppointment("admin admin", appointmentDate)){
                Toast.makeText(this, "duplicate appointment date, pick another", Toast.LENGTH_SHORT).show();
                flag = false;
            }
            if(flag){
                val username = MainActivity.userRealname
                db.UpdateAppointment(username,receivedValue!!, appointmentDate)
                db.printData()
                Toast.makeText(this, "Appointment Change!", Toast.LENGTH_LONG).show()
                finish()
            }
        }
    }
}