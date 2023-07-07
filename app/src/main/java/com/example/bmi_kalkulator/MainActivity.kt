package com.example.bmi_kalkulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.bmi_kalkulator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    //private lateinit berguna untuk memberitahu kompiler bahwa variabel tersebut akan diinisialisasi
    //sebelum digunakan, meskipun saat deklarasi belum ada nilai yang diberikan. Dengan kata lain,
    //variabel tersebut tidak perlu langsung diinisialisasi dengan nilai pada saat deklarasi.
    //intinya menginisialisasikan terlebih dahulu variabel sebelum digunakan dalam  kasus ini variabel binding

    //setelah itu variabel binding diberi nilai ActivityMainBinding untuk mengakses tampilan muka activity main
    //dalam kasus ini id nya tidak perlu di deklarasikan lagi seperti (R.id.....), karna sudah di akses dengan class
    //activitymainBinding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //membuat variabel binding tadi memiliki nilai ActivityMainBinding dan memanggil method inflate dari kelas
        //ActivityMainBinding yang berfungsi untuk menghubungkan layout xml ke kelas kotlin ini
        //menggunakan kelas layoutInflater juga, pokoknya kalau mau mengakses kelas xml pakai method ini
        //inflate(layoutInflater)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //selanjutnya perintah setContentView digunakan untuk  menentukan tata letak atau tampilan utama untuk aktivitas
        // kelas kotlin MainActivity  Dalam hal ini, Anda menggunakan root view (root) yang ada dalam objek variabel kelas binding
        // sebagai tampilan utama aktivitas.
        //simpel nya kita bisa menginisialisasi banyak xml di file ini tapi hanya satu yang bisa di tampilkan di layar. makanya menggunakan
        //setcontent
        setContentView(binding.root)


        //menentukan nilai max dan min dari number picker tinggi
        binding.numberscrooltinggi.minValue = 10
        binding.numberscrooltinggi.maxValue = 200

        //menentukan nilai max dan min dari number picker berat
        binding.numberscroolberat.minValue = 15
        binding.numberscroolberat.maxValue = 150



        //parameter nya setonvaluechange harus 3 yaitu nilai dari numberpicker itu sendiri, nilai sebelumnya
        // yang dipilih dan nilai baru yang akan dipilih, disini nilai baru tersebut akan berubah dengan
        // logika yang ada pada fungsi BMIkalkulator

        //intinyna ketika numberpicker tinggi di scrool atau berat dia akan mengubah nilai sebuah perintah di
        //fungsiBMIkalkulator
        binding.numberscroolberat.setOnValueChangedListener { numberPicker, i, i2 ->
            BMIkalkulator()
        }
        binding.numberscrooltinggi.setOnValueChangedListener{_, _, _ ->
            BMIkalkulator()
        }
    }
    private fun BMIkalkulator() {

        //memberi nilai pada variabel berat dengan nilai dari number picker berat
        val berat = binding.numberscroolberat.value

        //memberi nilai pada variabel berat dengan nilai dari number picker tinggi
        val tinggi = binding.numberscrooltinggi.value

        //mengkonvert nilai tinggi dibagi 100  (syarat kalkulator BMI)
        val komatinggi = tinggi.toDouble() / 100

        //logic kalkulator BMI
        val BMI = berat.toDouble() / (komatinggi * komatinggi)

        //nilai dari text view hasil mempunyai nilai string dengan format "tulisan"
        // dan hasil dari perhitungan variabel 'BMI' nilai nya hanya sampai 2 angka di belakkang koma
        binding.hasilbmi.text = String.format("BMI Kamu Adalah : %.2f", BMI)

        //nilai dari text view hasil mempunyai nilai string dengan format "tulisan"
        //dan hasil dari perhingan fungsi pesan kesehatan yang di simpan pada parameter BMI
        binding.keteranganbmi.text = String.format("Keterangan : %s", pesankesehatan(BMI))
    }

    //buat fungsi pesan kesahatan untuk mendapatkan logic dari pesan yang di berikan
    //pada fungsi tersebut buat parameter juga untuk menyimpan nilai nya
    private fun pesankesehatan(BMI: Double): String {

        //logic untuk pesan BMI
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