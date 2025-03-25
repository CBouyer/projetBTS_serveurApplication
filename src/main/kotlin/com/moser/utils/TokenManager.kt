package com.michael.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.michael.*
import com.michael.models.User
import java.util.*

class TokenManager {
    val expirationDate = System.currentTimeMillis() + validityInMs //expiration du jeton toute les heures

    fun generateJWTToken(user: User): String {
        val token = JWT.create()
            .withAudience(myAudience)
            .withIssuer(myIssuer)
            .withClaim("username", user.username)
            .withClaim("id_user", user.id_user)
            .withExpiresAt(Date(expirationDate))
            .sign(algorithme)
        return token
    }

    fun verifyJWTToken(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(mySecret))
            .withAudience(myAudience)
            .withIssuer(myIssuer)
            .build()
    }
}