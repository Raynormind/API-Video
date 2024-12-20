package com.projet.video.Mocks

import org.springframework.stereotype.Component
import org.springframework.security.authentication.TestingAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.test.context.support.WithSecurityContext
import org.springframework.security.test.context.support.WithSecurityContextFactory
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.Jwt.Builder

@WithSecurityContext( factory = MockSecurityContextFactory::class )
annotation class WithMockJwt( val nom: String ){
}

@Component("mockSecurityContext")
class MockSecurityContextFactory: WithSecurityContextFactory<WithMockJwt>{

	override fun createSecurityContext( jeton: WithMockJwt ) : SecurityContext {
		val context = SecurityContextHolder.createEmptyContext()

		lateinit var authorities: MutableList<SimpleGrantedAuthority>
		val jwt = if(jeton.nom == "admin") {
			authorities = mutableListOf( SimpleGrantedAuthority("deleteAll:videos"),
										 SimpleGrantedAuthority("delete:videos"),
										 SimpleGrantedAuthority("readAll:videos"), 
										 SimpleGrantedAuthority("read:videos"), 
										 SimpleGrantedAuthority("updateAll:videos"),
										 SimpleGrantedAuthority("update:videos"),
										 SimpleGrantedAuthority("write:videos")  )
			Jwt.withTokenValue("admin").header("test", "test").claim("courriel", "admin@test.com" ).claim("Roles", arrayOf("Admin")).build()
		}
		else {
			authorities = mutableListOf( SimpleGrantedAuthority("delete:videos"),
										 SimpleGrantedAuthority("update:videos"),
										 SimpleGrantedAuthority("write:videos")  )
			Jwt.withTokenValue(jeton.nom).header("test", "test").claim("courriel", jeton.nom+"@test.com" ).claim("Roles", arrayOf("User")).build()
		}

		context.authentication = TestingAuthenticationToken( jwt, "test", authorities )
		context.authentication.setAuthenticated( true )

		return context
	}
	
}

