package com.example.imeap.menu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.example.imeap.viewModel.ModelMusic
import com.example.imeap.R
import com.example.imeap.dagger.interface_dager.AppComponentDagger
import com.example.imeap.dagger.interface_dager.DaggerAppComponentDagger
import com.example.imeap.dagger.module_dager.ModuleSortMusic
import com.example.imeap.recycler.OpenActivities
import com.example.imeap.recycler.RecyclerMusicList
import com.example.imeap.storage_logic.data_music.MusicInfo

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


class MainActivity : AppCompatActivity(), OpenActivities {

    private lateinit var viewModel: ModelMusic
    private lateinit var dagger: AppComponentDagger
    private lateinit var recycler: RecyclerView
    private lateinit var recyclerList: RecyclerMusicList

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
        recycler = findViewById(R.id.listMusic)
        recyclerList = RecyclerMusicList(this)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = recyclerList

        checkPermission()
        searchMusic()
        getAllMusic()
        getSortName()
        getSortAlbum()
        getSortArtist()
        getSortDuration()
        getSortRandom()
    }


    private fun searchMusic(){
        lifecycleScope.launch(Dispatchers.IO){
            val list = viewModel.getMusic(this@MainActivity)
            if(list.isNotEmpty()) {
                allMusic.addAll(list)
                initDagger(list)
                recyclerList.list = dagger.getName()
            }
        }
    }


    private fun initDagger(list: ArrayList<MusicInfo>){
        dagger = DaggerAppComponentDagger.builder()
            .moduleSortMusic(ModuleSortMusic(list))
            .build()
    }


    private fun getAllMusic(){
        findViewById<Button>(R.id.all).setOnClickListener{
            recyclerList.list = allMusic
        }
    }

    private fun getSortName(){
        findViewById<Button>(R.id.name).setOnClickListener{
            recyclerList.list = dagger.getName()
        }
    }

    private fun getSortAlbum(){
        findViewById<Button>(R.id.album).setOnClickListener{
            recyclerList.list = dagger.getAlbum()
        }
    }

    private fun getSortArtist(){
        findViewById<Button>(R.id.artist).setOnClickListener{
            recyclerList.list = dagger.getArtist()
        }
    }

    private fun getSortDuration(){
        findViewById<Button>(R.id.duration).setOnClickListener{
            recyclerList.list = dagger.getDuration()
        }
    }

    private fun getSortRandom(){
        findViewById<Button>(R.id.random).setOnClickListener{
            recyclerList.list = dagger.getRandom()
        }
    }

    override fun openActivity() {
        val intent = Intent(this, MusicView::class.java)
        intent.putParcelableArrayListExtra("ListSong", recyclerList.list as java.util.ArrayList<out Parcelable>)
        startActivity(intent)
    }


}

