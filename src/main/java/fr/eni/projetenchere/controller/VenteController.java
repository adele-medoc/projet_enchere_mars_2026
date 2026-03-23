package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.dto.ArticleDto;
import fr.eni.projetenchere.dto.UtilisateurDto;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import fr.eni.projetenchere.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import java.util.List;

@Controller
@RequestMapping()
public class VenteController {

    @Autowired
    private VenteService venteService;

    @ModelAttribute("categories")
    public List<Categorie> getCategories(){
        return venteService.consulterCategories();
    }

    @GetMapping
    public String getListeEncheres(Model model) {
        model.addAttribute("listeArticlesEnCours", venteService.consulterArticles());
        return "accueil";
    }



    @GetMapping("/enchere/{id}")
    public String getDetailArticle(@PathVariable long id, Model model){
        //model.addAttribute("article", ArticleService.consulterArticleParId(id));
        return "";
    }

    @GetMapping("/enchere/nouvelArticle")
        public String getNouvelleVente(Model model){
        model.addAttribute("categories", venteService.consulterCategories());
        Article a = new Article();
        a.setAdresseRetrait(new Adresse());
        model.addAttribute("article", a);
        return "nouvelleVente";
    }

    @ModelAttribute("listeArticlesEnCours")public List<Article> getArticles(){
        return venteService.consulterArticles(); }

    @PostMapping("/enchere/nouvelArticle")
    public String postNouvelleVente(Article article, RedirectAttributes modelRedirect,@AuthenticationPrincipal UtilisateurSpringSecurity user){
//        long idUtilisateurActif = user.getUserId();
//        article.getUtilisateur().setIdUtilisateur(idUtilisateurActif);
//        System.out.println("************************* article = " + article.toString());
        venteService.CreerNouvelleVente(article,user);
        modelRedirect.addFlashAttribute("messageConfirmation", "L'article a bien été enregistrée !");
        return "redirect:/enchere/nouvelArticle";
    }

}
