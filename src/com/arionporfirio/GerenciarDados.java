package com.arionporfirio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

public class GerenciarDados extends javax.swing.JDialog {
    
    public static final int ACAO_IMPORTAR = 1;
    public static final int ACAO_EXPORTAR = 2;
    
    private String caminho;
    private File arquivo;
    private String tabela;
    private String[] tabelas;
    private int acao;
    Connection bd;
    
    public GerenciarDados(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        tabelas = new String[]{"Cliente", "Produto", "Venda", 
            "Telefone Cliente", "Produto Venda"};
        DefaultComboBoxModel dcm = new DefaultComboBoxModel(tabelas);
        tabelaCombo.setModel(dcm);
    }
    
    public void setConnection(Connection bd) {
        this.bd = bd;
    }
    
    public void setAcao(int acao) {
        this.acao = acao;
        if(acao == ACAO_IMPORTAR)acaoLabel.setText("Importar Dados");
        else if(acao == ACAO_EXPORTAR)acaoLabel.setText("Exportar Dados");
    }
    
    private void insertClientes(Connection bd) {        
        try {
            arquivo = new File(caminho);
            try {
                if (!arquivo.exists()) {
                    throw new IllegalArgumentException("O caminho do arquivo é "
                            + "inválido!\nCaminho: " + caminho);
                }
            } catch(IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null,
                        "Erro ao inserir dados em Cliente\n" + ex.getMessage(),
                        "Inserir Dados em cliente", JOptionPane.ERROR_MESSAGE);                 
            }
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            String[] valores;
            String query;
            PreparedStatement stmt;
            while(br.ready()) {
                linha = br.readLine();
                if(linha.isEmpty()) continue;
                valores = linha.split(",");
                if(valores.length != 8) continue;
                try {
                    query = "INSERT INTO cliente (cpf, nome, endereco) VALUES (?, ?, ?)";
                    stmt = bd.prepareStatement(query);
                    stmt.setString(1, valores[0]);
                    stmt.setString(2, valores[1]);
                    stmt.setString(3, String.join(",", valores[2],
                            valores[3], valores[4], valores[5], valores[6], valores[7]));
                    stmt.executeUpdate();
                    stmt.close();
                } catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                        "Erro ao inserir dados em Cliente\n" + ex.getMessage(),
                        "Inserir Dados em cliente", JOptionPane.ERROR_MESSAGE);                   
                }
            }
            fr.close();
            br.close();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao inserir dados em Cliente\n" + ex.getMessage(),
                    "Inserir Dados em cliente", JOptionPane.ERROR_MESSAGE);            
        }
    }
    
    private void insertProdutos(Connection bd) {
        try {
            arquivo = new File(caminho);
            if (!arquivo.exists()) {
                throw new IllegalArgumentException("O caminho do arquivo é "
                        + "inválido!\nCaminho: " + caminho);
            }
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            String[] valores;
            String query;
            PreparedStatement stmt;
            while(br.ready()) {
                linha = br.readLine();
                if(linha.isEmpty()) continue;
                valores = linha.split(",");
                if(valores.length != 4) continue;
                try {
                    query = "INSERT INTO produto(codigo,"
                            + " descricao, preco, unidade) VALUES(?, ?, ?, ?)";
                    stmt = bd.prepareStatement(query);
                    stmt.setString(1, valores[0]);
                    stmt.setString(2, valores[1]);
                    stmt.setDouble(3, Double.parseDouble(valores[2]));
                    stmt.setString(4, valores[3]);
                    stmt.executeUpdate();
                    stmt.close();
                } catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                        "Erro ao inserir dados em Produto\n" + ex.getMessage(),
                        "Inserir Dados em Produto", JOptionPane.ERROR_MESSAGE);                        
                }
            }
            fr.close();
            br.close();            
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao inserir dados em Produto\n" + ex.getMessage(),
                    "Inserir Dados em produto", JOptionPane.ERROR_MESSAGE);              
        }        
    }
    
    private void insertVendas(Connection bd) {
        try {
            arquivo = new File(caminho);
            if (!arquivo.exists()) {
                throw new IllegalArgumentException("O caminho do arquivo é inválido!");
            }
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            String[] valores;
            String query;
            PreparedStatement stmt;
            while(br.ready()) {
                linha = br.readLine();
                if(linha.isEmpty()) continue;
                valores = linha.split(",");
                if(valores.length != 6) continue;
                Date dataDaVenda = new SimpleDateFormat("dd/MM/yyyy").parse(valores[4]);
                try {
                query = "INSERT INTO venda (nota_fiscal, cliente_cpf,"
                        + " valor_total, desconto, pagamento, data_da_venda ) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                stmt = bd.prepareStatement(query);
                stmt.setString(1, valores[0]);
                stmt.setString(2, valores[1]);
                stmt.setDouble(3, Double.parseDouble(valores[2]));
                stmt.setDouble(4, Double.parseDouble(valores[3]));
                stmt.setDate(5, new java.sql.Date(dataDaVenda.getTime()));
                stmt.executeUpdate();
                stmt.close();
                } catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao inserir dados em Venda\n" + ex.getMessage(),
                            "Inserir Dados em venda", JOptionPane.ERROR_MESSAGE);                     
                }
            }
            fr.close();
            br.close();            
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao inserir dados em Venda\n" + ex.getMessage(),
                    "Inserir Dados em venda", JOptionPane.ERROR_MESSAGE);              
        }        
    }
    
    private void insertTelefonesCliente(Connection bd) {
        try {
            arquivo = new File(caminho);
            if (!arquivo.exists()) {
                throw new IllegalArgumentException("O caminho do arquivo é inválido!");
            }
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            String[] valores;
            String query;
            PreparedStatement stmt;
            while(br.ready()) {
                linha = br.readLine();
                if(linha.isEmpty()) continue;
                valores = linha.split(",");
                if(valores.length != 3) continue;
                try {
                    query = "INSERT INTO telefone_cliente (cliente_cpf, "
                            + "telefone, tipo) VALUES (?, ?, ?)";
                    stmt = bd.prepareStatement(query);
                    stmt.setString(1, valores[0]);
                    stmt.setString(2, valores[1]);
                    stmt.setString(3, valores[2]);
                    stmt.executeUpdate();
                    stmt.close();
                } catch(SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao inserir dados em Telefones do Cliente\n" + ex.getMessage(),
                            "Inserir Dados em telefones do cliente", JOptionPane.ERROR_MESSAGE);                     
                }
            }
            fr.close();
            br.close();            
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao inserir dados em Telefones Cliente\n" + ex.getMessage(),
                    "Inserir Dados em telefones cliente", JOptionPane.ERROR_MESSAGE);              
        }          
    }
    
    private void insertProdutosVenda(Connection bd) {
        try {
            arquivo = new File(caminho);
            if (!arquivo.exists()) {
                throw new IllegalArgumentException("O caminho do arquivo é inválido!");
            }
            FileReader fr = new FileReader(arquivo);
            BufferedReader br = new BufferedReader(fr);
            String linha;
            String[] valores;
            String query;
            PreparedStatement stmt;
            while(br.ready()) {
                linha = br.readLine();
                if(linha.isEmpty()) continue;
                valores = linha.split(",");
                if(valores.length != 3) continue;
                try {
                    query = "INSERT INTO produto_venda (cod_produto, "
                            + "nota_fiscal, quantidade) VALUES (?, ?, ?)";
                    stmt = bd.prepareStatement(query);
                    stmt.setString(1, valores[0]);
                    stmt.setString(2, valores[1]);
                    stmt.setInt(3, Integer.parseInt(valores[2]));
                    stmt.executeUpdate();
                    stmt.close();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Erro ao inserir dados em Produtos de venda\n" + ex.getMessage(),
                            "Inserir Dados em Produtos de venda", JOptionPane.ERROR_MESSAGE);
                }
            }
            fr.close();
            br.close();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao inserir dados em Produtos Venda\n" + ex.getMessage(),
                    "Inserir Dados em produtos venda", JOptionPane.ERROR_MESSAGE);              
        }        
    }    
    
    private void selectClientes(Connection bd) {
        File arquivo = new File(caminho);

        try {            
            arquivo.createNewFile();
            
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            String linha;
            String query = "SELECT * FROM cliente ORDER BY nome";
            PreparedStatement stmt = bd.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Cliente> clientes = new ArrayList();

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCPF(rs.getString("CPF"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEndereco(rs.getString("endereco"));

                clientes.add(cliente);
            }
            rs.close();
            stmt.close();
            for(Cliente c: clientes) {
                linha = String.format("%s,%s,%s", c.getCPF(), c.getNome(), 
                        c.getEndereco());
                bw.write(linha);
                bw.newLine();
            }
            fw.close();
            bw.close();
        } catch(Exception ex) {
            
        }
    }
    
    private void selectProdutos(Connection bd) {
        File arquivo = new File(caminho);

        try {            
            arquivo.createNewFile();
            
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            String query = "SELECT * FROM produto ORDER BY codigo";
            PreparedStatement stmt = bd.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            
            ArrayList<Produto> produtos = new ArrayList();
            Produto produto;
            
            while(rs.next()) {
                produto = new Produto();
                produto.setCodigo(rs.getString("codigo"));
                produto.setDescricao(rs.getString("descricao"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setUnidade(rs.getString("unidade"));
                produtos.add(produto);
                
            }            
            rs.close();
            stmt.close();
            String linha;
            for(Produto p: produtos) {
                linha = String.format("%s,%s,%.2f,%s", p.getCodigo(), 
                        p.getDescricao(), p.getPreco(), p.getUnidade());
                bw.write(linha);
                bw.newLine();
            }
            fw.close();
            bw.close();
        } catch(Exception ex) {
            
        }        
    }
    
    private void selectVendas(Connection bd) {
        File arquivo = new File(caminho);

        try {            
            arquivo.createNewFile();
            
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            String linha;
            String query = "SELECT * FROM venda ORDER BY nota_fiscal";
            PreparedStatement stmt = bd.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            ArrayList<Venda> vendas = new ArrayList();
            Venda venda;
            while (rs.next()) {
                venda = new Venda();
                venda.setNotaFiscal(rs.getString("nota_fiscal"));
                venda.setClienteCpf(rs.getString("cliente_cpf"));
                venda.setValorTotal(rs.getDouble("valor_total"));
                venda.setDesconto(rs.getDouble("desconto"));
                venda.setPagamento(rs.getString("pagamento"));
                venda.setDataDaVenda(new Date(rs.getDate("data_da_venda").getTime()));
                vendas.add(venda);
            }
            rs.close();
            stmt.close();
            for(Venda v: vendas) {
                linha = String.format("%s,%s,%s,%s,%s,%s", v.getNotaFiscal(),
                v.getClienteCpf(), v.getValorTotal(), v.getDesconto(), 
                v.getPagamento(), new SimpleDateFormat("dd/MM/yyyy").format(v.getDataDaVenda()));
                bw.write(linha);
                bw.newLine();
            }
            fw.close();
            bw.close();
        } catch(Exception ex) {
            
        }        
    }
    
    private void selectTelefonesCliente(Connection bd) {
        File arquivo = new File(caminho);

        try {            
            arquivo.createNewFile();
            
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            String linha;
            String query = "SELECT * FROM telefone_cliente ORDER BY id";
            PreparedStatement stmt = bd.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                linha = String.format("%s,%s,%s", rs.getString("cliente_cpf"),
                        rs.getString("telefone"), rs.getString("tipo"));
                bw.write(linha);
                bw.newLine();
            }
            rs.close();
            stmt.close();
            fw.close();
            bw.close();
        } catch(Exception ex) {
            
        }        
    }
    
    private void selectProdutosVenda(Connection bd) {
        File arquivo = new File(caminho);

        try {            
            arquivo.createNewFile();
            
            FileWriter fw = new FileWriter(arquivo, false);
            BufferedWriter bw = new BufferedWriter(fw);
            
            String linha;
            String query = "SELECT * FROM produto_venda ORDER BY id";
            PreparedStatement stmt = bd.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                linha = String.format("%s,%s,%d", rs.getString("cod_produto"),
                        rs.getString("nota_fiscal"), rs.getInt("quantidade"));
                bw.write(linha);
                bw.newLine();
            }
            rs.close();
            stmt.close();
            fw.close();
            bw.close();
        } catch(Exception ex) {
            
        }    
    }        

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        acaoLabel = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        tabelaCombo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        caminhoArquivoInput = new javax.swing.JTextField();
        confirmarBtn = new javax.swing.JButton();
        cancelarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setText("Gerenciar Dados");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações"));

        acaoLabel.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        acaoLabel.setText("Ação");

        jLabel3.setText("Em Tabela:");

        tabelaCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setText("Caminho do Arquivo:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(caminhoArquivoInput))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(acaoLabel)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(86, 86, 86)
                        .addComponent(tabelaCombo, 0, 203, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(acaoLabel)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(tabelaCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(caminhoArquivoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        confirmarBtn.setText("Confirmar");
        confirmarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                confirmarBtnMouseReleased(evt);
            }
        });

        cancelarBtn.setText("Cancelar");
        cancelarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                cancelarBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelarBtn)
                .addGap(35, 35, 35)
                .addComponent(confirmarBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirmarBtn)
                    .addComponent(cancelarBtn))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelarBtnMouseReleased
        setVisible(false);
        dispose();
    }//GEN-LAST:event_cancelarBtnMouseReleased

    private void confirmarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_confirmarBtnMouseReleased
        String caminhoInput = caminhoArquivoInput.getText();
        String t = (String) tabelaCombo.getSelectedItem();
        System.out.println("Tabela: " + t);
        if(caminhoInput.isEmpty())return;
        caminho = caminhoInput;
        if(acao == ACAO_IMPORTAR) {
            if(t.equals(tabelas[0]))insertClientes(bd);
            if(t.equals(tabelas[1]))insertProdutos(bd);
            if(t.equals(tabelas[2]))insertVendas(bd);
            if(t.equals(tabelas[3]))insertTelefonesCliente(bd);
            if(t.equals(tabelas[4]))insertProdutosVenda(bd);            
        } else if(acao == ACAO_EXPORTAR) {
            if(t.equals(tabelas[0]))selectClientes(bd);
            if(t.equals(tabelas[1]))selectProdutos(bd);
            if(t.equals(tabelas[2]))selectVendas(bd);
            if(t.equals(tabelas[3]))selectTelefonesCliente(bd);
            if(t.equals(tabelas[4]))selectProdutosVenda(bd);
        }
        setVisible(false);
        dispose();
    }//GEN-LAST:event_confirmarBtnMouseReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GerenciarDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarDados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GerenciarDados dialog = new GerenciarDados(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel acaoLabel;
    private javax.swing.JTextField caminhoArquivoInput;
    private javax.swing.JButton cancelarBtn;
    private javax.swing.JButton confirmarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JComboBox<String> tabelaCombo;
    // End of variables declaration//GEN-END:variables
}
