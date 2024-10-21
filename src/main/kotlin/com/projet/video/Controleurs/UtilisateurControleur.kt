package com.projet.video.Controleurs

import com.projet.video.Modèle.Utilisateur
import com.projet.video.Modèle.Video
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
class UtilisateurController {
    
    @GetMapping("/video")
    fun obtenirUtilisateur() = Utilisateur(1, "Bob")


    @GetMapping("/videos")
    fun obtenirVideosUtilisateur(): ResponseEntity<Utilisateur> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @GetMapping("/videos/{id_video}")
    fun obtenirUneVideoUtilisateur(@PathVariable id_video:Int) : ResponseEntity<Utilisateur> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @PutMapping("/videos/{id_video}")
    fun modifierVideoUtilisateur(@PathVariable id_video: Int, @RequestBody video: Video): ResponseEntity<Utilisateur> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)
    
    @PostMapping("/videos")
    fun creerVideoUtilisateur(@RequestBody video: Video): ResponseEntity<Utilisateur> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/videos/{id_video}")
    fun supprimerVideoUtilisateur(@PathVariable id_video: Int): ResponseEntity<Utilisateur> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)
    
}