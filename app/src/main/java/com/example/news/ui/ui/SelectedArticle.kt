package com.example.news.ui.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentPostBinding


class SelectedArticle : Fragment(R.layout.fragment_post) {
    private lateinit var binding: FragmentPostBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPostBinding.bind(view)
        navController = Navigation.findNavController(view)
        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        val url=arguments?.getString("url")
        binding.webView.loadUrl(url.toString())
    }

}