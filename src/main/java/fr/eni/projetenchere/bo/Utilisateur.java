package fr.eni.projetenchere.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Utilisateur {

    private long idUtilisateur;
    private String username;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private Adresse adresse;
    private String motDePasse;
    private int credit;
    private boolean administrateur;

    public Utilisateur(String username, String nom, String prenom, String email, String telephone, Adresse adresse, String motDePasse, int credit, boolean administrateur) {
        this.username = username;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.telephone = telephone;
        this.adresse = adresse;
        this.motDePasse = motDePasse;
        this.credit = credit;
        this.administrateur = administrateur;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Utilisateur{");
        sb.append("idUtilisateur=").append(idUtilisateur);
        sb.append(", username='").append(username).append('\'');
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", telephone='").append(telephone).append('\'');
        sb.append(", adresse=").append(adresse);
        sb.append(", motDePasse='").append(motDePasse).append('\'');
        sb.append(", credit=").append(credit);
        sb.append(", administrateur=").append(administrateur);
        sb.append('}');
        return sb.toString();
    }

}
