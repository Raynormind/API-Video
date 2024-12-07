package com.projet.video.DAO


import com.projet.video.Modele.Utilisateur

interface UtilisateursDAO: DAO<Utilisateur> {
   
    override fun chercherParId(id_video: Int): Utilisateur?
}