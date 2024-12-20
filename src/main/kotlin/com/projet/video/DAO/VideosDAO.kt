package com.projet.video.DAO

import com.projet.video.Modele.Video
import com.projet.video.Modele.Utilisateur
import org.springframework.security.oauth2.jwt.Jwt



interface VideosDAO: DAO<Video> {
    override fun chercherTous(): List<Video>
    override fun chercherParId(id_video: Int): Video?
    override fun chercherParTitreUnique(titre: String): Video?
    override fun chercherParAuteur(auteur: Utilisateur): List<Video>
    override fun chercherParStatut(status: String): List<Video>
    override fun ajouter(video: Video): Video?
    override fun modifier(id_video: Int, video: Video): Video?
    override fun effacer(id_video: Int)
}