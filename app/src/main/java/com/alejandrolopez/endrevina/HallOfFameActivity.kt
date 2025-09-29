package com.alejandrolopez.endrevina

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HallOfFameActivity : AppCompatActivity()  {

    private lateinit var table : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.hall_of_fame)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hallOfFameLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val records = intent.getSerializableExtra("records")

        table = findViewById<TextView>(R.id.tableTextView)
        table.setText(records.toString())
    }
}