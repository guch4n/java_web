package com.mauricio.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/saudacao")
public class SaudacaoServlet extends HttpServlet {

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nomeUsuario = request.getParameter("nomeinput");

        request.setAttribute("nome", nomeUsuario);

        request.getRequestDispatcher("saudacao.jsp").forward(request, response);

    }
}
