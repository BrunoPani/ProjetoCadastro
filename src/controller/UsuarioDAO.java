/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.Usuario;

/**
 *
 * @author clebe
 */
public class UsuarioDAO {

    private Connection conexao;
    PreparedStatement stm;
    ResultSet rs;

    public UsuarioDAO() {

        this.conexao = ModuleConexao.conectar();
    }

    public void cadastrarUsuario(Usuario obj) {
        try {
            //1 passo criar o comando sql
            String sql = "insert into usuarios (nome, login, senha, perfil)values (?,?,?,?)";
            //2 passo conectar o banco de dados e organizar o comando sql
            stm = conexao.prepareStatement(sql);
            stm.setString(1, obj.getNome());
            stm.setString(2, obj.getLogin());
            stm.setString(3, obj.getSenha());
            stm.setString(4, obj.getPerfil());

            //3 passo executar o comando sql
            stm.execute();
            stm.close();
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!!");
        } catch (Exception erro) {
            JOptionPane.showMessageDialog(null, "Erro:" + erro);
        }

    }

    public List<Usuario> consultaUsarios() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario;
        try {
            conexao = ModuleConexao.conectar();
            String sql = "select * from usuarios";
            stm = conexao.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil(rs.getString("perfil"));

                listaUsuarios.add(usuario);

            }
//            conexao = ModuleConexao.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaUsuarios;
    }

    public List<Usuario> consultaUsuarioNome(String nome) {
        List<Usuario> listaUsuarios = new ArrayList<>();
        Usuario usuario;
        try {
            conexao = ModuleConexao.conectar();
            String sql = "select * from usuarios where usuarios.nome like ?";
            stm = conexao.prepareStatement(sql);
            nome = "%" + nome + "%";
            stm.setString(1, nome);

            rs = stm.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("idusuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setLogin(rs.getString("login"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setPerfil(rs.getString("perfil"));

                listaUsuarios.add(usuario);

            }
//            conexao = ModuleConexao.desconectar();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return listaUsuarios;
    }

    public boolean excluirUsuario(Usuario usuario) {
        try {
            conexao = ModuleConexao.conectar();
            String sql = "delete from usuarios where usuarios.idusuario=?";
            stm = conexao.prepareStatement(sql);
            stm.setInt(1, usuario.getId());
            stm.executeUpdate();

            conexao.close();

            return true;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

    public boolean alterarUsuario(Usuario usuario) {
        try {
            conexao = ModuleConexao.conectar();
            String sql = "update usuarios set nome = ?, login = ?, senha = ?, perfil = ? where usuarios.idusuario = ?";
            
            stm  = conexao.prepareStatement(sql);
            stm.setString(1, usuario.getNome());
            stm.setString(2, usuario.getLogin());
            stm.setString(3, usuario.getSenha());
            stm.setString(4, usuario.getPerfil());
            stm.setInt(5, usuario.getId());
            
            stm.executeUpdate();
            conexao.close();
            return true;
        } catch (SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

}
