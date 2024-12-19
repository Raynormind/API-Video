package com.projet.video.Services


import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import org.mockito.Mock
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.description
import org.mockito.kotlin.verify
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.security.oauth2.jwt.Jwt
import java.time.LocalDate

import com.projet.video.DAO.*
import com.projet.video.Modele.*
import com.projet.video.Controleurs.*
import com.projet.video.Exceptions.*



@ExtendWith(MockitoExtension::class)
class VideosServiceTests {
    
    @Mock
    lateinit var mockvideoDAO : VideosDAO
    @Mock
    lateinit var mockUserDAO : UtilisateursDAO
    
    val a1 = Utilisateur(id_utilisateur = 101,
        nom = "Jacob Zapitoski", 
        courriel = "jacobz@live.ca", 
        coordonnées ="+33 6 12 34 56 178")
    
    val a2 = Utilisateur(id_utilisateur = 102,
        nom = "Lesly-Junior Gourdet", 
        courriel = "lesly@gmail.com", 
        coordonnées ="+33 6 12 34 56 179")
    
    val a3 = Utilisateur(id_utilisateur = 103,
        nom = "Martine Marchand",
        courriel = "martine@gmail.com", 
        coordonnées ="+33 6 12 34 56 180")
    
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

    val v8 = Video(id_video = 8,
        titre = "Histoire des Civilisation", 
        description = "Recettes et astuces culinaires.", 
        miniature = "miniature8.jpg",
        fichiervideo = "video8.mp4", 
        datePublication = LocalDate.parse("2023-01-08"),
        status = "public", 
        auteur = a1)

    @Test
	fun `Étant donné un admin recherchant toutes les videos, lorsqu'on récupère tous les videos on obtient, les même sept videos`(){

		//Mise en place
		val résultat_attendu = listOf<Video>( v1, v2, v3 ,v4, v5, v6, v7 )

		Mockito.`when`( mockvideoDAO.chercherTous() ).thenReturn( résultat_attendu )

		val serviceTest = VideosService(mockvideoDAO, mockUserDAO )

		//Action
		val résultat_observé = serviceTest.chercherTous()

		//Vérification
		assertEquals( résultat_attendu, résultat_observé )
	}

    @Test
	fun `Étant donné une video d'ID 1 lorsqu'on cherche la video par ID, on obtient la video complète`(){
		Mockito.doReturn( v1 ).`when`( mockvideoDAO  ).chercherParId( 1 )
		val cobaye = VideosService( mockvideoDAO ,mockUserDAO )

		val résultat_obtenu = cobaye.chercherParId( 1 )

		val résultat_attendu = v1
		
		assertEquals( résultat_attendu, résultat_obtenu )
	}

	@Test
	fun `Étant donné une banque de videos, lorsqu'on cherche une video avec un identifiant non existant, on obtient une RessourceInexistanteException`(){
		Mockito.doReturn( null ).`when`( mockvideoDAO  ).chercherParId( 215 )

		val cobaye = VideosService( mockvideoDAO, mockUserDAO  )
        

		assertFailsWith( RessourceInexistanteException::class ) {
			cobaye.chercherParId( 215 )
		}
	}

    @Test
	fun `Étant donné un utilisateur non authentifier, lorsqu'on demande de rechercher une video par titre, on obtient une liste de video corespondant à la recherche`(){
		
        val résultat_attendu = listOf<Video>( v7, v8 )
        Mockito.`when`(mockvideoDAO.chercherParTitre(v8.titre)).thenReturn(résultat_attendu)

		val cobaye = VideosService( mockvideoDAO, mockUserDAO )
        
        val résultat_obtenu = cobaye.chercherParTitre(v8.titre)
        
       
		assertEquals( résultat_attendu.get(0), résultat_obtenu.get(0) )
	}
 
