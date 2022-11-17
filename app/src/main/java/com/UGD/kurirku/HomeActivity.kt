package com.UGD.kurirku

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.close
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.UGD.kurirku.databinding.ActivityHomeBinding
import java.nio.channels.AsynchronousFileChannel.open

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        binding.apply {
//            toggle= ActionBarDrawerToggle(this@HomeActivity ,drawerLayout,R.string.open,R.string.close)
//            drawerLayout.addDrawerListener(toggle)
//            toggle.syncState()
//
//            supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//            navView.setNavigationItemSelectedListener {
//                when(it.itemId){
////                    R.id.menu_Register->{
////                        changeFragment(register())
////                    }
////                    R.id.menu_login->{
////                        changeFragment(MainActivity())
////                    }
//                    R.id.menu->{
//                        changeFragment(FragmentPengiriman())
//                    }
//                }
//                true
//            }
//        }
        changeFragment(FragmentPengiriman())
    }

//    binding.apply {
//        toggle=ActionBarDrawerToggle(this@HomeActivity,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close)
//        drawerLayout.addDrawerListener(toggle)
//        toggle.syncState()
//
//        supportActionBar?.setDisplayHomeAsUpEnabled(true)
//
//        navView.setNavigationItemSelectedListener {
//            when(it.itemId){
//                R.id.tv_name->{
//                    Toast.makeText(this@HomeActivity, "Name Item Clicked", Toast.LENGTH_SHORT).show()
//                }
//                R.id.nav_home->{
//                    changeFragment(FragmentProduk())
//                }
//                R.id.nav_favorite->{
//                    changeFragment(FragmentFavorit())
//                }
//                R.id.nav_location->{
//                    changeFragment(FragmentLokasi())
//                }
//                R.id.nav_message->{
//                    changeFragment(FragmentMessage())
//                }
//                R.id.nav_profile->{
//                    changeFragment(FragmentProfil())
//                }
//                R.id.nav_exit->{
//                    val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
//                    builder.setMessage("Are you sure want to exit?")
//                        .setPositiveButton("YES", object : DialogInterface.OnClickListener{
//                            override fun onClick(dialogInterface: DialogInterface, i : Int){
//                                finishAndRemoveTask()
//                            }
//                        })
//                        .show()
//                }
//            }
//            true
//        }
//    }
//    //fragment yg pertama muncul ialah fragment produk
//    changeFragment(FragmentProduk())
//
//}
//
//
////mengubah fragment
//fun changeFragment(fragment: Fragment?){
//    if(fragment != null){
//        getSupportFragmentManager()
//            .beginTransaction()
//            .replace(R.id.layout_fragment, fragment)
//            .commit()
//    }
//}
//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val menuInflater = MenuInflater(this)
//        menuInflater.inflate(R.menu.home
//                _menu, menu)
//        return true
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_login){
            changeFragment(FragmentPengiriman())
        } else if (item.itemId == R.id.menu_Register){
            changeFragment(FragmentPengiriman())
        } else {
            val builder: AlertDialog.Builder = AlertDialog.Builder(this@HomeActivity)
            builder.setMessage("Are you sure want to exit?")
                .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                    override fun onClick(dialogInterface: DialogInterface, i: Int) {

                        finishAndRemoveTask()
                    }
                })
                .show()
        }
        return super.onOptionsItemSelected(item)
    }
}