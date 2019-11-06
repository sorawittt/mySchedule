package com.example.myapplication

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

    private var dbHandler: DatabaseHandler? = null
    private val frameId = R.id.fragment_container
    private var selectedItemId = R.id.navigation_schedule

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        if (savedInstanceState == null) {
            addFragment(ScheduleFragment.newInstance())
        }
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)



    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        dbHandler = DatabaseHandler(this)
        when (item.itemId) {
            R.id.addSubject -> {
                startActivity(Intent(this, AddSubjectActivity::class.java))
            }
            R.id.clear_schedule -> {
                dbHandler?.deleteSchedule()
                Toast.makeText(this, "Clear schedule success",  Toast.LENGTH_LONG).show()
            }

        }
        return super.onOptionsItemSelected(item)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_schedule -> {
                if (item.itemId == selectedItemId)
                    toast("Schedule")
                else {
                    replaceFragment(ScheduleFragment.newInstance())
                    selectedItemId = item.itemId
                }
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_week -> {
                if (item.itemId == selectedItemId)
                    toast("All Schedule")
                else {
                    replaceFragment(AllScheduleFragment.newInstance())
                    selectedItemId = item.itemId

                }
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }

    private fun AppCompatActivity.replaceFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out).replace(frameId, fragment)
        }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

    private fun AppCompatActivity.addFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction { add(frameId, fragment) }
    }

    private fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}