    @WithMockJwt( nom = "bob" )
    @Test
	fun `Étant donné un utilisateur authentifié etune vidéo, lorsqu'on ajoute une video, on obtient la nouvelle video`(){
		
        val nouvelleVideo =Video(
            id_video = 0,
            titre = "Histoire des Civilisatio", 
            description = "Recettes et astuces culinaires.", 
            miniature = "miniature9.jpg",
            fichiervideo = "video9.mp4", 
            datePublication = LocalDate.parse("2023-01-29"),
            status = "public", 
            auteur = a1
        )

        val résultat_attendu = Video(
            id_video = 9,
            titre = "Histoire des Civilisatio", 
            description = "Recettes et astuces culinaires.", 
            miniature = "miniature9.jpg",
            fichiervideo = "video9.mp4", 
            datePublication = LocalDate.parse("2023-01-29"),
            status = "public", 
            auteur = a1
        )

        val userCourriel = "jacobz@live.ca"
       
        Mockito.`when`(mockvideoDAO.chercherParTitre("Histoire des Civilisatio")).thenReturn( listOf(nouvelleVideo, v8, v7) )
        Mockito.`when`(mockUserDAO.chercherParCourriel(userCourriel)).thenReturn(a1)
        Mockito.`when`(mockvideoDAO.ajouter(nouvelleVideo)).thenReturn(résultat_attendu)
        

		val cobaye = VideosService( mockvideoDAO, mockUserDAO  )
        
      
        val résultat_obtenu = cobaye.ajouter(nouvelleVideo, userCourriel)
       

		assertEquals( résultat_attendu, résultat_obtenu )
	}

    @Test
	fun `Étant donné un utilisateur authentifié et une vidéo existante , lorsqu'on ajoute la video, on obtient une ConflitAvecUneRessourceExistanteException`(){
		
        val nouvelleVideo = Video(
            id_video = 0,
            titre = "Histoire des Civilisatio", 
            description = "Recettes et astuces culinaires.", 
            miniature = "miniature9.jpg",
            fichiervideo = "video9.mp4", 
            datePublication = LocalDate.parse("2023-01-29"),
            status = "public", 
            auteur = a1
        )
    
        val usercourriel = "jacobz@live.ca"
       
        Mockito.`when`(mockvideoDAO.chercherParTitre("Histoire des Civilisatio")).thenReturn( listOf(v8, v7))
		val cobaye = VideosService( mockvideoDAO, mockUserDAO  )
       

		assertFailsWith( ConflitAvecUneRessourceExistanteException::class ) {
			cobaye.ajouter(nouvelleVideo, usercourriel)
		}
	}
    
    @Test
	fun `Étant donné un utilisateur authentifié n'ayant pas la propriété d'une vidéo, lorsqu'on ajoute la video, on obtient une OperationNonAutoriseeException`(){
		
        val nouvelleVideo = Video(
            id_video = 0,
            titre = "Histoire des Civilisatio", 
            description = "Recettes et astuces culinaires.", 
            miniature = "miniature9.jpg",
            fichiervideo = "video9.mp4", 
            datePublication = LocalDate.parse("2023-01-29"),
            status = "public", 
            auteur = a1
        )
       
		val cobaye = VideosService( mockvideoDAO, mockUserDAO  )
       
		assertFailsWith( OperationNonAutoriseeException::class ) {
			cobaye.ajouter(nouvelleVideo, null)
		}
	}

    @Test
	fun `Étant donné un utilisateur authentifié, lorsqu'on ajoute la video, on obtient une MauvaiseRequeteException`(){
		
        val nouvelleVideo =Video(
            id_video = 0,
            titre = "Histoire des Civilisatio", 
            description = "Recettes et astuces culinaires.", 
            miniature = "miniature9.jpg",
            fichiervideo = "video9.mp4", 
            datePublication = LocalDate.parse("2023-01-29"),
            status = "public", 
            auteur = a1
        )

        val résultat_attendu = null

        val userCourriel = "jacobz@live.ca"
       
        Mockito.`when`(mockvideoDAO.chercherParTitre("Histoire des Civilisatio")).thenReturn( listOf(nouvelleVideo, v8, v7) )
        Mockito.`when`(mockUserDAO.chercherParCourriel(userCourriel)).thenReturn(a1)
        Mockito.`when`(mockvideoDAO.ajouter(nouvelleVideo)).thenReturn(résultat_attendu)
        

		val cobaye = VideosService( mockvideoDAO, mockUserDAO  )
      

		assertFailsWith( MauvaiseRequeteException::class ) {
			cobaye.ajouter(nouvelleVideo, userCourriel)
		}
	}

