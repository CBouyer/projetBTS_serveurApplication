package com.moser.plugins

import com.moser.models.SendToken
import com.moser.models.User
import com.moser.models.UserCredentials
import com.moser.models.refreshTokenValidity_ms
import com.moser.utils.TokenManager
import com.serveur.bdd_MySql.Gestion
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.freemarker.FreeMarkerContent
import io.ktor.server.request.receive
import io.ktor.server.response.*
import io.ktor.server.routing.*


fun Application.configureRouting() {
    var maGestion = Gestion()
    val tokenManager = TokenManager()

    routing {
        get("/") {
            call.respondText("test")

        }
        post("/login") {
            val userCredentials = call.receive<UserCredentials>()
            val username = userCredentials.username
            val password = userCredentials.password

            println("username = " + username)
            println("password = " + password)


            //test de l'existence du username dans la bdd
            val user = maGestion.userExist(username, password)
            println(user)
            if (user == null) {
                call.respond(HttpStatusCode.BadRequest, "Username  invalide.")
                return@post
            }

//                 //pour generer hach pour le loging de la bdd
//                    val passwordMatch =BCrypt.hashpw("michael",BCrypt.gensalt())
//                    println(passwordMatch)
//
//                //si username existe on teste l'égalité du hash du password
//                val passwordMatch = BCrypt.checkpw(password, user.password)
//                if (!passwordMatch) {
//                    call.respond(HttpStatusCode.BadRequest, "Username ou password invalide.")
//                    return@post
//                }
            if (password != user.password) {
                call.respond(HttpStatusCode.BadRequest, "password invalide.")

                return@post
            }

            val accessToken = SendToken(tokenManager.generateAccessToken(user))
            val refreshToken = tokenManager.generateRefreshToken(user, refreshTokenValidity_ms)
            val decodedJWT = tokenManager.verifyJWTTokenRefreshToken().verify(refreshToken)
            val idUser = decodedJWT.getClaim("idamin").asInt()
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
                println(maGestion.lireUser())
                call.respond(FreeMarkerContent("index.ftl", mapOf("user" to maGestion.lireUser())))
            }

            get("/all") {
                var allUtilisateurs = maGestion.lireUser()
                println("dans /user/all-> $allUtilisateurs ")
                when (allUtilisateurs.size > 0) {
                    true -> {
                        call.respond(HttpStatusCode.OK, allUtilisateurs)
                    }

                    false -> {
                        call.respond(HttpStatusCode.NoContent)
                    }
                }
            }
        }
    }
}
