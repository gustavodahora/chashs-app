package com.example.crashs_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.crashs_app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var checkedThemeGlobal = false
    private var crashValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        pref.apply {
            val checked = getBoolean("checked", false)
            val getCrashValue = getInt("crashValue", 0)
            checkedThemeGlobal = checked
            crashValue = getCrashValue
        }

        // Set them on startup
        setTheme()
        setCrashValue(crashValue)
    }

    // Get and set theme using set Theme
    fun theme(view: View?) {
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()
        var checked = binding.theme1.isChecked
        editor.putBoolean("checked", checked).apply()
        checkedThemeGlobal = checked

        setTheme()
    }

    // Set theme
    fun setTheme() {
        binding.theme1.isChecked = checkedThemeGlobal
        if (checkedThemeGlobal) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    // Set the crash value
    fun setCrashValue(crashValue: Int) {
        if (crashValue >= 0) {
            val pref = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = pref.edit()
            editor.putInt("crashValue", crashValue).apply()
            binding.countCrash.text = crashValue.toString()
        }
    }

    fun addCrashValue(view: View?) {
        crashValue++
        setCrashValue(crashValue)
    }

    fun removeCrashValue(view: View?) {
        crashValue--
        setCrashValue(crashValue)
    }
}