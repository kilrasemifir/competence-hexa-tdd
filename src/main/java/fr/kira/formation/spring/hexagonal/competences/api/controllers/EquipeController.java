package fr.kira.formation.spring.hexagonal.competences.api.controllers;

import fr.kira.formation.spring.hexagonal.competences.api.adapters.EquipeAPIAdapter;
import fr.kira.formation.spring.hexagonal.competences.api.dto.EquipeCreationDTO;
import fr.kira.formation.spring.hexagonal.competences.models.Equipe;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionEquipe;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("equipes")
public class EquipeController{

    private final EquipeAPIAdapter adapter;

    public EquipeController(EquipeAPIAdapter adapter) {
        this.adapter = adapter;
    }

    @PostMapping
    public Equipe creationEquipe(@RequestBody EquipeCreationDTO dto) {
        return adapter.creationEquipe(dto);
    }

    @PostMapping("{idEquipe}/membres/{idPersonne}")
    public void ajouterPersonneAEquipe(
            @PathVariable String idPersonne,@PathVariable String idEquipe) {
        adapter.ajouterPersonneAEquipe(idPersonne, idEquipe);
    }

    @DeleteMapping("{idEquipe}/membres/{idPersonne}")
    public void supprimerPersonneDeEquipe(
            @PathVariable String idPersonne,@PathVariable String idEquipe) {
        adapter.supprimerPersonneDeEquipe(idPersonne, idEquipe);
    }
}
