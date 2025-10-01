package com.alejandrolopez.endrevina

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        public var records: ArrayList<Record> = ArrayList<Record>()
    }

    var numberToGuess : Int = 0
    var random : Random = Random.Default
    var attempts : Int = 1

    lateinit var input : TextInputEditText
    lateinit var logText : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.submitNumberButton)
            .setOnClickListener {
                processAttempt()
            }

        findViewById<Button>(R.id.goToHallOfFameButton)
            .setOnClickListener {
                val hallOfFameIntent = Intent(this, HallOfFameActivity::class.java)
                intent.putExtra("records", records)
                startActivity(hallOfFameIntent)
            }

        input = findViewById<TextInputEditText>(R.id.userInputText)
        logText = findViewById<TextView>(R.id.tableTextView)

        numberToGuess = generateRandomNumber()

    }

    fun generateRandomNumber(): Int {
        return random.nextInt(100)
    }

    fun processAttempt() {

        if (input.text.toString().equals("")) {
            Toast.makeText(this, "Has d'introduir un número", Toast.LENGTH_LONG).show()
            return
        }

        var userNumber = input.text.toString().toInt()

        if (userNumber > numberToGuess) {
            attempts++
            Toast.makeText(this, "El número es menor a " + userNumber, Toast.LENGTH_SHORT).show()
            input.text?.clear()
            logText.setText("El número es menor a " + userNumber + "\n" + logText.text.toString())
            return
        }

        if (userNumber < numberToGuess) {
            attempts++
            Toast.makeText(this, "El número es major a " + userNumber, Toast.LENGTH_SHORT).show()
            input.text?.clear()
            logText.setText("El número es major a " + userNumber + "\n" + logText.text.toString())
            return
        }

        generateAlert(attempts)
        logText.setText("Has endevinat el numero " + numberToGuess + " en " + attempts + " intents\n" + logText.text.toString())
        numberToGuess = generateRandomNumber()
        input.text?.clear()
        attempts = 1
    }
    fun generateAlert(attempts : Int) {

        val input: EditText = EditText(this).apply {
            hint = "Introdueix un nom d'usuari"
        }

        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder
            .setTitle("Alerta d'Andrevina")
            .setPositiveButton("Desa record") { dialog, which ->
                if (input.text.toString().isNotBlank()) {
                    records.add(Record(input.text.toString(), attempts))
                    Toast.makeText(this, "Record desat correctament", Toast.LENGTH_LONG).show()
                }
                Toast.makeText(this, "Record no serà desat", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("No desar") { dialog, which -> dialog.dismiss()}
            .setMessage("Has endevinat el numero " + numberToGuess + " en " + attempts + " intents")
            .setView(input)

        val alert: AlertDialog = builder.create()
        alert.show()
    }
}