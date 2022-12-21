package com.finpro.garudanih.view.wrapper

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.finpro.garudanih.adapter.ViewPagerFragmentAdapter
import com.finpro.garudanih.databinding.ActivityViewpagerBinding
import dagger.hilt.android.AndroidEntryPoint
import me.relex.circleindicator.CircleIndicator3

@Suppress("unused")
@AndroidEntryPoint
class ViewpagerActivity : AppCompatActivity() {



    private lateinit var binding : ActivityViewpagerBinding

    private val data = mutableListOf<String>()
    private var fragmentList = ArrayList<Fragment>()
    private lateinit var viewPager: ViewPager2
    private lateinit var indicator: CircleIndicator3
    private lateinit var btnNext: Button
    private lateinit var btnBack: Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewpagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
        addToList()
        fragmentList.add(fragment_one())
        fragmentList.add(fragment_two())
        fragmentList.add(fragment_three())

        // viewPager.adapter = ViewPagerAdapter(data, this)
        viewPager.adapter = ViewPagerFragmentAdapter(this, fragmentList)
        viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL

        indicator.setViewPager(viewPager)

//        binding.btnNext.setOnClickListener {
//            viewPager.apply {
//                beginFakeDrag()
//                fakeDragBy(-10f)
//                endFakeDrag()
//            }
//        }

//        binding.btnBack.setOnClickListener {
//            viewPager.apply {
//                beginFakeDrag()
//                fakeDragBy(10f)
//                endFakeDrag()
//            }
//        }
    }

    private fun castView() {
        viewPager = binding.viewPager2
        indicator = binding.indicator
//        btnNext = binding.btnNext
//        btnBack = binding.btnBack

    }

    private fun addToList() {
        for (item in 1..3) {
            data.add("item $item")
        }
    }


}


