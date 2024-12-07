package com.projet.video.Modele
import java.time.LocalDate
data class Video(val id_video:Int?, 
    val titre:String, 
    var description:String,
    var miniature:String,
    var fichiervideo:String,
    val datePublication:LocalDate,
    var status:String, 
    val auteur:Utilisateur){}