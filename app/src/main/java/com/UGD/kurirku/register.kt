package com.UGD.kurirku

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class register : AppCompatActivity() {
    private lateinit var nama: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var noHandphone : TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var btnRegister: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)

        nama = findViewById(R.id.etNama)
        email = findViewById(R.id.etEmail)
        noHandphone = findViewById(R.id.etNo_Handphone)
        password = findViewById(R.id.etPassword)
        btnRegister = findViewById(R.id.btnRegister)

        btnRegister.setOnClickListener{
            val mBundle = Bundle()

            //Memasukan
            mBundle.putString("nama", nama.text.toString())
            mBundle.putString("email", email.text.toString())
            mBundle.putString("noHandphone", noHandphone.text.toString())
            mBundle.putString("password", password.text.toString())
            //Intent dengan memanggil bundle
            intent.putExtra("register",mBundle)

            startActivity(intent)
        }
    }
}