package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Reference;
import dumaya.dev.BibApp.repository.OuvrageRepository;
import dumaya.dev.BibApp.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReferenceController {
    @Autowired
    ReferenceRepository referenceRepository;

    @GetMapping(value="/references")
    public List<Reference> listeDesReferences(){
        List<Reference> references = referenceRepository.findAll();
        if (references.isEmpty()) throw new NotFoundException("Aucune reference trouvée");
        return references;
    }

    @GetMapping(value = "/references/{id}")
    public Reference recupererUneReference (@PathVariable("id") int id){
        Reference reference = referenceRepository.findById(id);
        if (reference == null) throw new NotFoundException("Cette reference n'existe pas");
        return reference;
    }
}
