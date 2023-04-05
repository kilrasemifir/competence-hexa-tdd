package fr.kira.formation.spring.hexagonal.competences.infra.adapters;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.kira.formation.spring.hexagonal.competences.infra.entities.EquipeMongoEntity;
import fr.kira.formation.spring.hexagonal.competences.infra.repositories.EquipeMongoRepository;
import fr.kira.formation.spring.hexagonal.competences.models.Equipe;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.EquipeCRUD;
import org.springframework.stereotype.Component;

@Component
public class MongoEquipeAdapter implements EquipeCRUD {

    private final EquipeMongoRepository repository;
    private final ObjectMapper mapper;

    public MongoEquipeAdapter(EquipeMongoRepository repository, ObjectMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Equipe sauvegarder(Equipe equipe) {
        return this.repository.save(mapper.convertValue(equipe, EquipeMongoEntity.class));
    }

    @Override
    public Equipe findById(String id) {
        return this.repository.findById(id).orElseThrow();
    }
}
