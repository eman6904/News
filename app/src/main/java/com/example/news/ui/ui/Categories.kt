package com.example.news.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentCategoriesBinding
import com.example.news.databinding.FragmentFaceBinding
import com.example.news.ui.ui.Home.Companion.categoryName


class Categories : Fragment(R.layout.fragment_categories) {
    private lateinit var binding: FragmentCategoriesBinding
    private lateinit var navController: NavController
    companion object{
        var category=""
        var color=""
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCategoriesBinding.bind(view)
        navController = Navigation.findNavController(view)

        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        setColor()
        binding.sports.setOnClickListener()
        {
            binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            color="#2E7D32"
            category=getString(R.string.sports)
            categoryName="sports"
            navController.navigate(R.id.action_categories_to_home2)
        }
        binding.business.setOnClickListener()
        {
            binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            category=getString(R.string.business)
            color="#F9A825"
            categoryName="business"
            navController.navigate(R.id.action_categories_to_home2)
        }
        binding.science.setOnClickListener()
        {

            binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            category=getString(R.string.science)
            color="#283593"
            categoryName="science"
            navController.navigate(R.id.action_categories_to_home2)
        }
        binding.technology.setOnClickListener()
        {
            binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
            category=getString(R.string.technology)
            color="#0277BD"
            categoryName="technology"
            navController.navigate(R.id.action_categories_to_home2)
        }

    }
    fun setColor()
    {
        when(Categories.category)
        {
            "Sports"-> binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            "رياضيات"-> binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            "Business"-> binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            "أعمال"-> binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            "Science"->  binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            "علوم"->  binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            "Technology"-> binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
            "تكنولوجي"-> binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
        }

    }
}