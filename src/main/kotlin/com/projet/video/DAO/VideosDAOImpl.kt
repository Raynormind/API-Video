package com.projet.video.DAO

import org.springframework.stereotype.Repository
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.query
import com.projet.video.Modele.Video
import com.projet.video.Modele.Utilisateur


@Repository
class VideosDAOImpl(private val bd: JdbcTemplate):VideosDAO {
    
    override fun chercherTous(): List<Video> = bd.query("select * from Video") { réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")), réponse.getDate("date_publication").toLocalDate())
    }

    override fun chercherParId(id_video: Int): Video? = bd.query("select * from Video v where v.id = ?") { réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")), réponse.getDate("date_publication").toLocalDate())
    }.singleOrNull()

    override fun chercherParTitre(titre: String): List<Video> = bd.query("select * from Video v where v.titre = ?"){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")), réponse.getDate("date_publication").toLocalDate())
    }

    override fun chercherParAuteur(auteur: Utilisateur): List<Video> = bd.query("select * from Video v, Utilisateur u where v.auteur = u.idUtilisateur and v.auteur = ?", auteur.id_utilisateur){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")), réponse.getDate("date_publication").toLocalDate())
    }

    override fun chercherParStatut(statut: String): List<Video> = bd.query("select * from Video v where v.status = ?"){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")), réponse.getDate("date_publication").toLocalDate())
    }

    override fun ajouter(video: Video): Video? = bd.query("insert into Video(titre, description, miniature, fichier_video, status, auteur) values(?, ?, ?, ?, ?, ?)", video.titre, video.description, video.miniature, video.fichiervideo, video.status, video.auteur){ réponse, _ ->
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")), réponse.getDate("date_publication").toLocalDate())
    }.single()
    
    override fun modifier(id_video: Int, video: Video): Video? = bd.query("Update Video set titre = ?, description = ?, miniature = ?, fichier_video = ?, status = ? , auteur = ?, date_publication = ? where idVideo = ?", video.titre, video.description, video.miniature, video.fichiervideo, video.status, video.auteur.id_utilisateur, video.datePublication, id_video){ réponse, _ -> 
        Video(réponse.getInt(1), réponse.getString("titre"), réponse.getString("description"), réponse.getString("miniature"), réponse.getString("fichier_video"), réponse.getString("status"), Utilisateur(réponse.getInt(1), réponse.getString("nom"), réponse.getString("courriel"),réponse.getString("coordonnées")), réponse.getDate("date_publication").toLocalDate())
    }.single()

    override fun effacer(id_video: Int) = bd.query("delete from Video where idVideo = ?",id_video){}
}