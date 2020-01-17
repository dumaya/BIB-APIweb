package dumaya.dev.BibApp.repository;


import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PretRepository extends JpaRepository<Pret, Integer> {
    Pret findById(@Param("id") int id);
    List<Pret> findByIdOuvrage(@Param("id") int id);
    List<Pret> findByIdUsager(@Param("id") int id);
}
