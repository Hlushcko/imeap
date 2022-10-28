package com.example.imeap.dagger.module_dager

import android.util.Log
import com.example.imeap.storage_logic.data_music.MusicInfo
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Named

@Module
class ModuleSortMusic(listMusic: ArrayList<MusicInfo>) {

    private var music = listMusic

    init {
        music = deleteMelody()
        //music = deleteDuplicate()
    }

    @Provides @Named("name")
    fun getSortName() : List<MusicInfo>{
        return music.sortedBy { it.name }
    }

    @Provides @Named("album")
    fun getSortAlbum() : List<MusicInfo>{
        return music.sortedBy { it.album }
    }

    @Provides @Named("artist")
    fun getSortArtist() : List<MusicInfo>{
        return music.sortedBy { it.artist }
    }

    @Provides @Named("duration")
    fun getSortDuration() : List<MusicInfo>{
        return music.sortedBy { it.duration }
    }


    @Provides @Named("random")
    fun getSortRandom() : List<MusicInfo>{
        val list = ArrayList<MusicInfo>(music.size)
        //val twoMusic = music.clone()// i love ConcurrentModificationException
        val random: ArrayList<MusicInfo> = music.clone() as ArrayList<MusicInfo>

        music.forEach { _ ->
            val num = (0 until random.size).random()
            list.add(random[num])
            random.removeAt(num)
        }

        return list
    }


    //видалення мелодій вайбера, сmс повідомлень та інше...
    private fun deleteMelody() : ArrayList<MusicInfo>{
        val list = ArrayList<MusicInfo>()

        for(element in music){
            if(element.duration > 0.10f){ // записує тільки аудіо які грають довше 10 секунд
                list.add(element)
            }
        }

        return list
    }


    //видалення дублікатів по типу "name" and "name-1"
    private fun deleteDuplicate() : ArrayList<MusicInfo>{
        val list = ArrayList<MusicInfo>()

        for(element in music){
            for(bite in list){
                // todo "Доробити функцію для видалення дублікатів"
            }
        }

        return list
    }

}