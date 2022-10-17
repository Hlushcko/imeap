package com.example.imeap
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun main(){
}

private fun calculatedDistance(latOne: Double, lonOne: Double, latTwo: Double, lonTwo: Double) : Double{
    val earthRadiusKm = 6371;

    val lat = (latOne - latTwo) * Math.PI / 180
    val lon = (lonOne - lonTwo) * Math.PI / 180

    val radOne = latOne * Math.PI / 180
    val radTwo = latTwo * Math.PI / 180

    val sum = sin(lat/2) * sin(lat/2) + sin(lon/2) * sin(lon/2) * cos(radOne) * cos(radTwo)
    return earthRadiusKm * (2 * atan2(sqrt(sum), sqrt(1-sum)))
}
