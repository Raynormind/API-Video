package com.projet.video.Exceptions

import java.time.LocalDateTime

data class MessageErreur(val code: Int, val date: LocalDateTime, val message: String?, val chemin: String)