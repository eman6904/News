package com.example.news.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentSearchBinding
import com.example.news.ui.APIs.PostComponents
import com.example.news.ui.APIs.ArticlesAdapter
import com.example.news.ui.APIs.ViewModel
import kotlinx.coroutines.launch

class Search : Fragment(R.layout.fragment_search) {
    private lateinit var binding: FragmentSearchBinding
    private lateinit var navController: NavController
    private val viewModel = ViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSearchBinding.bind(view)
        navController = Navigation.findNavController(view)

        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        lifecycleScope.launch {
            setColor()
            binding.searchIcon.setOnClickListener()
            {
                if(binding.searchView.query.isNotEmpty()) {
                    search(binding.searchView.query.toString())
                }
                else
                    Toast.makeText(requireContext(),getString(R.string.searchMessage),Toast.LENGTH_LONG).show()
            }

        }
    }
    fun setColor()
    {
        when(Categories.category)
        {
            "Sports"-> {
                binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            }
            "رياضيات"-> {
                binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            }
            "Business"-> {
                binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            }
            "أعمال"-> {
                binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            }
            "Science"-> {
                binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            }
            "علوم"-> {
                binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            }
            "Technology"-> {
                binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
            }
            "تكنولوجيا"-> {
                binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
            }
        }

    }
    fun search(endPoint:String)
    {
        viewModel.getResult(endPoint).observe(viewLifecycleOwner, Observer { results->
           if(results.articles.size>0) {
               val adapter = ArticlesAdapter(results.articles)
               binding.resultsRecycler.layoutManager = LinearLayoutManager(requireContext())
               binding.resultsRecycler.adapter = adapter
               adapter.setOnClickListener(object : ArticlesAdapter.OnClickListener {
                   override fun onClick(position: Int, model: PostComponents) {

                       val url = bundleOf("url" to results.articles[position].url)
                       navController.navigate(R.id.action_search_to_post, url)
                   }
               })
           }else{Toast.makeText(requireContext(),getString(R.string.search_result),Toast.LENGTH_LONG).show()}
        })
    }
}