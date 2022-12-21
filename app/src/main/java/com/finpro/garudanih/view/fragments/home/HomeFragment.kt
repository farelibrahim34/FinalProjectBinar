package com.finpro.garudanih.view.fragments.home


import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.finpro.garudanih.adapter.AdapterInternasional
import com.finpro.garudanih.adapter.AdapterTiket
import com.finpro.garudanih.adapter.ViewPagerFragmentAdapter
import com.finpro.garudanih.databinding.FragmentHomeBinding
import com.finpro.garudanih.view.detils.DetailInternasionalActivity
import com.finpro.garudanih.view.detils.DetailPesawatActivity
import com.finpro.garudanih.view.detils.TiketInternasionalActivity
import com.finpro.garudanih.view.detils.TiketLokalActivity
import com.finpro.garudanih.view.profile.ProfileActivity
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeOne
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeThree
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeTwo
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.TiketViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
import com.finpro.garudanih.wishlist.WishlistActivity
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3
import java.io.File

@Suppress("RedundantNullableReturnType", "MemberVisibilityCanBePrivate", "ReplaceGetOrSet",
    "ConvertToStringTemplate", "unused"
)
@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val data = mutableListOf<String>()
    private var fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3
    lateinit var authViewModel : AuthViewModel
    lateinit var userViewModel : UserViewModel
    lateinit var tiketAdapter : AdapterTiket
    lateinit var adapterIntr : AdapterInternasional

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        throw RuntimeException("Test Crash")

        binding.showLocal.setOnClickListener {
            startActivity(Intent(context, TiketLokalActivity::class.java))
        }
        binding.showInternational.setOnClickListener {
            startActivity(Intent(context, TiketInternasionalActivity::class.java))
        }
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        authViewModel.getToken().observe(viewLifecycleOwner){
            if (it != null){
                userViewModel.currentUser("Bearer $it")
            }else{
                Log.d("TOKEN","Token Null")
            }
        }

        binding.wishlist.setOnClickListener {
            startActivity(Intent(context, WishlistActivity::class.java))
        }
        tiketAdapter = AdapterTiket {
            val pindah = Intent(context?.applicationContext, DetailPesawatActivity::class.java)
            pindah.putExtra("lokal", it)
            pindah.putExtra(DetailPesawatActivity.EXTRA_ID, it.id)
            startActivity(pindah)
        }
        adapterIntr = AdapterInternasional {
            val pindah = Intent(context?.applicationContext, DetailInternasionalActivity::class.java)
            pindah.putExtra("detail", it)
            pindah.putExtra(DetailInternasionalActivity.EXTRA_ID, it.id)
            startActivity(pindah)
        }


        getProfile()
        setUsername()


        binding.ivUser.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }


        bannerHome()
        getDatafoto()
        setUpTiketInt()
        setTiketIntr()
        setFotoProfile()

    }
    fun setFotoProfile(){
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)
        userViewModel.getCurrentObserve().observe(viewLifecycleOwner){
            if (it != null){
                val url = it.image
                Glide.with(this).load(url).circleCrop().into(binding.ivUser)
            }else{
                Log.d("PROFILE","Profile Null")
            }
        }
    }


    fun bannerHome(){
        castView()

        fragmentList.add(FragmentVpHomeOne())
        fragmentList.add(FragmentVpHomeTwo())
        fragmentList.add(FragmentVpHomeThree())

        viewPager.adapter = ViewPagerFragmentAdapter(requireActivity(),fragmentList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        indicator.setViewPager(viewPager)

    }

    private fun castView() {
        viewPager = binding.viewPagerHome
        indicator = binding.indicatorBanner
    }

    private fun addToList() {
        for (item in 1..3) {
            data.add("item $item")
        }
    }

    fun getDatafoto() {
        val image =
            BitmapFactory.decodeFile(requireActivity().applicationContext.filesDir.path + File.separator + "dataFoto" + File.separator + "fotoProfile.png")
        binding.ivUser.setImageBitmap(image)

    }
    private fun getProfile(){
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        authViewModel.getToken().observe(requireActivity()){
            if (it != null){
                userViewModel.currentUser("Bearer $it")
            }else{
                Log.d("TOKEN","Token Null")
            }
        }
    }
    @SuppressLint("SetTextI18n")
    private fun setUsername(){
        authViewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        userViewModel = ViewModelProvider(requireActivity()).get(UserViewModel::class.java)


        userViewModel.getCurrentObserve().observe(requireActivity()){
            if (it != null){
                binding.txtUsername.text = ("Hello, "+it.name)
            }else{
                Log.d("PROFILE","Profile Null")
            }
        }

        authViewModel.getUser().observe(requireActivity()){
            if (it != null){
                binding.txtUsername.text = ("Hello, "+it)
            }
        }
    }


    private fun setUpTiketInt(){
        val viewModel = ViewModelProvider(requireActivity()).get(TiketViewModel::class.java)
        viewModel.getLdTiket().observe(viewLifecycleOwner) {
            binding.homeProgressBar.visibility = View.VISIBLE
            if (it != null) {
                binding.homeProgressBar.visibility = View.GONE
                binding.rvLocal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                tiketAdapter.setListTiket(it.data.tickets)
                binding.rvLocal.adapter = tiketAdapter
            } else {
                binding.homeProgressBar.visibility = View.VISIBLE
                Toast.makeText(requireActivity(), "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.CallApiTiket()
    }
    private fun setTiketIntr(){
        val viewModel = ViewModelProvider(requireActivity()).get(TiketViewModel::class.java)
        viewModel.getLdTiketIntr().observe(viewLifecycleOwner){
            binding.homeProgressBar.visibility = View.GONE
            if (it != null){
                binding.homeProgressBar.visibility = View.GONE
                binding.rvInternational.layoutManager= LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                adapterIntr.setListTiketInter(it.data.tickets)
                binding.rvInternational.adapter = adapterIntr
            }else{
                binding.homeProgressBar.visibility = View.VISIBLE
                Toast.makeText(requireActivity(), "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.callApiTiketIntr()
    }
}


