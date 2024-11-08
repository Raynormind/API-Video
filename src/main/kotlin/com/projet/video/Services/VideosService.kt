package com.projet.video.Services

import org.springframework.stereotype.Service
import com.projet.video.DAO.VideosDAOImpl
import com.projet.video.Modele.Video
import com.projet.video.Modele.Utilisateur

@Service
class VideosService(private val videosDAO: VideosDAOImpl){
    //fun obtenirJoueurs(): List<Video> = videosDAO.chercherTous()
    //fun obtenirJoueurParId(idStr: String) : Video? {
    //    val id = (idStr.toIntOrNull() ?: 
    //        throw RequêteMalFormuléeException("L'id $idStr n'est pas dans un format valide."))
    //    return videosDAO.chercherParId(id)
    //}
    fun chercherParAuteur(auteur: Utilisateur): List<Video?> = videosDAO.chercherParAuteur(auteur)
    fun ajouter(video: Video): Video? = videosDAO.ajouter(video)
    fun modifier(id_video: Int, video: Video): Video? = videosDAO.modifier(id_video, video)
    fun effacer(id_video: Int) {
        videosDAO.effacer(id_video)
        
    }
}