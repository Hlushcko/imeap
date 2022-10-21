package com.example.imeap.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imeap.storage_logic.SearchMusic
import com.example.imeap.storage_logic.data_music.MusicInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelMusic : ViewModel() {

    fun a(){

        viewModelScope.launch(Dispatchers.IO){

        }

    }

    fun getMusic(context: Context) : ArrayList<MusicInfo>{
        return SearchMusic(context).getAllMusic()
    }
}