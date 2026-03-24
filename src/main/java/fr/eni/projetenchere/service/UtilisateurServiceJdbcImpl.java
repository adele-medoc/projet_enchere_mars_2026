package fr.eni.projetenchere.service;


import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dal.DaoUtilisateur;
import fr.eni.projetenchere.dto.UtilisateurUpdateDto;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("prod")
public class UtilisateurServiceJdbcImpl implements UtilisateurService{

    // On utilise le PasswordEncoder défini dans SecurityConfiguration afin d'encoder le mot de passe des utilisateurs lors de leur création
    @Autowired
    private PasswordEncoder passwordEncoder;
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
        return utilisateurDao.listUtilisateurs();
    }

    @Override
    public Utilisateur consultUserById(long idUtilisateur) {
        return utilisateurDao.consultUserById(idUtilisateur);
    }


    @Override
    public void creerUtilisateur(Utilisateur utilisateur) {
        String motDePasseEncode = passwordEncoder.encode(utilisateur.getMotDePasse());
        utilisateur.setMotDePasse(motDePasseEncode);
        utilisateurDao.creerUtilisateur(utilisateur);
    }



    @Override
    public void supprimerUtilisateur(long idUtilisateurASupprimer) {
        utilisateurDao.supprimerUtilisateur(idUtilisateurASupprimer);
    }

    @Override
    public Utilisateur consultUserByUsername(String username) {
        return utilisateurDao.consultUserByUsername(username);
    }


    @Override
    public UtilisateurUpdateDto consultUserDto(String username) {
        Utilisateur utilisateur = utilisateurDao.consultUserByUsername(username);

        UtilisateurUpdateDto utilisateurUpdateDto = new UtilisateurUpdateDto();
        Adresse adresse = new Adresse();

        adresse.setRue(utilisateur.getAdresse().getRue());
        adresse.setVille(utilisateur.getAdresse().getVille());
        adresse.setCodePostal(utilisateur.getAdresse().getCodePostal());

        utilisateurUpdateDto.setAdresse(adresse);
        utilisateurUpdateDto.setPseudo(utilisateur.getUsername());
        utilisateurUpdateDto.setNom(utilisateur.getNom());
        utilisateurUpdateDto.setPrenom(utilisateur.getPrenom());
        utilisateurUpdateDto.setEmail(utilisateur.getEmail());
        utilisateurUpdateDto.setTelephone(utilisateur.getTelephone());
        utilisateurUpdateDto.setMotDePasseActuel(utilisateur.getMotDePasse());

        System.out.println(utilisateurUpdateDto);

        return utilisateurUpdateDto;

    }

    @Override
    public void updateUserById(long id, UtilisateurUpdateDto utilisateurUpdateDto,
                                               UtilisateurSpringSecurity utilisateurConnecte, PasswordEncoder passwordEncoder) {

        if(id != utilisateurConnecte.getUserId()){
            throw new RuntimeException("Vous ne pouvez pas modifier la page d'un autre utilisateur");
        }

        // je récupère les données du formulaire pour les transférer à l'utilisateur
        Utilisateur utilisateur = utilisateurDao.consultUserById(id);
        utilisateur.setUsername(utilisateurUpdateDto.getPseudo());
        utilisateur.setNom(utilisateurUpdateDto.getNom());
        utilisateur.setPrenom(utilisateurUpdateDto.getPrenom());
        utilisateur.setEmail(utilisateurUpdateDto.getEmail());
        utilisateur.setTelephone(utilisateurUpdateDto.getTelephone());

        utilisateur.getAdresse().setRue(utilisateurUpdateDto.getAdresse().getRue());
        utilisateur.getAdresse().setVille(utilisateurUpdateDto.getAdresse().getVille());
        utilisateur.getAdresse().setCodePostal(utilisateurUpdateDto.getAdresse().getCodePostal());

        // logique de vérification enregistrement nouveau mdp

            // si le nouveauMotDePasse n'est pas null ou vide alors motDePasseIsUpdated = true
            boolean motDePasseIsUpdated = utilisateurUpdateDto.getNouveauMotDePasse() != null && !(utilisateurUpdateDto.getNouveauMotDePasse().isEmpty()) ;

                if(motDePasseIsUpdated){

                    // je récupère le mdp actuel de l'utilisateur
                    String ancientMotDePasse = utilisateur.getMotDePasse();

                    //je récupère les champs mdp renseignés dans le DTO Utilisateur
                    String ancientMotDePasseDto = utilisateurUpdateDto.getMotDePasseActuel();
                    String nouveauMotDePasse = utilisateurUpdateDto.getNouveauMotDePasse();
                    String confirmationMotDePasse = utilisateurUpdateDto.getConfirmMotDePasse();

                    // si le nouveauMdp et la confirmation sont identiques ET le mdpActuel du DTO est le même que le mdp de l'utilisateur
                    if(nouveauMotDePasse.equals(confirmationMotDePasse) &&  passwordEncoder.matches(ancientMotDePasseDto, ancientMotDePasse)){

                        // convertir le mot de passe en hash
                        String motDePasseEncode = passwordEncoder.encode(nouveauMotDePasse);

                        utilisateur.setMotDePasse(motDePasseEncode);
                    }
                }

            utilisateurDao.updateUserInfo(utilisateurConnecte.getUserId(), utilisateur);

    }
}
