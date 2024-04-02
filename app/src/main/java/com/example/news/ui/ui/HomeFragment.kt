package com.example.news.ui.ui


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.ui.APIs.*
import com.example.news.ui.ui.CategoriesFragment.Companion.category
import com.example.news.ui.ui.CategoriesFragment.Companion.color
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private val viewModel = ViewModel()
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    companion object {
        var sourceId = ""
        var language = "en"
        var categoryName = ""
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        navController = Navigation.findNavController(view)
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        if (category != "") {
            binding.title.setText(category)
            binding.startImage.isVisible = false
        }

        lifecycleScope.launch {
            setColor()
            actionBarDrawerToggle = ActionBarDrawerToggle(
                activity,
                binding.drawerLayout,
                R.string.nav_open,
                R.string.nav_close
            )

            // pass the Open and Close toggle for the drawer layout listener
            // to toggle the button
            binding.drawerLayout.addDrawerListener(actionBarDrawerToggle)
            actionBarDrawerToggle.syncState()

            // to make the Navigation drawer icon always appear on the action bar
            //activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
            binding.menu.setOnClickListener() {
                binding.drawerLayout.open()
            }
            binding.navigation.setNavigationItemSelectedListener { menuItem ->
                // Handle menu item selected
                when (menuItem.itemId) {
                    R.id.categories -> navController.navigate(R.id.action_home2_to_categories)
                    R.id.search -> navController.navigate(R.id.action_home2_to_search)
                    R.id.settings -> navController.navigate(R.id.action_home2_to_setting)
                }
                menuItem.isChecked = true
                binding.drawerLayout.close()
                true
            }
        }

        lifecycleScope.launch {
            if (category != "") {
                viewModel.getSources(categoryName)
                    .observe(viewLifecycleOwner, Observer { sources ->
                        if (sources.sources.size > 0) {
                            sourceId = sources.sources[0].id.toString()
                            displaySources(sources.sources)
                        } else {
                            Toast.makeText(
                                requireContext(),
                                getString(R.string.search_result),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
        }
    }

    fun displaySources(sourcesList: ArrayList<SourceComponents>) {
        val adapter = SourcesAdapter(activity!!.supportFragmentManager)
        for (item in sourcesList) {
            adapter.addFragment(GetPostsFragment(), item.name.toString())
        }
        binding.viewPager.adapter = adapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)
        setUnselectedItemColor(sourcesList.size)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val position = tab!!.position
                sourceId = sourcesList[position!!].id.toString()
                viewModel.getPosts(sourceId)
                setSelectedItemColor()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                setUnselectedItemColor(sourcesList.size)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }
    fun setColor() {
        val header = binding.navigation.getHeaderView(0)
        val sideNavLayout =
            header.findViewById<View>(com.example.news.R.id.header_layout) as ConstraintLayout
        val activity = activity as MainActivity
        if (color == "") {
            activity.supportActionBar?.setBackgroundDrawable(
                ColorDrawable(Color.parseColor("#2E7D32"))
            )
            sideNavLayout.setBackgroundColor((Color.parseColor("#EFEDEE")))
        } else {
            activity.supportActionBar?.setBackgroundDrawable(ColorDrawable(Color.parseColor(color)))
            sideNavLayout.setBackgroundColor((Color.parseColor(color)))
        }
        when (CategoriesFragment.category) {
            "Sports","رياضة" -> {
                binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            }
            "Business","أعمال" -> {
                binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            }
            "Science","علوم" -> {
                binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            }
            "Technology","تكنولوجيا" -> {
                binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return if (actionBarDrawerToggle.onOptionsItemSelected(item))
            true
        else super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    fun setSelectedItemColor() {
        when (category) {
            "Sports","رياضة" -> {
                binding.tabLayout.setSelectedTabIndicator(R.drawable.sports_background)
                binding.tabLayout.setTabTextColors(
                    Color.parseColor(color),
                    Color.parseColor("#FFFFFFFF")
                )
            }
            "Business","اعمال"-> {
                binding.tabLayout.setSelectedTabIndicator(R.drawable.business_background)
                binding.tabLayout.setTabTextColors(
                    Color.parseColor(color),
                    Color.parseColor("#FFFFFFFF")
                )
            }
            "Science", "علوم" -> {
                binding.tabLayout.setSelectedTabIndicator(R.drawable.science_background)
                binding.tabLayout.setTabTextColors(
                    Color.parseColor(color),
                    Color.parseColor("#FFFFFFFF")
                )
            }
            "Technology","تكنولوجيا" -> {
                binding.tabLayout.setSelectedTabIndicator(R.drawable.technology_background)
                binding.tabLayout.setTabTextColors(
                    Color.parseColor(color),
                    Color.parseColor("#FFFFFFFF")
                )
            }
        }
    }

    fun setUnselectedItemColor(size: Int) {
        when (category) {
            "Sports","رياضة" -> {
                setShape(R.drawable.sports_border, size)
            }
            "Business","أعمال" -> {
                setShape(R.drawable.business_border, size)
            }
            "Science","علوم" -> {
                setShape(R.drawable.science_border, size)
            }
            "Technology","تكنولوجيا" -> {
                setShape(R.drawable.technology_border, size)
            }
        }
    }
    fun setShape(shape: Int, size: Int) {
        for (indx in 0 until size) {
            val tab: TabLayout.Tab? = binding.tabLayout.getTabAt(indx)
            tab?.view?.background = resources.getDrawable(shape)
            tab?.view?.setPadding(50, 0, 50, 0)
            binding.tabLayout.setTabTextColors(Color.parseColor(color), Color.parseColor(color))
        }
        for (i in 0 until binding.tabLayout.getTabCount()) {
            val tab = (binding.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as MarginLayoutParams
            p.setMargins(0, 0, 20, 0)
            tab.requestLayout()
        }
    }

}
