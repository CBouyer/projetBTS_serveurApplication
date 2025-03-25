package com.example.retrofit.models

data class Etudiant(
    val id:Int?=null,
    val firstname:String?=null,
    val lastname:String?=null,
    val age:String?=null,
    val image:String?=null
) {
    override fun toString(): String {
        return "Etudiant(id=$id,firstname=$firstname, lastname=$lastname, age=$age, image=$image)"
    }
}


