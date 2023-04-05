package fr.kira.formation.spring.hexagonal.competences.infra.repositories;

import fr.kira.formation.spring.hexagonal.competences.infra.entities.CompetenceMongoEnity;
import org.springframework.data.repository.ListCrudRepository;

public interface CompetenceMongoRepository extends ListCrudRepository<CompetenceMongoEnity, String> {
}
