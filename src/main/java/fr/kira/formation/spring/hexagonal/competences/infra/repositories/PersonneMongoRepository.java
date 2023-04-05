package fr.kira.formation.spring.hexagonal.competences.infra.repositories;

import fr.kira.formation.spring.hexagonal.competences.infra.entities.PersonneMongoEntity;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonneMongoRepository extends MongoRepository<PersonneMongoEntity, String> {
}
