package com.recepgemalmaz.fastfork.ui.fragment


import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.recepgemalmaz.fastfork.R
import com.recepgemalmaz.fastfork.databinding.FragmentYemekListeBinding
import com.recepgemalmaz.fastfork.ui.adapter.YemeklerAdapter
import com.recepgemalmaz.fastfork.ui.viewmodel.YemekListeFragmentViewModel
import com.recepgemalmaz.fastfork.util.gecisYap

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class YemekListeFragment : Fragment() {
    private lateinit var binding : FragmentYemekListeBinding
    private lateinit var viewModel : YemekListeFragmentViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_yemek_liste, container, false)
        binding.yemekListeFragment = this
        binding.yemekListeToolbarBaslik = "      Yemekler Listesi"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarAnasayfa)

        viewModel.yemeklerListesi.observe(viewLifecycleOwner){
            val adapter = YemeklerAdapter(requireContext(),it,viewModel)
            binding.yemeklerAdapter = adapter
        }


        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : YemekListeFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }

    fun gecisYap(v:View){
        val gecis = YemekListeFragmentDirections.actionYemekListeFragmentToAnasayfaFragment()
        Navigation.gecisYap(v,gecis)
    }

    






}