package com.example.matrixapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.matrixapp.R
import com.example.matrixapp.databinding.ActivityDrawerBinding

class DrawerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_drawer) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)
        applyClick()
    }

    private fun applyClick(){
        with(binding){
            val headerLayout = navView.getHeaderView(0)
            val btnCloseDrawer = headerLayout.findViewById<ImageView>(R.id.bntCloseDrawer)
            btnCloseDrawer.setOnClickListener{
                root.closeDrawer(GravityCompat.START)
            }
        }
    }

    fun openDrawer(){
        binding.root.openDrawer(GravityCompat.START)
    }

}