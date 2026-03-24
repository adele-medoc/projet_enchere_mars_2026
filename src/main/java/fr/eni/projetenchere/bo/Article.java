package fr.eni.projetenchere.bo;

import jakarta.validation.constraints.*;
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
    private Utilisateur utilisateur;
    @NotEmpty @Size(max=250, message = "ne doit pas être vide")
    private String nom;
    @NotEmpty @Size(max=250, message = "ne doit pas être vide")
    private String description;
    @FutureOrPresent(message = "La date doit être postérieure ou égale à la date actuelle")
    private LocalDate dateDebut;
    @Future (message = "La date doit être postérieure à la date actuelle")
    private LocalDate dateFin;
    @Min(value = 0, message = "Le prix doit être supérieur ou égal à 0")
    private int miseAPrix;
    private Integer prixVente; //prixVente doit être objet Integer afin de pouvoir être null
    private int noCategorie;
    private List<Enchere> listeEncheres;
    private Adresse adresseRetrait;

    public Article(String nom, String description, LocalDate dateDebut, LocalDate dateFin, int miseAPrix, int prixVente, int noCategorie, List<Enchere> listeEncheres, Adresse adresseRetrait) {
        this.nom = nom;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.miseAPrix = miseAPrix;
        this.prixVente = prixVente;
        this.noCategorie = noCategorie;
        this.listeEncheres = listeEncheres;
        this.adresseRetrait = adresseRetrait;
    }
}
