package fr.eni.projetenchere.dal;

import fr.eni.projetenchere.bo.Utilisateur;

public interface DaoUtilisateur {
    Utilisateur consultUserById(long id);
    Utilisateur consultUserByPseudo(String pseudo);
}
