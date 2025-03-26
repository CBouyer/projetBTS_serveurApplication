package com.moser

import com.moser.mqtt.MQTT
import com.moser.plugins.configureAuthentication
import com.moser.plugins.configureCors
import com.moser.plugins.configureRouting
import com.moser.plugins.configureSerialization
import com.moser.plugins.configureTemplating
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
    configureCors()
    configureAuthentication()
    configureSerialization()
    configureTemplating()
    configureRouting()

}
