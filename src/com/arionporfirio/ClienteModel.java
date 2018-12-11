package com.arionporfirio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

public class ClienteModel {

    public static ArrayList<Cliente> listarClientes(Connection bd) {
        ArrayList<Cliente> clientesTodos = new ArrayList();

        if (bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return clientesTodos;
        }

        try {
            String query = "SELECT * FROM cliente ORDER BY nome";
            PreparedStatement stmt = bd.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            String CPF, nome, fone, endereco;
            HashMap<String, String> telefones = new HashMap();
            Cliente novoCliente;

            while (rs.next()) {
                CPF = rs.getString("CPF");
                nome = rs.getString("nome");
                endereco = rs.getString("endereco");

                novoCliente = new Cliente(CPF, nome, telefones, endereco);
                clientesTodos.add(novoCliente);
            }
            rs.close();
            stmt.close();
            if (!clientesTodos.isEmpty()) {
                int i = 0;
                for (Cliente cliente : clientesTodos) {
                    query = "SELECT * FROM telefone_cliente WHERE "
                            + "cliente_cpf = ? ORDER BY telefone";
                    stmt = bd.prepareStatement(query);
                    stmt.setString(1, cliente.getCPF());
                    rs = stmt.executeQuery();

                    while (rs.next()) {
                        telefones.put(rs.getString("telefone"), rs.getString("tipo"));
                    }
                    cliente.setTelefones(telefones);
                    clientesTodos.set(i, cliente);
                    telefones = new HashMap();
                    i++;
                }
                rs.close();
                stmt.close();
            }

            return clientesTodos;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na consulta SQL.\n" + e.getMessage(),
                    "Ler Dados Cliente", JOptionPane.ERROR_MESSAGE);
            return clientesTodos;
        }
    }

    public static boolean criarCliente(Connection bd, Cliente cliente) {

        if (bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        PreparedStatement stmt;
        try {
            String query = "INSERT INTO cliente(cpf, nome, endereco) VALUES(?, ?, ?)";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, cliente.getCPF());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getEndereco());

            int result = stmt.executeUpdate();
            stmt.close();

            if (result > 0) {
                int result2 = 1;
                for (Map.Entry telefone : cliente.getTelefones().entrySet()) {
                    query = "INSERT INTO telefone_cliente (cliente_cpf, "
                            + "telefone, tipo) VALUES (?, ?, ?)";
                    stmt = bd.prepareStatement(query);
                    stmt.setString(1, cliente.getCPF());
                    stmt.setString(2, (String) telefone.getKey());
                    stmt.setString(3, (String) telefone.getValue());
                    result2 *= stmt.executeUpdate();
                    stmt.close();
                }
                if (result2 <= 0) {
                    JOptionPane.showMessageDialog(null, "Erro ao adicionar um telefone do cliente!",
                            "Adicionar Telefone do Cliente", JOptionPane.ERROR_MESSAGE);
                }
            }

            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao registrar o cliente no BD.\n" + e.getMessage(),
                    "Registrar Cliente", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean deletarCliente(Connection bd, String CPF) {
        if (bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }

        PreparedStatement stmt;

        try {
            String query = "DELETE FROM cliente WHERE cpf = ?";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, CPF);
            stmt.executeUpdate();
            stmt.close();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar o cliente no BD.\n" + e.getMessage(),
                    "Deletar Cliente", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean alterarCliente(Connection bd, Cliente alterado) {
        if (bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        PreparedStatement stmt;

        try {
            String query = "UPDATE cliente SET endereco = ? WHERE cpf = ?";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, alterado.getEndereco());
            stmt.setString(2, alterado.getCPF());
            int result = stmt.executeUpdate();
            stmt.close();

            return result > 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os dados do cliente no BD.\n" + e.getMessage(),
                    "Alterar Dados do Cliente", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public static boolean adicionarFonesCliente(Connection bd, String clienteCpf, 
            HashMap<String, String> fones) {
        if (bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        PreparedStatement stmt;

        try {
            int result = 1;
            for(Map.Entry fone: fones.entrySet()){
                String query = "INSERT INTO telefone_cliente (cliente_cpf, telefone,"
                        + " tipo) VALUES (?, ?, ?)";
                stmt = bd.prepareStatement(query);
                stmt.setString(1, clienteCpf);
                stmt.setString(2, (String) fone.getKey());
                stmt.setString(3, (String) fone.getValue());
                result *= stmt.executeUpdate();
                stmt.close();
            }
            return result > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar os telefones"
                    + " do cliente no BD.\n" + ex.getMessage(),
                    "Adicionar Telefones do Cliente", JOptionPane.ERROR_MESSAGE);
            return false;
        }        
    }

    public static boolean alterarFoneCliente(Connection bd, String telefoneNovo, 
            String tipo, String clienteCpf, String telefoneAntigo) {
        if (bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        PreparedStatement stmt;

        try {
            String query = "UPDATE telefone_cliente SET telefone = ?,"
                    + " tipo = ? WHERE cliente_cpf = ? AND telefone = ?";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, telefoneNovo);
            stmt.setString(2, tipo);
            stmt.setString(3, clienteCpf);
            stmt.setString(4, telefoneAntigo);
            int result = stmt.executeUpdate();
            stmt.close();

            return result > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao alterar os telefones"
                    + " do cliente no BD.\n" + ex.getMessage(),
                    "Alterar Telefones do Cliente", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public static boolean removerFoneCliente(Connection bd, String telefone,
            String clienteCpf) {
        if (bd == null) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar-se ao banco de dados.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        PreparedStatement stmt;

        try {
            String query = "DELETE FROM telefone_cliente WHERE cliente_cpf = ? "
                    + "AND telefone = ?";
            stmt = bd.prepareStatement(query);
            stmt.setString(1, clienteCpf);
            stmt.setString(2, telefone);
            int result = stmt.executeUpdate();
            stmt.close();
            return result > 0;
        } catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao remover o telefone"
                    + " do cliente no BD.\n" + ex.getMessage(),
                    "Remover Telefone do Cliente", JOptionPane.ERROR_MESSAGE);
            return false;            
        }

    }
}
