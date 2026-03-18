package fr.eni.projetenchere.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Bean // on définit un bean pour l'utilitaire d'encryption de mot de passe
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean // on définit un bean pour la gestion des utilisateurs en mémoire
    public InMemoryUserDetailsManager userDetailsService() {
        List<UserDetails> userDetailsList = new ArrayList<>();
        /**
         * Création de 2 utilistaurs en mémoire de type UserDetials
         * Pour chaque utilistauer on définit: un username, un password encodé avec passwordEncoder, liste de rôles
         */
        userDetailsList.add(User.withUsername("membre").password(passwordEncoder().encode("membre"))
                .roles("user").build());
        userDetailsList.add(User.withUsername("admin").password(passwordEncoder().encode("admin"))
                .roles("admin", "user").build());
        return new InMemoryUserDetailsManager(userDetailsList);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // http.authorizeHttpRequests() : défini quelle requête HTTP j'autorise
        http.authorizeHttpRequests((authorize) -> authorize

                        .requestMatchers("/**").permitAll())
                // on effectue une authentification basique (user/mdp)
                .httpBasic(Customizer.withDefaults())
                // on utilise le formulaire par défaut de Spring
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll()
                )
                // quand on se déconnecte=> on redirige vers l'accueil
                .logout((logout) -> logout.logoutSuccessUrl("/"));
        return http.build();
    }
}