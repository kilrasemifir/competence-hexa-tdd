package fr.kira.formation.spring.hexagonal.competences.infra.entities;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class PersonneMongoEntity extends Personne {

    @Id
    private String id;
}
