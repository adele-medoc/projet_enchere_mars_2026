package fr.eni.projetenchere.controller;


import fr.eni.projetenchere.bo.Utilisateur;
import fr.eni.projetenchere.dto.UtilisateurUpdateDto;
import fr.eni.projetenchere.service.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller

public class ProfilController {

    @Autowired
    UtilisateurService utilisateurService;

    @GetMapping("/profil")
    public String getUtilisateurs(Model model){

        model.addAttribute("utilisateur", new Utilisateur());

        return "profilSetup";
    }

    @GetMapping("/profil/{username}")
    public String getUserById(@PathVariable String username, Model model){

        UtilisateurUpdateDto utilisateurUpdateDto = utilisateurService.consultUserByUsername(username);

        model.addAttribute("utilisateur", utilisateurUpdateDto);

        // TODO : changer par la template des infos utilisateur (vue profil un seul utilisateur)
        return "profilSetup";
    }

    @PostMapping("/")
    public String postAnnuler(){
        return "redirect:/";
    }
    }
