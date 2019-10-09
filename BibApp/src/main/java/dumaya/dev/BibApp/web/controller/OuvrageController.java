package dumaya.dev.BibApp.web.controller;

import org.springframework.web.bind.annotation.*;

@RestController
public class OuvrageController {
    @RequestMapping(value="/Ouvrages", method= RequestMethod.GET)
    public String listeOuvrages() {
        return "La liste des ouvrages";
    }
    @GetMapping(value = "/Produits/{id}")
    public String afficherUnProduit(@PathVariable int id) {
        return "Vous avez demandé un produit avec l'id  " + id;
    }
}
