
package com.example.imeap.menu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import com.example.imeap.R
import com.example.imeap.storage_logic.data_music.MusicInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MusicView : AppCompatActivity() {

    private val KEY_LIST_MUSIC = "ListSong"

    private var listMusic: ArrayList<MusicInfo> = ArrayList<MusicInfo>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
//        if(bundle != null){
//            listMusic = bundle.getParcelableArrayList(KEY_LIST_MUSIC)!!
//        }

        Log.i("Info", listMusic.size.toString())

        setContentView(R.layout.activity_music_view)
    }

    override fun onStart() {
        super.onStart()
        //rotateStar()
    }

    private fun rotateStar(){
        val image: ImageView = findViewById(R.id.starRotation)

        lifecycleScope.launch(Dispatchers.Main){
            do {
                image.rotation += 0.1f
                delay(10)
            }while(true)
        }
    }

}
