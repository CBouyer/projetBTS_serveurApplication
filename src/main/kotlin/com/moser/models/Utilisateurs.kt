package com.example.retrofit.models

data class Utilisateurs(
    val id_user:Int?=null,
    val username:String?=null,
    val password:String?=null,
    val role:String?=null,
) {

    override fun toString(): String {
        return "Utilisateurs(id=$id_user,username=$username,password=$password,role=$role)"
    }
}


