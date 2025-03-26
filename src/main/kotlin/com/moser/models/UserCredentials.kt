package com.moser.models

import kotlinx.serialization.Serializable
import org.mindrot.jbcrypt.BCrypt

@Serializable
data class UserCredentials(
    val username: String,
    val password: String,
) {

    override fun toString(): String {
        return "UserCredentials(username=$username,password=$password)"
    }

    fun hashedPassword(): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }


}
