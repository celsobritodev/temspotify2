/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.professorisidro.temspotify.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Celso
 */
public class DataSource {
    private String hostname;
    private String username;
    private String password;
    private String database;
    
    private Connection connection;
    
    public DataSource() {
        try {
            hostname = "localhost";
            database ="temspotify";
            username = "root";
            password = "admin";
  
            String URL = "jdbc:mysql://"+hostname+":3306/"+database;
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(URL, username, password);
            System.out.println("DataSource - Connected");
            
        } catch (SQLException ex) {
            System.out.println("Erro ao Conectar  - "+ex.getMessage());
        }
    }
    
    public Connection getConnection()  {
        return this.connection;
    }
    
}
