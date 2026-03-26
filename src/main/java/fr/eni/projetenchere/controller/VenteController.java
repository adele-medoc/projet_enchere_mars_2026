package fr.eni.projetenchere.controller;

import fr.eni.projetenchere.bo.Adresse;
import fr.eni.projetenchere.bo.Article;
import fr.eni.projetenchere.bo.Categorie;
import fr.eni.projetenchere.bo.Enchere;
import fr.eni.projetenchere.security.UtilisateurSpringSecurity;
import fr.eni.projetenchere.service.VenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
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
    public String getListeEncheres(Model model, @AuthenticationPrincipal UtilisateurSpringSecurity user) {
        //model.addAttribute("listeArticlesEnCours", venteService.consulterArticles());
        model.addAttribute("user", user);
        model.addAttribute("now", LocalDate.now());
        model.addAttribute("articles",venteService.consulterArticles());

        return "accueil";
    }

    @GetMapping("/enchere/{id}")
    public String getDetailArticle(@PathVariable long id, Model model,@AuthenticationPrincipal UtilisateurSpringSecurity user){
        model.addAttribute("article", venteService.consulterArticleById(id));
        model.addAttribute("enchere",new Enchere());
        model.addAttribute("meilleurOffre",venteService.consulterMeilleurOffreEnchere(id));
        model.addAttribute("userActif",user);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Utilisateur actif "+ user.toString() +"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Utilisateur article "+ venteService.consulterArticleById(id).getUtilisateur().toString() +"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!" );
        return "detailVente";
    }

    @PostMapping("/enchere/{id}")
    public String postEnchereArticle(Enchere enchere, @PathVariable long id,@AuthenticationPrincipal UtilisateurSpringSecurity user, Model model){
        model.addAttribute("article", venteService.consulterArticleById(id));
        //enchere.getArticle().setIdArticle(id);

        venteService.creerNouvelleEnchere(enchere,id,user);
        return "redirect:/enchere/{id}";
    }

    @GetMapping("/enchere/nouvelArticle")
        public String getNouvelleVente(Model model,@AuthenticationPrincipal UtilisateurSpringSecurity user){
        model.addAttribute("origine", "nouveau");
        model.addAttribute("article", new Article());
        model.addAttribute("adresseVendeur",user.getUtilisateur().getAdresse());
        return "nouvelleVente";
    }

    @ModelAttribute("listeArticlesEnCours")public List<Article> getArticles(){
        return venteService.consulterArticles(); }

    @PostMapping("/enchere/nouvelArticle")
    public String postNouvelleVente(Article article, Model model,RedirectAttributes modelRedirect,@AuthenticationPrincipal UtilisateurSpringSecurity user){
        model.addAttribute("origine", "nouveau");
        venteService.creerNouvelleVente(article,user);
        modelRedirect.addFlashAttribute("messageConfirmation", "L'article a bien été enregistrée !");
        return "redirect:/";
    }

    @GetMapping("/enchere/{id}/modifier")
    public String getModifierArticle(Model model,@PathVariable long id){
        System.out.println("********************************"+ venteService.consulterArticleById(id).toString() +"********************************************");
        model.addAttribute("origine", "modifier");
        model.addAttribute("article",venteService.consulterArticleById(id));
        model.addAttribute("article",new Article());

        return "nouvelleVente";
    }

    @PostMapping("/enchere/{id}/modifier")
    public String postModifierArticle(Article article,Model model,@PathVariable long id){
        venteService.modifierArticle(article);
        return "redirect:/enchere/{id}";
    }

}
