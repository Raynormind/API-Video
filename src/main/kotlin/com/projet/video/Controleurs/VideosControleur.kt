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
class VideosControleur( private val videosService: VideosService ) {


    @GetMapping("/")
    fun obtenirVideos(@AuthenticationPrincipal jeton: Jwt) : ResponseEntity<List<Video>> = ResponseEntity.ok(videosService.chercherTous(jeton.claims["permissions"] as ArrayList<String>, jeton.claims["courriel"] as? String?))

    @GetMapping("/{id_video}")
    fun obtenirVideoParId(@PathVariable id_video:Int) : ResponseEntity<Video> = ResponseEntity.ok(videosService.chercherParId( id_video))

    @GetMapping("?titre={titre}")
    fun obtenirVideoParRechercheTitre(@PathVariable titre: String) : ResponseEntity<Video> = ResponseEntity.ok(videosService.chercherParTitreUnique( titre ))

    @GetMapping("/status/{status}")
    fun obtenirStatutVideo(@PathVariable status: String) : ResponseEntity<List<Video>> = ResponseEntity.ok( videosService.chercherParStatut( status ))
        
    @PostMapping()
    fun creerVideo(@RequestBody video: Video, @AuthenticationPrincipal jeton: Jwt): ResponseEntity<Video>{ 
        
        val nouvelleVideo =  videosService.ajouter(video, jeton.claims["courriel"] as? String?, jeton.claims["permissions"] as ArrayList<String>)
        
        val uri = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{auteur}")
            .buildAndExpand(nouvelleVideo)
            .toUri()
        return ResponseEntity.created(uri).body(nouvelleVideo)
    } 

    @PutMapping("/{id_video}")
    fun modifierVideo(@PathVariable id_video: Int, @RequestBody video: Video, @AuthenticationPrincipal jeton: Jwt): ResponseEntity<Video> {
        if(videosService.chercherParId(id_video) != null){
            
            val videoMAJ = videosService.modifier(id_video, video, jeton.claims["courriel"] as? String?, jeton.claims["permissions"] as ArrayList<String>)
            return ResponseEntity.ok(videoMAJ)
        } else {
            return creerVideo(video, jeton)
        }
    } 

    @DeleteMapping("/{id_video}")
    fun supprimerVideo(@PathVariable id_video: Int, @AuthenticationPrincipal jeton: Jwt): ResponseEntity<Video> {
        videosService.effacer(id_video, jeton.claims["courriel"] as? String?, jeton.claims["permissions"] as ArrayList<String>)
        return ResponseEntity.noContent().build()
    } 
    
}