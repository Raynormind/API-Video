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


@RestController
@RequestMapping("/videos")
/*ModifierList*/
class VideosController( private val videosService: VideosService ) {


    @GetMapping()
    fun obtenirVideos() = videosService.chercherTous()

    @GetMapping("/{id_video}")
    fun obtenirVideoParId(@PathVariable id_video:Int) = videosService.chercherParId( id_video )

    @GetMapping("?titre={titre}")
    fun obtenirVideoParRechercheTitre(@PathVariable titre: String) = videosService.chercherParTitre( titre )

    @GetMapping("/{id_video}/{status}")
    fun obtenirStatutVideo(@PathVariable id_video: Int, @PathVariable status: String) = videosService.chercherParStatut( id_video, status )

    @GetMapping("?auteur={nomAuteur}")
    fun obtenirVideoParRechercheAuteur(@PathVariable auteur: Utilisateur) : ResponseEntity<List<Video>> = ResponseEntity.ok( videosService.chercherParAuteur( auteur ) )
        
    @PostMapping
    fun creerVideo(@RequestBody video: Video): ResponseEntity<Video>{ 
        val nouvelleVideo =  videosService.ajouter(video)
        if( nouvelleVideo != null){
            val uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{auteur}")
                .buildAndExpand(nouvelleVideo.auteur)
                .toUri()

            return ResponseEntity.created(uri).body(nouvelleVideo)

        }
        return ResponseEntity.badRequest().build()

    } 

    @PutMapping("/{id_video}")
    fun modifierVideo(@PathVariable id_video: Int, @RequestBody video: Video): ResponseEntity<Video> = ResponseEntity.ok( videosService.modifier(id_video, video))

    @DeleteMapping("/{id_video}")
    fun supprimerVideo(@PathVariable id_video: Int): ResponseEntity<Video> {
        ResponseEntity.ok( videosService.effacer(id_video))
        return ResponseEntity.noContent().build()
    } 
    
}