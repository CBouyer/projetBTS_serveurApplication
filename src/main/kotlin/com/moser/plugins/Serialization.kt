package com.moser

import freemarker.cache.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.event.*

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        gson {
        }
    }
    routing {
        get("/json/gson") {
            call.respond(mapOf("hello" to "world"))
        }
    }
}
