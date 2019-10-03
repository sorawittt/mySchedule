package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.toolbar.*


class MainActivity : AppCompatActivity() {

    private val frameId = R.id.fragment_container
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.tool_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addSubject -> {
                startActivity(Intent(this, AddSubjectActivity::class.java))
            }

//            R.id.addSubject -> {
//                replaceFragment(AddSubjectFragment.newInstance())
//            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun AppCompatActivity.replaceFragment(fragment: Fragment) {
        supportFragmentManager.inTransaction {
            setCustomAnimations(R.anim.fragment_in, R.anim.fragment_out).replace(frameId, fragment)
        }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> FragmentTransaction) {
        beginTransaction().func().commit()
    }

}
