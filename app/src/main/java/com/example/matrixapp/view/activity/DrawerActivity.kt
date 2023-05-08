package com.example.matrixapp.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.matrixapp.R
import com.example.matrixapp.databinding.ActivityDrawerBinding

class DrawerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDrawerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}