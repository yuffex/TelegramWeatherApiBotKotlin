package data

import domain.model.WeatherInfo
import domain.model.WeatherResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class WeatherApiClient(private val apiKey: String) {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://api.weatherapi.com/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val weatherApiService: WeatherApiService = retrofit.create(WeatherApiService::class.java)

    suspend fun getCurrentWeather(cityName: String): WeatherResponse {
        return weatherApiService.getCurrentWeather(apiKey, cityName)
    }
}
