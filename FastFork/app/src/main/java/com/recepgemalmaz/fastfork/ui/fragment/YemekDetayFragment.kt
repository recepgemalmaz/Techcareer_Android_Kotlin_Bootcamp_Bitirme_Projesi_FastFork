package com.recepgemalmaz.fastfork.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.recepgemalmaz.fastfork.R


import com.recepgemalmaz.fastfork.databinding.FragmentYemekDetayBinding
import com.recepgemalmaz.fastfork.ui.viewmodel.YemekDetayFragmentViewModel
import com.recepgemalmaz.fastfork.util.gecisYap

import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekDetayFragment : Fragment() {
    private lateinit var binding : FragmentYemekDetayBinding
    private lateinit var viewModel : YemekDetayFragmentViewModel
    private var adet = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_detay, container, false)
        binding.yemekDetayFragment = this
        binding.yemekDetayToolbarBaslik = "Yemek Detay"
        binding.yemekAdet = adet

        val bundle : YemekDetayFragmentArgs by navArgs()
        val gelenYemek = bundle.yemek
        binding.yemekNesnesi = gelenYemek

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${gelenYemek.yemek_resim_adi}"
        Picasso.get().load(url).into(binding.ivYemekGorsel)

        //binding.tvYemekAdi.text = gelenYemek.yemek_adi
        //binding.tvYemekFiyat.text = "${gelenYemek.yemek_fiyat} ₺"
        //binding.tvYemekAdet.text = "${adet}"



       /*binding.btnEksi.setOnClickListener {
            if(adet>1){
                adet--
                binding.tvYemekAdet.text = "${adet}"
                binding.tvYemekFiyat.text = "${adet * gelenYemek.yemek_fiyat} ₺"
            }
        }

        binding.btnArti.setOnClickListener {
            adet++
            binding.tvYemekAdet.text = "${adet}"
            binding.tvYemekFiyat.text = "${adet * gelenYemek.yemek_fiyat} ₺"
        }

        binding.btnSepeteEkle.setOnClickListener {
            buttonSepeteEkleTikla(gelenYemek.yemek_adi, gelenYemek.yemek_resim_adi, gelenYemek.yemek_fiyat, adet,"RecepGemalmaz")
        }

        binding.backButton.setOnClickListener {
            //activity?.onBackPressed()
            val gecis = YemekDetayFragmentDirections.detaydanYemekListeyeGecis()
            Navigation.gecisYap(it,gecis)
        }*/
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val temptViewModel : YemekDetayFragmentViewModel by viewModels()
        viewModel = temptViewModel
    }

    fun buttonSepeteEkleTikla(yemek_adi:String, yemek_resim_adi:String, yemek_fiyat:Int, yemek_siparis_adet:Int, kullanici_adi:String){
        viewModel.sepeteEkle(yemek_adi,yemek_resim_adi,yemek_fiyat,yemek_siparis_adet,kullanici_adi)
    }

    fun btnEksi(){
        if(adet>1){
            adet--
            binding.yemekAdet = adet
            binding.tvYemekFiyat.text = "${adet * binding.yemekNesnesi!!.yemek_fiyat} ₺"
        }
    }
    fun btnAtri(){
        adet++
        binding.yemekAdet = adet
        binding.tvYemekFiyat.text = "${adet * binding.yemekNesnesi!!.yemek_fiyat} ₺"
    }

    fun gecisYap(v:View){
        val gecis = YemekDetayFragmentDirections.detaydanYemekListeyeGecis()
        Navigation.gecisYap(v,gecis)
    }
}