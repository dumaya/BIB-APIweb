package dumaya.dev.BibApp.repository;


import dumaya.dev.BibApp.model.Ouvrage;
import dumaya.dev.BibApp.model.Pret;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface PretRepository extends JpaRepository<Pret, Integer> {

    Pret findById(@Param("id") int id);

}
