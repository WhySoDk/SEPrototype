package com.example.prototype2

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.prototype2.databinding.ActivityMainBinding
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {



    private lateinit var binding: ActivityMainBinding
    private lateinit var db: MydatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = MydatabaseHelper(this)
        db.printData()

        val registerButton: Button = findViewById(R.id.regisButton)
        registerButton.setOnClickListener{
            val intent = Intent(this@MainActivity, RegistrationActivity::class.java)
            startActivity(intent)
        }

        val loginButton: Button = findViewById(R.id.loginButton)
        loginButton.setOnClickListener{

            val emailInput: EditText = findViewById(R.id.emailInput)
            var email = emailInput.text.toString()
            val pwdInput: EditText = findViewById(R.id.pwdInput)
            var pwd = pwdInput.text.toString()

            if(db.isUserCredentialsValid(email, pwd)){
                Toast.makeText(
                    this,
                    "Login!!!",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                Toast.makeText(
                    this,
                    "Wrong Password!!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }
}