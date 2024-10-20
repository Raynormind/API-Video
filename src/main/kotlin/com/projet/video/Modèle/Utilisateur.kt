package com.projet.video.Modèle

data class Utilisateur(val id_utilisateur:Int, val nom:String, val videos: List<Video> = mutableListOf()){}

