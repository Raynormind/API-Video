package com.projet.video.Modele
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.Instant


data class Video(var id_video:Int , 
    val titre:String, 
    var description:String,
    var miniature:String,
    var fichiervideo:String,
    var datePublication:LocalDate = LocalDate.now() ,
    var status:String ="public", 
    var auteur:Utilisateur = Utilisateur(0,"", "", "") ){

       

    }