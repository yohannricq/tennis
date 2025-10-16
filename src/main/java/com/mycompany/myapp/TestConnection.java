package com.mycompany.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.postgresql.ds.PGSimpleDataSource;

public class TestConnection {

    public static void main(String... args){
        Connection conn = null;
        try {
            
            PGSimpleDataSource dataSource = new PGSimpleDataSource();

            String url = "jdbc:postgresql://localhost:5432/tennis";
            dataSource.setURL(url);
            dataSource.setUser("postgres");
            dataSource.setPassword("p@ssw0rd");
            //MySQL driver MySQL Connector
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","COURSDB","COURSDB");
            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
            //Postgres Driver officiel
            //conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","postgres","p@ssw0rd");

            conn = dataSource.getConnection();
            conn.setAutoCommit(false);

            PreparedStatement preparedStatement=conn.prepareStatement("INSERT INTO joueur(nom, prenom, sexe) VALUES(?, ?, ?)");
            
            String nom = "Capriati";
            String prenom = "Jennifer";
            String sexe = "F";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);

            preparedStatement.executeUpdate();

            nom = "Johannson";
            prenom = "Thomas";
            sexe = "H";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setString(3, sexe);
            //preparedStatement.setNull(3, java.sql.Types.CHAR);//Pour provoquer le rollback

            preparedStatement.executeUpdate();

            conn.commit();

            System.out.println("success");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if(conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            
        }
        finally {
            try {
                if (conn!=null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
