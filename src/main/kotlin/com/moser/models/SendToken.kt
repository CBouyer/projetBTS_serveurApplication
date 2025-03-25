package com.michael.models

import com.michael.validityInMs
import kotlinx.serialization.Serializable

@Serializable
data class SendToken(
    val id_user: Int,
    val username: String,
    val role: String,
    val token: String,
    val tokenTimeValid: Int = validityInMs,
)
