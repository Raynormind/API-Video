package com.projet.video.Services

import org.springframework.stereotype.Service
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.annotation.Secured
import com.projet.video.DAO.VideosDAO
import com.projet.video.Modele.Video
import com.projet.video.Modele.Utilisateur

@Service
class VideosService(private val videosDAO: VideosDAO){
    @Secured("ROLE_ADMIN")
    fun obtenirVideos(): List<Video> = videosDAO.chercherTous()
    @PreAuthorize("hasRole('USER')")
	@PostAuthorize("hasRole('ADMIN') || authentication.principal.username == returnObject.prenom ")
    fun obtenirUneVideoUtilisateur(id_video: Int): Video? = videosDAO.chercherParId(id_video)
    fun obtenirVideoParRechercheTitre(titre: String): List<Video> = videosDAO.chercherParTitre(titre)
    fun obtenirStatutVideo(statut: String): List<Video> = videosDAO.chercherParStatut(statut)

    fun chercherParAuteur(auteur: Utilisateur): List<Video?> = videosDAO.chercherParAuteur(auteur)
    fun ajouter(video: Video): Video? = videosDAO.ajouter(video)
    fun modifier(id_video: Int, video: Video): Video? = videosDAO.modifier(id_video, video)
    fun effacer(id_video: Int) {
        videosDAO.effacer(id_video)
        
    }
}