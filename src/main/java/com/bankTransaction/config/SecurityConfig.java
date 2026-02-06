package com.bankTransaction.config;


import com.bankTransaction.security.JwtFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;






@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final JwtFilter jwtAuthFilter;

    @Value("${cors.allowed.origins}")
    private String allowedOrigins;

    public SecurityConfig(JwtFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/register").permitAll()
                        .requestMatchers("/auth/thisislogin").permitAll()
                        .requestMatchers("/auth/yourcanchangepassword").authenticated()
                        .requestMatchers("/auth/youcanreset").permitAll()
                        .requestMatchers("/bvn/registerforbvn").permitAll()
                        .requestMatchers("/nin/registerfornin").permitAll()
                        .requestMatchers("/transactions/deposittoyouraccount").authenticated()
                        .requestMatchers("/transactions/transfertoyourfriend").authenticated()
                        .requestMatchers("/transactions/younurgolikebuyairtime").authenticated()
                        .requestMatchers("/transactions/getyourtransactionstatement").permitAll()
                        .requestMatchers("/transactions/getrecenttransaction").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();

        System.out.println("ALLOWED ORIGINS: " + allowedOrigins);

        List<String> origins = Arrays.asList(allowedOrigins.split(","));
        config.setAllowedOrigins(origins);
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowCredentials(true);
        config.setExposedHeaders(Arrays.asList("Authorization", "Content-Type"));
        config.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}
