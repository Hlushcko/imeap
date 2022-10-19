package com.example.imeap.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.imeap.viewModel.ModelMusic
import com.example.imeap.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onStart() {
        super.onStart()
        val viewModel = ViewModelProvider(this)[ModelMusic::class.java]
        viewModel.a()

        lifecycleScope.launch(Dispatchers.IO){

        }
    }


    
}