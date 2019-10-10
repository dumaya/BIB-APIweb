package dumaya.dev.BibApp.repository;

import dumaya.dev.BibApp.model.Ouvrage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OuvrageRepository extends JpaRepository<Ouvrage, Long> {

    Ouvrage findById(Integer id);

}
