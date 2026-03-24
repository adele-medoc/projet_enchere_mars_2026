package fr.eni.projetenchere.dto;

import fr.eni.projetenchere.bo.Adresse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UtilisateurUpdateDto {

    @NotBlank
    private String pseudo;

    @NotBlank
    private String nom;

    @NotBlank
    private String prenom;

    @Email
    private String email;

    @NotBlank
    private String telephone;

    @NotBlank @Min(8)
    private String motDePasseActuel;

    private String nouveauMotDePasse;

    private String confirmMotDePasse;

    @NotNull
    private Adresse adresse;

}
