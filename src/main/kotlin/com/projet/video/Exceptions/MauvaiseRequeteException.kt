package com.projet.video.Exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(HttpStatus.BAD_REQUEST)
class MauvaiseRequeteException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause) {
}