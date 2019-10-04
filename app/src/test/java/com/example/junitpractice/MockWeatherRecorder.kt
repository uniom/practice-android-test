package com.example.junitpractice

class MockWeatherRecorder : WeatherRecorder() {
    var weather: Weather? = null
    var isCalled = false

    override fun record(weather: Weather) {
        this.weather = weather
        isCalled = true
    }
}
