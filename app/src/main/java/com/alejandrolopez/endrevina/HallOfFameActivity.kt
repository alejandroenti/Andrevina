package com.alejandrolopez.endrevina

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class HallOfFameActivity : AppCompatActivity()  {

    private lateinit var table : TextView
    private var tableString : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.hall_of_fame)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.hallOfFameLayout)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        table = findViewById<TextView>(R.id.tableTextView)
        findViewById<Button>(R.id.goToMainButton).setOnClickListener {
                val hallOfFameIntent = Intent(this, MainActivity::class.java)
                startActivity(hallOfFameIntent)
            }

        tableString = "Noms".padEnd(35, ' ') + "  Intents" + "\n\n"
        for (record in MainActivity.records) {
            tableString += record.name.padEnd(35, ' ') + "  " + record.intents.toString().padEnd(8, ' ') + "\n"
        }

        table.setText(tableString)
    }
}