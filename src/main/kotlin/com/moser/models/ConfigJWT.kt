package com.michael

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

var mySecret = "secretAstier"
var myIssuer = "http://0.0.0.0:8080/"
var myAudience = "http://0.0.0.0:8080/hello"
var myRealm = "Accés gestion Onduleur'"
var validityInMs = 60000 // 1min
var algorithme = Algorithm.HMAC256(mySecret)
