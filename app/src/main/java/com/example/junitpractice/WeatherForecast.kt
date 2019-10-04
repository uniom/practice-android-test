package com.example.junitpractice

class WeatherForecast(val satellite: Satellite,
                      val recorder: WeatherRecorder,
                      val formatter: WeatherFormatter) {
    fun shouldBringUmbrella(latitude: Double, longtitude: Double): Boolean {
        val weather = satellite.getWeather(latitude, longtitude)
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather(latitude: Double, longtitude: Double) {
        val weather = satellite.getWeather(latitude, longtitude)
        val formatted = formatter.format(weather)
        recorder.record(weather)
    }
}
