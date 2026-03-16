package fr.eni.projetenchere.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Utilisateur {

    private long idUtilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Adresse adresse;
    private String motDePasse;
    private int credit;
    private byte administrateur;

    public Utilisateur(String pseudo, String nom, String prenom, String email, String telephone, Adresse adresse, String motDePasse, int credit, byte administrateur) {
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }
}
