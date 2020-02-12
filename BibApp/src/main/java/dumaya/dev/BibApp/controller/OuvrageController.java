package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Reference;
import dumaya.dev.BibApp.repository.OuvrageRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OuvrageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(OuvrageController.class);

    @Autowired
    OuvrageRepository ouvrageRepository;

    @GetMapping(value="/ouvrages")
    public List<Ouvrage> listeDesOuvrages(){
        LOGGER.debug("Liste des ouvrages");
        List<Ouvrage> ouvrages = ouvrageRepository.findAll();
        if (ouvrages.isEmpty()) throw new NotFoundException("Aucun ouvrage trouvé");
        return ouvrages;
    }

    @GetMapping(value = "/ouvrages/{id}")
    public Ouvrage recupererUnOuvrage (@PathVariable("id") int id){
        LOGGER.debug("Recherche ouvrage par ID");
        Ouvrage ouvrage = ouvrageRepository.findById(id);
        if (ouvrage == null) throw new NotFoundException("Cet ouvrage n'existe pas");
        return ouvrage;
    }
}
