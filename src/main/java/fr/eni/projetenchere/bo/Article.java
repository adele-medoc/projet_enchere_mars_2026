package fr.eni.projetenchere.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article {
    private long idArticle;
    private long idUtilisateur;
    private String nom;
    private String description;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int miseAPrix;
    private int prixVente;
    private EtatVente etatVente;
    private int noCategorie;
    private List<Enchere> listeEncheres;
    private Adresse adresseRetrait;

    public Article(long idUtilisateur, String nom, String description, LocalDate dateDebut, LocalDate dateFin, int miseAPrix, int prixVente, EtatVente etatVente, int noCategorie, List<Enchere> listeEncheres, Adresse adresseRetrait) {
        this.idUtilisateur = idUtilisateur;
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.etatVente = etatVente;
        this.noCategorie = noCategorie;
        this.listeEncheres = listeEncheres;
        this.adresseRetrait = adresseRetrait;
    }
}
