package com.example.news.ui.ui


import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.ui.APIs.*
import com.example.news.ui.ui.Categories.Companion.category
import com.example.news.ui.ui.Categories.Companion.color
import kotlinx.coroutines.launch


class Home : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var navController: NavController
    private val viewModel = SourcesViewModel()
    lateinit var actionBarDrawerToggle: ActionBarDrawerToggle

    companion object {
        var selectedItems = ArrayList<Boolean>()
        var sourceId = ""
        var language="en"
        var categoryName=""
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        navController = Navigation.findNavController(view)

        val activity = activity as MainActivity
        activity.supportActionBar?.show()
        activity.supportActionBar?.title = category

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
            activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
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
            if (categoryName != "") {
                viewModel.sources(categoryName).getSources()
                    .observe(viewLifecycleOwner, Observer { sources ->

                        for (indx in sources.sources.indices) {
                            selectedItems.add(false)
                        }
                        if (sources.sources.size > 0) {

                            for (indx in selectedItems.indices)
                                selectedItems[indx] = false
                            selectedItems[0] = true
                            sourceId = sources.sources[0].id.toString()
                            displaySources(sources.sources)
                            viewModel.Posts(sourceId).getPosts()
                                .observe(viewLifecycleOwner, Observer { posts ->
                                    val adapter = PostsAdapter(posts.articles)
                                    binding.ArticleRecycler.layoutManager =
                                        LinearLayoutManager(requireContext())
                                    binding.ArticleRecycler.adapter = adapter
                                    adapter.setOnClickListener(object :
                                        PostsAdapter.OnClickListener {
                                        override fun onClick(position: Int, model: PostComponents) {
                                            val url =
                                                bundleOf("url" to posts.articles[position].url)
                                            navController.navigate(R.id.action_home2_to_post, url)
                                        }
                                    })
                                })
                        } else {
                            Toast.makeText(
                                requireContext(),
                               getString(R.string.search_result) ,
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    })
            }
        }
    }

    fun displaySources(sourcesList: ArrayList<SourceComponents>) {
        val adapter = SourcesAdapter(sourcesList)
        binding.sourcesRecycler.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.sourcesRecycler.adapter = adapter
        adapter.setOnClickListener(object : SourcesAdapter.OnClickListener {
            override fun onClick(position: Int, model: SourceComponents) {
                //change articles value
                sourceId = sourcesList[position].id.toString()
                viewModel.Posts(sourceId).getPosts()
                for (indx in selectedItems.indices)
                    selectedItems[indx] = false
                selectedItems[position] = true
                adapter.notifyDataSetChanged()
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

}
