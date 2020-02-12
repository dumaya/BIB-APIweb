package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Reference;
import dumaya.dev.BibApp.repository.OuvrageRepository;
import dumaya.dev.BibApp.repository.ReferenceRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReferenceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceController.class);

    @Autowired
    ReferenceRepository referenceRepository;

    @GetMapping(value="/references")
    public List<Reference> listeDesReferences(){
        LOGGER.debug("liste references");
        List<Reference> references = referenceRepository.findAll();
        if (references.isEmpty()) throw new NotFoundException("Aucune reference trouvée");
        return references;
    }

    @GetMapping(value = "/references/{id}")
    public Reference recupererUneReference (@PathVariable("id") int id){
        LOGGER.debug("rechercher une ref par id");
        Reference reference = referenceRepository.findById(id);
        if (reference == null) throw new NotFoundException("Cette reference n'existe pas");
        return reference;
    }
}
