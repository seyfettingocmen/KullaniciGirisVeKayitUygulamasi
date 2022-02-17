package com.example.pratiklerim

import android.content.DialogInterface
import android.content.SharedPreferences
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pratiklerim.databinding.ActivityBilgilerEkraniBinding
import com.example.pratiklerim.databinding.ActivityMainBinding

class BilgilerEkrani : AppCompatActivity() {
    // binding isimli tasarım ekranına bağlanmaya yarayan fonksiyonu çağırmak için;
    lateinit var binding: ActivityBilgilerEkraniBinding

    // sharedPreferences isimli küçük verileri kaydetmeye yarayan fonksiyonu çağırmak için;
    lateinit var sharedPreferences: SharedPreferences

    // runnable isimli sayac fonksiyonunu bir değere eşitlemek için;
    var runnable: Runnable = Runnable {  }

    // handler isimli sayac yardımcısını bir değere eşitlemek için;
    var handler = Handler(Looper.myLooper()!!)

    // numara isimli değere int 0 değişkene eşitlemek için;
    var numara = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        // binding fonksiyonunu bu tahtada aktif etmek ve tasarım ekranına bağlanmak için;
        binding = ActivityBilgilerEkraniBinding.inflate(layoutInflater)
        setContentView(binding.root)

        super.onCreate(savedInstanceState)

        // sharedPreferences kayıt işlemi için anahtar kelimeyi ve modunu kilitli mod olarak belirleme;
        sharedPreferences = this.getSharedPreferences("bilgiler", MODE_PRIVATE)

        // sharedPreferences klasörü içinde anahtar kelimelerle alınan verileri değerlere eşitleme;
        var gelenAdd = sharedPreferences.getString("ad","")
        var gelenParolaa = sharedPreferences.getString("parola","")

        // alınan ve değerlere eşitlenen verileri textView metin kutularının içine yazdırma
        binding.gelenAd.text = "Kullanıcı Adı; ${gelenAdd}"
        binding.gelenParola.text = "Kullanıcı Şifre; ${gelenParolaa}"


        // sharedPreferences klasör içindeki verileri anahtar kelimelerine göre silme;
        binding.kayitSil.setOnClickListener {

            val uyari = AlertDialog.Builder(this@BilgilerEkrani)
            uyari.setTitle("KAYDINIZI SİLMEK İSTİYOR MUSUNUZ?")
            uyari.setPositiveButton("HAYIR",DialogInterface.OnClickListener { dialogInterface, i ->  })
            uyari.setNegativeButton("EVET",DialogInterface.OnClickListener { dialogInterface, i ->
                sharedPreferences.edit().remove("ad").apply()
                sharedPreferences.edit().remove("parola").apply()
                finish()
                Toast.makeText(this@BilgilerEkrani,"Kayıt Silindi",Toast.LENGTH_SHORT).show()
            })
            uyari.show()
        }

        // çıkış butonuna basınca yapılacak işlemleri oluşturma;
        binding.cikis3.setOnClickListener {
            finish()
            Toast.makeText(this@BilgilerEkrani,"ÇIKIŞ YAPILDI",Toast.LENGTH_SHORT).show()
        }

        // KRONEMETRE

        // 1- Kornemetreyi Başlatmak için
        binding.baslat1.setOnClickListener {

        runnable = object : Runnable{
            override fun run() {
                numara = numara + 1
                binding.sayac1.text = "${numara}"

                handler.postDelayed(runnable,1)
            }
        }
        handler.post(runnable)
        }

        // Kronemetreyi Durdurmak İçin

        binding.durdur.setOnClickListener {

        handler.removeCallbacks(runnable)

        numara = 0
        binding.sayac1.text = "${numara}"

        }
        // Sayacı Başlatmak İçin

        binding.baslat2.setOnClickListener {

            object : CountDownTimer(3000,1000){
                override fun onTick(p0: Long) {
                    binding.geriSayi.text = "${p0/1000}"
                }

                override fun onFinish() {
                    binding.geriSayi.text = "0"
                    Toast.makeText(this@BilgilerEkrani,"Geri Sayım Bitti",Toast.LENGTH_SHORT).show()
                }

            }.start()

        }

    }
}


