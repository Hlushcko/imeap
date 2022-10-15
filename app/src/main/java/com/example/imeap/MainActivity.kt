package com.example.imeap

import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //	public static double check(double posOneH, double posOneW,double posTwoH,double posTwoW){
    //	    short earthRadiusKm = 6371;
    //
    //	    double dLat = (posOneH - posTwoH) * Math.PI / 180;
    //	    double dLon = (posOneW - posTwoW) * Math.PI / 180;
    //
    //	    double a = posOneH * Math.PI / 180;
    //	    double b = posTwoH * Math.PI / 180;
    //
    //	    double c = Math.sin(dLat/2)*Math.sin(dLat/2)
    //	    + Math.sin(dLon/2)*Math.sin(dLon/2) * Math.cos(a) * Math.cos(b);
    //
    //	    return earthRadiusKm * (2 * Math.atan2(Math.sqrt(c), Math.sqrt(1-c)));
    //	}
    //}

    private fun calculatedDistance(startPoint: Point, endPoint: Point) : Double{
        val earthRadiusKm = 6371;

        val lat = (startPoint.x - endPoint.x) * Math.PI / 180
        val lon = (startPoint.y - endPoint.y) * Math.PI / 180

        val radOne = startPoint.x * Math.PI / 180
        val radTwo = endPoint.x * Math.PI / 180

        val sum = sin(lat/2) * sin(lat/2) + sin(lon/2) * sin(lon/2) * cos(radOne) * cos(radTwo)
        return earthRadiusKm * (2 * atan2(sqrt(sum), sqrt(1-sum)))
    }
}