package com.example.pertemuan7_71180315

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listContact = arrayListOf<Contact>()
        listContact.add(Contact("Jokowi", R.mipmap.jokowi, "0812456312", "jokowi1@gmail.com"))
        listContact.add(Contact("Luhut", R.mipmap.luhut, "0852453687", "luhut3@gmail.com"))
        listContact.add(Contact("Prabowo", R.mipmap.prabowo, "0865432176", "prabowo2@gmail.com"))


        val rvContact = findViewById<RecyclerView>(R.id.recyclerView)
        rvContact.layoutManager = LinearLayoutManager(this)
        val contactAdapter = ContactAdapter(listContact)
        rvContact.adapter = contactAdapter
    }
}