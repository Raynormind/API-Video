package com.projet.video.Controleurs

import com.projet.video.Modèle.Video
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
class PubliqueController {
    
    @GetMapping("/videoss")
    fun obtenirVideos(): ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/videoss/{id_video}")
    fun obtenirUneVideo(@PathVariable id_video: Int): ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/videoss?nom={titre}")
    fun obtenirVideoParRechercheTitre(@PathVariable titre: String) : ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED) 


}