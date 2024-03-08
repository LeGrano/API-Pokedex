package fr.iut.sj.pkdxapi.configurations;

import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import fr.iut.sj.pkdxapi.repositories.UserRepository;
import fr.iut.sj.pkdxapi.services.CustomUserDetailsService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests((authorizeRequests) -> authorizeRequests
						.requestMatchers("/test").permitAll()
						.requestMatchers("/users/test").hasAuthority("ROLE_ADMIN")
						.requestMatchers("/pkmn/delete").hasAuthority("ROLE_ADMIN")
						.requestMatchers("/pkmn/update").hasAuthority("ROLE_ADMIN")
						.requestMatchers("/users/register").permitAll()
						.requestMatchers("/pkmn/types").authenticated()
						.requestMatchers("/pkmn/**").permitAll()
						.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults()).csrf(csrf -> csrf.disable()); // disable csrf security to
																					// authorize post, patch & delete

		return http.build();
	}

	/*
	 * @Bean
	 * public UserDetailsService userDetailsService(@Value("${password}") String
	 * pass) { // password is injected from application.properties
	 * String password="{noop}"+ pass;
	 * return (username) -> new User(username, password,
	 * AuthorityUtils.createAuthorityList("ROLE_USER"));
	 * }
	 */
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService(userRepository);
	}

	private UserRepository userRepository;

	public SecurityConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}