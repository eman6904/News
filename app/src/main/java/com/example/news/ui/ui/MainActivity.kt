package com.example.news.ui.ui

import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.news.R
import java.util.*

class MainActivity : AppCompatActivity() {

    companion object {

        val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"
        val SELECTED_MODE = "Locale.Helper.Selected.Mode"
        // Initialize SharedPreferences
        lateinit var appLanguage: SharedPreferences
        lateinit var appMode: SharedPreferences

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appLanguage = PreferenceManager.getDefaultSharedPreferences(this)
        appMode = PreferenceManager.getDefaultSharedPreferences(this)
        val savedLanguage =appLanguage.getString(SELECTED_LANGUAGE, null)
        if (savedLanguage != null) {
            val locale = Locale(savedLanguage)
            Locale.setDefault(locale)
            val config = Configuration()
            config.locale = locale
            resources.updateConfiguration(config, resources.displayMetrics)
        }

        var selectedMode = appMode.getString(SELECTED_MODE, null)
        if(selectedMode=="Dark Mode") {


            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
        else
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
    }
}
