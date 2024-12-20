package com.projet.video.DAO

import com.projet.video.Modele.Utilisateur
import org.springframework.security.oauth2.jwt.Jwt


interface DAO<T> {
    fun chercherTous(): List<T>
    fun chercherParId(id_video: Int): T?
    fun chercherParCourriel(courriel: String): T?
    fun chercherParTitreUnique(titre: String): T?
    fun chercherParAuteur(auteur: Utilisateur): List<T>
    fun chercherParStatut(status: String): List<T>
    fun ajouter(video: T): T?
    fun modifier(id_video: Int, video: T): T?
    fun effacer(id_video: Int)
}