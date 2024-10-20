package com.projet.video.Modèle

data class Video(val id_video:Int, val titre:String, val description:String, val miniature:String(pathname), val fichiervideo:String(pathname),
val status:String, val auteur:String, val datePublication:DateTime){}