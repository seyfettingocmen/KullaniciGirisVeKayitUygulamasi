package com.example.pratiklerim

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pratiklerim.databinding.ActivityKayitEkraniBinding

class KayitEkrani : AppCompatActivity() {
    // binding isimli tasarım ekranına bağlanmaya yarayan fonksiyonu çağırmak için;
    lateinit var binding : ActivityKayitEkraniBinding

    // sharedPreferences isimli küçük verileri kaydetmeye yarayan fonksiyonu çağırmak için;
    lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // binding fonksiyonunu bu tahtada aktif etmek ve tasarım ekranına bağlanmak için;
        binding = ActivityKayitEkraniBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // sharedPreferences kayıt işlemi için anahtar kelime ve modunu kilitli mod olarak belirleme;
        sharedPreferences = getSharedPreferences("bilgiler", MODE_PRIVATE)

        // "bilgiler" isimli sharedPreferenses kayıt klasörüne anahtar kelimelerle verileri çekip değere eşitlemek için;
        val kayitliAd = sharedPreferences.getString("ad","")
        val kayitliParola = sharedPreferences.getString("parola","")


        // kayıt butonuna basınca yapılacak işlemleri belirlemek için;
        binding.kayit2.setOnClickListener {

            // Uyarı penceresini cağıran fonksiyonu bir değere eşitleme için;
            val uyari = AlertDialog.Builder(this@KayitEkrani)

            // editText metin kutularına girilen verileri değerlere eşitlemek için;
            val ad1 = binding.kayitAd.text.toString()
            val parolaa = binding.kayitParola.text.toString()
            val parolaa2 = binding.kayitParola2.text.toString()

            // editText metin kutularına değer girilmeden butona basılırsa eğer yapılacak işlemler;
            if (ad1 == "" || parolaa == "" || parolaa2 == ""){
            uyari.setTitle("Veri Hatası!")
            uyari.setMessage("Lütfen Gerekli Veriyi Giriniz!")
            uyari.setPositiveButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
            uyari.show()
            }

            // birinci ve ikinci parola bilgisi editText'lerine girilen değerlerin eşit olmaması durumunda yapılacak işlemler;
            else if (parolaa != parolaa2){

                uyari.setTitle("Parola Hatası!")
                uyari.setMessage("Girdiğiniz Parolalar Aynı Değil!")
                uyari.setPositiveButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
                uyari.show()
            }

            // "bilgiler" isimli sharedPreferences klasörüde daha önce kayıtlı verilerle, girilen verilerin eşit olması durumunda yapılacak işlemler;
            else if (ad1 == kayitliAd && parolaa == kayitliParola){
                uyari.setTitle("Kayıt Hatası")
                uyari.setMessage("Zaten Böyle Bir Hesap Mevcut")
                uyari.setPositiveButton("GİRİŞ YAP",DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                })
                uyari.setNeutralButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
                uyari.show()
            }

            // yazılan tüm kriterlere takılmadığı takdirde yapılacak işlemler;
            else{
                // "bilgiler" isimli kayıt alanına anahtar kelimelere sahip veri kaydetmek için;
                sharedPreferences.edit().putString("ad",ad1).apply()
                sharedPreferences.edit().putString("parola",parolaa).apply()

                Toast.makeText(this@KayitEkrani,"Kayıt Başarılı",Toast.LENGTH_SHORT).show()

                // belirtilen editText metin kutularının içinde ki tüm verileri silmek için;
                binding.kayitAd.text.clear()
                binding.kayitParola.text.clear()
                binding.kayitParola2.text.clear()

            }


        }
        // giriş ekranına geçmek için giriş butonuna basınca yapılacak olan işlemler;
        binding.giris2.setOnClickListener {
            val intent = Intent(applicationContext,MainActivity::class.java)
            startActivity(intent)
        }
        // cıkış butonuna basınca yapılacak işlemler;
        binding.cikis2.setOnClickListener {
            finish()
            Toast.makeText(this@KayitEkrani,"Çıkış Yapıldı",Toast.LENGTH_SHORT).show()
        }




    }
}