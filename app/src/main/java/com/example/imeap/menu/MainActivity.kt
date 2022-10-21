package com.example.imeap.menu

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.example.imeap.viewModel.ModelMusic
import com.example.imeap.R
import com.example.imeap.storage_logic.data_music.MusicInfo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var listMusic: MutableLiveData<ArrayList<MusicInfo>>
    private lateinit var viewModel: ModelMusic


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
        checkList()
        searchMusic()
    }

    override fun onStart() {
        super.onStart()

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            requestPermissions(listOf(Manifest.permission.READ_EXTERNAL_STORAGE).toTypedArray(), 431)
        }

    }

    private fun init(){
        listMusic = MutableLiveData<ArrayList<MusicInfo>>()
        viewModel = ViewModelProvider(this)[ModelMusic::class.java]
    }


    private fun searchMusic(){
        lifecycleScope.launch(Dispatchers.IO){
            val a = viewModel.getMusic(this@MainActivity)
            if(a.isNotEmpty()) {
                listMusic.postValue(a)
            }
        }
    }


    private fun checkList(){
        listMusic.observe(this, Observer {

        })
    }
    
}

