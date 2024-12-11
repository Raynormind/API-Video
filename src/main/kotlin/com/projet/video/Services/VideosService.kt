package com.projet.video.Services

import org.springframework.stereotype.Service
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.access.prepost.PostAuthorize
import org.springframework.security.access.annotation.Secured
import com.projet.video.DAO.VideosDAO
import com.projet.video.DAO.UtilisateursDAO
import com.projet.video.Modele.Video
import com.projet.video.Modele.Utilisateur
import com.projet.video.Exceptions.RessourceInexistanteException
import com.projet.video.Exceptions.MauvaiseRequeteException
import com.projet.video.Exceptions.ConflitAvecUneRessourceExistanteException

@Service
class VideosService(private val videosDAO: VideosDAO, private val utilisateursDAO: UtilisateursDAO){
    @Secured("ROLE_ADMIN")
    fun chercherTous(): List<Video> = videosDAO.chercherTous()
    @PreAuthorize("hasRole('USER')")
	@PostAuthorize("hasRole('ADMIN') || authentication.principal.email =  returnObject.auteur.courriel")
    fun chercherParId(id_video: Int): Video {
        val video = videosDAO.chercherParId(id_video)

        if ( video == null ) throw RessourceInexistanteException("La video $id_video n'est pas inscrite au service.")

        return video
    }

    fun chercherParTitre(titre: String): List<Video> = videosDAO.chercherParTitre(titre)
    fun chercherParStatut(status: String): List<Video> = videosDAO.chercherParStatut(status)

    fun chercherParAuteur(auteur: Utilisateur): List<Video> = videosDAO.chercherParAuteur(auteur)
      
   
    @PreAuthorize("hasRole('USER') || hasRole('ADMIN')")
    fun ajouter(video: Video): Video {
        if(videosDAO.chercherParTitre(video.titre) != null ) throw ConflitAvecUneRessourceExistanteException("Il existe déjà une équipe avec le nom ${video.titre}.")
        val nouvelleVideo = videosDAO.ajouter(video)
        
        if(nouvelleVideo == null) throw MauvaiseRequeteException("L'équipe ${video.titre} n'a pas pu être créée.")

        return nouvelleVideo
    }
    @PreAuthorize("hasRole('USER')")
    fun modifier(id_video: Int, video: Video): Video? {
        val videoModifier : Video?         
        if( authentication.principal.email == video.auteur.courriel || hasRole("ADMIN"))
        {
            videoModifier =videosDAO.modifier(id_video, video)
            if( videoModifier != null ){
                return videoModifier
            } else { 
                return throw RessourceInexistanteException("La video $id_video n'est pas inscrit au service.")
            }
        } else { 
            return throw
        }
    }

    @PreAuthorize("hasRole('USER') ")
    @PostAuthorize("hasRole('ADMIN') || authentication.principal.username == returnObject.prenom ")
    fun effacer(id_video: Int) {
        if( authentication.principal.email == utilisateursDAO.chercherParId(videosDAO.chercherParId(id_video)?.auteur.id_utilisateur)  || hasRole("ADMIN"))
        {
            if(videosDAO.chercherParId(id_video) == null){
                videosDAO.effacer(id_video)
            } throw RessourceInexistanteException("La video $id_video n'est pas inscrit au service.")
        } else { 
            return throw
        }
        
    }
}