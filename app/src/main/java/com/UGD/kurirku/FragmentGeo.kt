package com.UGD.kurirku

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.UGD.kurirku.Geo.MainActivityGeo
import com.UGD.kurirku.databinding.FragmentGeoBinding

class FragmentGeo : Fragment() {
    private var bindingLokasi: FragmentGeoBinding?=null
    private val binding get() = bindingLokasi!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingLokasi = FragmentGeoBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLocation.setOnClickListener{
            val intent = Intent(activity, MainActivityGeo::class.java)
            startActivity(intent)
        }
    }
}