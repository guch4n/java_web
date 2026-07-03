package com.mauricio.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mauricio.db.UsuarioDAO;
import com.mauricio.model.Usuario;

@WebServlet("/cadastro")
public class CadastroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Garante que requisições e respostas processem acentos corretamente
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Seu código vem aqui
        String nomeDigitado = request.getParameter("nome");
        String usuarioDigitado = request.getParameter("usuario");
        String senhaDigitada = request.getParameter("senha");

        Usuario novoUsuario = new Usuario(nomeDigitado, usuarioDigitado, senhaDigitada);

        UsuarioDAO dao = new UsuarioDAO();
        boolean sucesso = dao.cadastrar(novoUsuario);

        if (!sucesso) {
            request.setAttribute("mensagemErro", "Este usuário já existe!");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }
}