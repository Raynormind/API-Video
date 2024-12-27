package com.projet.video.Controleurs

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.annotation.AuthenticationPrincipal
import java.security.Principal

@RestController
class APIControleur {
    
    @GetMapping("/")
    fun index() = "Service web de l'API de video"
}
