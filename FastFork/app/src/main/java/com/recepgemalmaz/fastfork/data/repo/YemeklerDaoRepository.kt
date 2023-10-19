package com.recepgemalmaz.fastfork.data.repo

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.recepgemalmaz.fastfork.data.entity.CRUDCevap
import com.recepgemalmaz.fastfork.data.entity.Sepet
import com.recepgemalmaz.fastfork.data.entity.SepetCevap
import com.recepgemalmaz.fastfork.data.entity.Yemekler
import com.recepgemalmaz.fastfork.data.entity.YemeklerCevap
import com.recepgemalmaz.fastfork.retrofit.YemeklerDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class YemeklerDaoRepository(var ydao: YemeklerDao) {
    var yemeklerListesi : MutableLiveData<List<Yemekler>>
    var sepetListesi : MutableLiveData<List<Sepet>>

    init {
        yemeklerListesi = MutableLiveData()
        sepetListesi = MutableLiveData()
    }

    suspend fun yemekleriGetir() : MutableLiveData<List<Yemekler>> {
        return yemeklerListesi
    }

    suspend fun sepetiGetir() : MutableLiveData<List<Sepet>> {
        return sepetListesi
    }

    suspend fun yemekSepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        ydao.yemekEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi).enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body()!!.success
                val mesaj = response.body()!!.message
                Log.e("Yemek sepete ekle", "$basari - $mesaj")
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }

    suspend fun tumYemekleriAl() {
        ydao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body()!!.yemekler
                yemeklerListesi.value = liste

            }
            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {}
        })
    }

    suspend fun sepetYemekSil(sepet_yemek_id:Int,kullanici_adi:String){
        ydao.sepettenYemekSil(sepet_yemek_id,"RecepGemalmaz").enqueue(object : Callback<CRUDCevap>{
            override fun onResponse(call: Call<CRUDCevap>?, response: Response<CRUDCevap>) {
                val basari = response.body()!!.success
                val mesaj = response.body()!!.message
                Log.e("Yemek sepete ekle", "$basari - $mesaj")
                CoroutineScope(Dispatchers.IO).launch {
                    tumSepetiGoster()
                }
            }

            override fun onFailure(call: Call<CRUDCevap>, t: Throwable) {}
        })
    }

    suspend fun tumSepetiGoster(){
        ydao.sepettekiYemekler("RecepGemalmaz").enqueue(object:Callback<SepetCevap>{
            override fun onResponse(call: Call<SepetCevap>?, response: Response<SepetCevap>) {
                val liste = response.body()!!.sepet_yemekler
                sepetListesi.value = liste
            }

            override fun onFailure(call: Call<SepetCevap>, t: Throwable) {}
        })
    }

    fun yemekAra(){
        ydao.tumYemekler().enqueue(object:Callback<YemeklerCevap>{
            override fun onResponse(call: Call<YemeklerCevap>?, response: Response<YemeklerCevap>) {
                val liste = response.body()!!.yemekler
                yemeklerListesi.value = liste

            }
            override fun onFailure(call: Call<YemeklerCevap>, t: Throwable) {}
        })
    }
}