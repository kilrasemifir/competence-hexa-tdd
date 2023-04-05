package fr.kira.formation.spring.hexagonal.competences.api.adapters;

import fr.kira.formation.spring.hexagonal.competences.api.dto.EquipeCreationDTO;
import fr.kira.formation.spring.hexagonal.competences.models.Equipe;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionEquipe;
import org.springframework.stereotype.Component;

@Component
public class EquipeAPIAdapter {

    private final GestionEquipe service;

    public EquipeAPIAdapter(GestionEquipe service) {
        this.service = service;
    }

    public Equipe creationEquipe(EquipeCreationDTO dto) {
        return service.creationEquipe(dto.getNom());
    }

    public void ajouterPersonneAEquipe(String idPersonne, String idEquipe) {
        service.ajouterPersonneAEquipe(idPersonne, idEquipe);
    }

    public void supprimerPersonneDeEquipe(String idPersonne, String idEquipe) {
        service.supprimerPersonneDeEquipe(idPersonne, idEquipe);
    }
}
