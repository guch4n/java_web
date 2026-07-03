package br.com.aula.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/minhaRota")
public class TemplateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Garante que requisições e respostas processem acentos corretamente
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Seu código vem aqui
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Garante que requisições e respostas processem acentos corretamente
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Seu código vem aqui
        
    }
}