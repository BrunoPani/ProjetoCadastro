/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.sql.*;

/**
 *
 * @author clebe
 */
public class ModuleConexao {

    //criando um método ressponável por estabelecer uma conexão com o banco
    public static Connection conectar() {
        Connection conexao = null;
        //criando o driver  orrespondente ao banco
        String driver = "com.mysql.cj.jdbc.Driver";
        //armazenando informações referente ao banco de dados
        String url = "jdbc:mysql://localhost:3306/cadastrousuario?useTimezone=true&serverTimezone=UTC";
        String user = "root";
        String senha = "";
        //estabelecer a conexão com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, senha);
            return conexao;
        } catch (Exception e) {
            //a lina abaixo serve de apoio para esclarecer o erro
            System.out.println(e);
            return null;
        }

    }

    public static Connection desconectar() {
        Connection conexao = null;
        try {
            conexao.close();
        } catch (Exception e) {
        }

        return conexao;
    }
}


