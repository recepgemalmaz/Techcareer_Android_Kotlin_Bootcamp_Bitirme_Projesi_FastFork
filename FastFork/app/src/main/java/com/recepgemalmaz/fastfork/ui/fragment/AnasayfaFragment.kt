package com.recepgemalmaz.fastfork.ui.fragment



import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.recepgemalmaz.fastfork.R
import com.recepgemalmaz.fastfork.data.entity.BannerResimler
import com.recepgemalmaz.fastfork.databinding.FragmentAnasayfaBinding
import com.recepgemalmaz.fastfork.ui.adapter.BannerResimAdapter
import com.recepgemalmaz.fastfork.ui.viewmodel.AnasayfaFragmentViewModel
import com.recepgemalmaz.fastfork.ui.viewmodel.YemekListeFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding : FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaFragmentViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa,container,false)


        binding.anasayfaFragment = this

        binding.rvBannerResimler.layoutManager = StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.HORIZONTAL)

        binding.toolbarBaslik = "FastFork"


        viewModel.bannerResimYukle()
        val adapter = BannerResimAdapter(requireContext(),viewModel.bannerResimListe)
        binding.rvBannerResimler.adapter = adapter




        return binding.root

    }

    fun gecisYap(v:View){
        val gecis = AnasayfaFragmentDirections.anaSayfaYemekListeGecis()
        Navigation.findNavController(v).navigate(gecis)
    }


    /*
    fun bannerResimleriYukle(bannerResimListe: ArrayList<BannerResimler>) {
        val r1 = BannerResimler(1, "cv1")
        val r2 = BannerResimler(2, "cv2")
        val r3 = BannerResimler(3, "cv3")
        val r4 = BannerResimler(4, "cv4")
        val r5 = BannerResimler(5, "cv5")

        bannerResimListe.add(r1)
        bannerResimListe.add(r2)
        bannerResimListe.add(r3)
        bannerResimListe.add(r4)
        bannerResimListe.add(r5)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AnasayfaFragmentViewModel by viewModels()
        viewModel = tempViewModel
    }






}