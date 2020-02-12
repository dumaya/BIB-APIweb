package dumaya.dev.BibApp.controller;

import dumaya.dev.BibApp.model.Role;
import dumaya.dev.BibApp.model.Usager;
import dumaya.dev.BibApp.repository.RoleRepository;
import dumaya.dev.BibApp.repository.UsagerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    RoleRepository roleRepository;

    @GetMapping(value="/roles")
    public List<Role> listeDesRoles(){
        LOGGER.debug("liste roles");
        List<Role> roles = roleRepository.findAll();
        if (roles.isEmpty()) throw new NotFoundException("Aucun role trouvé");
        return roles;
    }

    @GetMapping(value = "/roles/role/{role}")
    public Role recupererUnRoleParRole (@PathVariable("role") String role){
        LOGGER.debug("rechercher un role par role");
        Role roleTrouve = roleRepository.findByRole(role);
        if (roleTrouve == null) throw new NotFoundException("Ce role n'existe pas");
        return roleTrouve;
    }

}
