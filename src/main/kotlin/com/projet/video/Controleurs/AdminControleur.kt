package com.projet.video.Controleurs


import com.projet.video.Modèle.Video
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
class AdminController {

    @PutMapping("/videos/{id_video}?statut={statut}")
    fun modifierStatutVideo(@PathVariable id_video: Int, @PathVariable statut: String): ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)

    @DeleteMapping("/videos/{id_video}")
    fun supprimerVideo(@PathVariable id_video: Int): ResponseEntity<Video> = ResponseEntity(
        HttpStatus.NOT_IMPLEMENTED)
}