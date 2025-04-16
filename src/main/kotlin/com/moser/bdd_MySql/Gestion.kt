import com.example.retrofit.models.Utilisateurs
import com.moser.models.User
import com.serveur.bdd_MySql.Connexion

class Gestion {
    private val laConnexion = Connexion("jdbc:mysql://127.0.0.1/application_serveurweb", "root", "root")
    // Pour Docker :
    // private val laConnexion = Connexion("jdbc:mysql://mysql/application_serveurweb", "root", "root")

    fun lireUser(): ArrayList<Utilisateurs> {
        val arLesUtilisateurs = ArrayList<Utilisateurs>()
        val prepStatement = laConnexion.getConnexion()
            .prepareStatement("SELECT * FROM application_serveurweb.info")
        val rs = prepStatement.executeQuery()
        while (rs.next()) {
            arLesUtilisateurs.add(Utilisateurs(rs.getInt("id"), rs.getString("username")))
        }
        return arLesUtilisateurs
    }

    fun ajoutUtilisateur(utilisateurs: Utilisateurs): Int {
        val prepStatement = laConnexion.getConnexion()
            .prepareStatement("INSERT INTO application_serveurweb.user (username,password,role) VALUES (?,?)")
        prepStatement.setString(1, utilisateurs.username)
        prepStatement.setString(2, utilisateurs.password)
        prepStatement.setString(3, utilisateurs.role)
        return prepStatement.executeUpdate()
    }

    fun supprimerUnEtudiant(id: Int): Int {
        val prepStatement = laConnexion.getConnexion()
            .prepareStatement("DELETE FROM application_serveurweb.user WHERE id_user=?")
        prepStatement.setInt(1, id)
        return prepStatement.executeUpdate()
    }

    fun lireUnUser(id: String): Utilisateurs {
        var utilisateur = Utilisateurs()
        val prepStatement = laConnexion.getConnexion()
            .prepareStatement("SELECT * FROM application_serveurweb.user WHERE id_user=?")
        prepStatement.setString(1, id)
        val rs = prepStatement.executeQuery()
        while (rs.next()) {
            utilisateur = Utilisateurs(rs.getInt("id_user"), rs.getString("username"))
        }
        return utilisateur
    }

    fun userExist(username: String, password: String): User? {
        val conn = laConnexion.getConnexion()

        // Vérifie d'abord dans la table admin
        var prepStatement = conn.prepareStatement(
            "SELECT * FROM application_serveurweb.admin WHERE username=? AND password=?"
        )
        prepStatement.setString(1, username)
        prepStatement.setString(2, password)
        var rs = prepStatement.executeQuery()
        if (rs.next()) {
            return User(
                idadmin = rs.getInt("idadmin"),
                username = rs.getString("username"),
                password = rs.getString("password"),
                role = rs.getString("role")
            )
        }

        // Sinon, vérifie dans la table user
        prepStatement = conn.prepareStatement(
            "SELECT * FROM application_serveurweb.user WHERE username=? AND password=?"
        )
        prepStatement.setString(1, username)
        prepStatement.setString(2, password)
        rs = prepStatement.executeQuery()
        if (rs.next()) {
            return User(
                idadmin = rs.getInt("id_user"),
                username = rs.getString("username"),
                password = rs.getString("password"),
                role = rs.getString("role")
            )
        }

        return null
    }

    fun lireRefreshTokenUUID(id_user: Int): String? {
        val prepStatement = laConnexion.getConnexion()
            .prepareStatement("SELECT * FROM application_serveurweb.user WHERE id_user=?")
        prepStatement.setInt(1, id_user)
        val rs = prepStatement.executeQuery()
        var uuid: String? = null
        if (rs.next()) {
            uuid = rs.getString("uuid")
        }
        return uuid
    }

    fun ecrireRefreshTokenUUID(id_user: Int, uuid: String) {
        val prepStatement = laConnexion.getConnexion()
            .prepareStatement("UPDATE application_serveurweb.user SET uuid = ? WHERE id_user = ?")
        prepStatement.setString(1, uuid)
        prepStatement.setInt(2, id_user)
        prepStatement.executeUpdate()
    }
}
