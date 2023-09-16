package com.route.todosappc38online.ui.fragments.settings_frag

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.fragment.app.Fragment
import com.route.isalmic38online.core.local_data_source.AppSharedReferences
import com.route.todosappc38online.R
import com.route.todosappc38online.databinding.FragmentSettingsBinding
import java.util.Locale


class SettingsFragment : Fragment() {

    lateinit var binding : FragmentSettingsBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listLanguage = listOf("Select Language","English" , "Arabic")
        val listMode = listOf("Select Light / Dark Mode","Light Mode" , "Dark Mode")
        val adapterLanguage = ArrayAdapter(requireContext(),R.layout.spinner_item, listLanguage)
        adapterLanguage.setDropDownViewResource(R.layout.spinner_item)
        binding.languageSpinner.adapter = adapterLanguage

        val adapterMod  = ArrayAdapter(requireContext(),R.layout.spinner_item, listMode)
        adapterMod.setDropDownViewResource(R.layout.spinner_item)
        binding.modeSpinner.adapter = adapterMod


        binding.languageSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1 ) {
                    val localeListCompat = LocaleListCompat.create(Locale("en"))
                     AppSharedReferences.write("language",true)

                    AppCompatDelegate.setApplicationLocales(localeListCompat)
                } else if (position == 2) {
                    val localeListCompat = LocaleListCompat.create(Locale("ar"))
                    AppSharedReferences.write("language",false)
                    AppCompatDelegate.setApplicationLocales(localeListCompat)
                }
            }


            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
        binding.modeSpinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (position == 1) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    AppSharedReferences.write("mode",true)
                } else if (position == 2) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    AppSharedReferences.write("mode",false)

                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
}