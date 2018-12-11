package com.arionporfirio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class VendaModel {
    public static ArrayList<Venda> listarVendas(Connection bd) {
        ArrayList<Venda> listaDeVendas = new ArrayList();
        if(bd == null) {
            return listaDeVendas;
        }
        
        PreparedStatement stmt;
        try {
            String query = "SELECT * FROM venda ORDER BY nota_fiscal";
            stmt = bd.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            Venda novaVenda;
            String notaFiscal, clienteCpf, pagamento;
            HashMap<String,Integer> produtos = new HashMap();
            double valorTotal, desconto;
            Date dataDaVenda;
            
            while(rs.next()) {
                notaFiscal = rs.getString("nota_fiscal");
                clienteCpf = rs.getString("cliente_cpf");
                valorTotal = rs.getDouble("valor_total");
                desconto = rs.getDouble("desconto");
                pagamento = rs.getString("pagamento");
                dataDaVenda = new Date(rs.getDate("data_da_venda").getTime());
                
                novaVenda = new Venda(notaFiscal, clienteCpf, produtos,
                        valorTotal, desconto, pagamento, dataDaVenda);
                listaDeVendas.add(novaVenda);
            }
            rs.close();
            stmt.close();
            
            if(!listaDeVendas.isEmpty()) {
                int i = 0;
                for(Venda venda: listaDeVendas) {
                    query = "SELECT * FROM produto_venda WHERE nota_fiscal = ?";
                    stmt = bd.prepareStatement(query);
                    stmt.setString(1, venda.getNotaFiscal());
                    rs = stmt.executeQuery();          
                    
                    while(rs.next()) {
                        produtos.put(rs.getString("cod_produto"),
                                rs.getInt("quantidade"));
                    }
                    
                    venda.setProdutos(produtos);
                    listaDeVendas.set(i, venda);
                    
                    produtos = new HashMap();                    
                    i++;
                }
                rs.close();
                stmt.close();
            }                        
            
            return listaDeVendas;
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Erro ao listar as vendas!", JOptionPane.ERROR_MESSAGE);            
            return listaDeVendas;
        }
    }
    
    public static boolean criarVenda(Connection bd, Venda novaVenda) {
        if(bd == null) {
            return false;
        }
        
        PreparedStatement stmt;
        
        try {
            String query = "INSERT INTO venda (nota_fiscal, cliente_cpf,"
                    + " valor_total, desconto, pagamento, data_da_venda ) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, novaVenda.getNotaFiscal());
            stmt.setString(2, novaVenda.getClienteCpf());
            stmt.setDouble(3, novaVenda.getValorTotal());
            stmt.setDouble(4, novaVenda.getDesconto());
            stmt.setString(5, novaVenda.getPagamento());
            stmt.setDate(6, new java.sql.Date(novaVenda.getDataDaVenda().getTime()));
            int vendaResult = stmt.executeUpdate();
            stmt.close();
            if(vendaResult > 0) {
                for(Map.Entry produto: novaVenda.getProdutos().entrySet()) {
                    query = "INSERT INTO produto_venda (cod_produto, "
                            + "nota_fiscal, quantidade) VALUES (?, ?, ?)";
                    stmt = bd.prepareStatement(query);
                    stmt.setString(1, (String) produto.getKey());
                    stmt.setString(2, novaVenda.getNotaFiscal());
                    stmt.setInt(3, (int) produto.getValue());
                    int produtoResult = stmt.executeUpdate();
                    if(!(produtoResult > 0)) return false;
                }
                stmt.close();
                return true;
            } else return false;
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Erro ao criar a venda!", JOptionPane.ERROR_MESSAGE);            
            return false;
        }
        
    }
    
    public static boolean deletarVenda(Connection bd, String notaFiscal) {
        if(bd == null) {
            return false;
        }
        
        PreparedStatement stmt;
        try {
            String query = "DELETE FROM venda WHERE nota_fiscal = ?";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, notaFiscal);
            int result = stmt.executeUpdate();
            stmt.close();
            return result > 0;
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Erro ao deletar a venda!", JOptionPane.ERROR_MESSAGE);            
            return false;
        }
    }
    
    public static boolean alterarVenda(Connection bd, Venda venda) {
        if(bd == null) {
            return false;
        }
        
        PreparedStatement stmt;
        
        try {
            String query = "UPDATE venda SET desconto = ?, pagamento = ?, "
                    + "data_da_venda = ? WHERE nota_fiscal = ?";
            stmt = bd.prepareStatement(query);
            stmt.setDouble(1, venda.getDesconto());
            stmt.setString(2, venda.getPagamento());
            stmt.setDate(3, new java.sql.Date(venda.getDataDaVenda().getTime()));
            stmt.setString(4, venda.getNotaFiscal());
            int result = stmt.executeUpdate();
            stmt.close();
            return result > 0;
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Erro ao alterar a venda!", JOptionPane.ERROR_MESSAGE);            
            return false;
        }        
    }
    
    public static boolean deletarProdutoVenda(Connection bd, String notaFiscal,
            String produtoCod) {
        if(bd == null) {
            return false;
        }
        
        PreparedStatement stmt;
        
        try {
            String query = "DELETE FROM produto_venda WHERE nota_fiscal = ?"
                    + " AND cod_produto = ?";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, notaFiscal);
            stmt.setString(2, produtoCod);
            int result = stmt.executeUpdate();
            stmt.close();
            return result > 0;
            
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Erro ao deletar o produto da venda!", JOptionPane.ERROR_MESSAGE);            
            return false;
        }
    }
    
    public static boolean alterarProdutoVenda(Connection bd, String produtoCod, 
            String notaFiscal, int qtd) {
        if(bd == null) {
            JOptionPane.showMessageDialog(null,"Erro ao alterar o produto da venda!",
                "Erro ao alterar o produto da venda!", JOptionPane.ERROR_MESSAGE);              
            return false;
        }
        
        PreparedStatement stmt;
        
        try {
            String query = "UPDATE produto_venda SET quantidade = ? "
                    + "WHERE cod_produto = ? AND nota_fiscal = ?";
            stmt = bd.prepareStatement(query);
            stmt.setInt(1, qtd);
            stmt.setString(2, produtoCod);
            stmt.setString(3, notaFiscal);
            int result = stmt.executeUpdate();
            stmt.close();
            return result > 0;
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Erro ao alterar o produto da venda!", JOptionPane.ERROR_MESSAGE);            
            return false;
        }
    }    
}
