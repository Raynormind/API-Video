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
import com.projet.video.Exceptions.OperationNonAutoriseeException

import org.springframework.security.core.annotation.AuthenticationPrincipal



@Service
class VideosService(private val videosDAO: VideosDAO, private val utilisateursDAO: UtilisateursDAO){
    
    fun chercherTous(): List<Video> = videosDAO.chercherTous()
    
    fun chercherParId(id_video: Int): Video {
        val video = videosDAO.chercherParId(id_video)

        if ( video == null ) throw RessourceInexistanteException("La video $id_video n'est pas inscrite au service.")

        return video
    }
    
    fun chercherParTitre(titre: String): List<Video> = videosDAO.chercherParTitre(titre)


    fun chercherParStatut(status: String): List<Video> {
        val videos = videosDAO.chercherParStatut(status)

        if ( videos.size != 0 ){ throw RessourceInexistanteException("Aucunes videos ne font partis du status $status recherché.")}

        return videos
    }

    fun chercherParAuteur(auteur: Utilisateur): List<Video> = videosDAO.chercherParAuteur(auteur)

    @PreAuthorize("hasAuthority('write:videos')")
    fun ajouter(video: Video, courriel: String?): Video {
        if( courriel == null ){
            throw OperationNonAutoriseeException("Votre jeton d'accès ne contient pas les éléments nécesssaires à la création d'une video")
        }
    
        if(videosDAO.chercherParTitreUnique(video.titre)?.titre == video.titre ){ 
            throw ConflitAvecUneRessourceExistanteException("Il existe déjà une video avec le titre ${video.titre}.")
        }
        
        val auteur = utilisateursDAO.chercherParCourriel(courriel)

        if(auteur != null){
            video.auteur = auteur 
        }else{
            throw OperationNonAutoriseeException("L'utilisateur ${courriel} est inconnu.")
        }

        val nouvelleVideo = videosDAO.ajouter(video)
        
        if(nouvelleVideo == null) {
            throw MauvaiseRequeteException("La video nommer ${video.titre} n'a pas pu être créée.")
        }
        return nouvelleVideo
    }

   
    fun modifier(id_video: Int, video: Video, courriel: String?, listpermission : ArrayList<String>): Video? {
        if(listpermission.first() == null){
            throw OperationNonAutoriseeException("Cette video ne vous appartient pas.")
        
        }
        val videoOriginal = videosDAO.chercherParId(id_video)
        val auteur : Utilisateur?
        if(videoOriginal!= null){
            auteur = utilisateursDAO.chercherParId(videoOriginal.auteur.id_utilisateur)
            if(auteur != null){
                video.auteur = auteur
            }
        }
         
        val videoModifier : Video?         
        if( courriel == video.auteur.courriel || listpermission.contains("updateAll:videos"))
        {
           
            videoModifier = videosDAO.modifier(id_video, video)
            if( videoModifier != null ){
                return videoModifier
            } else { 
                throw RessourceInexistanteException("La video $id_video n'est pas inscrit au service.")
            }
        } else { 
            throw OperationNonAutoriseeException("Cette video ne vous appartient pas. ou ne posséder pas les droits")
        }
    } 

    fun effacer(id_video: Int, courriel: String?, listpermission : ArrayList<String>) {

        val videoÀSuprimer = videosDAO.chercherParId(id_video)
        if(listpermission.first() == null){
            throw OperationNonAutoriseeException("Cette video ne vous appartient pas.")
        
        }

        if(videoÀSuprimer == null){
            throw RessourceInexistanteException("La video $id_video n'est pas inscrit au service.")
        }

        if(listpermission.contains("deleteAll:videos") || courriel == videoÀSuprimer.auteur.courriel)
        {
                videosDAO.effacer(id_video)

        } else { 
            throw OperationNonAutoriseeException("Cette video ne vous appartient pas.")
        }
        
    }
}