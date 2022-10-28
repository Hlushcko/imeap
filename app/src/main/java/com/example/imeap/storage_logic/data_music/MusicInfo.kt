package com.example.imeap.storage_logic.data_music

import android.os.Parcel
import android.os.Parcelable

data class MusicInfo(
    val name: String,
    val album: String,
    val artist: String,
    val path: String,
    val duration: Double
    ) : Parcelable {


    constructor(parcel: Parcel) : this(
        parcel.toString(),
        parcel.toString(),
        parcel.toString(),
        parcel.toString(),
        parcel.readDouble()
    )


    override fun writeToParcel(parcel: Parcel, flags: Int) {

    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MusicInfo> {
        override fun createFromParcel(parcel: Parcel): MusicInfo {
            return MusicInfo(parcel)
        }

        override fun newArray(size: Int): Array<MusicInfo?> {
            return arrayOfNulls(size)
        }
    }
}
