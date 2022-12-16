package com.finpro.garudanih.wishlist.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.finpro.garudanih.adapter.AdapterWishListInternasional
import com.finpro.garudanih.databinding.FragmentInternatonalTablayoutBinding
import com.finpro.garudanih.wishlistinternasional.DatabaseWishPesawatInternasional
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class InternatonalTablayoutFragment : Fragment() {

    private lateinit var binding : FragmentInternatonalTablayoutBinding
    private var databaseWishPesawatInternasional : DatabaseWishPesawatInternasional? = null


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
    }

//    private fun swipeToDelete(recyclerView: RecyclerView) {
//        val swipeCallback = object : SwipeToDelete() {
//            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//                val item = todoAdapter.Wishlistinternasional[viewHolder.adapterPosition]
//                wishpesawatDaoInternasional?.deleteWishInter(item)
//                restoreData(viewHolder.itemView, item)
//            }
//        }
//        val itemTouchHelper = ItemTouchHelper(swipeCallback)
//        itemTouchHelper.attachToRecyclerView(recyclerView)
//    }
//
//    private fun restoreData(
//        view: View,
//    ) {
//        Snackbar.make(view, "Deleted ", Snackbar.LENGTH_LONG).also {
//            it.apply {
//                setAction("Undo") {
//                    wishpesawatDaoInternasional?.addToWishListInternasional(dataWishPesawatInternasional(0,"",""))
//                }
//                show()
//            }
//        }
//    }
}
