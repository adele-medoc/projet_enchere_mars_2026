package fr.eni.projetenchere.dto;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurDto {

    private Utilisateur user;
    private Adresse adresse;
}
