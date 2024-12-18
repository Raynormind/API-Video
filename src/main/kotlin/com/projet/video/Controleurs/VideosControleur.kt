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
@RequestMapping("/videos")
//@CrossOrigin(origins = "https://atelier1.postman.co")
class VideosController( private val videosService: VideosService ) {


    @GetMapping("/")
    fun obtenirVideos() : ResponseEntity<List<Video>> = ResponseEntity.ok(videosService.chercherTous())

    @GetMapping("/{id_video}")
    fun obtenirVideoParId(@PathVariable id_video:Int) : ResponseEntity<Video> = ResponseEntity.ok(videosService.chercherParId( id_video ))

    @GetMapping("?titre={titre}")
    fun obtenirVideoParRechercheTitre(@PathVariable titre: String) : ResponseEntity<List<Video>> = ResponseEntity.ok(videosService.chercherParTitre( titre ))

    @GetMapping("/{status}")
    fun obtenirStatutVideo(@PathVariable status: String) : ResponseEntity<List<Video>> = ResponseEntity.ok( videosService.chercherParStatut( status ))

    @GetMapping("?auteur={nomAuteur}")
    fun obtenirVideoParRechercheAuteur(@PathVariable auteur: Utilisateur) : ResponseEntity<List<Video>> = ResponseEntity.ok( videosService.chercherParAuteur( auteur ))
        
    @PostMapping()
    fun creerVideo(@RequestBody video: Video, @AuthenticationPrincipal jeton: Jwt): ResponseEntity<Video>{ 
        
        val nouvelleVideo =  videosService.ajouter(video, jeton.claims["courriel"] as? String?)
        
        val uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{auteur}")
            .buildAndExpand(nouvelleVideo)
            .toUri()
        return ResponseEntity.created(uri).body(nouvelleVideo)
    } 

    @PutMapping("/{id_video}")
    fun modifierVideo(@PathVariable id_video: Int, @RequestBody video: Video, @AuthenticationPrincipal jeton: Jwt): ResponseEntity<Video> = ResponseEntity.ok( videosService.modifier(id_video, video, jeton.claims["courriel"] as? String?))

    @DeleteMapping("/{id_video}")
    fun supprimerVideo(@PathVariable id_video: Int, @AuthenticationPrincipal jeton: Jwt): ResponseEntity<Video> {
        ResponseEntity.ok( videosService.effacer(id_video, jeton))
        return ResponseEntity.noContent().build()
    } 
    
}