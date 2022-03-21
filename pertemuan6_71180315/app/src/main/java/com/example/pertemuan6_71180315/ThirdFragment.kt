package com.example.pertemuan6_71180315

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment

class ThirdFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.fragment_c, container, false)
        val btnFragmentThird = v.findViewById<Button>(R.id.btnFragThird)
        btnFragmentThird.setOnClickListener {
            val i = Intent(context, MainActivity::class.java)
            context?.startActivity(i)
        }
        return v
    }
}