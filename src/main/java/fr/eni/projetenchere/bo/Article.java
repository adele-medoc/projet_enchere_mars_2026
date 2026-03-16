package fr.eni.projetenchere.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
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

}
