package fr.kira.formation.spring.hexagonal.competences.usecases;

import fr.kira.formation.spring.hexagonal.competences.models.Personne;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.sortie.PersonneCRUD;
import fr.kira.formation.spring.hexagonal.competences.usecases.ports.entree.GestionPersonne;

public class GestionPersonneService implements GestionPersonne {
    private final PersonneCRUD personneCRUD;

    public GestionPersonneService(PersonneCRUD personneCRUD) {
        this.personneCRUD = personneCRUD;
    }


    @Override
    public Personne creationPersonne(String nom, String prenom) {
        return this.personneCRUD.sauvegarder(new Personne());
    }
}
