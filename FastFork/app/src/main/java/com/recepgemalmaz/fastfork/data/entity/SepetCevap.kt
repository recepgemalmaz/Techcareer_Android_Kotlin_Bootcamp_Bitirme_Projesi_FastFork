package com.recepgemalmaz.fastfork.data.entity

import com.google.gson.annotations.SerializedName

data class SepetCevap (@SerializedName("sepet_yemekler") var sepet_yemekler:List<Sepet>,
                       @SerializedName("success") var success:Int) {
}