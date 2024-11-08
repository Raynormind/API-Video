package com.projet.video.DAO

import com.projet.video.Modele.Video
import com.projet.video.Modele.Utilisateur

interface VideosDAO: DAO<Video> {
    //override fun chercherTous(): List<Video>
    //override fun chercherParId(video_id: Int): Video?
    override fun chercherParAuteur(auteur: Utilisateur): List<Video>
    override fun ajouter(video: Video): Video?
    override fun modifier(video_id: Int, video: Video): Video?
    override fun effacer(video_id: Int)
}