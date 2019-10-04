package com.example.junitpractice

import io.mockk.every
import org.junit.After
import org.junit.Before
import org.junit.Test

//import org.junit.Assert.*
import org.assertj.core.api.Assertions.*
//import org.mockito.ArgumentMatchers
//import org.mockito.Mockito.*

import io.mockk.mockk


class WeatherForecastTest {
    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast
    lateinit var recorder: MockWeatherRecorder
    lateinit var formatter: SpyWeatherFormatter

    @Before
    fun setUp() {
//        satellite = mock(Satellite::class.java)
        satellite = mockk<Satellite>()
//        `when`(satellite.getWeather(ArgumentMatchers.anyDouble(), ArgumentMatchers.anyDouble())).thenAnswer { invocation ->
         every { satellite.getWeather(any(), any()) } answers  {
             val latitude = invocation.args[0] as Double
             val longtitude = invocation.args[1] as Double

             if (latitude in 20.343..45.444 && longtitude in 3333.333..4444.4444) {
                return@answers Weather.SUNNY
             } else {
                return@answers Weather.RAINY
             }
        }
//        `when`(satellite.getWeather(eq(37.0000), eq(-122.000))).thenReturn(Weather.SUNNY)
        every { satellite.getWeather(eq(37.0000), eq(-122.000)) } returns Weather.SUNNY
//        `when`(satellite.getWeather(eq(38.000), eq(-23.000))).thenReturn(Weather.RAINY)
        every { satellite.getWeather(eq(38.000), eq(-23.000)) } returns  Weather.RAINY

        recorder = MockWeatherRecorder()
        formatter = SpyWeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnFalse() {
        val actual = weatherForecast.shouldBringUmbrella(35.444, 46.33)
        assertThat(actual).isTrue()
    }

    @Test
    fun recordCurrentWeather_assertCalled() {
        weatherForecast.recordCurrentWeather(333.333, 444.5555)

        val isCalled = recorder.isCalled
        assertThat(isCalled).isTrue()

        val weather = recorder.weather
        assertThat(weather).isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)
    }

    @Test
    fun recordCurrentWeather_assertFormatterCalled() {
        weatherForecast.recordCurrentWeather(333.4444, 222.22)

        val isCalled = formatter.isCalled
        assertThat(isCalled).isTrue()

        val weather = formatter.weather
        assertThat(weather).isIn(Weather.SUNNY, Weather.CLOUDY, Weather.RAINY)
    }
}
