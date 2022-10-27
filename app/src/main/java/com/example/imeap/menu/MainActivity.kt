package com.example.imeap.menu

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope

import com.example.imeap.viewModel.ModelMusic
import com.example.imeap.R
import com.example.imeap.dagger.interface_dager.AppComponentDagger
import com.example.imeap.dagger.interface_dager.DaggerAppComponentDagger
import com.example.imeap.dagger.module_dager.ModuleSortMusic
import com.example.imeap.storage_logic.data_music.MusicInfo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ModelMusic
    private lateinit var dagger: AppComponentDagger
    private val allMusic = ArrayList<MusicInfo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }


    private fun checkPermission(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            do {
                requestPermissions(listOf(Manifest.permission.READ_EXTERNAL_STORAGE).toTypedArray(), 577)
            }while(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        }
    }

    private fun init(){
        viewModel = ViewModelProvider(this)[ModelMusic::class.java]
        checkPermission()
        searchMusic()
    }


    private fun searchMusic(){
        lifecycleScope.launch(Dispatchers.IO){
            val list = viewModel.getMusic(this@MainActivity)
            if(list.isNotEmpty()) {
                allMusic.addAll(list)
                initDagger(list)
            }
        }
    }


    private fun initDagger(list: ArrayList<MusicInfo>){
        dagger = DaggerAppComponentDagger.builder()
            .moduleSortMusic(ModuleSortMusic(list))
            .build()
    }


    private fun getSortName(){
        //setOnClick.....{
    //         recyclerView.put(dagger.getName())
    // }
    }



    
}

