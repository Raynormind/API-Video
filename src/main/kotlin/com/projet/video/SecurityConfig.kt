


import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.boot.autoconfigure.security.SecurityProperties
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.ProviderManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity.http
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
public class SecurityConfig {

    @Bean
	fun filterChain(http: HttpSecurity): SecurityFilterChain {
		http
			.authorizeHttpRequests {
				authorize -> authorize.anyRequest().authenticated()
			}
			.httpBasic { }
			.authenticationManager(authenticationManager())

		return http.build()
	}

    @Bean
	fun authenticationManager(): AuthenticationManager {
		val authenticationProvider = DaoAuthenticationProvider()
		authenticationProvider.setUserDetailsService(userDetailsService())
		authenticationProvider.setPasswordEncoder(passwordEncoder())

		val providerManager = ProviderManager(authenticationProvider)
		providerManager.isEraseCredentialsAfterAuthentication = false

		return providerManager
	}

    private fun userDetailsService(): UserDetailsService {
		val users = User.withDefaultPasswordEncoder()
		val user = users
			.username("Bob")
			.password("password")
			.roles("USER")
			.build()
		val admin = users
			.username("admin")
			.password("password")
			.roles("USER", "ADMIN")
			.build()

		return InMemoryUserDetailsManager(user, admin)
	}

	private fun passwordEncoder(): PasswordEncoder {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder()
	}
}