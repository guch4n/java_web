package com.mauricio.db;

import com.mauricio.model.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioDAO {

    public Usuario autenticar(String usuarioDigitado, String senhaDigitada) {
        Usuario usuarioEncontrado = null;

        // Variavel criada que tentará se conectar ao banco através do catch
        // É utilizado o "?" para evitar SQL Injection
        String sql = "SELECT * FROM usuarios WHERE usuario = ? AND senha = ?";
        // Caso dê certo, a variavel do tipo PreparedStatement
        try (Connection conexao = ConexaoDB.conectar(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setString(1, usuarioDigitado);
            comando.setString(2, senhaDigitada);

            ResultSet resultado = comando.executeQuery(); // executará o texto sql com as variaveis definidas
            if (resultado.next()) { // next pegará o primeiro da lista
                usuarioEncontrado = new Usuario(resultado.getString("nome"), resultado.getString("usuario"),
                        resultado.getString("senha"));
            }
        } catch (Exception e) {
            System.out.println("Erro no banco de dados: " + e.getMessage());
        }
        return usuarioEncontrado;
    }

    public boolean cadastrar(Usuario novoUsuario) {

        String sql = "INSERT INTO usuarios (nome, usuario, senha) VALUES(?, ?, ?)";

        try (Connection conexao = ConexaoDB.conectar(); PreparedStatement comando = conexao.prepareStatement(sql)){
            comando.setString(1, novoUsuario.getNome());
            comando.setString(2, novoUsuario.getUsuario());
            comando.setString(3, novoUsuario.getSenha());

            int linhasAfetadas = comando.executeUpdate();
            return (linhasAfetadas > 0);

            
        } catch (SQLException e) {
            System.out.println("Erro ao cadastrar usuário: " + e.getMessage());
            return false;
        }
    }

}
