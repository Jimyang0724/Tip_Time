package com.example.tiptime

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import java.util.*
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener {
            calculateTip()
        }
    }

    fun calculateTip(){
        // Get the cost
        val cost_Str = binding.costOfService.text.toString()
        val cost = cost_Str.toDouble()

        // Get %
        val selectID = binding.tipOptions.checkedRadioButtonId
        val tipPercentage = when(selectID) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }

        // calculate
        var tip = cost*tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp) tip = ceil(tip)
        val formattedTip = NumberFormat.getCurrencyInstance(Locale.TAIWAN).format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }
}