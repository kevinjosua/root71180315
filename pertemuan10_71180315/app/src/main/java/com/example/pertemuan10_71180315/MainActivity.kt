package com.example.pertemuan10_71180315

import android.annotation.SuppressLint
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.BaseColumns
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var db: SQLiteDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = DatabaseHelper(this).writableDatabase

        val editNama = findViewById<EditText>(R.id.editNama)
        val editUsia = findViewById<EditText>(R.id.editUsia)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnDelete = findViewById<Button>(R.id.btnDelete)
        val btnFind = findViewById<Button>(R.id.btnFind)

        btnSave.setOnClickListener{
            val values = ContentValues().apply{
                put(DatabaseContract.Penduduk.COLUMN_NAME_NAMA, editNama.text.toString())
                put(DatabaseContract.Penduduk.COLUMN_NAME_USIA, editUsia.text.toString())
            }

            db?.insert(DatabaseContract.Penduduk.TABLE_NAME, null, values)

            editNama.setText("")
            editUsia.setText("")
            refreshData()
        }

        btnDelete.setOnClickListener{
            val selection = "nama = ? OR usia = ?"
            val selectionArgs = arrayOf(editNama.text.toString(), editUsia.text.toString())

            db?.delete(DatabaseContract.Penduduk.TABLE_NAME, selection, selectionArgs)

            editNama.setText("")
            editUsia.setText("")
            refreshData()
        }

        btnFind.setOnClickListener{
            search(editNama.text.toString(), editUsia.text.toString())
        }
        refreshData()
    }

    fun refreshData(){
        val tvHasil = findViewById<TextView>(R.id.tvHasil)
        val columns = arrayOf(BaseColumns._ID, DatabaseContract.Penduduk.COLUMN_NAME_NAMA, DatabaseContract.Penduduk.COLUMN_NAME_USIA)
        val cursor = db?.query(
            DatabaseContract.Penduduk.TABLE_NAME,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        var result = ""
        with(cursor){
            while (this!!.moveToNext()){
                result += "${this!!.getString(1)}-${this!!.getString(2)}th\n"
            }
        }
        tvHasil.text = result
    }

    @SuppressLint("Range")
    fun search(nama: String, usia: String){
        val tvHasilCari = findViewById<TextView>(R.id.tvHasilCari)
        val query = "SELECT * FROM ${DatabaseContract.Penduduk.TABLE_NAME} WHERE ${DatabaseContract.Penduduk.COLUMN_NAME_NAMA} LIKE ? OR "+
                "${DatabaseContract.Penduduk.COLUMN_NAME_USIA} LIKE ?"
        val selectionArgs = arrayOf(nama, usia)
        val cursor = db?.rawQuery(
            query,
            selectionArgs
        )

        var result = ""
        with(cursor){
            while (this?.moveToNext() == true) {
                val nama = getString(this?.getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_NAMA)
                )
                val usia = getString(this?.getColumnIndex(DatabaseContract.Penduduk.COLUMN_NAME_USIA)
                )
                result += "${nama} - ${usia} th\n"
            }
        }
        tvHasilCari.text = result
    }
}