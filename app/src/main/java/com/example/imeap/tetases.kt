package com.example.imeap

import android.graphics.Point
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun main(){
    println(calculatedDistance(51.5, 0.0, 38.8, -77.1))
}

//https://earth.nullschool.net/#current/wind/surface/level/orthographic=-329.68,46.36,2888/loc=35.520,46.713

private fun calculatedDistance(latOne: Double, lonOne: Double, latTwo: Double, lonTwo: Double) : Double{
    val earthRadiusKm = 6371;

    val lat = (latOne - latTwo) * Math.PI / 180
    val lon = (lonOne - lonTwo) * Math.PI / 180

    val radOne = latOne * Math.PI / 180
    val radTwo = latTwo * Math.PI / 180

    val sum = sin(lat/2) * sin(lat/2) + sin(lon/2) * sin(lon/2) * cos(radOne) * cos(radTwo)
    return earthRadiusKm * (2 * atan2(sqrt(sum), sqrt(1-sum)))
}