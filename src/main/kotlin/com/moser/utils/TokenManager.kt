package com.moser.utils

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.moser.models.*
import java.util.*


class TokenManager {

    fun generateAccessToken(user: User): String {
        return JWT.create()
            .withAudience(myAudience)
            .withIssuer(myIssuer)
            .withClaim("id_user", user.idadmin) // Garder id_user, pas idadmin
            .withClaim("role", user.role)
            .withClaim("username", user.username)
            .withClaim("type", "access_token")
            .withExpiresAt(Date(System.currentTimeMillis() + accessTokenValidity_ms))
            .sign(algorithme)
    }

    fun verifyJWTToken(): JWTVerifier {
        return JWT.require(algorithme)
            .withAudience(myAudience)
            .withIssuer(myIssuer)
            .build()
    }

    fun generateRefreshToken(user: User, duree: Long): String {
        return JWT.create()
            .withAudience(myAudience)
            .withIssuer(myIssuer)
            .withClaim("id_user", user.idadmin) // idem ici
            .withClaim("role", user.role)
            .withClaim("username", user.username)
            .withClaim("type", "refresh_token")
            .withClaim("uuid", UUID.randomUUID().toString())
            .withExpiresAt(Date(System.currentTimeMillis() + duree))
            .sign(algorithmeRefreshToken)
    }

    fun verifyJWTTokenRefreshToken(): JWTVerifier {
        return JWT.require(algorithmeRefreshToken)
            .withAudience(myAudience)
            .withIssuer(myIssuer)
            .build()
    }
}