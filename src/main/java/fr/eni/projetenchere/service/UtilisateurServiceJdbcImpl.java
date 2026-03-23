package fr.eni.projetenchere.service;


import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DaoUtilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("prod")
public class UtilisateurServiceJdbcImpl implements UtilisateurService{

    // On utilise le PasswordEncoder défini dans SecurityConfiguration afin d'encoder le mot de passe des utilisateurs lors de leur création
//    @Autowired
//    private PasswordEncoder passwordEncoder;
    @Autowired
    private DaoUtilisateur utilisateurDao;

//    @Override
//    public List<Utilisateur> consulterUtilisateurs() {
//        return utilisateurDao.listUtilisateurs();
//    }
//
//    @Override
//    public void creerUtilisateur(Utilisateur utilisateur) {
//        // il faut ENCODER LE MOT DE PASSE avant d'enregistrer le utilisateur
//        String motDePasseEncode = passwordEncoder.encode(utilisateur.getPassword());
//        utilisateur.setPassword(motDePasseEncode);
//        utilisateurDao.createUtilisateur(utilisateur);
//    }
//
//    @Override
//    public Utilisateur consulterUtilisateurParEmail(String email) {
//        return utilisateurDao.getUtilisateurByEmail(email);
//    }
//
//    @Override
//    public void supprimerUtilisateur(long idUtilisateurASupprimer) {
//        // TODO : si on supprime un utilisateur, on doit supprimer ses dépendances aux tables Film/Acteurs
//        utilisateurDao.deleteUtilisateur(idUtilisateurASupprimer);
//    }


    @Override
    public List<Utilisateur> consulterUtilisateurs() {
        return List.of();
    }

    @Override
    public Utilisateur consultUserById(long idUtilisateur) {
        return utilisateurDao.consultUserById(idUtilisateur);
    }


    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {

    }

    @Override
    public void supprimerUtilisateur(long idUtilisateurASupprimer) {

    }

    @Override
    public Utilisateur consultUserByUsername(String username) {
        return utilisateurDao.consultUserByUsername(username);
    }
}
