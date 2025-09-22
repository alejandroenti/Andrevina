package com.alejandrolopez.endrevina

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.textfield.TextInputEditText
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var numberToGuess : Int = 0
    var random : Random = Random.Default
    var attemps : Int = 1

    lateinit var input : TextInputEditText
    lateinit var message : TextView

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
        message = findViewById<TextView>(R.id.messageText)

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
            attemps++
            message.setText("El numero és menor que " + userNumber)
            return
        }

        if (userNumber < numberToGuess) {
            attemps++
            message.setText("El numero és major que " + userNumber)
            return
        }

        message.setText("Has endevinat el numero en " + attemps + " intents")
    }
}