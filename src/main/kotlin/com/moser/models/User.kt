package com.moser.models

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val idadmin: Int? = null,
    val username: String? = null,
    val password: String? = null,
    val role: String? = null,
    val uuid: String? = null,
) {
    override fun toString(): String {
        return "User(id=$idadmin,username=$username,password=$password,role=$role)"
    }
}


