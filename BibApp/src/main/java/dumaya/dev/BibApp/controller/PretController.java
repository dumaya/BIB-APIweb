package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Pret;
import dumaya.dev.BibApp.repository.OuvrageRepository;
import dumaya.dev.BibApp.repository.PretRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


@RestController
public class PretController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PretController.class);

    @Autowired
    PretRepository pretRepository;

    /**
     * @return list de tous les prets
     */
    @GetMapping(value="/prets")
    public List<Pret> listeDesPrets(){
        LOGGER.debug("Liste des prets");
        List<Pret> prets = pretRepository.findAll();
        if (prets.isEmpty()) throw new NotFoundException("Aucun pret trouvé");
        return prets;
    }

    /**
     * @param id usager
     * @return list de tous les prets en retard pour un usager
     */
    @GetMapping(value="/prets/arelancer/{id}")
    public List<Pret> listeDesPretsARelancer(@PathVariable("id") int id) {
        LOGGER.debug("Liste des prets à relancer pour un id usager");
        List<Pret> prets = pretRepository.findAllByIdUsagerAndDateFinIsBeforeAndDateRetourIsNull(id,new Date());
        if (prets.isEmpty()) throw new NotFoundException("Aucun pret trouvé");
        return prets;
    }

    /**
     * @param id  pret
     * @return pret cherché
     */
    @GetMapping(value = "/prets/{id}")
    public Pret recupererUnPret (@PathVariable("id") int id){
        LOGGER.debug("Rechercher un pret par id");
        Pret pret = pretRepository.findById(id);
        if (pret == null) throw new NotFoundException("Ce pret n'existe pas");
        return pret;
    }

    /**
     * @param id pret
     * @return pret prolongé
     */
    @PutMapping(value = "/prets/prolongation/{id}")
    public Pret prolongerUnPret (@PathVariable("id") int id){
        LOGGER.debug("Prolongation d'un pret");
        Pret pret = pretRepository.findById(id);
        if (pret == null) throw new NotFoundException("Ce pret n'existe pas");
        if (pret.getTopProlongation()) throw new NotFoundException("Ce pret a déjà été prolongé");
        if (null != pret.getDateRetour() ) throw new NotFoundException("On ne peut pas prolonger un pret déjà retourné");
        pret.setTopProlongation(true);
        Date dateProlongee;
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(pret.getDateFin());
        gc.add(GregorianCalendar.DATE,28);
        dateProlongee = gc.getTime();
        pret.setDateFin(dateProlongee);
        pretRepository.save(pret);
        return pret;
    }

    /**
     * @param id pret
     * @return pret retourné
     */
    @PutMapping(value = "/prets/retour/{id}")
    public Pret retourDePret (@PathVariable("id") int id){
        LOGGER.debug("Retour d'un pret");
        Pret pret = pretRepository.findById(id);
        if (pret == null) throw new NotFoundException("Ce pret n'existe pas");
        if (null != pret.getDateRetour() ) throw new NotFoundException("Ce pret a déjà été retourné");
        pret.setDateRetour(new Date());
        pretRepository.save(pret);
        return pret;
    }

    /**
     * @param id ouvrage
     * @return liste des prets en cours pour cet ouvrage
     */
    @GetMapping(value = "/prets/ouvrages/{id}")
    public Pret pretEnCours (@PathVariable("id") int id){
        LOGGER.debug("Liste des prets en cours");
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

    /**
     * @param id usager
     * @return liste des prets de cet usager
     */
    @GetMapping(value = "/prets/usagers/{id}")
    public List<Pret> pretsUsager (@PathVariable("id") int id){
        LOGGER.debug("Liste des prets par id usager");
        List<Pret> listePret = pretRepository.findByIdUsager(id);
        List<Pret> listePretEnCours = new ArrayList<>();
        for (Pret pret : listePret) {
            if (null == pret.getDateRetour()) {
                listePretEnCours.add(pret);
            }
        }
        return listePretEnCours;
    }

    /**
     * @param idOuvrage
     * @return pretCree
     */
    @PostMapping(value = "/prets/")
    public Pret nouveauPret (@RequestParam("idOuvrage") int idOuvrage, @RequestParam("idUsager") int idUsager){
        LOGGER.debug("Creation d'un pret");
        // Cet ouvrage est'il déjà preté ?
        List<Pret> prets = pretRepository.findByIdOuvrageAndDateRetourNull(idOuvrage);
        if (prets.isEmpty()) {
            Pret nouveauPret = new Pret();
            nouveauPret.setIdOuvrage(idOuvrage);
            nouveauPret.setIdUsager(idUsager);
            // Date de fin de pret = date du jour + 28
            Date dateFin = new Date();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTime(dateFin);
            gc.add(GregorianCalendar.DATE,28);
            dateFin = gc.getTime();
            nouveauPret.setDateFin(dateFin);
            pretRepository.save(nouveauPret);
            return nouveauPret;
        } else {
            return null;
        }
    }
}
