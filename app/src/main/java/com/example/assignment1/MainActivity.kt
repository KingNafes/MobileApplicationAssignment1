package com.example.assignment1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    private lateinit var mortgageAmountInput: EditText
    private lateinit var tenureInput: EditText
    private lateinit var interestRateInput: EditText
    private lateinit var calculateButton: Button
    private lateinit var resultTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        initViews()

        calculateButton.setOnClickListener {
            val emiValue = calculateEMI(
                mortgageAmountInput.text.toString(),
                interestRateInput.text.toString(),
                tenureInput.text.toString()
            )
            resultTextView.text = if (emiValue == null) {
                "Invalid input! Please enter valid numbers."
            } else {
                "Your Monthly EMI is: %.2f".format(emiValue)
            }
        }
    }

    private fun initViews() {
        mortgageAmountInput = findViewById(R.id.mortgageAmount)
        tenureInput = findViewById(R.id.tenure)
        interestRateInput = findViewById(R.id.interestRate)
        calculateButton = findViewById(R.id.calculateBtn)
        resultTextView = findViewById(R.id.result)
    }

    private fun calculateEMI(principalStr: String, rateStr: String, tenureStr: String): Double? {
        return try {
            val principal = principalStr.toDouble()
            val monthlyRate = rateStr.toDouble() / 12 / 100
            val numberOfMonths = tenureStr.toInt()

            val emi = (principal * monthlyRate * Math.pow(1 + monthlyRate, numberOfMonths.toDouble())) /
                    (Math.pow(1 + monthlyRate, numberOfMonths.toDouble()) - 1)
            emi
        } catch (e: NumberFormatException) {
            null
        }
    }
}
