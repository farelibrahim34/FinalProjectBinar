package com.finpro.garudanih.wishlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.adapter.AdapterWishListInternasional
import com.finpro.garudanih.databinding.FragmentInternatonalTablayoutBinding
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import com.finpro.garudanih.wishlistinternasional.WishpesawatDaoInternasional
import com.finpro.garudanih.wishlistinternasional.dataWishPesawatInternasional
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class InternatonalTablayoutFragment : Fragment() {

    companion object{
        val DELETE_WISHLIST = 0
    }


    private lateinit var binding : FragmentInternatonalTablayoutBinding
    private var databaseWishPesawatInternasional : DatabaseWishPesawatInternasional? = null
    private lateinit var  onDelete : WishpesawatDaoInternasional
    private lateinit var adapter : AdapterWishListInternasional


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInternatonalTablayoutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseWishPesawatInternasional = DatabaseWishPesawatInternasional.getInstance(requireActivity())
        getWishListInter()

    }
    private fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getWishListInter(){
        binding.rvInternational.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        GlobalScope.launch {
            val wishListInter = databaseWishPesawatInternasional?.WishInternasionalDao()?.getWishPesawatInternasional()
            runOnUiThread {
                wishListInter.let {
                    val adap = AdapterWishListInternasional(it!!)
                    binding.rvInternational.adapter = adap
                }
            }
        }
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                    onDelete.deleteWishInter(adapter.getWishlist(viewHolder.layoutPosition))
                Toast.makeText(requireActivity(),"Berhasil dihapus",Toast.LENGTH_SHORT).show()
            }
        }).attachToRecyclerView(binding.rvInternational)
    }
}
