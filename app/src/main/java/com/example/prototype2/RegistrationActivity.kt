package com.example.prototype2

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity



class RegistrationActivity : AppCompatActivity() {

    private lateinit var db: MydatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeration)

        db = MydatabaseHelper(this)

        val registerButton: View = findViewById(R.id.submitButton)
        registerButton.setOnClickListener {
            val nameInput: EditText = findViewById(R.id.nameInput)
            val name = nameInput.text.toString()

            val famnameInput: EditText = findViewById(R.id.famnameInput)
            val famname = famnameInput.text.toString()

            val genderInput: RadioGroup = findViewById(R.id.radioGender)
            val ganderSelect: RadioButton = findViewById(genderInput.checkedRadioButtonId)
            val gender = ganderSelect.text.toString()

            val BdayInput: EditText = findViewById(R.id.bdateInput)
            val Bday = BdayInput.text.toString()

            val EmailInput: EditText = findViewById(R.id.emailInput)
            val email = EmailInput.text.toString()

            val PwdInput: EditText = findViewById(R.id.pwdInput)
            val pwd = PwdInput.text.toString()

            val TelInput: EditText = findViewById(R.id.telInput)
            val PhoneNumber = TelInput.text.toString()

            val fullname = "$name $famname"
            val account = Account(
                0,
                fullname,
                email,
                PhoneNumber,
                gender,
                Bday,
                pwd
            )
            db.insertUser(account)
            db.printData()
            finish()
        }

    }
}
