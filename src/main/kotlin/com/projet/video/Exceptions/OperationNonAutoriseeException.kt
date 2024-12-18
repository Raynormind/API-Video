package com.projet.video.Exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ResponseStatusException

@ResponseStatus(HttpStatus.FORBIDDEN)
class OperationNonAutoriseeException(message: String? = null, cause: Throwable? = null) : RuntimeException(message, cause) {
}
