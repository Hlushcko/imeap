package com.example.imeap.storage_logic

import android.content.Context
import androidx.core.content.ContextCompat


class SearchMusic(private val ctx: Context) {

    fun getAllMusic() : HashMap<String, String>{

        return try {
            checkSdCard()
        }catch (ex: Exception){
            emptyMap<String, String>() as HashMap<String, String>
        }

    }


    private fun checkSdCard() : HashMap<String, String>{

    }

    fun hasRealRemovableSdCard(): Boolean {
        return ContextCompat.getExternalFilesDirs(ctx, null).size >= 2
    }

    private fun searchMusicCardStorage() : HashMap<String, String>{

    }

}