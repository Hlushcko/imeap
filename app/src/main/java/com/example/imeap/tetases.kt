package com.example.imeap

import android.graphics.Point
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

fun main(){
    println(calculatedDistance(51.5, 0.0, 38.8, -77.1))
}

//https://earth.nullschool.net/#current/wind/isobaric/1000hPa/overlay=temp/orthographic=35.75,47.82,5113/loc=21.056,49.994

//1000 hPa	 м, умови на рівні моря
//850 hPa	 ~ 1,500 м, Планетарний прикордонний шар, низький
//700 hPa	 ~ 3,500 м, Планетарний прикордонний шар, високий
//500 hPa	 ~ 5,000 м, вири
//250 hPa	~10 500 м, Реактивний потік
//70 hPa	~17 500 м, Стратосфера
//10 hPa	~26 500 м, більше стратосфери

//overlay = temp - температура повітря.
//overlay = Rh - відносна вологість повітря на заданій вистоі.

private fun calculatedDistance(latOne: Double, lonOne: Double, latTwo: Double, lonTwo: Double) : Double{
    val earthRadiusKm = 6371;

    val lat = (latOne - latTwo) * Math.PI / 180
    val lon = (lonOne - lonTwo) * Math.PI / 180

    val radOne = latOne * Math.PI / 180
    val radTwo = latTwo * Math.PI / 180

    val sum = sin(lat/2) * sin(lat/2) + sin(lon/2) * sin(lon/2) * cos(radOne) * cos(radTwo)
    return earthRadiusKm * (2 * atan2(sqrt(sum), sqrt(1-sum)))
}