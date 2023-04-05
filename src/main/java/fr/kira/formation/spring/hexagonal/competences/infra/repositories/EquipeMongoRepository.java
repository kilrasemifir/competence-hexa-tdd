package fr.kira.formation.spring.hexagonal.competences.infra.repositories;

import fr.kira.formation.spring.hexagonal.competences.infra.entities.EquipeMongoEntity;
import org.springframework.data.repository.CrudRepository;

public interface EquipeMongoRepository extends CrudRepository<EquipeMongoEntity, String> {
}
