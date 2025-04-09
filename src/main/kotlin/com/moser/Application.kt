package com.moser

import com.moser.mqtt.MQTT
import com.moser.plugins.*
import io.ktor.client.plugins.websocket.*
import io.ktor.server.application.*
import kotlinx.coroutines.runBlocking

fun main(args: Array<String>) {
    runBlocking {
        val mqtt = MQTT()
        mqtt.connect() // Connexion au broker MQTT
    }
    io.ktor.server.netty.EngineMain.main(args)

}

fun Application.module() {
    configureSocket()
    configureCors()
    configureAuthentication()
    configureSerialization()
    configureTemplating()
    configureRouting()

}
