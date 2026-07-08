package com.entra21.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException; //verificações de entrada e saida
import com.entra21.db.UsuarioDAO;
import com.entra21.model.Usuario;

@WebServlet("/cadastro") // Por ser um servlet é obrigatorio possuir essa linha
public class CadastroServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        // 1. Captura nome, usuario e senha digitados no formulário de cadastro
        String nomeDigitado = request.getParameter("nome");
        String usuarioDigitado = request.getParameter("usuario");
        String senhaDigitada = request.getParameter("senha");

        // 2. Transformar esses dados em um novoUsuario do tipo Usuario
        Usuario novoUsuario = new Usuario(nomeDigitado, usuarioDigitado, senhaDigitada);

        // 4. Chama a função cadastrar do UsuarioDAO passando o novoUsuario
        //Verifica se o retorno é true or false
        UsuarioDAO dao = new UsuarioDAO();
        boolean sucesso = dao.cadastrar(novoUsuario);

        // Se for true, leva o usuário para login
        // Se for false, mantem o usuario na tela cadastrar
        if (!sucesso) {
            request.setAttribute("mensagemErro", "Este usuário já existe!");
            request.getRequestDispatcher("cadastro.jsp").forward(request, response);
        } else {
            request.setAttribute("mensagemSucesso", "Cadastro efetuado com sucesso!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }

    }

}
