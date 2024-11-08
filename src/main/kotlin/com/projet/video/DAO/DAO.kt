package com.projet.video.DAO

import com.projet.video.Modele.Utilisateur

interface DAO<T> {
    //fun chercherTous(): List<T>
    //fun chercherParId(id_video: Int): T?
    //fun chercherParTitre(element: T): T?
    fun chercherParAuteur(auteur: Utilisateur): List<T>
    //fun chercherParStatut(element: T): List<T>
    fun ajouter(element: T): T?
    fun modifier(id_video: Int, element: T): T?
    fun effacer(id_video: Int)
}