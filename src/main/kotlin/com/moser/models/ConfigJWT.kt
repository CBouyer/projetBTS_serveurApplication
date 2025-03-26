package com.moser.models

import com.auth0.jwt.algorithms.Algorithm

var mySecret = "secretAstier"
var mySecretRefreshToken = "~6\\r?6BQ2^-kM!dk_(be4A/Xh`9L{7~v"
var myIssuer = "http://0.0.0.0:8080/"
var myAudience = "http://0.0.0.0:8080/hello"
var myRealm = "Serveur d'application"
var validityInMs = 60*1000 // 1min
var algorithme = Algorithm.HMAC256(mySecret)
var algorithmeRefreshToken = Algorithm.HMAC256(mySecretRefreshToken)
val accessTokenValidity_ms = 1L * 60L * 1000L // 1 minutes
//val refreshTokenValidity_ms = 7L * 24L * 3600L *1000L // 7 jours
val refreshTokenValidity_ms = 2L * 60L * 1000L // 10 minutes