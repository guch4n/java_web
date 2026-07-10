package com.entra21.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException; //verificações de entrada e saida
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.crypto.Data;

import com.entra21.db.GastoDAO;
import com.entra21.model.Gasto;
import com.entra21.model.Usuario;

@WebServlet("/painel") // Por ser um servlet é obrigatorio possuir essa linha
public class PainelServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(); // Puxa a sessão do usuario, caso não dê certo, volta pro login
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        ;

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        GastoDAO dao = new GastoDAO();
        ArrayList<Gasto> listaGastos = dao.listarGastosDoUsuario(usuario.getId());
        
        request.setAttribute("listaGastos", listaGastos);
        request.setAttribute("nomeUsuario", usuario.getNome()); // insere o nome do usuario após o OLA na pagina principal

        request.getRequestDispatcher("painel.jsp").forward(request, response);
    }

}
