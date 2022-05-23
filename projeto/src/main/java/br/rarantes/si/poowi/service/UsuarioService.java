package br.rarantes.si.poowi.service;

import br.rarantes.si.poowi.dao.UsuarioDAO;
import br.rarantes.si.poowi.model.Usuario;

import java.sql.SQLException;


public class UsuarioService {
    private UsuarioDAO dao;

    public Usuario autenticado (String email, String senha) throws SQLException, ClassNotFoundException {
        dao = new UsuarioDAO();
        Usuario user = dao.getUsuario(email);
        if(user.getEmail().equals(email) && user.getSenha().equals(senha)) {
            return user;
        }
        return null;
    }
}
