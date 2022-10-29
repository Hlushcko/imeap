package com.example.imeap.menu

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import androidx.core.content.ContextCompat
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



class MainActivity : AppCompatActivity(), OpenActivities {

    private val KEY_LIST_MUSIC = "ListSong"

    private lateinit var viewModel: ModelMusic
    private lateinit var dagger: AppComponentDagger
    private lateinit var recycler: RecyclerView
    private lateinit var recyclerList: RecyclerMusicList

    private var correctList = ArrayList<MusicInfo>()
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

                val a = dagger.getName()

                correctList.addAll(a)
                recyclerList.list = correctList
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
            correctList.clear()
            correctList = allMusic
            recyclerList.list = allMusic
        }
    }

    private fun getSortName(){
        findViewById<Button>(R.id.name).setOnClickListener{
            correctList.clear()
            correctList.addAll(dagger.getName())
            recyclerList.list = correctList
        }
    }

    private fun getSortAlbum(){
        findViewById<Button>(R.id.album).setOnClickListener{
            correctList.clear()
            correctList.addAll(dagger.getAlbum())
            recyclerList.list = correctList
        }
    }

    private fun getSortArtist(){
        findViewById<Button>(R.id.artist).setOnClickListener{
            correctList.clear()
            correctList.addAll(dagger.getArtist())
            recyclerList.list = correctList
        }
    }

    private fun getSortDuration(){
        findViewById<Button>(R.id.duration).setOnClickListener{
            correctList.clear()
            correctList.addAll(dagger.getDuration())
            recyclerList.list = correctList
        }
    }

    private fun getSortRandom(){
        findViewById<Button>(R.id.random).setOnClickListener{
            correctList.clear()
            correctList.addAll(dagger.getRandom())
            recyclerList.list = correctList
        }
    }

    override fun openActivity() {
        val intent = Intent(this, MusicView::class.java)
        intent.putParcelableArrayListExtra(KEY_LIST_MUSIC, correctList)
        startActivity(intent)
    }


}

