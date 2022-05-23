package br.rarantes.si.poowi.controller;
import br.rarantes.si.poowi.dao.UsuarioDAO;
import br.rarantes.si.poowi.model.Usuario;
import br.rarantes.si.poowi.service.UsuarioService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("register")
public class RegisterController extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String route = req.getParameter("route");
        String uri;
        if (route.equals("register")) {
            uri = "/WEB-INF/home/register.jsp";
        } else {
            uri = "/";
        }
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean registered = false;
        Usuario user = new Usuario();
        user.setEmail(req.getParameter("email"));
        user.setSenha(req.getParameter("password"));
        user.setNome(req.getParameter("nome"));
        RequestDispatcher rd;
        try {
            UsuarioDAO dao = new UsuarioDAO();
            registered = dao.postUsuario(user);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (registered) {
            rd = req.getRequestDispatcher("/WEB-INF/home/login.jsp");
        } else {
            req.setAttribute("error", "Erro ao cadastrar o usuário!");
            rd = req.getRequestDispatcher("/WEB-INF/home/register.jsp");
        }
        rd.forward(req, resp);
    }
}
