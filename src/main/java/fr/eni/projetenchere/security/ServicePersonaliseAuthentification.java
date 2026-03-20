package fr.eni.projetenchere.security;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.service.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//@Data
//@AllArgsConstructor
//@Service
//public class ServicePersonaliseAuthentification implements UserDetailsService {
//
//    UtilisateurService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Utilisateur user = userService.consulterUserByUsername(username);
//        return new UtilisateurSpringSecurity(user);
//    }
//}