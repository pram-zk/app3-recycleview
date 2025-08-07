package com.smkth.app3_recycleview

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailActivity : AppCompatActivity() {
    private lateinit var tvNama: TextView
    private lateinit var tvNis: TextView
    private lateinit var tvKelas: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        tvNama = findViewById(R.id.tvNama)
        tvNis = findViewById(R.id.tvNis)
        tvKelas = findViewById(R.id.tvKelas)

        val nama = intent.getStringExtra("student_nama")
        val nis = intent.getStringExtra("student_nis")
        val kelas = intent.getStringExtra("student_kelas")

        tvNama.text = nama
        tvNis.text = nis
        tvKelas.text = kelas
    }
}