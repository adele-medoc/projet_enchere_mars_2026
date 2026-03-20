package fr.eni.projetenchere.controller;


import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProfilController {

    @GetMapping("/profil")
    public String getUtilisateurs(Model model){


        model.addAttribute("utilisateur", new Utilisateur());

        return "profil";
    }

    @PostMapping("/")
    public String postAnnuler(Model model){
        return "redirect:/";
    }

}
