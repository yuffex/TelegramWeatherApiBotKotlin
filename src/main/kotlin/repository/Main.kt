package repository

import domain.Utils
import kotlinx.coroutines.runBlocking
import org.telegram.telegrambots.meta.TelegramBotsApi
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession

fun main() {
    val botToken = Utils.TELEGRAM_BOT_TOKEN

    val bot = MyTelegramBot(botToken)
    runBlocking {
        TelegramBotsApi(DefaultBotSession::class.java).registerBot(bot)
    }
}

