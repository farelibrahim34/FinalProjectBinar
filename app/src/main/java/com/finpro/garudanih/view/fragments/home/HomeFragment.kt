package com.finpro.garudanih.view.fragments.home

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.finpro.garudanih.R
import com.finpro.garudanih.adapter.AdapterInternasional
import com.finpro.garudanih.adapter.AdapterTiket
import com.finpro.garudanih.adapter.ViewPagerFragmentAdapter
import com.finpro.garudanih.databinding.FragmentHomeBinding
import com.finpro.garudanih.databinding.ItemBinding
import com.finpro.garudanih.model.ListInternasional
import com.finpro.garudanih.model.ListPesawat
import com.finpro.garudanih.model.Ticket
import com.finpro.garudanih.view.HomeBottomActivity
import com.finpro.garudanih.view.detils.DetailInternasionalActivity

import com.finpro.garudanih.view.profile.ProfileActivity
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeOne
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeThree
import com.finpro.garudanih.view.wrapper.home.FragmentVpHomeTwo
import com.finpro.garudanih.viewmodel.AuthViewModel
import com.finpro.garudanih.viewmodel.TiketViewModel
import com.finpro.garudanih.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3
import java.io.File

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private val data = mutableListOf<String>()
    private var fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3

    lateinit var viewModelListTiket : TiketViewModel
    lateinit var authViewModel : AuthViewModel
    lateinit var userViewModel : UserViewModel
    lateinit var tiketAdapter : AdapterTiket



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


        getProfile()
        setUsername()


        binding.ivUser.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }

        val listInt = arrayListOf(
            ListInternasional("China",2000000,"16 Agustus","24/100",R.drawable.ic_logogn,"pending"),
            ListInternasional("Malaysia",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),
            ListInternasional("Thailand",2000000,"16 Agustus","24/100",R.drawable.jakarta,"pending"),
            ListInternasional("Singapura",2000000,"16 Agustus","24/100",R.drawable.pesawat,"pending"),

            )
        binding.rvInternational.adapter = AdapterInternasional(listInt)
        binding.rvInternational.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)

        bannerHome()
        getDatafoto()
        setUpTiketInt()

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
<<<<<<< HEAD
                binding.rvLocal.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
=======
                binding.rvLocal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
>>>>>>> dev-ibrahim
                tiketAdapter = AdapterTiket(it.data.tickets)
                binding.rvLocal.adapter = tiketAdapter
                Toast.makeText(requireActivity(), "Data Tampil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireActivity(), "Data Tidak Tampil", Toast.LENGTH_SHORT).show()
            }
        }
        viewModel.CallApiTiket()
    }
}


