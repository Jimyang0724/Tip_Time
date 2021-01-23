package com.example.tiptime

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import androidx.appcompat.app.AlertDialog
import com.example.tiptime.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
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
            if(binding.costOfService.text.isEmpty()) showDialog()
            else calculateTip()
        }
    }

    fun showDialog(){
        val builder = AlertDialog.Builder(this)
                .setTitle("Error")
                .setMessage("Input cost of service!")
                .setPositiveButton("OK", null)
                .show()
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
            R.id.option_fifteen_percent -> 0.15
            else -> 0.0
        }
        if(selectID == R.id.option_bad) linkToWeb("https://www.youtube.com/watch?v=_6TtTRrno3E")

        // calculate
        var tip = cost*tipPercentage
        val roundUp = binding.roundUpSwitch.isChecked
        if(roundUp) tip = ceil(tip)
        val formattedTip = NumberFormat.getCurrencyInstance(Locale.TAIWAN).format(tip)
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }

    fun linkToWeb(url: String){
        val website = Intent(Intent.ACTION_VIEW)
        website.data = Uri.parse(url)
        startActivity(website)
    }
}