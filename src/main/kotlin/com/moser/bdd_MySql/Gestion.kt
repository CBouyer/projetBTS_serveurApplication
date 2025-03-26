package com.serveur.bdd_MySql

import com.example.retrofit.models.Utilisateurs
import com.moser.models.User
import com.moser.models.UserCredentials


class Gestion() {
    var laConnexion = Connexion("jdbc:mysql://127.0.0.1/application_serveurweb", "root", "root")
    //pour docker
    //var laConnexion = Connexion("jdbc:mysql://mysql/application_serveurweb", "root", "root")


    fun lireUser(): ArrayList<Utilisateurs> {
        var arLesUtilisateurs = ArrayList<Utilisateurs>()
        var prepStatement = laConnexion.getConnexion()
            .prepareStatement("SELECT * from application_serveurweb.info")
        var rs = prepStatement.executeQuery()
        while (rs.next()) {
            arLesUtilisateurs.add(Utilisateurs(rs.getInt("idadmin"),
                rs.getString("username"),
            ))
        }
        return arLesUtilisateurs
    }

    fun ajoutUtilisateur(utilisateurs: Utilisateurs): Int {
         var prepStatement = laConnexion.getConnexion()
            .prepareStatement("insert into application_serveurweb.user (username,password) VALUES (?,?) ")
        prepStatement.setString(1,utilisateurs.username)
        prepStatement.setString(2,utilisateurs.password)
        return prepStatement.executeUpdate()
    }

    fun supprimerUnEtudiant(id:Int):Int{
        var prepStatement = laConnexion.getConnexion()
            .prepareStatement("delete from application_serveurweb.user where id_user=?")
        prepStatement.setInt(1,id)
        return prepStatement.executeUpdate()
    }

    fun lireUnUser(id: String): Utilisateurs {
        var utilisateurs = Utilisateurs()
        var prepStatement = laConnexion.getConnexion()
            .prepareStatement("select * from application_serveurweb.user where id_user=?")
        prepStatement.setString(1,id)
        var rs = prepStatement.executeQuery()
        while (rs.next()) {
            utilisateurs=(Utilisateurs(rs.getInt("id_user"),
                rs.getString("username"),
            ))
        }
        return utilisateurs
    }

    fun userExist(username: String, password: String): User {
        var user = User()
        var prepStatement = laConnexion.getConnexion()
            .prepareStatement("SELECT * from application_serveurweb.admin WHERE username=? AND password=?")
        prepStatement.setString(1, username)
        prepStatement.setString(2, password)
        var rs = prepStatement.executeQuery()
        while (rs.next()) {
            user = User(
                rs.getInt("idadmin"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("role"),
            )
        }
        return user
    }

    fun lireRefreshTokenUUID(id_user: Int): String? {
        var prepStatement = laConnexion.getConnexion()
            .prepareStatement("SELECT * from application_serveurweb.user WHERE id_user=?")
        prepStatement.setInt(1, id_user)
        var rs = prepStatement.executeQuery()
        var user: User? = null
        while (rs.next()) {
            user = User(
                rs.getInt("id_user"),
                rs.getString("role"),
                rs.getString("username"),
                rs.getString("password"),
                rs.getString("uuid")
            )
        }
        return user?.uuid
    }

    fun ecrireRefreshTokenUUID(id_user: Int, uuid: String)  {
        var prepStatement = laConnexion.getConnexion()
            .prepareStatement("UPDATE application_serveurweb.user SET uuid = ? WHERE id_user = ?")
        prepStatement.setString(1, uuid)
        prepStatement.setInt(2, id_user)
        var rs = prepStatement.executeUpdate()
    }
}