    @Test
	fun `Étant donné un utilisateur authentifié propriétaire d'une video, lorsqu'on modifie la video, on obtient une RessourceInexistanteException`(){
		
        val idVideo = 101
        val videoAModifier = Video(id_video = idVideo,
        titre = "Voyage au Centre de la Terre Cuite", 
        description = "Une exploration des profondeurs de notre planète.", 
        miniature = "miniature2.jpg",
        fichiervideo = "video2.mp4", 
        datePublication = LocalDate.parse("2023-01-02"),
        status = "privé", 
        auteur = a2)

        val userCourriel = "lesly@gmail.com"
       
       
        Mockito.`when`(mockvideoDAO.modifier(idVideo, videoAModifier)).thenReturn( null )
        

		val cobaye = VideosService( mockvideoDAO, mockUserDAO  )
      
		cobaye.modifier(idVideo, videoAModifier, userCourriel)
		
       

		assertFailsWith( RessourceInexistanteException::class ) {
			cobaye.modifier(idVideo, videoAModifier, userCourriel)
		}
	}

    @Test
	fun `Étant donné un utilisateur authentifié non propriétaire d'une video, lorsqu'on modifie la video, on obtient une OperationNonAutoriseeException`(){
		
        val idVideo = 2
        val videoAModifier = Video(id_video = idVideo,
        titre = "Voyage au Centre de la Terre Cuite", 
        description = "Une exploration des profondeurs de notre planète.", 
        miniature = "miniature2.jpg",
        fichiervideo = "video2.mp4", 
        datePublication = LocalDate.parse("2023-01-02"),
        status = "privé", 
        auteur = a2)

    

         val userCourriel = "x"
       
       
        Mockito.`when`(mockvideoDAO.modifier(idVideo, videoAModifier)).thenReturn( null )
        

		val cobaye = VideosService( mockvideoDAO, mockUserDAO  )
      
		val résultat_observé = cobaye.modifier(idVideo, videoAModifier, userCourriel)
		
        

		assertFailsWith( OperationNonAutoriseeException::class ) {
			cobaye.modifier(idVideo, videoAModifier, userCourriel)
		}
	}

    @Test
	fun `Étant donné un utilisateur authentifié  propriétaire d'une video, lorsqu'on modifie la video, on obtient une video mis à jour`(){
		
        val idVideo = 2
        val videoAModifier = Video(id_video = idVideo,
        titre = "Voyage au Centre de la Terre Cuite", 
        description = "Une exploration des profondeurs de notre planète.", 
        miniature = "miniature2.jpg",
        fichiervideo = "video2.mp4", 
        datePublication = LocalDate.parse("2023-01-02"),
        status = "privé", 
        auteur = a2)

        val résultat_attendu = Video(id_video = 2,
        titre = "Voyage au Centre de la Terre Cuite", 
        description = "Une exploration des profondeurs de notre planète.", 
        miniature = "miniature2.jpg",
        fichiervideo = "video2.mp4", 
        datePublication = LocalDate.parse("2023-01-02"),
        status = "public", 
        auteur = a2)

         val userCourriel = "lesly@gmail.com"
       
       
        Mockito.`when`(mockvideoDAO.modifier(idVideo, videoAModifier)).thenReturn( résultat_attendu )
        

		val cobaye = VideosService( mockvideoDAO, mockUserDAO  )
      
		val résultat_observé = cobaye.modifier(idVideo, videoAModifier, userCourriel)
		
        assert(résultat_attendu.equals(résultat_observé))
        
	}
/*
    @Test
	fun `Étant donné un utilisateur authentifié , lorsqu'on suprime l'une de ses videos, on obtient la nouvelle video`(){
		
        
        // Étant donné
		val cobaye = VideosService( mockvideoDAO, mockUserDAO )
        Mockito.`when`( mockvideoDAO.chercherTous() ).thenReturn( listOf<Video>( v1, v2, v3 ,v4, v5, v6, v7 ) )
        Mockito.`when`(mockUserDAO.chercherParCourriel("jacobz@live.ca")).thenReturn( a1 )
        Mockito.`when`(mockvideoDAO.chercherParId(v1.id_video)).thenReturn(v1)
        
        // lorsque
        Mockito.`when`(mockvideoDAO.effacer(v1.id_video)).

        verify(mockvideoDAO.effacer(v1.id_video))

        // alors
        val résultat_obtenu = cobaye.chercherTous()
        val résultat_attendu = listOf<Video>( v2, v3 ,v4, v5, v6, v7 )

		assertEquals( résultat_attendu, résultat_obtenu )
	}
*/
}