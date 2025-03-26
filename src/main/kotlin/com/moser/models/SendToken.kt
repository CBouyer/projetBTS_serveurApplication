package com.moser.models

import kotlinx.serialization.Serializable

@Serializable
data class SendToken(
    val token: String
)