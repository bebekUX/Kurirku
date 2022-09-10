package com.UGD.kurirku

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class register : AppCompatActivity() {
    private lateinit var inputnama: TextInputLayout
    private lateinit var inputemail: TextInputLayout
    private lateinit var inputnoHandphone : TextInputLayout
    private lateinit var inputpassword: TextInputLayout
    private lateinit var btnRegister: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        inputnama = findViewById(R.id.tilNama)
        inputemail = findViewById(R.id.tilEmail)
        inputnoHandphone = findViewById(R.id.tilNo_Handphone)
        inputpassword = findViewById(R.id.tilPassword)
        btnRegister = findViewById(R.id.btnRegister)



        btnRegister.setOnClickListener(View.OnClickListener {
            var checkLogin = false

            val nama: String = inputnama.getEditText()?.getText().toString()
            val email: String = inputemail.getEditText()?.getText().toString()
            val noHanphone: String = inputnoHandphone.getEditText()?.getText().toString()
            val password: String = inputpassword.getEditText()?.getText().toString()

            if(nama.isEmpty()){
                inputnama.setError("Nama must be filled!!")
                checkLogin = false
            }

            if(email.isEmpty()){
                inputemail.setError("Email must be filled!!")
                checkLogin= false
            }

            if(noHanphone.isEmpty()){
                inputnoHandphone.setError("No Handphone must be filled!!")
                checkLogin=false
            }else if(noHanphone.length <11){
                inputnoHandphone.setError("Phone Number must contain 10 number or more ")
                checkLogin = false
            }


            if(password.isEmpty()){
                inputpassword.setError("Password must be filled!!")
                checkLogin = false
            }

            if(nama.isNotEmpty() && email.isNotEmpty() && noHanphone.isNotEmpty() && password.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && noHanphone.isNotEmpty() && noHanphone.length>10) checkLogin = true
            if(!checkLogin) return@OnClickListener
            val moveMain = Intent(this, MainActivity::class.java)
            startActivity(moveMain)

        })
    }
}