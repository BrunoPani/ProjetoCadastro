/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Fornecedor;
import model.Produto;
import model.Usuario;

/**
 *
 * @author clebe
 */
public class ProdutoDAO {

    private Connection conexao;
    PreparedStatement stm;
    ResultSet rs;

    public ProdutoDAO() {

        this.conexao = ModuleConexao.conectar();
    }

    public void cadastrarProduto(Produto obj) {
        try {
            //1 passo criar o comando sql
            String sql = "insert into produtos (descricao, preco, qtd_estoque, for_id)values (?,?,?,?)";
            //2 passo conectar o banco de dados e organizar o comando sql
            stm = conexao.prepareStatement(sql);
            stm.setString(1, obj.getDescricao());
            stm.setDouble(2, obj.getPreco());
            stm.setInt(3, obj.getQtd_estoque());
            stm.setObject(4, obj.getFornecedor());

            //3 passo executar o comando sql
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
        }

    }

    public List<Produto> consultaProdutos() {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto;
        try {
            conexao = ModuleConexao.conectar();
            String sql = "select * from produtos";
            stm = conexao.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto.setId(rs.getInt("id"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtd_estoque(rs.getInt("qtd_estoque"));
                produto.setFornecedor((Fornecedor) rs.getObject("for_id"));

                listaProdutos.add(produto);

            }
            conexao = ModuleConexao.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaProdutos;
    }

    public List<Produto> consultaProdutoNome(String desc) {
        List<Produto> listaProdutos = new ArrayList<>();
        Produto produto;
        try {
            conexao = ModuleConexao.conectar();
            String sql = "select * from produtos where produtos.descricao like ?";
            stm = conexao.prepareStatement(sql);
            desc = "%" + desc + "%";
            stm.setString(1, desc);

            rs = stm.executeQuery();
            while (rs.next()) {
                produto = new Produto();
                produto = new Produto();
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQtd_estoque(rs.getInt("qtd_estoque"));
                produto.setFornecedor((Fornecedor) rs.getObject("for_id"));

                listaProdutos.add(produto);

            }
            conexao = ModuleConexao.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaProdutos;
    }

    public boolean excluirProduto(Produto produto) {
        try {
            conexao = ModuleConexao.conectar();
            String sql = "delete from produtos where produtos.id=?";
            stm = conexao.prepareStatement(sql);
            stm.setInt(1, produto.getId());
            stm.executeUpdate();

            conexao.close();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    public boolean alterarProduto(Produto produto) {
        try {
            conexao = ModuleConexao.conectar();
            String sql = "update produtos set descricao = ?, preco = ?, qtd_estoque = ?, for_id = ? where produtos.id = ?";
            
            stm  = conexao.prepareStatement(sql);
            stm.setString(1, produto.getDescricao());
            stm.setDouble(2, produto.getPreco());
            stm.setInt(3, produto.getQtd_estoque());
            stm.setObject(4, produto.getFornecedor());
            stm.setInt(5, produto.getId());
            
            stm.executeUpdate();
            conexao.close();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

}
