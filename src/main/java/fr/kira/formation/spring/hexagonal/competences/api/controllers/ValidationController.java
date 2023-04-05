package fr.kira.formation.spring.hexagonal.competences.api.controllers;

import fr.kira.formation.spring.hexagonal.competences.api.dto.ValidationParPairDTO;
import fr.kira.formation.spring.hexagonal.competences.api.dto.ValidationTestsDTO;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.ValidationParLesPairs;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.ValidationParLesTests;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/validations")
public class ValidationController {
    private final ValidationParLesPairs serviceParLesPairs;
    private final ValidationParLesTests serviceParLesTests;

    public ValidationController(ValidationParLesPairs serviceParLesPairs, ValidationParLesTests serviceParLesTests) {
        this.serviceParLesPairs = serviceParLesPairs;
        this.serviceParLesTests = serviceParLesTests;
    }

    @PostMapping("/competences/pairs")
    public void validerCompetencePairs(@RequestBody ValidationParPairDTO dto) {
        serviceParLesPairs.validerCompetence(
                dto.getIdPersonneCible(),
                dto.getIdPersonneValidateur(),
                dto.getIdCompetence(),
                dto.getNiveau());
    }

    @PostMapping("/competences/tests")
    public void validerCompetence(@RequestBody ValidationTestsDTO dto) {
        serviceParLesTests.validerCompetence(
                dto.getIdPersonne(),
                dto.getIdCompetence(),
                dto.getIdValidateur());
    }
}
