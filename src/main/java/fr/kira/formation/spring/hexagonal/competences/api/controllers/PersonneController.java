package fr.kira.formation.spring.hexagonal.competences.api.controllers;

import fr.kira.formation.spring.hexagonal.competences.api.adapters.PersonneAPIAdapter;
import fr.kira.formation.spring.hexagonal.competences.api.dto.PersonneCreationDTO;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionPersonne;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/personnes")
public class PersonneController {

    private final PersonneAPIAdapter adapter;

    public PersonneController(PersonneAPIAdapter adapter) {
        this.adapter = adapter;
    }

    @PostMapping
    public void creationPersonne(@RequestBody PersonneCreationDTO dto) {
        adapter.creationPersonne(dto);
    }
}
