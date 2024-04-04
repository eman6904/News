package com.example.news.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.news.R
import com.example.news.databinding.FragmentSourcesListBinding
import com.example.news.ui.APIs.PostComponents
import com.example.news.ui.APIs.ArticlesAdapter
import com.example.news.ui.APIs.ViewModel
import com.example.news.ui.ui.HomeFragment.Companion.sourceId


class GetPostsFragment() : Fragment(R.layout.fragment_sources_list) {
    private lateinit var binding: FragmentSourcesListBinding
    private lateinit var navController: NavController
    private val viewModel = ViewModel()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSourcesListBinding.bind(view)
        navController = Navigation.findNavController(view)

        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

            viewModel.getPosts(sourceId)
                .observe(viewLifecycleOwner, Observer { posts ->
                    if (posts.articles.size > 0) {
                        val adapter = ArticlesAdapter(posts.articles)
                        binding.ArticleRecycler.layoutManager = LinearLayoutManager(requireContext())
                        binding.ArticleRecycler.adapter = adapter
                        adapter.setOnClickListener(object :
                            ArticlesAdapter.OnClickListener {
                            override fun onClick(position: Int, model: PostComponents) {
                                val url = bundleOf("url" to posts.articles[position].url)
                                navController.navigate(R.id.action_home2_to_post, url)
                            }
                        })
                    }
                })
        }
}