package com.example.bmi_kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmi_kalkulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.numberscrooltinggi.minValue = 10
        binding.numberscrooltinggi.maxValue = 200

        binding.numberscroolberat.minValue = 15
        binding.numberscroolberat.maxValue = 150

        binding.numberscroolberat.setOnValueChangedListener{_, _, _ ->
        BMIkalkulator()
        }
        binding.numberscrooltinggi.setOnValueChangedListener{_, _, _ ->
            BMIkalkulator()
        }
    }
    private fun BMIkalkulator() {

        val berat = binding.numberscroolberat.value

        val tinggi = binding.numberscrooltinggi.value
        val komatinggi = tinggi.toDouble() / 100


        val BMI = berat.toDouble() / (komatinggi * komatinggi)

        binding.hasilbmi.text = String.format("BMI Kamu Adalah : %.2f", BMI)
        binding.keteranganbmi.text = String.format("Keterangan : %s", pesankesehatan(BMI))
    }

    private fun pesankesehatan(BMI: Double): String {
        if (BMI < 18.5){
            return "Terlalu Kurus"
        }else if (BMI < 25.0){
            return "Normal"
        } else if (BMI < 30.0){
            return "Kelebihan Berat Badan"
        } else {
            return "Obesitas"
        }
    }
}