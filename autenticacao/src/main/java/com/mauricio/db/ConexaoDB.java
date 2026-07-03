package com.mauricio.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoDB {

    //defini o caminho padrão para o banco de dados abaixo
    private static final String URL = "jdbc:mysql://localhost:3306/autenticacao";
    private static final String USUARIO = "root";
    private static final String SENHA = "";

    public static Connection conectar() {
        try{
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USUARIO, SENHA);

        } catch(ClassNotFoundException | SQLException e) {
            System.out.println("Erro ao conectar no banco: " + e.getMessage());
            return null;
        }
    }
    
}
