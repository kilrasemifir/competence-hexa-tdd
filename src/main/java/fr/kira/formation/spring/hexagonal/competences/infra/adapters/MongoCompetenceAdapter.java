package fr.kira.formation.spring.hexagonal.competences.infra.adapters;

import fr.kira.formation.spring.hexagonal.competences.infra.entities.CompetenceMongoEnity;
import fr.kira.formation.spring.hexagonal.competences.infra.repositories.CompetenceMongoRepository;
import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.CompetenceCRUD;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoCompetenceAdapter implements CompetenceCRUD {

    private final CompetenceMongoRepository repository;

    public MongoCompetenceAdapter(CompetenceMongoRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Competence> findAll() {
        return new ArrayList<>(this.repository.findAll());
    }

    @Override
    public Competence findById(String id) {
        return this.repository.findById(id).orElseThrow();
    }
}
