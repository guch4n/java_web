package com.entra21.db;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.print.DocFlavor.STRING;

import com.entra21.model.Gasto;
import com.entra21.db.ConexaoDB;

public class GastoDAO { // Classe que interage com o banco de dados, somente o DAO pode acessar o banco

    public boolean inserirGasto(Gasto gasto) {
        String sql = "INSERT INTO gastos (usuario_id, descricao, valor, data_gasto, categoria) VALUES (?, ?, ?, ?, ?)";
        // toda conexao de banco é feita através de um trycatch
        // classe prepareStatement responsavel por lidar com as interrogações
        try (Connection conexao = ConexaoDB.conectar(); PreparedStatement comando = conexao.prepareStatement(sql)) {
            comando.setInt(1, gasto.getUsuarioId());
            comando.setString(2, gasto.getDescricao());
            comando.setDouble(3, gasto.getValor());
            comando.setDate(4, Date.valueOf(gasto.getDataGasto()));
            comando.setString(5, gasto.getCategoria());

            int linhasAfetadas = comando.executeUpdate();

            return linhasAfetadas > 0;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }

    public ArrayList<Gasto> listarGastosDoUsuario(int usuarioId) { // puxa os dados do banco de dados
        String sql = "SELECT * FROM gastos WHERE usuario_id = ? ORDER BY data_gasto DESC";
        ArrayList<Gasto> listaGastos = new ArrayList<>();

        try (Connection conexao = ConexaoDB.conectar();
            PreparedStatement comando = conexao.prepareStatement(sql)) {

            comando.setInt(1, usuarioId);
            ResultSet resultado = comando.executeQuery();


            while (resultado.next()) { //busca de forma automatica resultados dentro de uma lista, e quando não encontra para
                Gasto gasto = new Gasto(
                    usuarioId, 
                    resultado.getString("descricao"),
                    resultado.getDouble("valor"),
                    resultado.getDate("data_gasto").toLocalDate(),
                    resultado.getString("categoria")
                );
                gasto.setId(resultado.getInt("id"));

                listaGastos.add(gasto);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return listaGastos;
    }
}
