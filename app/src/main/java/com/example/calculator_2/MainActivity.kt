package com.example.calculator_2

import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.calculator_2.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // or
        // val view = binding.root
        //        setContentView(view)

        binding.clear.setOnClickListener{
            binding.input.text = ""
            binding.output.text = ""
        }
        binding.left.setOnClickListener{
            binding.input.text =addToInputText("(")
        }
        binding.right.setOnClickListener{
            binding.input.text =addToInputText(")")
        }
        binding.btn0.setOnClickListener{
            binding.input.text =addToInputText("0")
        }
        binding.btn1.setOnClickListener{
            binding.input.text =addToInputText("1")
        }
        binding.btn2.setOnClickListener{
            binding.input.text =addToInputText("2")
        }
        binding.btn3.setOnClickListener{
            binding.input.text =addToInputText("3")
        }
        binding.btn4.setOnClickListener{
            binding.input.text =addToInputText("4")
        }
        binding.btn5.setOnClickListener{
            binding.input.text =addToInputText("5")
        }
        binding.btn6.setOnClickListener{
            binding.input.text =addToInputText("6")
        }
        binding.btn7.setOnClickListener{
            binding.input.text =addToInputText("7")
        }
        binding.btn8.setOnClickListener{
            binding.input.text =addToInputText("8")
        }
        binding.btn9.setOnClickListener{
            binding.input.text =addToInputText("9")
        }
        binding.dotBtn.setOnClickListener{
            binding.input.text =addToInputText(".")
        }
        binding.subBtn.setOnClickListener{
            binding.input.text =addToInputText("-")
        }
        binding.sumBtn.setOnClickListener{
            binding.input.text =addToInputText("+")
        }
        binding.multiplicationBtn.setOnClickListener{
            binding.input.text =addToInputText("×")
        }
        binding.divitionBtn.setOnClickListener{
            binding.input.text =addToInputText("÷")
        }
        binding.eqBtn.setOnClickListener{
            showResult()
            binding.output.visibility = View.VISIBLE

        }
        binding.backBtn.setOnClickListener {
            var input = binding.input.text.toString()
            if (input.isNotEmpty()) {
                input = input.substring(0, input.length - 1)
                binding.input.text = input
            }

            if (input.isEmpty()) {
                binding.output.visibility = View.INVISIBLE
            }
        }

    }
    private fun showResult() {
        val calculator = Calculator()

        try {
            val expression = getInputExpression()
            val result = calculator.calculate(expression)
            binding.output.text = result.toString()
//            binding.output.setTextColor(ContextCompat.getColor(this, R.color.green))

        } catch (e: Exception) {
//            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show()
            if (binding.input.text.isNullOrBlank()){
                return
            }
            binding.output.text = "Math Error"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }

    private fun getInputExpression(): String {
        return binding.input.text.toString()
    }
    private fun addToInputText(btnVal: String) :String{
        binding.output.text = ""
        return "${binding.input.text}$btnVal"
    }

}
