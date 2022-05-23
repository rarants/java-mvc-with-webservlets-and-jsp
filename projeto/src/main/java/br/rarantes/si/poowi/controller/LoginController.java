package br.rarantes.si.poowi.controller;

import br.rarantes.si.poowi.model.Usuario;
import br.rarantes.si.poowi.service.UsuarioService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("login controller working");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        RequestDispatcher rd;
        Usuario user = null;
        try {
            user = new UsuarioService().autenticado(email, password);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if ( user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("usuario_logado", user);
            rd = req.getRequestDispatcher("/WEB-INF/home/dashboard.jsp");
        } else {
            req.setAttribute("error", "Usuário e/ou senha incorretos!");
            rd = req.getRequestDispatcher("/WEB-INF/home/login.jsp");
        }
        rd.forward(req, resp);
    }
}
