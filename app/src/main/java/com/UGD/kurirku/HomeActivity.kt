package com.UGD.kurirku

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import androidx.fragment.app.Fragment
import com.UGD.kurirku.PDF.FragmentPDF
import com.google.android.material.navigation.NavigationBarView

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_home)

//        changeFragment(FragmentFilm())

        val botNav : NavigationBarView = findViewById(R.id.bottom_navigation)

        botNav.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.home -> {
                    changeFragment(FragmentHome())
                    true
                }
                R.id.maps -> {
                    changeFragment(FragmentGeo())
                    true
                }
                R.id.pengiriman -> {
                    changeFragment(FragmentPengiriman())
                    true
                }
                R.id.profil -> {
                    changeFragment(FragmentProfil())
                    true
                }
                R.id.pdf -> {
                    changeFragment(FragmentPDF())
                    true
                }
                else -> false
            }
        }
    }
    fun changeFragment(fragment: Fragment){
        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.layout_fragment, fragment)
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val menuInflater = MenuInflater(this)
        menuInflater.inflate(R.menu.home_menu, menu)
        return true
    }
}
