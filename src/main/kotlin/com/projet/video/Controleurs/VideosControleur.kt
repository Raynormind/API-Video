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

@RestController
@RequestMapping("/videos")
/*ModifierList*/
class VideosController( private val videosService: VideosService ) {


    @GetMapping()
    fun obtenirVideos(): ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/{id_video}")
    fun obtenirUneVideoUtilisateur(@PathVariable id_video:Int) : ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("?titre={titre}")
    fun obtenirVideoParRechercheTitre(@PathVariable titre: String) : ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/{id_video}/status")
    fun obtenirStatutVideo(@PathVariable id_video: Int, @PathVariable status: String): ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("?auteur={auteur}")
    fun obtenirVideoParRechercheAuteur(@PathVariable auteur: Utilisateur) : ResponseEntity<Video> = ResponseEntity.ok( videosService.chercherParAuteur( auteur.id_utilisateur ) )
        
    @PostMapping
    fun creerVideo(@RequestBody video: Video): ResponseEntity<Video> = ResponseEntity.ok( videosService.ajouter(video))

    @PutMapping("/{id_video}")
    fun modifierVideo(@PathVariable id_video: Int, @RequestBody video: Video): ResponseEntity<Video> = ResponseEntity.ok( videosService.modifier(id_video, video))

    @DeleteMapping("/{id_video}")
    fun supprimerVideo(@PathVariable id_video: Int): ResponseEntity<Video> {
        ResponseEntity.ok( videosService.effacer(id_video))
        return ResponseEntity.noContent().build()
    } 
    
}