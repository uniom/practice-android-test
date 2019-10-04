package com.example.junitpractice

open class WeatherFormatter {
    open fun format(weather: Weather): String = "Weather is ${weather}"
}
