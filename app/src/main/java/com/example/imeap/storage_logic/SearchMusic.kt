package com.example.imeap.storage_logic


import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.util.Log

import com.example.imeap.storage_logic.data_music.MusicInfo



class SearchMusic(private val ctx: Context) {

    fun getAllMusic() : ArrayList<MusicInfo>{

        return try {
            searchMusicCardStorage()
        }catch (ex: Exception){
            ex.printStackTrace()
            ArrayList<MusicInfo>()
        }

    }


    @SuppressLint("Recycle")
    private fun searchMusicCardStorage() : ArrayList<MusicInfo>{
        val uri: Uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(
            MediaStore.Audio.AudioColumns.DATA,
            MediaStore.Audio.AudioColumns.ALBUM,
            MediaStore.Audio.ArtistColumns.ARTIST,
            MediaStore.Audio.AudioColumns.DURATION
            )

        val music: Cursor? = ctx.contentResolver.query(uri, projection, null, null, null)

        val listMusic = ArrayList<MusicInfo>()
        if(music != null){

            while (music.moveToNext()){
                listMusic.add(MusicInfo(
                    path = music.getString(0),
                    album = music.getString(1),
                    artist = music.getString(2),
                    name = music.getString(0).substring(music.getString(0).lastIndexOf("/") + 1),
                    duration = (music.getString(3).toLong() * 0.001) *  0.0166 //похибка в 0.1 секунду.
                ))
            }

            music.close()
        }

        return listMusic
    }

}