package com.example.imeap.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.imeap.storage_logic.SearchMusic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ModelMusic : ViewModel() {

    fun a(){

        viewModelScope.launch(Dispatchers.IO){

        }

    }

    fun getMusic(context: Context) : HashMap<String, String>{
        return SearchMusic(context).getAllMusic()
    }
}