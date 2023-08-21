package com.route.todosappc38online

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.route.todosappc38online.database.TodoDatabase
import com.route.todosappc38online.designPatterns.DatabaseInstance2
import com.route.todosappc38online.designPatterns.database1
import com.route.todosappc38online.designPatterns.database2
import com.route.todosappc38online.fragments.AddTodoBottomSheetFragment
import com.route.todosappc38online.fragments.SettingsFragment
import com.route.todosappc38online.fragments.TodosListFragment

class MainActivity : AppCompatActivity() {
    /*

        Drawable -> sura_details_bg
        Drawable-night ->
     */
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var addTodoButton: FloatingActionButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        bottomNavigationView = findViewById(R.id.bottom_navigation_view)
        bottomNavigationView.setOnItemSelectedListener {
            if (it.itemId == R.id.navigation_list) {
                pushFragment(TodosListFragment())
            } else if (it.itemId == R.id.navigation_settings) {
                pushFragment(SettingsFragment())
            }
            return@setOnItemSelectedListener true
        }
        bottomNavigationView.selectedItemId = R.id.navigation_list
        addTodoButton = findViewById(R.id.add_todo_button)
        addTodoButton.setOnClickListener {
            val bottomSheetFragment = AddTodoBottomSheetFragment()
            bottomSheetFragment.show(supportFragmentManager, "Add-Todo")
        }
    }

    private fun pushFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}