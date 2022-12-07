package com.UGD.kurirku

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.UGD.kurirku.databinding.RegisterBinding
import com.UGD.kurirku.room.User
import com.UGD.kurirku.room.UserDB
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class register : AppCompatActivity() {

    val dbU by lazy { UserDB(this) }
    private var userId: Int = 0
    private lateinit var binding: RegisterBinding

    private val myPreference = "myPref"
    private val usernameK = "usernameKey"
    private val passK = "passKey"
    var sharedPreferencesRegister: SharedPreferences? = null

    private val BTN_REGISTER = "register_notification"
    private val notificationId1 = 101

//    private lateinit var inputnama: TextInputLayout
//    private lateinit var inputemail: TextInputLayout
//    private lateinit var inputnoHandphone : TextInputLayout
//    private lateinit var inputpassword: TextInputLayout
//    private lateinit var inputTanggalLahir: TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        getSupportActionBar()?.hide()
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.register)
        binding = RegisterBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        createNotificationChannel()

//        inputnama = findViewById(R.id.tilNama)
//        inputemail = findViewById(R.id.tilEmail)
//        inputnoHandphone = findViewById(R.id.tilNo_Handphone)
//        inputpassword = findViewById(R.id.tilPassword)
//        inputTanggalLahir = findViewById(R.id.tilTanggalLahir)
//        btnRegister = findViewById(R.id.btnRegister)
//        val btnRegister : Button = findViewById(R.id.btnRegister)
//
//
//        btnRegister.setOnClickListener(View.OnClickListener {
//            var checkLogin = false
//            sendNotification()
//            val moveMain = Intent(this@register, MainActivity::class.java)
//            val mBundle = Bundle()
//
//            val nama: String = inputnama.getEditText()?.getText().toString()
//            val email: String = inputemail.getEditText()?.getText().toString()
//            val noHanphone: String = inputnoHandphone.getEditText()?.getText().toString()
//            val password: String = inputpassword.getEditText()?.getText().toString()
//            val TanggalLahir: String = inputTanggalLahir.getEditText()?.getText().toString()
//
//            if(nama.isEmpty()){
//                inputnama.setError("Nama must be filled!!")
//                checkLogin = false
//            }
//
//            if(email.isEmpty()){
//                inputemail.setError("Email must be filled!!")
//                checkLogin= false
//            }
//
//            if(noHanphone.isEmpty()){
//                inputnoHandphone.setError("No Handphone must be filled!!")
//                checkLogin=false
//            }else if(noHanphone.length <10){
//                inputnoHandphone.setError("Phone Number must contain 10 number or more ")
//                checkLogin = false
//            }
//
//            if(password.isEmpty()){
//                inputpassword.setError("Password must be filled!!")
//                checkLogin = false
//            }
//
//            if(TanggalLahir.isEmpty()){
//                inputTanggalLahir.setError("Birth Date must be filled!!")
//                checkLogin = false
//            }
//
//            if(nama.isNotEmpty() && email.isNotEmpty() && noHanphone.isNotEmpty() && password.isNotEmpty() && TanggalLahir.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && noHanphone.isNotEmpty() && noHanphone.length>10) {
//                checkLogin = true
//
//                mBundle.putString("name", nama)
//                mBundle.putString("password", password)
//            }
//            if(!checkLogin) return@OnClickListener
//            startActivity(moveMain)
//        })
//    }

        sharedPreferencesRegister = getSharedPreferences(myPreference, Context.MODE_PRIVATE)

        binding?.btnRegister?.setOnClickListener(View.OnClickListener{
            var checkLogin = false
            val intentLogin = Intent(this@register, MainActivity::class.java)

            val username: String = binding?.tilNama?.getEditText()?.getText().toString()
            val password: String = binding?.tilPassword?.getEditText()?.getText().toString()
            val email: String = binding?.tilEmail?.getEditText()?.getText().toString()
            val tanggalLahir: String = binding?.tilTanggalLahir?.getEditText()?.getText().toString()
            val noTelp: String = binding?.tilNoHandphone?.getEditText()?.getText().toString()

            if(username.isEmpty()){
                binding?.tilNama?.setError("Username Tidak Boleh Kosong")
                checkLogin=false
            }

            if(password.isEmpty()){
                binding?.tilPassword?.setError("Password Tidak Boleh Kosong")
                checkLogin=false
            }

            if(email.isEmpty()){
                binding?.tilEmail?.setError("Email Tidak Boleh Kosong")
                checkLogin=false
            }else if(!email.matches(Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})"))){
                binding?.tilEmail?.setError("Email is invalid")
                checkLogin=false
            }

            if(tanggalLahir.isEmpty()){
                binding?.tilTanggalLahir?.setError("Tanggal Lahir Tidak Boleh Kosong")
                checkLogin=false
            }

            if(noTelp.isEmpty()){
                binding?.tilNoHandphone?.setError("Nomor Telepon Tidak Boleh Kosong")
                checkLogin=false
            } else if(noTelp.length < 11){
                binding?.tilNoHandphone?.setError("Nomor Telepon Harus 12 Angka")
                checkLogin=false
            }

            if(username.isNotEmpty() && password.isNotEmpty() && email.isNotEmpty() && email.matches(Regex("^[A-Za-z](.*)([@]{1})(.{1,})(\\.)(.{1,})")) && noTelp.isNotEmpty() && noTelp.length==12){
                checkLogin = true
            }

            if(!checkLogin) {
                return@OnClickListener
            } else {
                intentLogin.putExtra("username", binding.tilNama.getEditText()?.text.toString())
                intentLogin.putExtra("password", binding.tilPassword.getEditText()?.text.toString())

                CoroutineScope(Dispatchers.IO).launch {
                    dbU.userDao().addUser(
                        User(0, username, password, email, tanggalLahir, noTelp)
                    )
                    finish()
                }

                //save to shareP
                var strUserName: String = binding.tilNama.editText?.text.toString().trim()
                var strPass: String = binding.tilPassword.editText?.text.toString().trim()
                val editor: SharedPreferences.Editor = sharedPreferencesRegister!!.edit()
                editor.putString(usernameK, strUserName)
                editor.putString(passK, strPass)
                editor.apply()

                //pop notification
                sendNotification()
                startActivity(intentLogin)
            }
        })
    }

    private fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"

            val channel1 = NotificationChannel(BTN_REGISTER, name, NotificationManager.IMPORTANCE_DEFAULT).apply{
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE)as NotificationManager
            notificationManager.createNotificationChannel(channel1)
        }
    }

    private fun sendNotification(){

        val intent : Intent = Intent(this@register, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)

        val broadcastIntent : Intent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", binding?.tilNama?.getEditText()?.text.toString())
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)


        val builder = NotificationCompat.Builder(this, BTN_REGISTER)
            .setSmallIcon(R.drawable.ic_register_24)
            .setContentTitle(binding?.tilNama?.getEditText()?.text.toString())
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setColor(Color.BLUE)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .addAction(R.drawable.logo, "LANJUTKAN", actionIntent)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            notify(notificationId1,builder.build())

        }
    }
}