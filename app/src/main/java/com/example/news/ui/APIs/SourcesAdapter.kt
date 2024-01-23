package com.example.news.ui.APIs

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.news.R
import com.example.news.databinding.SourcesModelBinding
import com.example.news.ui.ui.Categories
import com.example.news.ui.ui.Categories.Companion.category
import com.example.news.ui.ui.Categories.Companion.color



class SourcesAdapter(supportFragmentManager: FragmentManager) :
    FragmentStatePagerAdapter(supportFragmentManager) {
    var tabs=ArrayList<Pair<Fragment,String>>()
    override fun getCount(): Int {
       return tabs.size
    }
    override fun getItem(position: Int): Fragment {
        //return particular fragment
        return tabs[position].first
    }
    override fun getPageTitle(position: Int): CharSequence{
        // return title of the tab
        return tabs[position].second
    }
    fun addFragment(fragment: Fragment, title: String) {
        // add each fragment and its title to the array list
       tabs.add(Pair(fragment,title))
    }
}