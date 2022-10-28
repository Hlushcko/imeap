package com.example.imeap.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.imeap.R
import com.example.imeap.storage_logic.data_music.MusicInfo

class RecyclerMusicList(
    private val open: OpenActivities
    ) : RecyclerView.Adapter<RecyclerMusicList.MusicHolder>() {

    var list: List<MusicInfo> = ArrayList<MusicInfo>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.soud, parent, false)
        return MusicHolder(inflate)
    }

    override fun onBindViewHolder(holder: MusicHolder, position: Int) {
        holder.name.text = list[position].name
        holder.openMusic(open)
    }

    override fun getItemCount() = list.size


    class MusicHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.soundName)

        fun openMusic(open: OpenActivities){
            name.setOnClickListener {
                open.openActivity()
            }
        }

    }

}