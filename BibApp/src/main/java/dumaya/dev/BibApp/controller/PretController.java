package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Pret;
import dumaya.dev.BibApp.repository.OuvrageRepository;
import dumaya.dev.BibApp.repository.PretRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@RestController
public class PretController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PretController.class);

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

    @PutMapping(value = "/prets/{id}")
    public Pret prolongerUnPret (@PathVariable("id") int id){
        Pret pret = pretRepository.findById(id);
        if (pret == null) throw new NotFoundException("Ce pret n'existe pas");
        if (pret.getTopProlongation()) throw new NotFoundException("Ce pret a déjà été prolongé");
        pret.setTopProlongation(true);
        Date dateProlongee = new Date();
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(pret.getDateFin());
        gc.add(GregorianCalendar.DATE,30);
        dateProlongee = gc.getTime();
        pret.setDateFin(dateProlongee);
        pretRepository.save(pret);
        return pret;
    }

    @GetMapping(value = "/prets/ouvrages/{id}")
    public Pret pretEnCours (@PathVariable("id") int id){
        List<Pret> listePret = pretRepository.findByIdOuvrage(id);
        Pret pretEnCours = null;
        for (Pret pret : listePret) {
            if (null != pret.getDateRetour()) {
                if (pretEnCours != null) {
                    //cas de 2 prets sur la même période
                    LOGGER.error("cas de 2 prets sur la même période, idOuvrage : " + pret.getIdOuvrage());
                } else {
                    pretEnCours = pret;
                }
            }
        }
        return pretEnCours;
    }
    @GetMapping(value = "/prets/usagers/{id}")
    public List<Pret> pretsUsager (@PathVariable("id") int id){
        List<Pret> listePret = pretRepository.findByIdUsager(id);
        List<Pret> listePretEnCours = new ArrayList<>();
        for (Pret pret : listePret) {
            if (null == pret.getDateRetour()) {
                listePretEnCours.add(pret);
            }
        }
        return listePretEnCours;
    }
}
