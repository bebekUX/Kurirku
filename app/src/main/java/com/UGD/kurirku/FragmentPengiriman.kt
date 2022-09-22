package com.UGD.kurirku

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.UGD.kurirku.entity.Pengiriman

class FragmentPengiriman : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_pengiriman, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val adapter : RVPengirimanAdapter = RVPengirimanAdapter(Pengiriman.listOfPengiriman)

        val rvPengiriman : RecyclerView = view.findViewById(R.id.rv_pengiriman)

        rvPengiriman.layoutManager = layoutManager

        rvPengiriman.setHasFixedSize(true)
        rvPengiriman.adapter = adapter
    }
}