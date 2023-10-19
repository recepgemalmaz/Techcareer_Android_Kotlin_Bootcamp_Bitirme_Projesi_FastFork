package com.recepgemalmaz.fastfork.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.recepgemalmaz.fastfork.data.repo.YemeklerDaoRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekDetayFragmentViewModel @Inject constructor (var yrepo: YemeklerDaoRepository) : ViewModel() {

    fun sepeteEkle(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.yemekSepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
        }
    }
}