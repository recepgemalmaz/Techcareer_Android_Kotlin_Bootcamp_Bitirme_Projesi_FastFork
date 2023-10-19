package com.recepgemalmaz.fastfork.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.recepgemalmaz.fastfork.R
import com.recepgemalmaz.fastfork.data.entity.Sepet
import com.recepgemalmaz.fastfork.databinding.SepetCardTasarimBinding
import com.recepgemalmaz.fastfork.ui.viewmodel.SepetFragmentViewModel
import com.squareup.picasso.Picasso

class SepetAdapter(
    private val context: Context,
    private val sepetListesi: List<Sepet>,
    private val viewModel: SepetFragmentViewModel
) : RecyclerView.Adapter<SepetAdapter.SepetCardTasarimTutucu>() {

    inner class SepetCardTasarimTutucu(val binding: SepetCardTasarimBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SepetCardTasarimTutucu {
        val inflater = LayoutInflater.from(context)
        val binding: SepetCardTasarimBinding =
            DataBindingUtil.inflate(inflater, R.layout.sepet_card_tasarim, parent, false)
        return SepetCardTasarimTutucu(binding)
    }

    override fun onBindViewHolder(holder: SepetCardTasarimTutucu, position: Int) {
        val sepet = sepetListesi[position]
        val binding = holder.binding

        with(binding) {
            tvSepetYemekAdi.text = sepet.yemek_adi
            tvSepetYemekFiyati.text = "${sepet.yemek_fiyat * sepet.yemek_siparis_adet} ₺"
            tvSepetYemekAdet.text = sepet.yemek_siparis_adet.toString()

            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${sepet.yemek_resim_adi}"
            Picasso.get().load(url).into(ivSepetYemekResim)

            ivSilResim.setOnClickListener {
                val message = "${sepet.yemek_adi} sepetten çıkarılsın mı?"
                Snackbar.make(it, message, Snackbar.LENGTH_LONG)
                    .setAction("EVET") {

                        viewModel.sil(sepet.sepet_yemek_id, sepet.kullanici_adi)
                        viewModel.sepetiYukle()
                    }.show()
            }

            btnSepetEksi.setOnClickListener {
                if (sepet.yemek_siparis_adet > 1) {
                    sepet.yemek_siparis_adet--
                    updateItem(sepet)
                }
            }

            btnSepetArti.setOnClickListener {
                sepet.yemek_siparis_adet++
                updateItem(sepet)
            }
        }
    }

    private fun updateItem(sepet: Sepet) {
        viewModel.toplamSepet.value =
            (viewModel.toplamSepet.value!!.toInt() + sepet.yemek_fiyat).toString()
        buttonSepeteEkleTikla(
            sepet.yemek_adi,
            sepet.yemek_resim_adi,
            sepet.yemek_fiyat,
            sepet.yemek_siparis_adet,
            "RecepGemalmaz"
        )
        viewModel.sil(sepet.sepet_yemek_id, sepet.kullanici_adi)
        viewModel.sepetiYukle()
    }

    override fun getItemCount(): Int = sepetListesi.size

    private fun buttonSepeteEkleTikla(
        yemek_adi: String,
        yemek_resim_adi: String,
        yemek_fiyat: Int,
        yemek_siparis_adet: Int,
        kullanici_adi: String
    ) {
        viewModel.sepeteEkle(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi)
    }

    fun hesaplaToplamFiyat() {
        var toplam = 0
        for (sepet in sepetListesi) {
            toplam +=  sepet.yemek_fiyat * sepet.yemek_siparis_adet
        }
        viewModel.toplamSepet.value = toplam.toString()
    }
}
