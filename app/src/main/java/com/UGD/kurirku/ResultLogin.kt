package com.UGD.kurirku

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class resultLogin : AppCompatActivity() {
    private lateinit var nama: TextView
    private lateinit var email: TextView
    private lateinit var fakultas: TextView
    private lateinit var password: TextView
    lateinit var mBundle: Bundle

    lateinit var vNama: String
    lateinit var vEmail: String
    lateinit var vNoHandphone: String
    lateinit var vPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        getBundle()
        setText()
    }

    fun getBundle(){
        //mengambil bundle dari activity sebelumnya dengan menggunakan key register
        mBundle = intent.getBundleExtra("register")!!
        //mengambil data dari bundle
        vNama = mBundle.getString("nama")!!
        vEmail = mBundle.getString("email")!!
        vNoHandphone= mBundle.getString("noHandphone")!!
        vPassword = mBundle.getString("password")!!
    }

    fun setText(){
        nama = findViewById(R.id.tvNama)
        nama.setText(vNama)
        email = findViewById(R.id.tvEmail)
        email.setText(vEmail)
        fakultas = findViewById(R.id.tvPassword)
        fakultas.setText(vPassword)
        nama = findViewById(R.id.tvNama)
        nama.setText(vNama)
    }
}