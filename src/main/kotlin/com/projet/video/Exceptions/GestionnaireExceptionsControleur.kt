package com.projet.video.Exceptions

import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.http.HttpStatus
import java.time.LocalDateTime

@RestControllerAdvice
class GestionnaireExceptionsControleur() {

    @ExceptionHandler(RessourceInexistanteException::class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun gérerRessourceInexistanteException(exception: RessourceInexistanteException, requête: WebRequest): MessageErreur =
        MessageErreur(HttpStatus.NOT_FOUND.value(), LocalDateTime.now(), exception.message, requête.getDescription(false))

    @ExceptionHandler(MauvaiseRequeteException::class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    fun gérerMauvaiseRequeteException(exception: MauvaiseRequeteException, requête: WebRequest): MessageErreur =
        MessageErreur(HttpStatus.BAD_REQUEST.value(), LocalDateTime.now(), exception.message, requête.getDescription(false))

    @ExceptionHandler(ConflitAvecUneRessourceExistanteException::class)
    @ResponseStatus(code = HttpStatus.CONFLICT)
    fun gérerConflitAvecUneRessourceExistanteException(exception: ConflitAvecUneRessourceExistanteException, requête: WebRequest): MessageErreur =
        MessageErreur(HttpStatus.CONFLICT.value(), LocalDateTime.now(), exception.message, requête.getDescription(false))
        
    @ExceptionHandler(OperationNonAutoriseeException::class)
    @ResponseStatus(code = HttpStatus.FORBIDDEN)
    fun gérerOpérationNonAutoriséeException(exception: OperationNonAutoriseeException, requête: WebRequest): MessageErreur =
                MessageErreur(HttpStatus.FORBIDDEN.value(), LocalDateTime.now(), exception.message, requête.getDescription(false))


}