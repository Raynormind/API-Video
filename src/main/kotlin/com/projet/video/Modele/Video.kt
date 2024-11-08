package com.projet.video.Modele
import java.time.LocalDate
data class Video(val id_video:Int, val titre:String, val description:String, val miniature:String, val fichiervideo:String,
val status:String, val auteur:Utilisateur, val datePublication:LocalDate){}