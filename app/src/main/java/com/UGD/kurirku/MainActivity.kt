package com.UGD.kurirku

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.UGD.kurirku.databinding.RegisterBinding
import com.UGD.kurirku.room.UserDB


class MainActivity : AppCompatActivity() {

    private lateinit var inputUsername: TextInputLayout
    private lateinit var inputPassword: TextInputLayout
    private lateinit var mainLayout: ConstraintLayout
    var mBundle = Bundle()

    lateinit var tempUsername: String
    lateinit var tempPassword: String

    //database
    val dbU by lazy { UserDB(this) }

    private val myPreference = "myPref"
    private val usernameK = "usernameKey"
    private val id = "idKey"
    private val passK = "passKey"
    var sharedPreferencesRegister: SharedPreferences? = null

    private var BTN_REGISTER = "channel_notification_01"
    private val notificationId1 = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setTitle("User Login")


        inputUsername = findViewById(R.id.inputLayoutUsername)
        inputPassword = findViewById(R.id.inputLayoutPassword)

        sharedPreferencesRegister = getSharedPreferences(myPreference, Context.MODE_PRIVATE)

        mainLayout = findViewById(R.id.mainLayout)
        val btnRegister: Button = findViewById(R.id.btnRegister)
        val btnLogin: Button = findViewById(R.id.btnLogin)
        val btnclearText: Button = findViewById(R.id.btnclearText)

        getBundle()

        btnRegister.setOnClickListener{
            val moveregister = Intent(this@MainActivity, register::class.java)
            startActivity(moveregister)
        }

        btnclearText.setOnClickListener{
            inputUsername.getEditText()?.setText("")
            inputPassword.getEditText()?.setText("")
            inputUsername.setError("")
            inputPassword.setError("")

            Snackbar.make(mainLayout,"Text Cleared Success", Snackbar.LENGTH_LONG) .show()
        }


        btnLogin.setOnClickListener(View.OnClickListener {
            var checkLogin = false
            val username: String = inputUsername.getEditText()?.getText().toString()
            val password: String = inputPassword.getEditText()?.getText().toString()

            if(username.isEmpty()){
                Toast.makeText(this@MainActivity, "Username tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                checkLogin = false
            }

            if(password.isEmpty()){
                Toast.makeText(this@MainActivity, "Password tidak boleh kosong!", Toast.LENGTH_SHORT).show()
                checkLogin = false
            }

            val user = dbU.userDao().getUsers()
            Log.d("LoginActivity", "dbResponse: $user")

            for(i in user){
                if (username == i.username && password == i.password){
                    val editor: SharedPreferences.Editor = sharedPreferencesRegister!!.edit()
                    editor.putString(id, i.id.toString()).apply()
                    checkLogin=true
                    break
                }
            }

            if(username != tempUsername || password != tempPassword){
                checkLogin = false

                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
//                builder.setTitle("Password atau Username Salah")
//                    .setPositiveButton("Yes"){ dialog, which ->}
//                    .show()
            }

            if (username == "admin" && password == "admin"){
                val moveHome = Intent(this@MainActivity, HomeActivity::class.java)

                val builder: AlertDialog.Builder = AlertDialog.Builder(this@MainActivity)
//                builder.setTitle("ANDA ADALAH ADMIN")
//                    .setPositiveButton("Yes"){ dialog, which ->}
//                    .show()

                createNotificationChannel()
                sendNotification()
                startActivity(moveHome)
            }
            if(!checkLogin)return@OnClickListener

            val moveHome = Intent(this@MainActivity, HomeActivity::class.java)
            createNotificationChannel()
            sendNotification()
            startActivity(moveHome)
        })

    }

    fun getBundle(){
//        //preference
//        sharedPreferencesRegister = getSharedPreferences(myPreference, Context.MODE_PRIVATE)
//        if (sharedPreferencesRegister!!.contains(usernameK)){
//            inputUsername.getEditText()?.setText(sharedPreferencesRegister!!.getString(usernameK, ""))
//        }
//        if (sharedPreferencesRegister!!.contains(passK)){
//            inputPassword.getEditText()?.setText(sharedPreferencesRegister!!.getString(passK, ""))
//        }

        tempUsername = intent.getStringExtra("username").toString()
        tempPassword = intent.getStringExtra("password").toString()

        if(tempUsername!="null") {
            inputUsername.getEditText()?.setText(tempUsername)
            inputPassword.getEditText()?.setText(tempPassword)
        }
    }

//    fun getCompare(str: String){
//        CoroutineScope(Dispatchers.Main).launch {
//            val user = dbU.userDao().getUser(str)[0]
//            tempUsername = user.username
//            tempPassword = user.password
//        }
//    }

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

        val intent : Intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_MUTABLE)

        val broadcastIntent : Intent = Intent(this, NotificationReceiver::class.java)
        broadcastIntent.putExtra("toastMessage", R.id.inputLayoutUsername.toString())
        val actionIntent = PendingIntent.getBroadcast(this, 0, broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE)

        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.logo)

        val builder = NotificationCompat.Builder(this, BTN_REGISTER)
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("User: " + inputUsername.getEditText()?.getText().toString() + " Berhasil Login!!")
            .setLargeIcon(bitmap)
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(getString(R.string.ThankYou))
                .setBigContentTitle("Thank You")
                .setSummaryText("Username: " + inputUsername.getEditText()?.getText().toString()))
            .setCategory(NotificationCompat.CATEGORY_MESSAGE)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(Color.RED)
            .setAutoCancel(true)
            .setOnlyAlertOnce(true)
            .setContentIntent(pendingIntent)
            .addAction(R.mipmap.ic_launcher, "NEXT", actionIntent)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId1, builder.build())
        }
    }

}