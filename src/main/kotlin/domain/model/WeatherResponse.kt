package domain.model

data class WeatherResponse(
    val location: Location,
    val current: Current
)
