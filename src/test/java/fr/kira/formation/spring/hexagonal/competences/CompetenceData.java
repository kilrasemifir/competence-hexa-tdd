package fr.kira.formation.spring.hexagonal.competences;

import fr.kira.formation.spring.hexagonal.competences.models.Competence;
import fr.kira.formation.spring.hexagonal.competences.models.NiveauCompetence;
import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.models.Prerequis;

import java.util.ArrayList;
import java.util.List;

public class CompetenceData {
    public static Competence JAVA = new Competence("1", "java", "poo", List.of() );

    public static Competence SPRING = new Competence("2", "spring","framework",new ArrayList<>());
    public static Competence DOT_NET_CORE = new Competence("3", ".NET CORE", "C#", new ArrayList<>());
    public static Competence C_SHARP = new Competence("4", "C#", "POO", new ArrayList<>());
    public static Prerequis SPRING_JAVA = new Prerequis(SPRING, 5, JAVA, 5);
    public static Prerequis DOT_NET_C_SHARP = new Prerequis(DOT_NET_CORE, 5, C_SHARP, 5);


    public static Personne PERSONNE_VIDE = new Personne("vide","vide",List.of());

    public static Personne PERSONNE_JAVA = new Personne("Doe","John", List.of(
            new NiveauCompetence(JAVA, 6)
    ));

    public static Personne PERSONNE_JAVA_INF = new Personne("","",List.of(
            new NiveauCompetence(JAVA, 4)
    ));

    public static List<Personne> PERSONNES = List.of(
        PERSONNE_VIDE, PERSONNE_JAVA, PERSONNE_JAVA_INF
    );

    public static List<Competence> competences = List.of(
            JAVA,
            SPRING,
            C_SHARP,
            DOT_NET_CORE
    );

    static {
        SPRING.getPrerequis().add(SPRING_JAVA);
        DOT_NET_CORE.getPrerequis().add(DOT_NET_C_SHARP);
    }

}
