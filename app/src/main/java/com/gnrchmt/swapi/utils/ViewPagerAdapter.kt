package com.gnrchmt.swapi.utils

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.gnrchmt.swapi.feature.main.view.ListFragment
import com.gnrchmt.swapi.model.ServiceModel

class ViewPagerAdapter(activity: AppCompatActivity, val list: ArrayList<ServiceModel>): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = list.size

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        bundle.putParcelable("model", list[position])
        return newFragmentInstance(ListFragment::class.java, bundle)
    }
}