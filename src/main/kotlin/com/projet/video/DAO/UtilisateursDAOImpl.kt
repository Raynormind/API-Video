package com.projet.video.DAO

import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import org.springframework.security.oauth2.jwt.Jwt

import com.projet.video.Modele.Utilisateur


@Repository
class UtilisateursDAOImpl(private val bd: JdbcTemplate): UtilisateursDAO {
    
    override fun chercherParId(id_video: Int): Utilisateur? = bd.query("select * from Utilisateur u where u.idUtilisateur = ?", id_video) { réponse, _ ->
        Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"), réponse.getString("coordonnées"))
    }.singleOrNull()

    override fun chercherParCourriel(courriel: String): Utilisateur? = bd.query("select * from Utilisateur u where u.courriel = ?", courriel) { réponse, _ ->
        Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"), réponse.getString("coordonnées"))
    }.singleOrNull()

    override fun chercherTous(): List<Utilisateur> {throw UnsupportedOperationException() }
    
    override fun chercherParTitreUnique (titre: String): Utilisateur? { throw UnsupportedOperationException() }

    override fun chercherParTitre(titre: String): List<Utilisateur> { throw UnsupportedOperationException() }

    override fun chercherParAuteur(auteur: Utilisateur): List<Utilisateur> {throw UnsupportedOperationException() }

    override fun chercherParStatut(status: String): List<Utilisateur> {throw UnsupportedOperationException() }

    override fun ajouter(video: Utilisateur, jeton: Jwt): Utilisateur? { throw UnsupportedOperationException()}

    override fun modifier(id_video: Int, video: Utilisateur): Utilisateur? { throw UnsupportedOperationException() }

    override fun effacer(id_video: Int) { throw UnsupportedOperationException() }

    
}