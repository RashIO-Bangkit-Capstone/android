package id.rashio.android.data.repository

import id.rashio.android.domain.util.Resource
import id.rashio.android.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}