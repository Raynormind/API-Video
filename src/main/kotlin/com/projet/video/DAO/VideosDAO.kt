package com.projet.video.DAO

import com.projet.video.Modele.Video
import com.projet.video.Modele.Utilisateur

interface VideosDAO: DAO<Video> {
    override fun chercherTous(): List<Video>
    override fun chercherParId(id_video: Int): Video?
    override fun chercherParTitre(titre: String): List<Video>
    override fun chercherParAuteur(auteur: Utilisateur): List<Video>
    override fun chercherParStatut(statut: String): List<Video>
    override fun ajouter(video: Video): Video?
    override fun modifier(id_video: Int, video: Video): Video?
    override fun effacer(id_video: Int)
}