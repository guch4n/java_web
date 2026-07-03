package com.mauricio.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.mauricio.db.UsuarioDAO;
import com.mauricio.model.Usuario;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Garante que requisições e respostas processem acentos corretamente
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // Seu código vem aqui
        String usuarioDigitado = request.getParameter("usuario");
        String senhaDigitada = request.getParameter("senha");

        UsuarioDAO dao = new UsuarioDAO();

        Usuario usuarioLogado = dao.autenticar(usuarioDigitado, senhaDigitada);

        if (usuarioLogado != null) {
            // login esta autorizado
            request.getRequestDispatcher("bemvindo.jsp").forward(request, response);
        } else {
            // login nao esta autorizado
            request.setAttribute("mensagemErro", "Usuario ou senha invalidos!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

}