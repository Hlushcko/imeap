package com.example.imeap.dagger.interface_dager

import com.example.imeap.dagger.module_dager.ModuleSortMusic
import com.example.imeap.storage_logic.data_music.MusicInfo
import dagger.Component
import javax.inject.Named

@Component (modules = [ModuleSortMusic::class])
interface AppComponentDagger {

    //1 module
    @Named("name")
    fun getName() : List<MusicInfo>

    @Named("album")
    fun getAlbum() : List<MusicInfo>

    @Named("artist")
    fun getArtist() : List<MusicInfo>

    @Named("duration")
    fun getDuration() : List<MusicInfo>

    @Named("random")
    fun getRandom() : List<MusicInfo>

}