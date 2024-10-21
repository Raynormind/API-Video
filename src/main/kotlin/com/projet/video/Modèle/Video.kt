package com.projet.video.Modèle
import java.time.LocalDate
data class Video(val id_video:Int, val titre:String, val description:String, val miniature:String, val fichiervideo:String,
val status:String, val auteur:String, val datePublication:LocalDate){}