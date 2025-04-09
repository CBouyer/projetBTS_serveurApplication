package com.moser.plugins

import com.moser.models.MonTest
import io.ktor.serialization.kotlinx.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.delay
import kotlinx.serialization.json.Json
import java.util.concurrent.CopyOnWriteArrayList
import kotlin.time.DurationUnit
import kotlin.time.toDuration

val webSocketSessions = CopyOnWriteArrayList<DefaultWebSocketSession>()

fun Application.configureSocket(){
    install(WebSockets){
        contentConverter = KotlinxWebsocketSerializationConverter(Json)
        pingPeriod = 60.toDuration(DurationUnit.SECONDS)
        timeout = 60.toDuration(DurationUnit.SECONDS)
        maxFrameSize = Long.MAX_VALUE
        masking = false
    }
    println("websocket OK")
    try {
        routing {
            webSocket("/ws") {
                webSocketSessions.add(this)
                try {
                    for (frame in incoming) {
                        if (frame is Frame.Text) {
                            println("Message reçu du client WebSocket: ${frame.readText()}")
                        }
                    }
                } catch (e: Exception) {
                    println("Erreur WebSocket: ${e.message}")
                } finally {
                    webSocketSessions.remove(this)
                }
            }
        }
    }catch (e:Exception){
        println(e.message)
    }
}