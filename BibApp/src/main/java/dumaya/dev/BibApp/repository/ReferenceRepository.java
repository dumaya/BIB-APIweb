package dumaya.dev.BibApp.repository;

import dumaya.dev.BibApp.model.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenceRepository extends JpaRepository<Reference, Integer> {

    Reference findById(int id);

}
