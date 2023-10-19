package com.recepgemalmaz.fastfork.ui.fragment


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.recepgemalmaz.fastfork.R
import com.recepgemalmaz.fastfork.databinding.FragmentSepetBinding
import com.recepgemalmaz.fastfork.ui.adapter.SepetAdapter
import com.recepgemalmaz.fastfork.ui.viewmodel.SepetFragmentViewModel
import com.recepgemalmaz.fastfork.util.gecisYap

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SepetFragment : Fragment() {
    private lateinit var binding : FragmentSepetBinding
    private lateinit var viewModel : SepetFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_sepet, container, false)
        binding.sepetFragment = this

        binding.sepetToolbarBaslik2 = "Sepet"
        binding.sepetToolbarBaslik = ""

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarSepet)



        viewModel.toplamSepet.observe(viewLifecycleOwner){
            binding.textViewToplam.text = "Toplam: ${it} ₺"
        }

        viewModel.sepetListesi.observe(viewLifecycleOwner){
            val adapter = SepetAdapter(requireContext(),it,viewModel)
            binding.sepetAdapter = adapter
            adapter.hesaplaToplamFiyat()
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : SepetFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun btnTikla(v:View){
        //odeme ekranina gecis
        val gecis = SepetFragmentDirections.odemeGecis()
        Navigation.gecisYap(v,gecis)
    }

    override fun onResume() {
        super.onResume()
        viewModel.sepetiYukle()
    }



}