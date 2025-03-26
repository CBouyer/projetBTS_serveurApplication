package com.moser.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.moser.models.*
import java.util.*


class TokenManager {

    fun generateAccessToken(user: User): String {
        val token = JWT.create()
            .withAudience(myAudience)   //Spécifie qui est censé utiliser ce token.
            .withIssuer(myIssuer)       //Indique qui a généré ce token.
            .withClaim("id_user", user.idadmin)
            .withClaim("role", user.role)
            .withClaim("username", user.username)
            .withClaim("type", "access_token")
            .withExpiresAt(Date(System.currentTimeMillis() + accessTokenValidity_ms))
            .sign(algorithme)
        return token
    }

    fun verifyJWTToken(): JWTVerifier {
        return JWT.require(Algorithm.HMAC256(mySecret))
            .withAudience(myAudience)   // Vérifie l'audience qui à utiliser le token
            .withIssuer(myIssuer)       // Vérifie l'émetteur
            .build()
    }

    fun generateRefreshToken(user: User, duree: Long): String {
        val token = JWT.create()
            .withAudience(myAudience)   //Spécifie qui est censé utiliser ce token.
            .withIssuer(myIssuer)       //Indique qui a généré ce token.
            .withClaim("idadmin", user.idadmin)
            .withClaim("role", user.role)
            .withClaim("username", user.username)
            .withClaim("type", "refresh_token")
            .withClaim("uuid", UUID.randomUUID().toString()) // Ajoute le claim UUID
            .withExpiresAt(Date(System.currentTimeMillis() + duree))
            .sign(algorithmeRefreshToken)
        return token
    }

    fun verifyJWTTokenRefreshToken (): JWTVerifier{
        return JWT.require(Algorithm.HMAC256(mySecretRefreshToken))
            .withAudience(myAudience)   // Vérifie l'audience qui à utiliser le token
            .withIssuer(myIssuer)       // Vérifie l'émetteur
            .build()
    }

}