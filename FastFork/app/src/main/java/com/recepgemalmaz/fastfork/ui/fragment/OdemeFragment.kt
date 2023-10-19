package com.recepgemalmaz.fastfork.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.recepgemalmaz.fastfork.R

import androidx.navigation.Navigation
import com.recepgemalmaz.fastfork.databinding.FragmentOdemeBinding
import com.recepgemalmaz.fastfork.util.gecisYap


class OdemeFragment : Fragment() {

    private lateinit var binding: FragmentOdemeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_odeme, container, false)
        binding.odemeFragment = this

        return binding.root

    }

    fun gecisYap(v:View){
        val gecis = OdemeFragmentDirections.tekrarSiparis()
        Navigation.findNavController(v).navigate(gecis)
    }
}
