package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Pret;
import dumaya.dev.BibApp.model.Reference;
import dumaya.dev.BibApp.model.Usager;
import dumaya.dev.BibApp.repository.ReferenceRepository;
import dumaya.dev.BibApp.repository.UsagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class UsagerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UsagerController.class);
    @Autowired
    UsagerRepository usagerRepository;

    @GetMapping(value="/usagers")
    public List<Usager> listeDesUsagers(){
        LOGGER.debug("Liste usagers");
        List<Usager> usagers = usagerRepository.findAll();
        if (usagers.isEmpty()) throw new NotFoundException("Aucun usager trouvé");
        return usagers;
    }

    @GetMapping(value = "/usagers/{id}")
    public Usager recupererUnUsager (@PathVariable("id") int id){
        LOGGER.debug("usager par id");
        Usager usager = usagerRepository.findById(id);
        if (usager == null) throw new NotFoundException("Cet usager n'existe pas");
        return usager;
    }

    @GetMapping(value = "/usagers/email/{email}")
    public Usager recupererUnUsagerParEmail (@PathVariable("email") String email){
        LOGGER.debug("usager par email");
        Usager usager = usagerRepository.findByEmail(email);
        if (usager == null) throw new NotFoundException("Cet usager n'existe pas");
        return usager;
    }

    @PostMapping(value = "/usagers/")
    @ResponseBody
    public Usager creerUnUsager (@RequestBody Usager usager){
        LOGGER.debug("creation usager");
        Usager usagerExistant =usagerRepository.findById(usager.getId());
        if(null != usagerExistant) {
            majUsager(usager , usagerExistant);
            usagerRepository.save(usagerExistant);
            usager=usagerExistant;
        } else {
            Usager usagerACreer = new Usager();
            majUsager(usager, usagerACreer);
            usagerRepository.save(usagerACreer);
            usager=usagerACreer;
        }
        return usager;
    }

    private void majUsager(Usager usagerSource, Usager usagerCible) {
        usagerCible.setEmail(usagerSource.getEmail());
        usagerCible.setNom(usagerSource.getNom());
        usagerCible.setPrenom(usagerSource.getPrenom());
        usagerCible.setPassword(usagerSource.getPassword());
        usagerCible.setRoles(usagerSource.getRoles());
        usagerCible.setActive(usagerSource.isActive());
    }
}
