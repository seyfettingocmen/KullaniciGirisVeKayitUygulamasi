package com.example.pratiklerim

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.os.Binder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pratiklerim.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    // binding isimli tasarım ekranına bağlanmaya yarayan fonksiyonu çağırmak için;
    lateinit var binding: ActivityMainBinding

    // sharedPreferences isimli küçük verileri kaydetmeye yarayan fonksiyonu çağırmak için;
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding fonksiyonunu bu tahtada aktif etmek ve tasarım ekranına bağlanmak için;
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // sharedPreferences kayıt işlemi için anahtar kelime ve modunu kilitli mod olarak belirleme;
        sharedPreferences = getSharedPreferences("bilgiler", MODE_PRIVATE)


        // giriş butonuna dokununca yapılacak işlemler
        binding.giris1.setOnClickListener {


            // kullanici girdisine değer verme;
            val girilenAd = binding.kullaniciAd.text.toString()
            val girilenParola = binding.parola.text.toString()


            // kayıtlı kullanıcı bilgilerini çekme ve değer verme;
            val gelenAd = sharedPreferences.getString("ad","")
            val gelenParola = sharedPreferences.getString("parola","")


            // kullanıcı ve parola editText'leri boştayken butona basılırsa yapılacaklar;
            if (girilenAd == "" || gelenParola == "" ){

            // Uyarı ekranı oluşturma için;
            val uyari = AlertDialog.Builder(this@MainActivity)
            uyari.setTitle("Giriş Hatası")
            uyari.setMessage("Lütfen Gerekli Verileri Giriniz!!")
            uyari.setPositiveButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
            uyari.show()

             }

            // kullanıcı girdisi veriler ile kayıtlı verilerin eşleşmemesi durumunda yapılacaklar;
            else if (girilenAd != gelenAd || girilenParola != gelenParola){

            // uyari ekranı oluşturmak için;
            val uyari = AlertDialog.Builder(this@MainActivity)
            uyari.setTitle("Giriş Hatası")
            uyari.setMessage("Lütfen Doğru Verileri Giriniz!!")
            uyari.setPositiveButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
            uyari.setNeutralButton("KAYIT OL",DialogInterface.OnClickListener { dialogInterface, i ->
                val intent = Intent(applicationContext,KayitEkrani::class.java)
                startActivity(intent)
            })
            uyari.show()

            }

            // hiçbir şart koşulu uymayınca yapılacaklar;
            else {

                // kullaniciAd kısmına yazılan text'leri silmek için;
                binding.kullaniciAd.text.clear()
                binding.parola.text.clear()
                val intent = Intent(applicationContext,BilgilerEkrani::class.java)
                startActivity(intent)
                Toast.makeText(this@MainActivity,"HOŞGELDİNİZ ${girilenAd}",Toast.LENGTH_SHORT).show()
            }

        }

        // çıkış butonuna basınca yapılacak işlemler için;
        binding.cikis1.setOnClickListener {
            finish()
            // çıkış yapılınca altta "Çıkış Yapıldı" yazılı kısa süreli bildirim baloncuğu oluşturmak için;
            Toast.makeText(this@MainActivity,"Çıkış Yapıldı",Toast.LENGTH_SHORT).show()
        }

        // kayıt ol ekranına geçmek için basılan butona basılınca yapılacak işlemler için
        binding.kayit1.setOnClickListener {
            // KayitEkrani isimli MainActivity ekranına geçmek için;
            var intent = Intent(applicationContext,KayitEkrani::class.java)
            startActivity(intent)
        }


    }

}