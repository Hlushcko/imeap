
package com.example.imeap.menu

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.imeap.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MusicView : AppCompatActivity() {

    private val KEY_NAME_MUSIC = "NameList"
    private val KEY_PATH_MUSIC = "PathList"
    private val KEY_POSITION_MUSIC = "PositionSound"

    private lateinit var listName: ArrayList<String>
    private lateinit var listPath: ArrayList<String>
    private var correctPosition: Int = 0
    private var restartView = false
    private val mediaPlayer = MediaPlayer()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent?.extras
        if(bundle != null){
            listName = bundle.getStringArrayList(KEY_NAME_MUSIC)!!
            listPath = bundle.getStringArrayList(KEY_PATH_MUSIC)!!
            correctPosition = bundle.getInt(KEY_POSITION_MUSIC)
        }

        setContentView(R.layout.activity_music_view)
    }

    override fun onStart() {
        super.onStart()

        if(!restartView) {
            init()
        }
    }

    private fun init(){
        startMusic(correctPosition)
        nextSound()
        pauseOrPlaySound()
        backSound()
        setProgressBar()
    }

    private fun startMusic(position: Int) {
        lifecycleScope.launch(Dispatchers.Main){ // чомусь в потоці IO видає помилку iilegalStateExceptione
            mediaPlayer.reset()
            mediaPlayer.setDataSource(listPath[position])
            mediaPlayer.prepareAsync()

            mediaPlayer.setOnPreparedListener {
                mediaPlayer.start()
            }

            setName(listName[position])
        }
    }

    private fun setName(name: String){
        findViewById<TextView>(R.id.nameMusic).text = name
    }


    private fun pauseOrPlaySound(){
        findViewById<Button>(R.id.pauseOrPlay).setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }else{
                mediaPlayer.start()
            }
        }
    }

    private fun nextSound(){
        findViewById<Button>(R.id.nextMusic).setOnClickListener {
            if(correctPosition >= listPath.size-1){
                correctPosition = 0
                startMusic(correctPosition)
            }else{
                startMusic(++correctPosition)
            }
        }
    }

    private fun backSound(){
        findViewById<Button>(R.id.backMusic).setOnClickListener {
            if(correctPosition > 0){
                startMusic(--correctPosition)
            }
        }
    }

    private fun setProgressBar(){
        val progressBar: ProgressBar = findViewById(R.id.progressSound)
        lifecycleScope.launch(Dispatchers.IO){

            do {
                val duration = mediaPlayer.duration.toFloat()
                val currentTime = mediaPlayer.currentPosition.toFloat()

                if (mediaPlayer.isPlaying) {

                    val result: Int = ((currentTime / duration) * 100).toInt()
                    launch(Dispatchers.Main) {
                        progressBar.setProgress(result, true)
                    }

                }

                if(duration <= currentTime){
                    startMusic(++correctPosition)
                }


                delay(500)
            }while(true)
        }
    }

    override fun onRestart() {
        super.onRestart()
        restartView = true
    }


    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}
