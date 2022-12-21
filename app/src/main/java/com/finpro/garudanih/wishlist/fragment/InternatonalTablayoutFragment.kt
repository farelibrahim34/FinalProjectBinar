package com.finpro.garudanih.wishlist.fragment

import android.icu.lang.UCharacter.IndicPositionalCategory.LEFT
import android.os.Bundle
import android.util.Log
import android.view.Gravity.LEFT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.adapter.AdapterWishListInternasional
import com.finpro.garudanih.databinding.FragmentInternatonalTablayoutBinding
import com.finpro.garudanih.helper.SwipeToDeleteCallback
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import com.finpro.garudanih.wishlistinternasional.WishpesawatDaoInternasional
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val TAG = "InternatonalTablayoutFr"
class InternatonalTablayoutFragment : Fragment() {

    private lateinit var binding : FragmentInternatonalTablayoutBinding
    private var databaseWishPesawatInternasional : DatabaseWishPesawatInternasional? = null
    private val adapter : AdapterWishListInternasional = AdapterWishListInternasional()


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
        databaseWishPesawatInternasional = DatabaseWishPesawatInternasional.getInstance(requireContext())
        binding.rvInternational.adapter = adapter
        binding.rvInternational.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        getWishListInter()
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                val dataDelete = adapter.deleteInternational(adapter.getWishlist(position), position)
                getWishListInter()

                Log.d(TAG, "onSwiped: ${dataDelete}")

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvInternational)
    }
    private fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun getWishListInter(){
        GlobalScope.launch {
            val wishListInter = databaseWishPesawatInternasional?.WishInternasionalDao()?.getWishPesawatInternasional()
            runOnUiThread {
                wishListInter.let {
                    adapter.setListWishlist(it!!)
                }
            }
        }

    }
}
