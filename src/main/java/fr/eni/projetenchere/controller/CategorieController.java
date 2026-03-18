package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.bo.Categorie;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategorieController {

    @GetMapping
    public String getCategories(Model model){

        model.addAttribute("categorie", new Categorie());

        return "categories";
    }

    @PostMapping
    public String postCategorie(Categorie categorie){
        // TODO: Renseigner la méthode pour ajouter une catégorie du CategorieService
        return "redirect:/categories";
    }

    @PostMapping("/supprimer")
    public String suppressionCategorie(long idCategorieASupprimer){
        // TODO: Renseigner la méthode pour supprimer une catégorie du CategorieService
        return "redirect:/categories";
    }

}
