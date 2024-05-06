package id.rashio.android.data.repository

import id.rashio.android.data.mappers.toWeatherInfo
import id.rashio.android.data.network.api.weather_api.WeatherAPI
import id.rashio.android.domain.util.Resource
import id.rashio.android.domain.weather.WeatherInfo
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val api: WeatherAPI
) : WeatherRepository {
    override suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo> {
        return try {
            Resource.Success(
                data = api.getWeatherData(
                        lat = lat,
                        long = long
                        ).toWeatherInfo()
            )

        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An error occurred")
        }
    }
}