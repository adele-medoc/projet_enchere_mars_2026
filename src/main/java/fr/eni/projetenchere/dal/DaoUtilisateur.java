package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;

public interface DaoUtilisateur {
    Utilisateur consultUserById(long id);
    Utilisateur consultUserByUsername(String username);

    void updateUserInfo(long id, Utilisateur utilisateur, UtilisateurSpringSecurity authenticatedUser);
}
