package com.mycompany.myapp;

import com.mycompany.myapp.entity.Joueur;
import com.mycompany.myapp.repository.JoueurRepositoryImplementation;

public class TestConnection {

    public static void main(String... args){
        
        JoueurRepositoryImplementation repository = new JoueurRepositoryImplementation();

        //repository.getById(21L);

        /* Joueur joueur1 = new Joueur("Noah", "Yannik", 'H');
        repository.create(joueur1); */

        Joueur joueur1 = repository.getById(55L);

        joueur1.setPrenom("Yannick");

        repository.update(joueur1);

    }
}
