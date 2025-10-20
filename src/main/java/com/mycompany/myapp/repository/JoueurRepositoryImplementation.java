package com.mycompany.myapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.mycompany.myapp.DataSourceProvider;
import com.mycompany.myapp.entity.Joueur;

public class JoueurRepositoryImplementation {

    public void create(Joueur joueur) {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSource();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn
                    .prepareStatement("INSERT INTO joueur(nom, prenom, sexe) VALUES(?, ?, ?)");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());

            preparedStatement.executeUpdate();

            conn.commit();

            System.out.println(String.format("Succes : Joueur %s %s de sexe %c créé", joueur.getPrenom(), joueur.getNom(), joueur.getSexe()));

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public Joueur getById(long id){

        DataSource dataSource = DataSourceProvider.getSingleDataSource();

        Connection connect = null;

        Joueur joueur = null;

        try {
            
            connect = dataSource.getConnection();

            PreparedStatement preparedStatement = connect.prepareStatement("SELECT nom, prenom, sexe FROM joueur WHERE id=?");

            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){
                joueur = new Joueur();

                String nom = rs.getString("nom");
                String prenom = rs.getString("prenom");
                Character sexe = rs.getString("sexe").charAt(0);
            
                joueur.setId(id);
                joueur.setNom(nom);
                joueur.setPrenom(prenom);
                joueur.setSexe(sexe);

                System.out.println(String.format("Joueur avec l'id %d retourné : %s %s, sexe : %c", id, prenom, nom, sexe));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(connect != null)
                    connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }
        finally {
            try {
                if (connect!=null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return joueur;
    }

    public List<Joueur> getAll(){

        Connection connect = null;

        List<Joueur> joueurs = new ArrayList<>();

        try {
            
        DataSource dataSource = DataSourceProvider.getSingleDataSource();

        connect = dataSource.getConnection();

        PreparedStatement preparedStatement = connect.prepareStatement("SELECT id, nom, prenom, sexe FROM joueur");

        ResultSet rs = preparedStatement.executeQuery();

        while (rs.next()) {
            
            Long id = rs.getLong("id");
            String nom = rs.getString("nom");
            String prenom = rs.getString("prenom");
            Character sexe = rs.getString("sexe").charAt(0);

            Joueur joueur = new Joueur(id, nom, prenom, sexe);

            joueurs.add(joueur);
        }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connect != null)
                    connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return joueurs;
    }

    public void update(Joueur joueur) {

        Connection conn = null;
        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSource();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn
                    .prepareStatement("UPDATE joueur SET nom=?, prenom=?, sexe=? WHERE id=?");

            preparedStatement.setString(1, joueur.getNom());
            preparedStatement.setString(2, joueur.getPrenom());
            preparedStatement.setString(3, joueur.getSexe().toString());
            preparedStatement.setLong(4, joueur.getId());

            preparedStatement.executeUpdate();

            conn.commit();

            System.out.println("Succes : Joueur mis à jour");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(long id){

        Connection connect = null;

        try {

            DataSource dataSource = DataSourceProvider.getSingleDataSource();

            connect = dataSource.getConnection();

            connect.setAutoCommit(false);

            PreparedStatement preparedStatement = connect.prepareStatement("DELETE FROM joueur WHERE id=?");

            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            connect.commit();

            System.out.println("Joueur avec l'id " + id + " supprimé");
            
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (connect != null)
                    connect.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                if (connect != null) {
                    connect.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
