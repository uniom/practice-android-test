package com.example.junitpractice

open class Satellite {
    open fun getWeather(latitude: Double, longtitude: Double): Weather {
        return Weather.SUNNY
    }
}
