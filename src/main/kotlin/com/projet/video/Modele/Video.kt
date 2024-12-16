package com.projet.video.Modele
import java.time.LocalDate
data class Video(var id_video:Int , 
    val titre:String, 
    var description:String,
    var miniature:String,
    var fichiervideo:String,
    var datePublication:LocalDate ,
    var status:String, 
    var auteur:Utilisateur ){

        

    }