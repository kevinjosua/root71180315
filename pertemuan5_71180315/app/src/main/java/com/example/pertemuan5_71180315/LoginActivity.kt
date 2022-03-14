package com.example.pertemuan5_71180315

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val txUsernameReg : EditText = findViewById(R.id.txUsernameReg)
        val txPasswordReg = findViewById<EditText>(R.id.txPasswordReg)
        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener {
            login(txUsernameReg.text.toString(), txPasswordReg.text.toString())
        }
    }
    fun login(username: String, password: String){
        if (password.equals("1234")){
            val i : Intent = Intent(this,MainActivity::class.java)
            i.putExtra("user", username)
            startActivity(i)
        }else{
            toast("Password Anda Salah!, Silahkan Masukkan Kembali !")
        }
    }
    fun toast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}