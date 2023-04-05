package fr.kira.formation.spring.hexagonal.competences.api.controllers;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.PersonneConnaissantCompetence;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/competences")
public class CompetenceController {

    private final PersonneConnaissantCompetence service;

    public CompetenceController(PersonneConnaissantCompetence service) {
        this.service = service;
    }

    @GetMapping("/{competenceId}/personnes")
    public List<Personne> findPersonnesConnaissantCompetence(String competenceId) {
        return service.findPersonnesConnaissantCompetence(competenceId);
    }
}
