package br.rarantes.si.poowi.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
java-mvc-with-webservlets-and-jsp
        Trabalho desenvolvido durante a disciplina de Programação Orientada a Objetos da Web I - Implementação de sistema de loja: CRUD de produtos e clientes com usuários, permissões e sessões
public class Conexao {
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String URL = "jdbc:postgresql://localhost:5433/poowi";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123456";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException ex) {
            System.out.println(ex);
            return null;
        }
    }
}
