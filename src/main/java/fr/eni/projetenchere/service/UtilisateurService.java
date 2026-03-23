package fr.eni.projetenchere.service;

import fr.eni.projetenchere.bo.Utilisateur;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UtilisateurService {

    List<Utilisateur> consulterUtilisateurs();
    Utilisateur consultUserById(long id);
    void creerUtilisateur(Utilisateur utilisateur);
    void supprimerUtilisateur(long idUtilisateurASupprimer);

}
