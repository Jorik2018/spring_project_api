package gob.regionancash.project.config;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
//@EnableWebSecurity
@RequiredArgsConstructor
/*@EnableGlobalMethodSecurity(prePostEnabled = true)*/

//@EnableWebFluxSecurity(exclude = WebFluxSecurityConfiguration.class)
public class SecurityConfiguration {

   //private final JwtAuthenticationFilter jwtAuthFilter;

    //private final AuthenticationProvider authenticationProvider;

   /* @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("SecurityFilterChainSecurityFilterChainSecurityFilterChainSecurityFilterChain=======");
        http
                .cors().and()
                .csrf().disable()
                //*.authorizeRequests()//.authorizeHttpRequests()
                //.requestMatchers("/*")
                .permitAll()
                .authorizeRequests()
                //.requestMatchers("/**").permitAll()
                .requestMatchers("/**").anonymous()//.permitAll()
                .and()
                .anonymous()
                .and()
                .httpBasic()
                .and()

                //.requestMatchers("/atencion/**")
                // .authenticated()
                //.and()
                //.sessionManagement()
                // .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //.and()
                //.authenticationProvider(authenticationProvider)
                //.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
                ;

        return http.build();
    }*/

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("*"); // Set your allowed origin here
        config.addAllowedMethod("*"); // Set your allowed HTTP methods here
        config.addAllowedHeader("*"); // Set your allowed headers here
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public CorsFilter corsFilter() {
        return new CorsFilter(corsConfigurationSource());
    }

}