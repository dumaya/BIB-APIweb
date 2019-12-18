package dumaya.dev.BibApp.repository;

import dumaya.dev.BibApp.model.Reference;
import dumaya.dev.BibApp.model.Usager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsagerRepository extends JpaRepository<Usager, Integer> {

    Usager findById(int id);
    Usager findByIdWeb(int id);

}
