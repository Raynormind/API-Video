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
    
    override fun chercherTous(): List<Video> = bd.query("select * from Video v, Utilisateur u where u.idUtilisateur = v.auteur") { réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }.toList()
    
    override fun chercherParCourriel(courriel: String): Video?{throw UnsupportedOperationException()}

    override fun chercherParId(id_video: Int): Video? = bd.query("select * from Video v, Utilisateur u where u.idUtilisateur = v.auteur && v.idVideo = ?", id_video) { réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }.singleOrNull()

    override fun chercherParTitreUnique(titre: String): Video? = bd.query("select * from Video v, Utilisateur u where u.idUtilisateur = v.auteur && v.titre = ?",titre){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }.singleOrNull()
    override fun chercherParTitre(titre: String): List<Video> = bd.query("select * from Video v, Utilisateur u where u.idUtilisateur = v.auteur && v.titre = ?",titre){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }.toList()

    override fun chercherParAuteur(auteur: Utilisateur): List<Video> = bd.query("select * from Video v, Utilisateur u where v.auteur = u.idUtilisateur && v.auteur = ?", auteur.id_utilisateur){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }

    override fun chercherParStatut(status: String): List<Video> = bd.query("select * from Video v, Utilisateur u where u.idUtilisateur = v.auteur && v.status = ?", status){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
    }.toList()

    override fun ajouter(video: Video): Video? {
        
        val réponse = bd.update("insert into Video(titre, description, miniature, fichier_video, status, auteur) values(?, ?, ?, ?, ?, ?)", video.titre, video.description, video.miniature, video.fichiervideo, video.status, video.auteur.id_utilisateur)

        if(réponse > 0){
            
            return chercherParTitreUnique(video.titre)
        }
        return null
    }
 
    
    override fun modifier(id_video: Int, video: Video): Video? { 

       val videoOriginale = bd.query("select * from Video v, Utilisateur u where u.idUtilisateur = v.auteur && v.idVideo = ?", id_video) { réponse, _ ->
            Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
        }.singleOrNull()
        
        if( videoOriginale != null){
            bd.update("update Video set titre = ? where idVideo = ?", video.titre, id_video)
            bd.update("update Video set description = ? where idVideo = ?",  video.description, id_video)
            bd.update("update Video set miniature = ? where idVideo = ?", video.miniature, id_video)
            bd.update("update Video set fichier_video = ? where idVideo = ?", video.fichiervideo, id_video)
            bd.update("update Video set status = ? where idVideo = ?",  video.status, id_video)

            val videoModifier = bd.query("select * from Video v, Utilisateur u where u.idUtilisateur = v.auteur && v.idVideo = ?", id_video) { réponse, _ ->
                Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getDate("date_publication").toLocalDate(), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")))
            }.singleOrNull()

            if( videoModifier != null){
                return videoModifier
            }else{
                return null
            }
        }else{
            return null
        }
    }

    override fun effacer(id_video: Int){
        bd.update("delete from Video where idVideo = ?",id_video)
    }
}