package com.serveur.bdd_MySql

import java.sql.*
import java.util.*

class Connexion(url:String, username:String, password:String) {

    private lateinit var conn: Connection
    private val url=url
    private val username =username
    private val password = password


     init {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver")
        } catch (ex: ClassNotFoundException) {
            println("erreur com.mysql.jdbc.Driver")
        }
        try {
            conn = DriverManager.getConnection(url,username,password)
            println("connexion OK")
        } catch (ex: SQLException) {
            println("erreur de connexion à la bdd")
        }
    }


    fun fermerConnexion(){
        conn.close()
    }

    fun getConnexion():Connection{
        return conn
    }


}