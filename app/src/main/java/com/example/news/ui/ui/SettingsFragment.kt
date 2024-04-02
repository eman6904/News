package com.example.news.ui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.news.R
import com.example.news.databinding.FragmentSettingBinding
import com.example.news.ui.ui.MainActivity.Companion.SELECTED_LANGUAGE
import com.example.news.ui.ui.MainActivity.Companion.SELECTED_MODE
import com.example.news.ui.ui.MainActivity.Companion.appLanguage
import com.example.news.ui.ui.MainActivity.Companion.appMode
import java.util.*


class SettingsFragment : Fragment(R.layout.fragment_setting), AdapterView.OnItemSelectedListener {
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

        var selectedLanguage = appLanguage.getString(SELECTED_LANGUAGE, null)
        var selectedMode = appMode.getString(SELECTED_MODE, null)
        if(selectedLanguage=="ar"){

            languageOptions.add(getString(R.string.arabic))
            languageOptions.add(getString(R.string.english))
        }else{

            languageOptions.add(getString(R.string.english))
            languageOptions.add(getString(R.string.arabic))
        }

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
                if (languageOptions[position] == getString(R.string.arabic)) {

                    var selectedLanguage = appLanguage.getString(SELECTED_LANGUAGE, null)
                    if (selectedLanguage == null||selectedLanguage=="en") {

                        appLanguage.edit().putString(SELECTED_LANGUAGE, "ar").apply()
                        val refreshIntent = Intent(context, MainActivity::class.java)
                        refreshIntent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        context!!.startActivity(refreshIntent)

                      }
                } else {
                    var selectedLanguage = appLanguage.getString(SELECTED_LANGUAGE, null)
                    if (selectedLanguage == null||selectedLanguage=="ar") {

                        appLanguage.edit().putString(SELECTED_LANGUAGE, "en").apply()
                        val refreshIntent = Intent(context, MainActivity::class.java)
                        refreshIntent.flags =
                            Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                        context!!.startActivity(refreshIntent)

                    }
                }
            }
            R.id.modeSpinner -> {
                if (modeOptions[position] == getString(R.string.dark_mode) ) {

                    var selectedMode = appMode.getString(SELECTED_MODE, null)
                    if(selectedMode==null||selectedMode==getString(R.string.light_mode)) {

                        appMode.edit().putString(SELECTED_MODE,getString(R.string.dark_mode)).apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)


                    }
                } else{
                    var selectedMode = appMode.getString(SELECTED_MODE, null)
                    
                    if(selectedMode==null||selectedMode==getString(R.string.dark_mode)) {

                        appMode.edit().putString(SELECTED_MODE,getString(R.string.light_mode)).apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                    }
                }
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
        when (CategoriesFragment.category) {
            "Sports","رياضة"-> binding.toolbar.setBackgroundResource(R.drawable.sports_toobar)
            "Business","أعمال" -> binding.toolbar.setBackgroundResource(R.drawable.business_toolbar)
            "Science","علوم" -> binding.toolbar.setBackgroundResource(R.drawable.science_toolbar)
            "Technology","تكنولوجي" -> binding.toolbar.setBackgroundResource(R.drawable.technology_toolbar)
        }

    }

}