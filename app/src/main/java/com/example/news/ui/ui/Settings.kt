package com.example.news.ui.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import android.view.Window
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentSettingBinding
import com.example.news.ui.ui.Home.Companion.language
import java.util.*
import kotlin.concurrent.fixedRateTimer


class Settings : Fragment(R.layout.fragment_setting), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentSettingBinding
    private lateinit var navController: NavController
    val languageOptions = ArrayList<String>()
    val modeOptions = ArrayList<String>()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSettingBinding.bind(view)
        navController = Navigation.findNavController(view)

        val activity = activity as MainActivity
        activity.supportActionBar?.hide()

        languageOptions.add(getString(R.string.english))
        languageOptions.add(getString(R.string.arabic))

        modeOptions.add(getString(R.string.light_mode))
        modeOptions.add(getString(R.string.dark_mode))
        setLanguageOptions()
        setModeOptions()
        setColor()

    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, id: Long) {
        val idd = p0?.id
        when (idd) {
            R.id.languageSpinner -> {
                if (languageOptions[position] == "Arabic") {
                    language = "ar"
                    val locale = Locale("ar")
                    Locale.setDefault(locale)
                    val config = Configuration()
                    config.locale = locale
                    resources.updateConfiguration(config, resources.displayMetrics)
                    //refreshFragment(requireContext())

                } else {
                    language = "en"
                    val locale = Locale("en")
                    Locale.setDefault(locale)
                    val config = Configuration()
                    config.locale = locale
                    resources.updateConfiguration(config, resources.displayMetrics)
                }
            }
            R.id.modeSpinner -> {
                if (modeOptions[position] == "Dark Mode" || modeOptions[position] == "مظلم")
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                else
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

    fun setLanguageOptions() {

        binding.languageSpinner.onItemSelectedListener = this

        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            languageOptions
        )


        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.languageSpinner.adapter = adapter
    }

    fun setModeOptions() {
        binding.modeSpinner.onItemSelectedListener = this

        val adapter = ArrayAdapter<String>(
            requireContext(),
            android.R.layout.simple_spinner_item,
            modeOptions
        )


        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        binding.modeSpinner.adapter = adapter
    }

    fun setColor() {
        when (Categories.category) {
            "Sports" -> binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            "رياضيات" -> binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            "Business" -> binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            "أعمال" -> binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            "Science" -> binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            "علوم" -> binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            "Technology" -> binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
            "تكنولوجي" -> binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
        }

    }

    fun refreshFragment(context: Context) {
        context?.let {
            var fragmentManager = (context as? AppCompatActivity)?.supportFragmentManager
            fragmentManager?.let {
                val currentFragment = fragmentManager.findFragmentById(R.id.setting_fr)
                currentFragment?.let {
                    val fragmentTransaction = fragmentManager.beginTransaction()
                    fragmentTransaction.detach(it)
                    fragmentTransaction.attach(it)
                    fragmentTransaction.commit()
                }
            }
        }
    }
}