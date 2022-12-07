package com.UGD.kurirku.PDF

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.UGD.kurirku.R
import kotlinx.android.synthetic.main.fragment_pdfragment.*
import com.UGD.kurirku.databinding.FragmentPdfragmentBinding

class FragmentPDF : Fragment() {
    private var bindingPDF : FragmentPdfragmentBinding?=null
    private val binding get() = bindingPDF!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bindingPDF = FragmentPdfragmentBinding.inflate(inflater,container,false)
        val view = binding.root
        return view
//        return inflater.inflate(R.layout.fragment_pdfragment, container, false)
//        val view = binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnPdf.setOnClickListener {
            val intent = Intent(activity, ActivityPDF::class.java)
            startActivity(intent)
        }
    }
}