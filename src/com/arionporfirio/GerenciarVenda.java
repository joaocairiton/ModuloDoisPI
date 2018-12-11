package com.arionporfirio;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

public class GerenciarVenda extends javax.swing.JDialog {
    public static final int OPERACAO_ENVIAR = 0;
    public static final int OPERACAO_DELETAR = 1;
    public static final int OPERACAO_CANCELAR = 2;
    
    private Connection bdConnection;
    private Venda vendaAberta;
    private FramePrincipal framePrincipal;
    
    private boolean registrando;
    private int operacao = OPERACAO_CANCELAR;
    
    private ArrayList<Produto> produtosVenda = new ArrayList();
    
    public GerenciarVenda(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        bdConnection = null;
        framePrincipal = (FramePrincipal) parent;
        
        vendaAberta = new Venda();
        registrando = true;
        
        MaskFormatter maskNota = null;
        MaskFormatter maskCpf = null;
        MaskFormatter maskData = null;
        
        try {
            maskNota = new MaskFormatter("####-####-####-####-####-####-####-"
                    + "####-####-####-####");
            maskNota.setPlaceholderCharacter('_');
            maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.setPlaceholderCharacter('_');
            maskData = new MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
        } catch(ParseException ex) {
            ex.printStackTrace();
        }
        maskNota.install(vendaNotaInput);
        maskCpf.install(vendaCpfInput);
        maskData.install(vendaDataInput);
        
        vendaDescontoIntInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String in = vendaDescontoIntInput.getText();
                if(!Character.isDigit(c) || in.length() > 6) e.consume();
            }
        });
        vendaDescontoFraInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                String in = vendaDescontoFraInput.getText();
                if(!Character.isDigit(c) || in.length() >= 2) e.consume();
            }
        });
        
        String[] pgFormas = {"Cartão de Crédito", "Cartão de Débito", 
            "Dinheiro", "Boleto"};
        DefaultComboBoxModel pgComboModel = new DefaultComboBoxModel(pgFormas);
        vendaPagamentoCombo.setModel(pgComboModel);
    }
    
    public void setConnection(Connection bdConnection) {
        this.bdConnection = bdConnection;
    }
    
    public void setVenda(Venda venda) {
        if(venda != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            
            vendaAberta = new Venda(venda.getNotaFiscal(), venda.getClienteCpf(),
                venda.getProdutos(), venda.getValorTotal(), venda.getDesconto(),
                venda.getPagamento(), venda.getDataDaVenda());
            
            vendaNotaInput.setText(venda.getNotaFiscal());
            vendaCpfInput.setText(venda.getClienteCpf());
            vendaPagamentoCombo.setSelectedItem(venda.getPagamento());
            
            vendaDataInput.setText(sdf.format(venda.getDataDaVenda()));
            String[] desconto = String.format("%.2f", venda.getDesconto()).
                    replace(".", ",").split(",");
            vendaDescontoIntInput.setText(desconto[0]);
            vendaDescontoFraInput.setText(desconto[1]);
            vendaTotalInput.setText(String.format("R$ %.2f", venda.getValorTotal()));
        }
    }
    
    public void setProdutosVenda(ArrayList<Produto> produtosVenda) {
        this.produtosVenda = produtosVenda;
    }
    
    public void registrandoVenda(boolean novaVenda) {
        registrando = novaVenda;
        DefaultTableModel ptm = (DefaultTableModel) vendaProdutoTabela.getModel();

        JTextField qtdInput = new JTextField();
        qtdInput.setBorder(null);

        qtdInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || c == '0') {
                    e.consume();
                }
            }
        });

        TableColumn qtdColumn = vendaProdutoTabela.getColumnModel().getColumn(3);
        qtdColumn.setCellEditor(new DefaultCellEditor(qtdInput));
            
        if(registrando) {
            if(!produtosVenda.isEmpty()){
                produtosVenda.forEach((produto) -> {
                    ptm.addRow(new Object[]{produto.getCodigo(), produto.getDescricao(),
                        produto.getPrecoFormatado(), 1, false});
                });
            }
            vendaDeletarBtn.setEnabled(false);
            vendaDataInput.setText(new SimpleDateFormat("ddMMyyyy").format(new Date()));
        }                    
        else {            
            for(Map.Entry produto: vendaAberta.getProdutos().entrySet()) {
                Produto p = framePrincipal.filtrarProdutos(
                        (String) produto.getKey()).get(0);
                ptm.addRow(new Object[]{produto.getKey(), p.getDescricao(), 
                    p.getPrecoFormatado(), produto.getValue(), false});               
            }
            
            vendaNotaInput.setEditable(false);
            vendaNotaInput.setFocusable(false);
            vendaCpfInput.setEditable(false);
            vendaCpfInput.setFocusable(false);
        }
    }
    
    public int getOperacao() {
        return operacao;
    }
    
    public Venda getVenda() {
        return vendaAberta;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        vendaPagamentoCombo = new javax.swing.JComboBox<>();
        vendaNotaInput = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        vendaCpfInput = new javax.swing.JFormattedTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        vendaProdutoTabela = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        vendaDescontoFraInput = new javax.swing.JTextField();
        vendaDescontoIntInput = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        vendaTotalInput = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        vendaRemoverProduto = new javax.swing.JButton();
        vendaDataInput = new javax.swing.JFormattedTextField();
        vendaEnviarBtn = new javax.swing.JButton();
        vendaCancelarBtn = new javax.swing.JButton();
        vendaDeletarBtn = new javax.swing.JButton();
        vendaHeaderLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Vendas");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados da Venda"));

        jLabel1.setText("Nota Fiscal: ");

        jLabel2.setText("Forma de Pagamento: ");

        jLabel3.setText("Desconto:");

        vendaPagamentoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("CPF do Cliente:");

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Produtos"));

        vendaProdutoTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Preço", "Qtd", "Remover"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane3.setViewportView(vendaProdutoTabela);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel6.setText(",");

        jLabel8.setText("R$");

        jLabel7.setText("Total da Compra: ");

        vendaTotalInput.setEditable(false);

        jLabel9.setText("Data da Venda:");

        vendaRemoverProduto.setText("Remover Produto da Lista");
        vendaRemoverProduto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendaRemoverProdutoMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(vendaTotalInput, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vendaRemoverProduto))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(jLabel1))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(vendaNotaInput)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(vendaCpfInput)
                                    .addComponent(vendaPagamentoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 91, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(vendaDescontoIntInput, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(vendaDescontoFraInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(vendaDataInput, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(vendaNotaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vendaCpfInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(vendaDataInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(vendaPagamentoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(vendaDescontoIntInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vendaDescontoFraInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(vendaTotalInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendaRemoverProduto))
                .addContainerGap())
        );

        vendaEnviarBtn.setText("Enviar");
        vendaEnviarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendaEnviarBtnMouseReleased(evt);
            }
        });

        vendaCancelarBtn.setText("Cancelar");
        vendaCancelarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendaCancelarBtnMouseReleased(evt);
            }
        });

        vendaDeletarBtn.setText("Deletar");
        vendaDeletarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendaDeletarBtnMouseReleased(evt);
            }
        });

        vendaHeaderLabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        vendaHeaderLabel.setText("Gerenciar Venda");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(vendaCancelarBtn)
                .addGap(18, 18, 18)
                .addComponent(vendaEnviarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(vendaHeaderLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vendaDeletarBtn))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vendaDeletarBtn)
                    .addComponent(vendaHeaderLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vendaEnviarBtn)
                    .addComponent(vendaCancelarBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void vendaCancelarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendaCancelarBtnMouseReleased
        operacao = OPERACAO_CANCELAR;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_vendaCancelarBtnMouseReleased

    private void vendaDeletarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendaDeletarBtnMouseReleased
        if(framePrincipal.filtrarVendas(vendaNotaInput.getText()) != null) {
            boolean vendaDeletada = VendaModel.deletarVenda(bdConnection, 
                    vendaAberta.getNotaFiscal());
            if(vendaDeletada) {
                setVisible(false);
                dispose();
            }
        }
    }//GEN-LAST:event_vendaDeletarBtnMouseReleased

    private void vendaEnviarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendaEnviarBtnMouseReleased
        String notaFiscal = vendaNotaInput.getText().replaceAll("[\\s-_]", "");
        String cpf = vendaCpfInput.getText().replaceAll("[\\s_.-]", "");
        String descontoInt = vendaDescontoIntInput.getText().isEmpty() ? "0":
                vendaDescontoIntInput.getText();
        String descontoFra = vendaDescontoFraInput.getText().isEmpty() ? "00":
                vendaDescontoFraInput.getText();
        double desconto = Double.parseDouble(String.format("%s.%s", descontoInt,
                descontoFra));
        String pagamento = (String) vendaPagamentoCombo.getSelectedItem();
        Date dataDaVenda;
        
        try {
            dataDaVenda = new SimpleDateFormat("dd/MM/yyyy").parse(
                    vendaDataInput.getText().replaceAll("[\\s_]", ""));
        } catch (ParseException ex) {
            dataDaVenda = null;
        }
        if(registrando) {
            try {
                vendaAberta.setNotaFiscal(notaFiscal);
                vendaAberta.setClienteCpf(cpf);
            } catch(IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Campo Inválido!", JOptionPane.ERROR_MESSAGE);
                return;                
            }
        }
        
        HashMap<String, Integer> produtos = new HashMap();
        double valorTotal = 0.00;
        
        for(int row = 0; row < vendaProdutoTabela.getRowCount(); row++){
            DefaultTableModel dtm = (DefaultTableModel) vendaProdutoTabela.getModel();
            String pc = (String) dtm.getValueAt(row, 0);
            double pPreco = Double.parseDouble(((String)dtm.getValueAt(
                    row, 2)).replaceAll("[\\sR$]", "").replace(",", "."));
            int pQtd = dtm.getValueAt(row, 3) != null ? 
                    (int) dtm.getValueAt(row, 3) : 1;
            valorTotal += pPreco * pQtd;
            produtos.put(pc, pQtd);
        }
        desconto = desconto > valorTotal ? valorTotal: desconto; 
        try {
            vendaAberta.setProdutos(produtos);
            vendaAberta.setValorTotal(valorTotal);
            vendaAberta.setDesconto(desconto);
            vendaAberta.setPagamento(pagamento);
            vendaAberta.setDataDaVenda(dataDaVenda);            
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Campo Inválido!", JOptionPane.ERROR_MESSAGE);
            return;                
        }        
        if(registrando) {
            if(framePrincipal.filtrarVendas(notaFiscal) != null) {
                JOptionPane.showMessageDialog(null, "A venda inserida já existe "
                        + "no banco de dados!",
                    "A venda já existe!", JOptionPane.ERROR_MESSAGE);
                vendaNotaInput.requestFocus();
                return;                
            }            
            
            boolean vendaCriada = VendaModel.criarVenda(bdConnection, vendaAberta);
            if(vendaCriada) {
                operacao = OPERACAO_ENVIAR;
                setVisible(false);
                dispose();
            }
        } else {
            if(framePrincipal.filtrarVendas(notaFiscal) == null) {
                JOptionPane.showMessageDialog(null, "A venda inserida não existe "
                        + "no banco de dados!",
                    "A venda não existe!", JOptionPane.ERROR_MESSAGE);
                vendaNotaInput.requestFocus();
                return;                
            }
            boolean vendaAlterada = VendaModel.alterarVenda(bdConnection, vendaAberta);
            if(vendaAlterada) {
                operacao = OPERACAO_ENVIAR;
                setVisible(false);
                dispose();
            }            
        }
    }//GEN-LAST:event_vendaEnviarBtnMouseReleased

    private void vendaRemoverProdutoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendaRemoverProdutoMouseReleased
        String codPro = "";        
        for(int row = 0; row < vendaProdutoTabela.getRowCount(); row++) {
            DefaultTableModel dtm = (DefaultTableModel) vendaProdutoTabela.getModel();
            if(dtm.getValueAt(row, 4) != null && dtm.getRowCount() > 1) {
                codPro = (String) dtm.getValueAt(row, 0);
                dtm.removeRow(row);
            }
        }
        if(!registrando) {
            boolean produtoVendaDeletado = VendaModel.deletarProdutoVenda(
                    bdConnection, codPro, vendaAberta.getNotaFiscal());
            if (produtoVendaDeletado) {
                System.out.println("Produto removido de venda");
            }
        }
    }//GEN-LAST:event_vendaRemoverProdutoMouseReleased

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
            java.util.logging.Logger.getLogger(GerenciarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarVenda.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GerenciarVenda dialog = new GerenciarVenda(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton vendaCancelarBtn;
    private javax.swing.JFormattedTextField vendaCpfInput;
    private javax.swing.JFormattedTextField vendaDataInput;
    private javax.swing.JButton vendaDeletarBtn;
    private javax.swing.JTextField vendaDescontoFraInput;
    private javax.swing.JTextField vendaDescontoIntInput;
    private javax.swing.JButton vendaEnviarBtn;
    private javax.swing.JLabel vendaHeaderLabel;
    private javax.swing.JFormattedTextField vendaNotaInput;
    private javax.swing.JComboBox<String> vendaPagamentoCombo;
    private javax.swing.JTable vendaProdutoTabela;
    private javax.swing.JButton vendaRemoverProduto;
    private javax.swing.JTextField vendaTotalInput;
    // End of variables declaration//GEN-END:variables
}
