package com.entra21.controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException; //verificações de entrada e saida
import java.time.LocalDate;

import javax.xml.crypto.Data;

import com.entra21.db.GastoDAO;
import com.entra21.model.Gasto;
import com.entra21.model.Usuario;

@WebServlet("/inserirGasto") // Por ser um servlet é obrigatorio possuir essa linha
public class InserirGastoServlet extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession(); // Puxa a sessão do usuario
        if (session == null || session.getAttribute("usuarioLogado") == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        ;

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");

        // 1. Peguem as informações digitadas pelo usuario no inserir-gasto.jsp
        String descricaoDigitado = request.getParameter("descricao");
        Double valorDigitado = Double.parseDouble(request.getParameter("valor"));
        LocalDate dataDigitada = LocalDate.parse(request.getParameter("data"));
        String categoriaEscolhida = request.getParameter("categoria");
        int uId = usuario.getId();

        // 2. Transformar as informações digitadas em um novo objeto do tipo Gasto
        Gasto novoGasto = new Gasto(uId, descricaoDigitado, valorDigitado, dataDigitada, categoriaEscolhida);

        // 3. Crie uma instância do GastoDAO
        GastoDAO dao = new GastoDAO();

        // 4. Chamar o método inserirGasto passando o objeto novoGasto
        boolean inseriuGasto = dao.inserirGasto(novoGasto);

        // 5. Redirecionar o usuário para o painel caso o gasto tenha sido inserido
        if (inseriuGasto == true) {
            response.sendRedirect("painel");
        } else {
            request.setAttribute("mensagemErro", "Ocorreu um erro.");
            request.getRequestDispatcher("inserir-gasto.jsp").forward(request, response);
        }

    }

}
