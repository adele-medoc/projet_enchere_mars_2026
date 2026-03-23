package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Utilisateur;

import java.util.List;

public interface DaoUtilisateur {
    Utilisateur consultUserById(long id);
    Utilisateur consultUserByUsername(String username);

    void creerUtilisateur(Utilisateur utilisateur);

    List<Utilisateur> listUtilisateurs();

    void supprimerUtilisateur(long idUtilisateur);
}
