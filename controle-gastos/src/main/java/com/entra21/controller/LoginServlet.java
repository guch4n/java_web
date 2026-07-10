package com.entra21.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException; //verificações de entrada e saida
import com.entra21.db.UsuarioDAO;
import com.entra21.model.Usuario;

@WebServlet("/login") // Por ser um servlet é obrigatorio possuir essa linha
public class LoginServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 1. Capturar o usuario e senha digitados no form de login
        String usuarioDigitado = request.getParameter("usuario");
        String senhaDigitada = request.getParameter("senha");

        // 2. Instanciar o UsuarioDAO
        UsuarioDAO dao = new UsuarioDAO();

        // 3. Chamar o método autenticar do DAO passando o usuario e senha
        // digitados
        Usuario usuarioLogado = dao.autenticar(usuarioDigitado, senhaDigitada);

        if (usuarioLogado != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", usuarioLogado);
            response.sendRedirect("painel.jsp");

        } else {
            request.setAttribute("mensagemErro", "Usuario ou senha invalidos!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

}
