package com.example.pertemuan11_71180315

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    val db = Firebase.firestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editNama = findViewById<EditText>(R.id.editNama)
        val editNim = findViewById<EditText>(R.id.editNIM)
        val editIpk = findViewById<EditText>(R.id.editIPK)
        val rg1 = findViewById<RadioGroup>(R.id.rg1)
        val rg2 = findViewById<RadioGroup>(R.id.rg2)
        val radioAsc = findViewById<RadioButton>(R.id.radioAsc)
        val radioDesc = findViewById<RadioButton>(R.id.radioDesc)
        val radioNim = findViewById<RadioButton>(R.id.radioNim)
        val radioNama = findViewById<RadioButton>(R.id.radioNama)
        val radioIpk = findViewById<RadioButton>(R.id.radioIpk)
        val btnSimpan = findViewById<Button>(R.id.btnSimpan)
        val btnCari = findViewById<Button>(R.id.btnCari)
        val btnCariData = findViewById<Button>(R.id.btnCariData)
        val txtHasil = findViewById<TextView>(R.id.tvHasil)

        btnSimpan.setOnClickListener {
            val mahasiswa = Mahasiswa(
                editNama.text.toString(),
                editNim.text.toString().toInt(),
                editIpk.text.toString().toInt()
            )
            db.collection("mahasiswa").add(mahasiswa)
            editNama.setText("")
            editNim.setText("")
            editIpk.setText("")
            refreshData()
        }
        refreshData()

        btnCari.setOnClickListener {
            if (radioAsc.isChecked()) {
                if (radioNama.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nama", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                } else if (radioNim.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nim", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                } else if (radioIpk.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("ipk", Query.Direction.ASCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                }
            } else if (radioDesc.isChecked()) {
                if (radioNama.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nama", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)

                        }
                } else if (radioNim.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("nim", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                } else if (radioIpk.isChecked()) {
                    db.collection("mahasiswa")?.orderBy("ipk", Query.Direction.DESCENDING)?.get()!!
                        .addOnSuccessListener { docs ->
                            var output = ""
                            for (doc in docs) {
                                output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                            }
                            txtHasil.setText(output)
                        }
                }
            }
            rg1.clearCheck()
            rg2.clearCheck()
        }

        btnCariData.setOnClickListener {
            if (editNama.text.toString() != "" && editNama.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("nama", editNama.text.toString())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txtHasil.setText(output)

                    }
            } else if (editNim.text.toString() != "" && editNim.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("nim", editNim.text.toString().toInt())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txtHasil.setText(output)
                    }
            } else if (editIpk.text.toString() != "" && editNama.text.toString() != null) {
                db.collection("mahasiswa")?.whereEqualTo("ipk", editIpk.text.toString().toInt())?.get()!!
                    .addOnSuccessListener { docs ->
                        var output = ""
                        for (doc in docs) {
                            output += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                        }
                        txtHasil.setText(output)
                    }
            }

        }
    }

    fun refreshData() {
        db.collection("mahasiswa")
            .get()
            .addOnSuccessListener {snapshot ->
                val txtData = findViewById<TextView>(R.id.tvData)
                var data = ""
                for(doc in snapshot){
                    data += "${doc["nama"]}-${doc["nim"]}-${doc["ipk"]}\n"
                }
                txtData.text = data
            }
    }
}