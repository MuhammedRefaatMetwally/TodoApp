package com.route.todosappc38online.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.route.isalmic38online.core.local_data_source.AppSharedReferences
import com.route.todosappc38online.R
import com.route.todosappc38online.ui.home.HomeActivity
import java.util.Locale

class SplashActivity : AppCompatActivity() {
    var nightMode = false
    var languageMode = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initParams()
        isNightMode()
        isLanguageMode()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity( Intent(this@SplashActivity, HomeActivity::class.java))
                finish()
            },
            2500
        )
    }
    private fun isNightMode() {
        if(nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }
    }

    private fun isLanguageMode() {
        if(languageMode){
            val localeListCompat = LocaleListCompat.create(Locale("en"))
            AppCompatDelegate.setApplicationLocales(localeListCompat)
        }else{
            val localeListCompat = LocaleListCompat.create(Locale("ar"))
            AppCompatDelegate.setApplicationLocales(localeListCompat)
        }
    }

    private fun initParams() {
    nightMode = AppSharedReferences.read("mode",false)
    languageMode = AppSharedReferences.read("language",false)
    }


}