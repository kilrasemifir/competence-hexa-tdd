package fr.kira.formation.spring.hexagonal.competences.api.adapters;

import fr.kira.formation.spring.hexagonal.competences.api.dto.PersonneCreationDTO;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionPersonne;
import org.springframework.stereotype.Component;

@Component
public class PersonneAPIAdapter {

    private final GestionPersonne gestionPersonne;

    public PersonneAPIAdapter(GestionPersonne gestionPersonne) {
        this.gestionPersonne = gestionPersonne;
    }

    public void creationPersonne(PersonneCreationDTO dto) {
        gestionPersonne.creationPersonne(dto.getNom(), dto.getPrenom());
    }
}
