package fr.kira.formation.spring.hexagonal.competences.configs;

import fr.kira.formation.spring.hexagonal.competences.usecases.GestionEquipeService;
import fr.kira.formation.spring.hexagonal.competences.usecases.GestionPersonneService;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionEquipe;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionPersonne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.EquipeCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EquipeConfiguration {

    @Bean
    public GestionEquipe gestionEquipe(EquipeCRUD equipeCRUD, PersonneCRUD personneCRUD){
        return new GestionEquipeService(equipeCRUD, personneCRUD);
    }

    @Bean
    public GestionPersonne gestionPersonne(PersonneCRUD personneCRUD){
        return new GestionPersonneService(personneCRUD);
    }
}
