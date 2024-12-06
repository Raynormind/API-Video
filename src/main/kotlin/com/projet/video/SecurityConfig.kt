




import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.web.invoke
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator
import org.springframework.security.oauth2.core.OAuth2TokenValidator
import org.springframework.security.oauth2.jwt.JwtValidators
import org.springframework.security.oauth2.jwt.JwtDecoder
import org.springframework.security.oauth2.jwt.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.config.Customizer.withDefaults
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.factory.PasswordEncoderFactories



@Configuration
@EnableMethodSecurity(prePostEnabled = true)

public class SecurityConfig {

	/*Code utilisateur jz
	jacob@live.ca pw : Jacob12$
	martine@gmail.com, pw : Martine12$
	luc@gmail.com, pw : Luc%*12$
	lesly@gamil.com pw : Lesly12$ 
	*/

	@Bean
	@throw(Exception::class)
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		return http
			.authorizeHttpRequests {
				it.requestMatchers("/videos").permitAll()
				.anyRequest().authenticated()
			/* 	
			authorizeRequests {
				authorize("/videos", permitAll)
				authorize("/videos/private", authenticated)
				authorize("/videos?auteur={nomAuteur}", hasAuthority("SCOPE_read:videos"))
				authorize("/videos", hasAuthority("SCOPE_write:videos"))
				authorize("/videos/{id_video}", hasAuthority("SCOPE_update:videos"))
				authorize("/videos/{id_video}", hasAuthority("SCOPE_delete:videos"))
			}
		*/	
			}
			.cors(withDefaults())
			.oauth2ResourceServer { oauth2 ->
                oauth2 .jwt(withDefaults())
			}
			.build()
	}
		
}
