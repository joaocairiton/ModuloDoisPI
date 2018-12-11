package com.arionporfirio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BD {
    private Connection connection;
    
    public BD() {
        connection = null;
    }    
    
    public Connection getConnection() {
        return connection;
    } 
    
    public boolean connect() {
        connection = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            String msg = String.format("Driver JDBC PostgreSQL não foi encontrado.%n%s", e.getMessage());
            JOptionPane.showMessageDialog(null, msg, "Conectar com BD", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String connectionPath = "jdbc:postgresql://localhost:5432/registro_vendas";
        try {
            connection = DriverManager.getConnection(connectionPath, "postgres", "postgres");
        } catch(SQLException e) {
            String msg = String.format("Não foi possível conectar com o BD.%n%s", e.getMessage());
            JOptionPane.showMessageDialog(null, msg, "Conectar com BD", JOptionPane.ERROR_MESSAGE);
            connection = null;
            return false;
        }
        return true;        
    }
    
   public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
            } catch (SQLException e) {
            String msg = String.format("Erro ao encerrar o BD.%n%s", e.getMessage());
            JOptionPane.showMessageDialog(null, msg, "Conectar com BD", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    
}
