package com.projet.video.DAO

import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import com.projet.video.Modele.Video
import com.projet.video.Modele.Utilisateur
import com.projet.video.Exceptions.RessourceInexistanteException
import com.projet.video.DAO.UtilisateursDAO

@Repository
class VideosDAOImpl(private val bd: JdbcTemplate, private val utilisateursDAO : UtilisateursDAO ):VideosDAO {
    
    override fun chercherTous(): List<Video> = bd.query("select * from Video") { réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }.toList()
    
    override fun chercherParCourriel(courriel: String): Video?{throw UnsupportedOperationException()}

    override fun chercherParId(id_video: Int): Video? = bd.query("select * from Video v where v.id = ?") { réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }.singleOrNull()

    override fun chercherParTitre(titre: String): List<Video> = bd.query("select * from Video v where v.titre = ?"){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }.toList()

    override fun chercherParAuteur(auteur: Utilisateur): List<Video> = bd.query("select * from Video v, Utilisateur u where v.auteur = u.idUtilisateur and v.auteur = ?", auteur.id_utilisateur){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }

    override fun chercherParStatut(status: String): List<Video> = bd.query("select * from Video v where v.status = ?"){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }

    override fun ajouter(video: Video): Video? {
        
        val réponse = bd.update("insert into Video(titre, description, miniature, fichier_video, status, auteur) values(?, ?, ?, ?, ?, ?)", video.titre, video.description, video.miniature, video.fichiervideo, video.status, video.auteur.id_utilisateur)

        if(réponse > 0){
            return chercherParTitre(video.titre).get(0)
        }
        return null
    }
 
    
    override fun modifier(id_video: Int, video: Video): Video? = bd.query("Update Video set titre = ?, description = ?, miniature = ?, fichier_video = ?, status = ? , auteur = ?, date_publication = ? where idVideo = ?", video.titre, video.description, video.miniature, video.fichiervideo, video.status, video.auteur.id_utilisateur, video.datePublication, id_video){ réponse, _ -> 
        val utilisateur = utilisateursDAO.chercherParId(réponse.getInt("auteur"))
        val utilisateur_id =  réponse.getInt("auteur")
        
        if( utilisateur != null){
            Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur ( utilisateur.id_utilisateur, utilisateur.nom, utilisateur.courriel, utilisateur.coordonnées ) )
        } 
        else{
            throw RessourceInexistanteException("La video $utilisateur_id n'est pas inscrit au service.")
        } 

    }.singleOrNull()

    override fun effacer(id_video: Int){
        bd.update("delete from Video where idVideo = ?",id_video)
    }
}