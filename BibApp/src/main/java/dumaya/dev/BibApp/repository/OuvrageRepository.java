package dumaya.dev.BibApp.repository;


import dumaya.dev.BibApp.model.Ouvrage;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


public interface OuvrageRepository extends PagingAndSortingRepository<Ouvrage, Integer> {

    Ouvrage findById(@Param("id") int id);

}
