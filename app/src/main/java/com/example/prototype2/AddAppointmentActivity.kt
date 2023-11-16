package com.example.prototype2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class AddAppointmentActivity : AppCompatActivity() {

    val BdatePattern = Regex("^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d\$")
    private lateinit var db: MydatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_appointment)

        db = MydatabaseHelper(this)

        val nameNotInput : EditText = findViewById(R.id.nameNotInput)
        nameNotInput.setText("Your not writing this")
        val appointmentInput : EditText = findViewById(R.id.AppointdateInput)
        var appointmentDate = appointmentInput.text.toString().trim()

        val appointSubmitButton : Button = findViewById(R.id.submitAppointmentButton)
        appointSubmitButton.setOnClickListener {

            var flag = true

            if (!appointmentDate.matches(BdatePattern)) {
                Toast.makeText(this, "invalid birth date", Toast.LENGTH_SHORT).show();
                flag = false;
            }

            if(flag){
                db.insertAppointment(MainActivity.Companion.userRealname, appointmentDate)
                db.printData()
                Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show();
                finish()
            }
        }
    }
}