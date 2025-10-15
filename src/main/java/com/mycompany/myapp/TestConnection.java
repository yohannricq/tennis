package com.mycompany.myapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestConnection {

    public static void main(String... args){
        Connection conn = null;
        try {
            
            //MySQL driver MySQL Connector
            //conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tennis?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=Europe/Paris","COURSDB","COURSDB");
            //Oracle Driver officiel OJDBC Thin
            //conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:tennis","COURSDB","COURSDB");
            //Postgres Driver officiel
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/tennis","postgres","p@ssw0rd");

            PreparedStatement preparedStatement=conn.prepareStatement("UPDATE joueur SET nom=?, prenom=? WHERE id=?");
            long identifiant=24L;
            String nom = "Errani";
            String prenom = "Sara";

            preparedStatement.setString(1, nom);
            preparedStatement.setString(2, prenom);
            preparedStatement.setLong(3,identifiant);

            int nbRowsUpdated = preparedStatement.executeUpdate();

            System.out.println("Nombre lignes modifiées : " + nbRowsUpdated);
            System.out.println("success");

        } catch (SQLException e) {
            e.printStackTrace();
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
