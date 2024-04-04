# Телеграм бот "Погода в городе"

Привет, друзья! 🌞

Этот проект представляет собой телеграм бота, который предоставляет информацию о погоде в указанном пользователем городе. С его помощью вы можете быстро узнать текущую температуру и другие погодные условия в вашем регионе.

Никаких сложных маневров - просто напишите название вашего города, и я мгновенно предоставлю вам актуальную информацию о погоде! 🚀

## Как использовать
Для использования бота, просто отправьте название интересующего вас города в чат с ботом. Он автоматически ответит вам текущей температурой в городе и дополнительной информацией о погоде.
## Пример:

Откройте чат с ботом в Telegram.
Напишите название города, о котором вы хотите узнать погоду.
Дождитесь ответа от бота с информацией о погоде в указанном городе.

``` kotlin
Copy code
fun processMessage(message: String): String {
    return if (message.startsWith("/start")) {
        "Привет! Пожалуйста, напишите название вашего города."
    } else {
        try {
            val weatherResponse = runBlocking {
                weatherApiClient.getCurrentWeather(message)
            }
            val location = weatherResponse.location
            val current = weatherResponse.current
            "В городе ${location.name}, ${location.region}, ${location.country} " +
                    "температура в данный момент составляет ${current.tempC}°C."
        } catch (e: Exception) {
            "К сожалению, не удалось получить информацию о погоде для города $message."
        }
    }
}
 ```
## Установка
Чтобы добавить бота в ваш чат, просто найдите его по имени "Weather Bot" в Telegram и нажмите кнопку "Start".

# Будьте в курсе погоды в вашем городе с нашим удивительным телеграм ботом! 🌞🌧️
