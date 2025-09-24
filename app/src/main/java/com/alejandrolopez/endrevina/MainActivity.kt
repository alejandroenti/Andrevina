package com.alejandrolopez.endrevina

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
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

        input = findViewById<TextInputEditText>(R.id.userInputText)
        logText = findViewById<TextView>(R.id.logText)

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
    }

    fun generateAlert(attempts : Int) {
        val alert = AlertDialog.Builder(this)
                    .setTitle("Alerta d'Andrevina")
                    .setPositiveButton("Tanca") { dialog, which -> dialog.dismiss() }
                    .setCancelable(false)
                    .setMessage("Has endevinat el numero " + numberToGuess + " en " + attempts + " intents")
                    .create()
        alert.show()
    }
}