package com.example.news.ui.ui

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentFaceBinding

class FaceFragment : Fragment(R.layout.fragment_face) {
    private lateinit var binding: FragmentFaceBinding
    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFaceBinding.bind(view)
        navController = Navigation.findNavController(view)

        val activity = activity as MainActivity
        activity.supportActionBar?.hide()
        activity.supportActionBar?.title=""

        Handler(Looper.getMainLooper()).postDelayed({
            navController.navigate(R.id.action_face_to_home2)

        }, 2600)
    }
}