package com.recepgemalmaz.fastfork.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.recepgemalmaz.fastfork.data.entity.BannerResimler

class AnasayfaFragmentViewModel : ViewModel() {

    val bannerResimListe = ArrayList<BannerResimler>()

    fun bannerResimYukle(){
        bannerResimListe.add(BannerResimler(1, "cv1"))
        bannerResimListe.add(BannerResimler(2, "cv2"))
        bannerResimListe.add(BannerResimler(3, "cv3"))
        bannerResimListe.add(BannerResimler(4, "cv4"))
        bannerResimListe.add(BannerResimler(5, "cv5"))
    }


}