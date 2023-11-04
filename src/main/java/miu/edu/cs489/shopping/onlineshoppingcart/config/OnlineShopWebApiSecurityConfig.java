package miu.edu.cs489.shopping.onlineshoppingcart.config;

import miu.edu.cs489.shopping.onlineshoppingcart.filter.JWTAuthFilter;
import miu.edu.cs489.shopping.onlineshoppingcart.service.imp.OnlineShopUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Step8
 */
@Configuration
@EnableWebSecurity
public class OnlineShopWebApiSecurityConfig{
    private static final String[] AUTH_WHITE_LIST = {
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/v2/api-docs/**",
            "/swagger-resources/**"
    };

    private OnlineShopUserDetailsService advantisUserDetailsService;
    private JWTAuthFilter jwtAuthFilter;

    public OnlineShopWebApiSecurityConfig(OnlineShopUserDetailsService onlineShopUserDetailsService,
                                          JWTAuthFilter jwtAuthFilter) {
        this.advantisUserDetailsService = onlineShopUserDetailsService;
        this.jwtAuthFilter = jwtAuthFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        auth -> {
                            auth.requestMatchers("/osc/api/v1/service/public/**",
                                            "/v3/api-docs/**",
                                            "/swagger-ui/**",
                                            "/v2/api-docs/**",
                                            "/swagger-resources/**").permitAll()
//                                    .requestMatchers(AUTH_WHITE_LIST).permitAll()
                                    .requestMatchers("/osc/api/v1/customer/**").authenticated()
                                    .requestMatchers("/osc/api/v1/**").permitAll();
                        }
                )
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(advantisUserDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

