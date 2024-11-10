package com.projet.video.DAO

import com.projet.video.Modele.Utilisateur

interface DAO<T> {
    fun chercherTous(): List<T>
    fun chercherParId(id_video: Int): T?
    fun chercherParTitre(titre: String): List<T>
    fun chercherParAuteur(auteur: Utilisateur): List<T>
    fun chercherParStatut(statut: String): List<T>
    fun ajouter(element: T): T?
    fun modifier(id_video: Int, element: T): T?
    fun effacer(id_video: Int)
}