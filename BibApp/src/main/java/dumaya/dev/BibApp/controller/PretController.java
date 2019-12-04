package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Pret;
import dumaya.dev.BibApp.repository.OuvrageRepository;
import dumaya.dev.BibApp.repository.PretRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PretController {
    @Autowired
    PretRepository pretRepository;

    @GetMapping(value="/prets")
    public List<Pret> listeDesPrets(){
        List<Pret> prets = pretRepository.findAll();
        if (prets.isEmpty()) throw new NotFoundException("Aucun pret trouvé");
        return prets;
    }

    @GetMapping(value = "/prets/{id}")
    public Pret recupererUnPret (@PathVariable("id") int id){
        Pret pret = pretRepository.findById(id);
        if (pret == null) throw new NotFoundException("Ce pret n'existe pas");
        return pret;
    }

    @GetMapping(value = "/prets/ouvrage/{id}")
    public Pret pretDeLOuvrage (@PathVariable("id") int id){
        List<Pret> listePret = pretRepository.findByIdOuvrage(id);


        if (pret == null) throw new NotFoundException("Ce pret n'existe pas");
        return pret;
    }
}
