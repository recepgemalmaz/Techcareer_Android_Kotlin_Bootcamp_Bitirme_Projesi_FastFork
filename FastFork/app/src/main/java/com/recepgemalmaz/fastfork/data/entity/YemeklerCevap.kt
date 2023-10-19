package com.recepgemalmaz.fastfork.data.entity

import com.google.gson.annotations.SerializedName

data class YemeklerCevap(@SerializedName("yemekler") var yemekler:List<Yemekler>,
                         @SerializedName("success") var success:Int) {
}