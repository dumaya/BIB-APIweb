package dumaya.dev.BibApp;

import dumaya.dev.BibApp.repository.OuvrageRepository;
import dumaya.dev.BibApp.repository.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@Import(SpringDataRestConfiguration.class)
@EnableSwagger2
public class BibApp {
	@Autowired
	public OuvrageRepository ouvrageRepository;
	@Autowired
	public ReferenceRepository referenceRepository;

	public static void main(String[] args) {
		SpringApplication.run(BibApp.class, args);
	}

}
