package com.arionporfirio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ProdutoModel {
    public static ArrayList<Produto> listarProdutos(Connection bd) {
        ArrayList<Produto> produtos = new ArrayList();
        
        if(bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);            
            return produtos;            
        }
        
        PreparedStatement stmt;
        
        try {
            String query = "SELECT * FROM produto ORDER BY codigo";
            stmt = bd.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            String codigo, descricao, unidade;
            double preco;
            Produto novoProduto;
            
            while(rs.next()) {
                codigo = rs.getString("codigo");
                descricao = rs.getString("descricao");
                preco = rs.getDouble("preco");
                unidade = rs.getString("unidade");
                
                novoProduto = new Produto(codigo, descricao, preco, unidade);
                produtos.add(novoProduto);
                
            }
            stmt.close();
            rs.close();
            return produtos;
            
        } catch(SQLException ex) {
            return produtos;
        }
    }
    
    public static int criarProduto(Connection bd, Produto novoProduto) {
        if(bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);            
            return 0;            
        }
        
        PreparedStatement stmt;
        
        try {
            String query = "INSERT INTO produto(codigo,"
                    + " descricao, preco, unidade) VALUES(?, ?, ?, ?)";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, novoProduto.getCodigo());
            stmt.setString(2, novoProduto.getDescricao());
            stmt.setDouble(3, novoProduto.getPreco());
            stmt.setString(4, novoProduto.getUnidade());
            
            int resultado = stmt.executeUpdate();                      
            stmt.close();
            
            return resultado;
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar o produto no BD.\n" + ex.getMessage(),
                    "Registrar Produto", JOptionPane.ERROR_MESSAGE);
            return 0;            
        }
    }
    
    public static boolean alterarProduto(Connection bd, Produto alterado) {
        if(bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);            
            return false;            
        }
        
        PreparedStatement stmt;
        
        try {
            String query = "UPDATE produto SET descricao = ?, preco = ?, "
                    + "unidade = ? WHERE codigo = ?";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, alterado.getDescricao());
            stmt.setDouble(2, alterado.getPreco());
            stmt.setString(3, alterado.getUnidade());
            stmt.setString(4, alterado.getCodigo());            
            
            int result = stmt.executeUpdate();
            stmt.close();
            
            return result > 0;                        
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados do produto no BD.\n" + ex.getMessage(),
                    "Alterar Dados do Produto", JOptionPane.ERROR_MESSAGE);
            return false;            
        }        
    }
    
    public static boolean deletarProduto(Connection bd, String codigo) {
        if(bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);            
            return false;            
        }
        PreparedStatement stmt;
        try {
            String query = "DELETE FROM produto WHERE codigo = ?";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, codigo);
            
            int result = stmt.executeUpdate();
            stmt.close();
            
            return result > 0;
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o produto no BD.\n" + ex.getMessage(),
                    "Deletar Produto", JOptionPane.ERROR_MESSAGE);
            return false;            
        }
    }
}
