package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.repository.OuvrageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OuvrageController {
    @Autowired
    OuvrageRepository ouvrageRepository;

    @GetMapping(value="/ouvrages")
    public List<Ouvrage> listeDesOuvrages(){
        List<Ouvrage> ouvrages = ouvrageRepository.findAll();
        if (ouvrages.isEmpty()) throw new OuvrageNotFoundException("Aucun ouvrage trouvé");
        return ouvrages;
    }
}
