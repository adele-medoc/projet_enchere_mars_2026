package fr.eni.projetenchere.controller;


import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.service.UtilisateurService;
import fr.eni.projetenchere.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class ProfilController {

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("/profil")
    public String getUtilisateurs(Model model){

        model.addAttribute("utilisateur", new Utilisateur());

        return "profil";
    }

    @GetMapping("/profil/{username}")
    public String getUserById(@PathVariable String username, Model model){
        model.addAttribute("utilisateur", utilisateurService.consultUserByUsername(username));

        // TODO : changer par la template des infos utilisateur (vue profil un seul utilisateur)
        return "profil";
    }


    @PostMapping("/")
    public String postAnnuler(){
        return "redirect:/";
    }
    }
