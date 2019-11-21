package dumaya.dev.BibApp.repository;


import dumaya.dev.BibApp.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface OuvrageRepository extends JpaRepository<Ouvrage, Integer> {

    Ouvrage findById(@Param("id") int id);

}
