package com.recepgemalmaz.fastfork.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.recepgemalmaz.fastfork.data.entity.Yemekler
import com.recepgemalmaz.fastfork.data.repo.YemeklerDaoRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YemekListeFragmentViewModel @Inject constructor (var yrepo: YemeklerDaoRepository ) : ViewModel() {
    var yemeklerListesi = MutableLiveData<List<Yemekler>>()

    init {
        yemekleriYukle()
        CoroutineScope(Dispatchers.Main).launch {
            yemeklerListesi = yrepo.yemekleriGetir()
        }
    }

    fun yemekleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            yrepo.tumYemekleriAl()
        }
    }
}