package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Reference;
import dumaya.dev.BibApp.repository.OuvrageRepository;
import dumaya.dev.BibApp.repository.ReferenceRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@Api(value = "API du domaine bibliothèque : gestion des ouvrages et des prêts")
public class BibController {

    @Autowired
    private OuvrageRepository ouvrageRepository;
    @Autowired
    private ReferenceRepository referenceRepository;
    @ApiOperation(value = "Récupére la liste des ouvrages : id de la reference et emplacement")
    @RequestMapping(value="/Ouvrages", method= RequestMethod.GET)
    public List<Ouvrage> listeOuvrages() {
        return ouvrageRepository.findAll();
    }

    @ApiOperation(value = "Récupére les infos sur une référence : titre et auteur")
    @GetMapping(value="/References/{id}")
    public Reference getReference(@PathVariable int id) {
        return referenceRepository.findById(id);
    }

    @GetMapping(value = "/Produits/{id}")
    public String afficherUnProduit(@PathVariable int id) {
        return "Vous avez demandé un produit avec l'id  " + id;
    }
}
