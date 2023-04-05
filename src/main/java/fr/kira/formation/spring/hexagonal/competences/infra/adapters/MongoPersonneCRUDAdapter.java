package fr.kira.formation.spring.hexagonal.competences.infra.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kira.formation.spring.hexagonal.competences.infra.entities.PersonneMongoEntity;
import fr.kira.formation.spring.hexagonal.competences.infra.repositories.PersonneMongoRepository;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MongoPersonneCRUDAdapter implements PersonneCRUD{

    private final PersonneMongoRepository repository;
    private final ObjectMapper mapper;

    public MongoPersonneCRUDAdapter(PersonneMongoRepository personneMongoRepository, ObjectMapper mapper) {
        this.repository = personneMongoRepository;
        this.mapper = mapper;
    }

    @Override
    public Personne findById(String id) {
        return this.repository.findById(id).orElseThrow();
    }

    @Override
    public List<Personne> findAll() {
        return new ArrayList<>(this.repository.findAll());
    }

    @Override
    public Personne sauvegarder(Personne personne) {
        return this.repository.save(mapper.convertValue(personne, PersonneMongoEntity.class));
    }
}
