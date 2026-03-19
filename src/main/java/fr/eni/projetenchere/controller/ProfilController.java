package fr.eni.projetenchere.controller;


import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Utilisateur;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    @GetMapping
    public String getUtilisateurs(Model model){


        model.addAttribute("utilisateur", new Utilisateur());

        return "profil";
    }



}
