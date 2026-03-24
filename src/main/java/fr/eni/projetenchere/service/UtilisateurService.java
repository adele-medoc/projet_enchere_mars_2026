package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dto.UtilisateurUpdateDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UtilisateurService {

    List<Utilisateur> consulterUtilisateurs();
    Utilisateur consultUserById(long id);
    void creerUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(long idUtilisateurASupprimer);
    UtilisateurUpdateDto consultUserByUsername(String username);
}
