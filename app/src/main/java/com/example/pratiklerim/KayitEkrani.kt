package com.example.pratiklerim

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.CheckBox
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.pratiklerim.databinding.ActivityKayitEkraniBinding
import kotlinx.android.synthetic.main.activity_kayit_ekrani.*
import kotlinx.android.synthetic.main.activity_main.*

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
        sharedPreferences = this.getSharedPreferences("bilgiler", MODE_PRIVATE)




        // kayıt butonuna basınca yapılacak işlemleri belirlemek için;
        binding.kayit2.setOnClickListener {

            // "bilgiler" isimli sharedPreferenses kayıt klasörüne anahtar kelimelerle verileri çekip değere eşitlemek için;
            val kayitliAd = sharedPreferences.getString("ad","")

            // Uyarı penceresini cağıran fonksiyonu bir değere eşitleme için;
            val uyari = AlertDialog.Builder(this@KayitEkrani)

            // editText metin kutularına girilen verileri değerlere eşitlemek için;
            val ad1 = binding.kayitAd.text.toString()
            val parola = binding.kayitParola.text.toString()
            val parola2 = binding.kayitParola2.text.toString()

            // editText metin kutularına girilen değerlerin index sayısını bir değişkene eşitlemek için;
            val adIndex = ad1.length
            val pIndex = parola.length
            val p2Index = parola2.length

            // editText metin kutularına değer girilmeden butona basılırsa eğer yapılacak işlemler;
            if (ad1 == "" || parola == "" || parola2 == ""){

            uyari.setTitle("Veri Hatası!")
            uyari.setMessage("Lütfen Gerekli Veriyi Giriniz!")
            uyari.setPositiveButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
            uyari.show()
            }

            // "bilgiler" isimli sharedPreferences klasörüde daha önce kayıtlı verilerle, girilen verilerin eşit olması durumunda yapılacak işlemler;
            else if (kayitliAd == ad1){

                uyari.setTitle("Kayıt Hatası")
                uyari.setMessage("Zaten Böyle Bir Hesap Mevcut")
                uyari.setPositiveButton("GİRİŞ YAP",DialogInterface.OnClickListener { dialogInterface, i ->
                    val intent = Intent(applicationContext,MainActivity::class.java)
                    startActivity(intent)
                })
                uyari.setNeutralButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
                uyari.show()
            }


            // Girilen verilerin hane sayısı 5ten küçük ise uyari göstermek için;
            else if (adIndex < 5 || pIndex < 5 || p2Index < 5){
                uyari.setTitle("Veri Hatası")
                uyari.setMessage("En Az 5 Hane Giriniz!")
                uyari.setPositiveButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
                uyari.show()
            }

            // Girilen verilerin hane sayısı 9 dan büyük ise uyari göstermek için;
            else if (adIndex > 9 || p2Index > 9 || pIndex > 9){
                uyari.setTitle("Veri Hatası")
                uyari.setMessage("En Fazla 9 Hane Giriniz!")
                uyari.setPositiveButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
                uyari.show()

            }
            // Kullanıcı adı ve parola aynı ise uyarı ekranı gostermek için;
            else if (ad1 == parola) {
                uyari.setTitle("Giriş Hatası")
                uyari.setMessage("Kullanıcı Adı ve Parola Aynı Olamaz")
                uyari.setPositiveButton(
                    "TAMAM",
                    DialogInterface.OnClickListener { dialogInterface, i -> })
                uyari.show()
            }

            // birinci ve ikinci parola bilgisi editText'lerine girilen değerlerin eşit olmaması durumunda yapılacak işlemler;
            else if (parola != parola2){

                uyari.setTitle("Parola Hatası!")
                uyari.setMessage("Girdiğiniz Parolalar Aynı Değil!")
                uyari.setPositiveButton("TAMAM",DialogInterface.OnClickListener { dialogInterface, i ->  })
                uyari.show()
            }

            // yazılan tüm kriterlere takılmadığı takdirde yapılacak işlemler;
            else{
                // "bilgiler" isimli kayıt alanına anahtar kelimelere sahip veri kaydetmek için;
                sharedPreferences.edit().putString("ad",ad1).apply()
                sharedPreferences.edit().putString("parola",parola).apply()

                Toast.makeText(this@KayitEkrani,"Kayıt Başarılı",Toast.LENGTH_SHORT).show()

                // belirtilen editText metin kutularının içinde ki tüm verileri silmek için;
                binding.kayitAd.text.clear()
                binding.kayitParola.text.clear()
                binding.kayitParola2.text.clear()

            }

        }


        // cıkış butonuna basınca yapılacak işlemler;
        binding.cikis2.setOnClickListener {
            finish()
            Toast.makeText(this@KayitEkrani,"Çıkış Yapıldı",Toast.LENGTH_SHORT).show()
        }




    }
}