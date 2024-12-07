package com.projet.video.Services

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.junit.jupiter.api.extension.ExtendWith
import java.time.LocalDate

import com.projet.video.DAO.*
import com.projet.video.Modele.*
import com.projet.video.Controleurs.*
import com.projet.video.Exceptions.*



@ExtendWith(MockitoExtension::class)
class VideosServiceTests {
    
    @Mock
    lateinit var mockDAO : VideosDAO
    
    val a1 =Utilisateur(id_utilisateur = 1,
        nom = "Alice Martin", 
        courriel = "alice.martin@domaine.com", 
        coordonnées ="+33 6 12 34 56 78")
    
    val a2 =Utilisateur(id_utilisateur = 2,
        nom = "Bob Dupont", 
        courriel = "bob.dupont@domaine.com", 
        coordonnées ="+33 6 12 34 56 79")
    
    val a3 =Utilisateur(id_utilisateur = 3,
        nom = "Chloé Bernard", 
        courriel = "chloe.bernard@domaine.com", 
        coordonnées ="+33 6 12 34 56 80")

    
    val v1 = Video(id_video = 1,
        titre = "Les Aventures d'Alice",
        description = "Une aventure captivante dans un monde fantastique.",
        miniature = "miniature1.jpg",
        fichiervideo = "video1.mp4",
        datePublication = LocalDate.parse("2023-01-01"),
        status = "public",
        auteur = a1)
   
    val v2 = Video(id_video = 2,
        titre = "Voyage au Centre de la Terre", 
        description = "Une exploration des profondeurs de notre planète.", 
        miniature = "miniature2.jpg",
        fichiervideo = "video2.mp4", 
        datePublication = LocalDate.parse("2023-01-02"),
        status = "privé", 
        auteur = a2)
	
    val v3 = Video(id_video = 3,
        titre = "La Magie de Noël", 
        description = "Un film sur l'esprit des fêtes.", 
        miniature = "miniature3.jpg",
        fichiervideo = "video3.mp4", 
        datePublication = LocalDate.parse("2023-01-03"),
        status = "public", 
        auteur = a3)
    val v4 = Video(id_video = 4,
        titre = "Un Monde Sous-Marin", 
        description = "Découvrez les merveilles de l'océan.", 
        miniature = "miniature4.jpg",
        fichiervideo = "video4.mp4", 
        datePublication = LocalDate.parse("2023-01-04"),
        status = "public", 
        auteur = a3)
    val v5 = Video(id_video = 5,
        titre = "Secrets de la Nature", 
        description = "Un documentaire sur la biodiversité.", 
        miniature = "miniature5.jpg",
        fichiervideo = "video5.mp4", 
        datePublication = LocalDate.parse("2023-01-05"),
        status = "privé", 
        auteur = a3)

    val v6 = Video(id_video = 6,
        titre = "Les Robots de Demain", 
        description = "Un aperçu des technologies futures.", 
        miniature = "miniature6.jpg",
        fichiervideo = "video6.mp4", 
        datePublication = LocalDate.parse("2023-01-06"),
        status = "public", 
        auteur = a3)
    
    val v7 = Video(id_video = 7,
        titre = "Histoire des Civilisations", 
        description = "Un voyage à travers le temps.", 
        miniature = "miniature7.jpg",
        fichiervideo = "video7.mp4", 
        datePublication = LocalDate.parse("2023-01-07"),
        status = "privé", 
        auteur = a3)

    @Test
	fun `Étant donné un admin recherchant toutes les videos, lorsqu'on récupère tous les videos on obtient, les même sept videos`(){

		//Mise en place
		val résultat_attendu = listOf<Video>( v1, v2, v3 ,v4, v5, v6, v7 )

		Mockito.`when`( mockDAO.chercherTous() ).thenReturn( résultat_attendu )

		val serviceTest = VideosService(videosDAO = mockDAO)

		//Action
		val résultat_observé = serviceTest.chercherTous()

		//Vérification
		assertEquals( résultat_attendu, résultat_observé )
	}

    @Test
	fun `Étant donné une video d'ID 1 lorsqu'on cherche la video par ID, on obtient la video complète`(){
		Mockito.doReturn( v1 ).`when`( mockDAO ).chercherParId( 1 )
		val cobaye = VideosService( mockDAO )

		val résultat_obtenu = cobaye.chercherParId( 1 )

		val résultat_attendu = v1
		
		assertEquals( résultat_attendu, résultat_obtenu )
	}

	@Test
	fun `Étant donné une banque de videos, lorsqu'on cherche une video avec un identifiant non existant, on obtient une RessourceInexistanteException`(){
		Mockito.doReturn( null ).`when`( mockDAO ).chercherParId( 215 )

		val cobaye = VideosService( mockDAO )

		assertFailsWith( RessourceInexistanteException::class ) {
			cobaye.chercherParId( 215 )
		}
	}


}