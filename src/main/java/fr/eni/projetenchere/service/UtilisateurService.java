package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dto.UtilisateurUpdateDto;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UtilisateurService {

    List<Utilisateur> consulterUtilisateurs();
    Utilisateur consultUserById(long id);
    void creerUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(long idUtilisateurASupprimer);

    Utilisateur consultUserByUsername(String username);
    UtilisateurUpdateDto consultUserDto(String username);
    void updateUserById(long id, UtilisateurUpdateDto utilisateurUpdateDto,
                                        @AuthenticationPrincipal UtilisateurSpringSecurity utilisateurConnecte, PasswordEncoder passwordEncoder);

}
