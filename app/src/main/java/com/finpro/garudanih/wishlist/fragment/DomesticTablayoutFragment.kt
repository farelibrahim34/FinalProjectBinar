package com.finpro.garudanih.wishlist.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.finpro.garudanih.adapter.AdapterWishListLoc
import com.finpro.garudanih.databinding.FragmentDomesticTablayoutBinding
import com.finpro.garudanih.helper.SwipeToDelete
import com.finpro.garudanih.wishlist.DatabaseWishPesawatLoc
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


private const val TAG = "DomesticTablayoutFr"
@Suppress("MemberVisibilityCanBePrivate", "RedundantNullableReturnType")

class DomesticTablayoutFragment : Fragment() {

    private lateinit var binding : FragmentDomesticTablayoutBinding
    private var databaseWishPesawatLoc : DatabaseWishPesawatLoc? = null
    private val adapter : AdapterWishListLoc = AdapterWishListLoc()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDomesticTablayoutBinding.inflate(layoutInflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        databaseWishPesawatLoc = DatabaseWishPesawatLoc.getInstance(requireContext())
        binding.rvLocal.adapter = adapter
        binding.rvLocal.layoutManager = LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false)
        getWishlistLocal()
        val swipeToDeleteCallback = object : SwipeToDelete(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val dataDelete = adapter.deleteDomestic(adapter.getWishlist(position), position)
                getWishlistLocal()

                Log.d(TAG, "onSwiped: ${dataDelete}")

            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(binding.rvLocal)
    }

    private fun Fragment?.runOnUiThread(action: () -> Unit) {
        this ?: return
        if (!isAdded) return // Fragment not attached to an Activity
        activity?.runOnUiThread(action)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getWishlistLocal(){
        GlobalScope.launch {
            val wishList = databaseWishPesawatLoc?.WishDao()?.getWishPesawat()
            runOnUiThread{
                wishList.let {
                    adapter.setListWishlist(it!!)
                }
            }
        }
    }
}