package br.rarantes.si.poowi.dao;

import br.rarantes.si.poowi.connection.Conexao;
import br.rarantes.si.poowi.model.Usuario;

import java.sql.*;
import java.util.Date;

public class UsuarioDAO {
    public Usuario getUsuario(String email) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuario user = new Usuario();
        try (Connection con = Conexao.getConnection()) {
            stmt = con.prepareStatement("SELECT * FROM usuario WHERE email = ?");
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            while (rs.next()) {
                user = new Usuario();
                user.setId(rs.getInt("id"));
                user.setNome(rs.getString("nome"));
                user.setEmail(rs.getString("email"));
                user.setSenha(rs.getString("senha"));
            }
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return user;
    }
    public boolean postUsuario(Usuario user) throws ClassNotFoundException {
        PreparedStatement stmt = null;
        int rs = 0;
        try (Connection con = Conexao.getConnection()) {
            Date dt = new Date();
            stmt = con.prepareStatement("INSERT INTO usuario VALUES (DEFAULT, ?, ?, ?, true, DEFAULT )");
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getEmail());
            stmt.setString(3, user.getSenha());
            rs = stmt.executeUpdate();
            return true;
        } catch(SQLException ex){
            System.out.println(ex);
        }
        return false;
    }
}
