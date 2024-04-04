package repository

import data.WeatherApiClient
import data.WeatherApiService
import domain.Utils
import domain.model.WeatherResponse
import kotlinx.coroutines.runBlocking
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

class MyTelegramBot(private val token: String) : TelegramLongPollingBot() {
    private val weatherApiClient = WeatherApiClient(Utils.WEATHER_API_KEY)

    override fun getBotToken(): String = token

    override fun getBotUsername(): String = "Weather Bot"

    override fun onUpdateReceived(update: Update?) {
        if (update != null && update.hasMessage() && update.message.hasText()) {
            val messageText = update.message.text
            val chatId = update.message.chatId
            val response = processMessage(messageText)
            sendTextMessage(chatId.toString(), response)
        }
    }

    private fun sendTextMessage(chatId: String, message: String) {
        val sendMessage = SendMessage()
        sendMessage.chatId = chatId
        sendMessage.text = message
        execute(sendMessage)
    }

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
}



