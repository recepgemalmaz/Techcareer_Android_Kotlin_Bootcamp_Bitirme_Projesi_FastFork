package com.recepgemalmaz.fastfork.ui.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recepgemalmaz.fastfork.data.entity.Sepet
import com.recepgemalmaz.fastfork.data.repo.YemeklerDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SepetFragmentViewModel @Inject constructor (var yrepo: YemeklerDaoRepository) : ViewModel() {

    var toplamSepet = MutableLiveData("0")
    var sepetListesi = MutableLiveData<List<Sepet>>()

    init {
        sepetiYukle()
        CoroutineScope(Dispatchers.Main).launch {
            sepetListesi = yrepo.sepetiGetir()
        }

    }

    fun sil(sepet_yemek_id:Int,kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.sepetYemekSil(sepet_yemek_id,kullanici_adi)
        }
    }

    fun sepetiYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.tumSepetiGoster()
        }
    }
    fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){

        CoroutineScope(Dispatchers.Main).launch {
            yrepo.yemekSepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        }
    }

}