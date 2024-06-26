# Телеграм бот "Погода в городе"

## Привет, друзья! 🌞

Этот проект представляет собой телеграм бота, который предоставляет информацию о погоде в указанном пользователем городе. С его помощью вы можете быстро узнать текущую температуру и другие погодные условия в вашем регионе.

## Сервер и API

Для получения данных о погоде мы используем API от [Weather API](https://www.weatherapi.com/). Для доступа к этому API необходимо зарегистрироваться на их сайте и получить API ключ. Этот ключ будет использоваться в нашем приложении для запроса информации о погоде.

## Как получить токен для бота

1. Зайдите в Telegram и найдите бота под названием "BotFather".
2. Напишите ему команду "/start" для начала диалога.
3. Создайте нового бота с помощью команды "/newbot" и следуйте инструкциям.
4. После успешного создания бота, BotFather выдаст вам токен вашего бота. Сохраните этот токен в безопасном месте.

## Как использовать токен и API ключ

1. Скопируйте токен вашего бота, который был выдан BotFather.
2. Замените значение переменной `TELEGRAM_BOT_TOKEN` в файле `domain.Utils` на ваш токен.
3. Получите API ключ на сайте Weather API и замените значение переменной `WEATHER_API_KEY` в том же файле на ваш API ключ.

## Как использовать

Для использования бота, просто отправьте название интересующего вас города в чат с ботом. Он автоматически ответит вам текущей температурой в городе и дополнительной информацией о погоде.

### Пример:

1. Откройте чат с ботом в Telegram.
2. Напишите название города, о котором вы хотите узнать погоду.
3. Дождитесь ответа от бота с информацией о погоде в указанном городе.

```kotlin
private fun processMessage(message: String): String {
    return if (message.startsWith("/start")) {
        "Привет! Пожалуйста, напишите название вашего города."
    } else {
        try {
            val weatherResponse = runBlocking {
                weatherApiClient.getCurrentWeather(message)
            }
            val location = weatherResponse.location
            val current = weatherResponse.current
            "Город: ${location.name}\nСтрана: ${location.country}\n" +
                    "Температура в городе ${location.name}, ${location.region}, ${location.country} " +
                    "в данный момент составляет ${current.tempC}°C."
        } catch (e: Exception) {
            "К сожалению, не удалось получить информацию о погоде для города $message."
        }
    }
}
