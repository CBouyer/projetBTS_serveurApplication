package com.moser.plugins

import Gestion
import com.moser.models.SendToken
import com.moser.models.User
import com.moser.models.UserCredentials
import com.moser.models.refreshTokenValidity_ms
import com.moser.utils.TokenManager
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    val maGestion = Gestion()
    val tokenManager = TokenManager()

    routing {
        get("/") {
            call.respondText("test")
        }


        post("/login") {
            val credentials = call.receive<UserCredentials>()
            val username = credentials.username
            val password = credentials.password

            println("username = $username")
            println("password = $password")

            val user = maGestion.userExist(username, password)

            if (user == null) {
                call.respond(HttpStatusCode.BadRequest, "Identifiants invalides.")
                return@post
            }

            if (password != user.password) {
                call.respond(HttpStatusCode.BadRequest, "Mot de passe invalide.")
                return@post
            }

            val accessToken = SendToken(tokenManager.generateAccessToken(user))
            val refreshToken = tokenManager.generateRefreshToken(user, refreshTokenValidity_ms)
            val decodedJWT = tokenManager.verifyJWTTokenRefreshToken().verify(refreshToken)
            val idUser = decodedJWT.getClaim("id_user").asInt()
            val uuid = decodedJWT.getClaim("uuid").asString()

            println("AccessToken = ${accessToken.token}")
            println("RefreshToken = $refreshToken")

            call.response.cookies.append(
                Cookie(
                    name = "refreshToken",
                    value = refreshToken,
                    httpOnly = true,
                    secure = true,
                    maxAge = (refreshTokenValidity_ms / 1000).toInt()
                )
            )
            call.respond(HttpStatusCode.OK, accessToken)

            maGestion.ecrireRefreshTokenUUID(idUser, uuid)
        }

        authenticate("auth-jwt") {
            get("/dashboard") {
                call.respond(FreeMarkerContent("index.ftl", mapOf("user" to maGestion.lireUser())))
            }

            get("/all") {
                val allUtilisateurs = maGestion.lireUser()
                if (allUtilisateurs.isNotEmpty()) {
                    call.respond(HttpStatusCode.OK, allUtilisateurs)
                } else {
                    call.respond(HttpStatusCode.NoContent)
                }
            }
        }
    }
}

