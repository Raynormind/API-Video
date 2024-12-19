package com.projet.video.Controleurs

import com.projet.video.Modele.Utilisateur
import com.projet.video.Modele.Video
import com.projet.video.Services.VideosService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt



@RestController
@RequestMapping("/utilisateurs")
class UtilisateursControleur( private val videosService: VideosService ) {


    @GetMapping("/")
    fun obtenirUtilisateurs() : ResponseEntity<List<Video>> = ResponseEntity.ok(videosService.chercherTous())

    @GetMapping("/{id_utilisateur}")
    fun obtenirUtilisateurParId(@PathVariable id_video:Int) : ResponseEntity<Video> = ResponseEntity.ok(videosService.chercherParId( id_video ))

    @GetMapping("?titre={titre}")
    fun obtenirVideoParRechercheTitre(@PathVariable titre: String) : ResponseEntity<List<Video>> = ResponseEntity.ok(videosService.chercherParTitre( titre ))

    @GetMapping("/{status}")
    fun obtenirStatutVideo(@PathVariable status: String) : ResponseEntity<List<Video>> = ResponseEntity.ok( videosService.chercherParStatut( status ))

    @GetMapping("?auteur={nomAuteur}")
    fun obtenirVideoParRechercheAuteur(@PathVariable auteur: Utilisateur) : ResponseEntity<List<Video>> = ResponseEntity.ok( videosService.chercherParAuteur( auteur ))
    
}