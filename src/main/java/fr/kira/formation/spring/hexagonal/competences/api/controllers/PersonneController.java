package fr.kira.formation.spring.hexagonal.competences.api.controllers;

import fr.kira.formation.spring.hexagonal.competences.api.adapters.PersonneAPIAdapter;
import fr.kira.formation.spring.hexagonal.competences.api.dto.PersonneCreationDTO;
import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionNiveauCompetence;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    private final PersonneAPIAdapter adapter;
    private final GestionNiveauCompetence serviceNiveauCompetence;

    public PersonneController(PersonneAPIAdapter adapter, GestionNiveauCompetence serviceNiveauCompetence) {
        this.adapter = adapter;
        this.serviceNiveauCompetence = serviceNiveauCompetence;
    }

    @PostMapping
    public void creationPersonne(@RequestBody PersonneCreationDTO dto) {
        adapter.creationPersonne(dto);
    }

    @GetMapping("/{personneId}/competences/accessibles")
    public List<Competence> findCompetencesAccessibles(@PathVariable String personneId) {
        return serviceNiveauCompetence.competenceAccessibles(personneId);
    }


}
