package br.rarantes.si.poowi.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("controlador")
public class DashboardController extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // System.out.println("********\nuser-agent: " + req.getHeader("User-Agent") + "\n********");
        String opcao = req.getParameter("opcao");
        String uri = "/";
        if (req.getSession().getAttribute("usuario_logado") != null) {
            if (opcao.equals("cliente") || opcao.equals("produto")) {
                uri = "/WEB-INF/home/" + opcao + ".jsp";
            }  else if(opcao.equals("logout")) {
                req.getSession().invalidate();
                uri = "/";
            }  else {
                uri = "/WEB-INF/home/dashboard.jsp";
            }
        }
        RequestDispatcher rd = req.getRequestDispatcher(uri);
        rd.forward(req, resp);
    }
}
