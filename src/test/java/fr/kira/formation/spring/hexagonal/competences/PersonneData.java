package fr.kira.formation.spring.hexagonal.competences;

import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;

import java.util.List;
import static fr.kira.formation.spring.hexagonal.competences.CompetenceData.*;

public class PersonneData {

    public static Personne PERSONNE_VIDE = new Personne("vide","vide", List.of());

    public static Personne PERSONNE_JAVA = new Personne("Doe","John", List.of(
            new NiveauCompetence(JAVA, 6)
    ));

    public static Personne PERSONNE_JAVA_INF = new Personne("","",List.of(
            new NiveauCompetence(JAVA, 4)
    ));

    public static List<Personne> PERSONNES = List.of(
            PERSONNE_VIDE, PERSONNE_JAVA, PERSONNE_JAVA_INF
    );
}
