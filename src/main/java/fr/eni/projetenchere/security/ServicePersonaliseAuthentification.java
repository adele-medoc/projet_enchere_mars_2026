package fr.eni.projetenchere.security;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.service.UtilisateurService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ServicePersonaliseAuthentification implements UserDetailsService {

    @Autowired
    UtilisateurService utilisateurService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) {
        List<Utilisateur> users = utilisateurService.consulterUtilisateurs();
        for (Utilisateur utilisateur : users) {
            if (utilisateur.getUsername().equals(username)) {
                System.out.println(utilisateur.getMotDePasse());
                System.out.println(utilisateur.getMotDePasse().length());
                return new UtilisateurSpringSecurity(utilisateur);
            }
        }
        throw new UsernameNotFoundException(username);
    }
}

//@Data
//@AllArgsConstructor
//@Service
//public class ServicePersonaliseAuthentification implements UserDetailsService {
//
//    UtilisateurService userService;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Utilisateur user = userService.consultUserByUsername(username);
//        System.out.println("******************" + user + "******************");
//        return new UtilisateurSpringSecurity(user);
//    }

//    public UserDetails loadUserById(long id) throws UsernameNotFoundException {
//        Utilisateur user = userService.consultUserById(id);
//        return new UtilisateurSpringSecurity(user);
//    }
//}