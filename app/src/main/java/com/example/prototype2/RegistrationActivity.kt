package com.example.prototype2

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity



class RegistrationActivity : AppCompatActivity() {

    private lateinit var db: MydatabaseHelper


    val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
    val namePattern = Regex("[a-zA-Z]{1,}")
    val telPattern = Regex("[0]+[0-9]{9}")
    val BdatePattern = Regex("^(?:0[1-9]|[12]\\d|3[01])([\\/.-])(?:0[1-9]|1[012])\\1(?:19|20)\\d\\d\$")
    val pwdPattern = Regex(".{8,}")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registeration)

        db = MydatabaseHelper(this)

        val registerButton: View = findViewById(R.id.submitAppointmentButton)
        registerButton.setOnClickListener {

            var flag = true


            val nameInput: EditText = findViewById(R.id.nameNotInput)
            val name = nameInput.text.toString().trim()

            val famnameInput: EditText = findViewById(R.id.famnameInput)
            val famname = famnameInput.text.toString().trim()

            var gender = "Null"
            val genderInput: RadioGroup = findViewById(R.id.radioGender)
            if(genderInput.checkedRadioButtonId == -1){
                Toast.makeText(this,"Choose Gender",Toast.LENGTH_SHORT).show();
                flag = false;
            }
            else {
                val ganderSelect: RadioButton = findViewById(genderInput.checkedRadioButtonId)
                gender = ganderSelect.text.toString().trim()
            }

            val BdayInput: EditText = findViewById(R.id.AppointdateInput)
            val Bday = BdayInput.text.toString().trim()

            val EmailInput: EditText = findViewById(R.id.emailInput)
            val email = EmailInput.text.toString().trim()

            val PwdInput: EditText = findViewById(R.id.pwdInput)
            val pwd = PwdInput.text.toString().trim()

            val TelInput: EditText = findViewById(R.id.telInput)
            val PhoneNumber = TelInput.text.toString().trim()

            if(flag) {
                if (!email.matches(emailPattern)) {
                    Toast.makeText(this, "invalid email address", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else if (!name.matches(namePattern)) {
                    Toast.makeText(this, "invalid name", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else if (!famname.matches(namePattern)) {
                    Toast.makeText(this, "invalid family name", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else if (!PhoneNumber.matches(telPattern)) {
                    Toast.makeText(this, "invalid phone number", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else if (!Bday.matches(BdatePattern)) {
                    Toast.makeText(this, "invalid birth date", Toast.LENGTH_SHORT).show();
                    flag = false;
                } else if (!pwd.matches(pwdPattern)) {
                    Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
                    flag = false;
                }
            }

            if(flag){

                var fullname = "$name $famname"
                var account = Account(
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
                Toast.makeText(this, "Account Created", Toast.LENGTH_LONG).show();
                finish()
            }
        }

    }
}
