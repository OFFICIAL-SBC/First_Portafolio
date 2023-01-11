package com.example.countriesapp.presentation.fragments.tabubication

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle):FragmentStateAdapter(fm,lifecycle) {

    private val mFragmentList= ArrayList<Fragment>()

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return mFragmentList[position]
    }

    fun addFragment(fragment: Fragment){
        mFragmentList.add(fragment)
    }

}