/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Fornecedor;

/**
 *
 * @author clebe
 */
public class FornecedorDAO {
    private Connection con;
    ResultSet rs;

    public FornecedorDAO(Connection con) {
        this.con = ModuleConexao.conectar();
    }

    public FornecedorDAO() {
        
    }

    
    
    //Metodo cadastrarFornecedor
    public void cadastrarFornecedor(Fornecedor obj) {
        try {
            con = ModuleConexao.conectar();

            //1 passo  - criar o comando sql
            String sql = "insert into fornecedores (nome,cnpj,email,telefone,celular,cep,endereco,numero,complemento,bairro,cidade,estado)  values (?,?,?,?,?,?,?,?,?,?,?,?)";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }
    
     //Metodo AlterarFornecedor
    public void alterarFornecedor(Fornecedor obj) {
        try {
            con = ModuleConexao.conectar();

            //1 passo  - criar o comando sql
            String sql = "update fornecedores set  nome=?, cnpj=?, email=?, telefone=?, celular=?, cep=?, endereco=?, numero=?,complemento=?,bairro=?,cidade=?, estado=?  where id =?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getEmail());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getCep());
            stmt.setString(7, obj.getEndereco());
            stmt.setInt(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());

            stmt.setInt(14, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterado com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }
    }
    
    //Metodo ExcluirFornecedor
    public void excluirFornecedor(Fornecedor obj) {
        try {
            con = ModuleConexao.conectar();

            //1 passo  - criar o comando sql
            String sql = "delete from fornecedores where id = ?";

            //2 passo - conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            //3 passo - executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluido com Sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);

        }

    }
    
    //Metodo Listar Todos Fornecedores
    public List<Fornecedor> listarFornecedores() {
        try {
            con = ModuleConexao.conectar();

            //1 passo criar a lista
            List<Fornecedor> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from fornecedores";
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }

    }
    
   //metodo consultaFornecedor por Nome
    public Fornecedor consultaPorNome(String nome) {
        try {
            con = ModuleConexao.conectar();
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from fornecedores where nome = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();
            Fornecedor obj = new Fornecedor();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            return null;
        }
    } 
    
  //metodo busca Fornecedor  por Cnpj
    public Fornecedor buscaporCnpj(String cnpj) {
        try {
            con = ModuleConexao.conectar();
            //1 passo - criar o sql , organizar e executar.
            String sql = "select * from fornecedores where cnpj = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, cnpj);

            ResultSet rs = stmt.executeQuery();
            Fornecedor obj = new Fornecedor();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));
            }

            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Fornecedor não encontrado!");
            return null;
        }
    } 
    
    //Metodo buscarfornecedorPorNome - retorna uma lista
    public List<Fornecedor> buscaFornecedorPorNome(String nome) {
        try {
            con = ModuleConexao.conectar();

            //1 passo criar a lista
            List<Fornecedor> lista = new ArrayList<>();

            //2 passo - criar o sql , organizar e executar.
            String sql = "select * from fornecedores where nome like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Fornecedor obj = new Fornecedor();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setEmail(rs.getString("email"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCep(rs.getString("cep"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getInt("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("estado"));

                lista.add(obj);
            }

            return lista;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro :" + erro);
            return null;
        }
    }
    
}
