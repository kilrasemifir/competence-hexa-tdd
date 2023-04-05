package fr.kira.formation.spring.hexagonal.competences.infra.entities;

import fr.kira.formation.spring.hexagonal.competences.models.Equipe;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class EquipeMongoEntity extends Equipe {
    @Id private String id;
}
