package com.example.simplecalculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operation: String = ""
    private var isNewOperation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)

        val buttons = listOf(
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9),
            findViewById<Button>(R.id.buttonDot),
            findViewById<Button>(R.id.buttonAdd),
            findViewById<Button>(R.id.buttonSubtract),
            findViewById<Button>(R.id.buttonMultiply),
            findViewById<Button>(R.id.buttonDivide),
            findViewById<Button>(R.id.buttonEqual),
            findViewById<Button>(R.id.buttonClear)
        )

        buttons.forEach { button ->
            button.setOnClickListener {
                onButtonClick(button)
            }
        }
    }

    private fun onButtonClick(button: Button) {
        val text = button.text.toString()

        when (text) {
            "C" -> clear()
            "=" -> calculate()
            "+", "-", "*", "/" -> selectOperation(text)
            else -> appendNumberOrDot(text)
        }
    }

    private fun clear() {
        editText.setText("0")
        operand1 = 0.0
        operand2 = 0.0
        operation = ""
        isNewOperation = true
    }

    private fun calculate() {
        operand2 = editText.text.toString().toDouble()
        val result = when (operation) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> operand2
        }
        editText.setText(result.toString())
        isNewOperation = true
    }

    private fun selectOperation(op: String) {
        operand1 = editText.text.toString().toDouble()
        operation = op
        isNewOperation = true
    }

    private fun appendNumberOrDot(numberOrDot: String) {
        if (isNewOperation) {
            editText.setText(numberOrDot)
            isNewOperation = false
        } else {
            editText.append(numberOrDot)
        }
    }
}
