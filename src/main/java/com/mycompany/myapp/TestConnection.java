package com.mycompany.myapp;

import java.util.List;

import com.mycompany.myapp.entity.Joueur;
import com.mycompany.myapp.repository.JoueurRepositoryImplementation;

public class TestConnection {

    public static void main(String... args){
        
        JoueurRepositoryImplementation repository = new JoueurRepositoryImplementation();

        /* Joueur joueur = new Joueur("Noah", "Yannik", 'H');
        repository.create(joueur); */

        /* Joueur joueur = repository.getById(57L);
        joueur.setPrenom("Yannick");
        repository.update(joueur); */

        //repository.delete(57L);

        List<Joueur> joueurs = repository.getAll();

        for (Joueur joueur : joueurs) {
            
            System.out.println(String.format("Joueur -> id:%d|nom:%s|prenom:%s|sexe:%c", joueur.getId(), joueur.getNom(), joueur.getPrenom(), joueur.getSexe()));
        }

    }
}
