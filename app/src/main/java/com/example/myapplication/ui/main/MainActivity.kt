package com.example.myapplication.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.VisibleForTesting
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding
import com.skydoves.whatif.whatIfNotNull
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), View.OnClickListener {

    @VisibleForTesting
    val viewModel: MainViewModel by viewModels()

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.vm = viewModel
        initOnClicks()
    }

    private fun initOnClicks(){
        binding.selector.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        view.whatIfNotNull {
            when(view){
                binding.selector -> {
                    viewModel.showRandomCard()
                }
            }
        }
    }

}