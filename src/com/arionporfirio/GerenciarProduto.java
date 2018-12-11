package com.arionporfirio;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.text.ParseException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

public class GerenciarProduto extends javax.swing.JDialog {

    public static final int OPERACAO_ENVIAR = 0;
    public static final int OPERACAO_DELETAR = 1;
    public static final int OPERACAO_CANCELAR = 2;
    
    private Connection bdConnection;
    private Produto produtoAberto;
    private FramePrincipal framePrincipal;
    
    private boolean registrando;
    private int operacao = OPERACAO_CANCELAR;
    
    public GerenciarProduto(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        bdConnection = null;
        framePrincipal = (FramePrincipal) parent;
        
        produtoAberto = new Produto();
        registrando = true;
        
        MaskFormatter maskCodigo = null;
        //MaskFormatter maskPreco = null;
        
        try {
            maskCodigo = new MaskFormatter("# ###### ######");
            maskCodigo.setPlaceholderCharacter('_');
            //maskPreco = new MaskFormatter("##,##");
            
        } catch(ParseException ex) {
            ex.printStackTrace();
        }
        maskCodigo.install(produtoCodigoInput);
        
        produtoPrecoInput.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || !(produtoPrecoInput.getText().length() < 8)) {
                    e.consume();
                }
            }
        });
        produtoDecimalInput.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isDigit(c) || !(produtoDecimalInput.getText().length() < 2)) {
                    e.consume();
                }                
                
            }
        });
        
        String[] unidadesOpcoes = {"Unidade", "Metro",
            "Centímetro", "Kilo", "Grama", "Litro", "Mililitro"};
        DefaultComboBoxModel unidadeComboModel = new DefaultComboBoxModel(
            unidadesOpcoes);
        produtoUnidadeCombo.setModel(unidadeComboModel);
        
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        produtoDescricaoInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        produtoUnidadeCombo = new javax.swing.JComboBox<>();
        produtoCodigoInput = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        produtoPrecoInput = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        produtoDecimalInput = new javax.swing.JTextField();
        produtoEnviarBtn = new javax.swing.JButton();
        produtoCancelarBtn = new javax.swing.JButton();
        produtoDeletarBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Produtos");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Produto"));

        jLabel1.setText("Código do Produto: ");

        jLabel2.setText("Descrição: ");

        jLabel3.setText("Preço Unitário:");

        jLabel4.setText("Tipo de Unidade:");

        produtoUnidadeCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel5.setText("R$");

        jLabel6.setText(",");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(33, 33, 33))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(produtoDescricaoInput, javax.swing.GroupLayout.DEFAULT_SIZE, 312, Short.MAX_VALUE)
                    .addComponent(produtoUnidadeCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(produtoCodigoInput)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(produtoPrecoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(produtoDecimalInput, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produtoCodigoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produtoDescricaoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel5)
                            .addComponent(produtoPrecoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(produtoDecimalInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produtoUnidadeCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        produtoEnviarBtn.setText("Enviar");
        produtoEnviarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                produtoEnviarBtnMouseReleased(evt);
            }
        });

        produtoCancelarBtn.setText("Cancelar");
        produtoCancelarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                produtoCancelarBtnMouseReleased(evt);
            }
        });

        produtoDeletarBtn.setText("Deletar");
        produtoDeletarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                produtoDeletarBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(produtoCancelarBtn)
                .addGap(18, 18, 18)
                .addComponent(produtoEnviarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(produtoDeletarBtn)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(produtoDeletarBtn)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produtoEnviarBtn)
                    .addComponent(produtoCancelarBtn))
                .addGap(24, 24, 24))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void setConnection(Connection bdConnection) {
        this.bdConnection = bdConnection;
    }
    
    public void setProduto(Produto produto){
        if(produto != null){
            produtoAberto = new Produto(
                    produto.getCodigo(), produto.getDescricao(),
                    produto.getPreco(), produto.getUnidade());            
            
            produtoCodigoInput.setText(produto.getCodigo());
            produtoDescricaoInput.setText(produto.getDescricao());
            
            String[] preco = String.format("%.2f", produto.getPreco()).replace(".", ",").split(",");
            produtoPrecoInput.setText(preco[0]);
            produtoDecimalInput.setText(preco[1]);
            produtoUnidadeCombo.setSelectedItem(produto.getUnidade());
        }
    }
    
    public void registrandoProduto(boolean novoProduto) {
        registrando = novoProduto;
        if(registrando) produtoDeletarBtn.setEnabled(false);
        else{
            produtoCodigoInput.setEditable(false);
            produtoCodigoInput.setFocusable(false);
        }
    }
    
    public int getOperacao() {
        return operacao;
    }
    
    public Produto getProduto() {
        return produtoAberto;
    }
    
    private void produtoDeletarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoDeletarBtnMouseReleased
        if(framePrincipal.filtrarProdutos(produtoAberto.getCodigo()) != null) {
            boolean produtoDeletado = ProdutoModel.deletarProduto(bdConnection,
                    produtoAberto.getCodigo());
            if(produtoDeletado){
                operacao = OPERACAO_DELETAR;
                setVisible(false);
                dispose();                
            }
        }
    }//GEN-LAST:event_produtoDeletarBtnMouseReleased

    private void produtoCancelarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoCancelarBtnMouseReleased
        operacao = OPERACAO_CANCELAR;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_produtoCancelarBtnMouseReleased

    private void produtoEnviarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoEnviarBtnMouseReleased
        String codigo = produtoCodigoInput.getText().replaceAll("[\\s_]", "");
        String descricao = produtoDescricaoInput.getText();
        String precoInt = produtoPrecoInput.getText().isEmpty() ? "0" : 
                produtoPrecoInput.getText();
        String precoFra = produtoDecimalInput.getText().isEmpty() ? "00" :
                produtoDecimalInput.getText();        
        double preco = Double.parseDouble(precoInt + "." + precoFra);
        String unidade = (String) produtoUnidadeCombo.getSelectedItem();

        if (registrando) {
            try {
                produtoAberto.setCodigo(codigo);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Campo Inválido!", JOptionPane.ERROR_MESSAGE);
                return;                
            }
        }
        
        try {
            produtoAberto.setDescricao(descricao);
            produtoAberto.setPreco(preco);
            produtoAberto.setUnidade(unidade);
        } catch(IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Campo Inválido!", JOptionPane.ERROR_MESSAGE);
            return;            
        }
        
        if(registrando){
            if(framePrincipal.filtrarProdutos(produtoAberto.getCodigo()) != null) {
                JOptionPane.showMessageDialog(null, "O produto inserido já existe!",
                    "O Produto já existe!", JOptionPane.ERROR_MESSAGE);
                produtoCodigoInput.requestFocus();
                return;
            }
            int res = ProdutoModel.criarProduto(bdConnection, produtoAberto);
            if(res > 0){
                operacao = OPERACAO_ENVIAR;
                setVisible(false);
                dispose();
            }
        } else {
            if(framePrincipal.filtrarProdutos(produtoAberto.getCodigo()) == null) {
                JOptionPane.showMessageDialog(null, "O produto inserido não existe!",
                    "O Produto não existe!", JOptionPane.ERROR_MESSAGE);
                produtoCodigoInput.requestFocus();
                return;
            }
            boolean produtoAlterado = ProdutoModel.alterarProduto(
                    bdConnection, produtoAberto);
            if(produtoAlterado){
                operacao = OPERACAO_ENVIAR;
                setVisible(false);
                dispose();
            }
        }
    }//GEN-LAST:event_produtoEnviarBtnMouseReleased

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
            java.util.logging.Logger.getLogger(GerenciarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarProduto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GerenciarProduto dialog = new GerenciarProduto(new javax.swing.JFrame(), true);
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
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton produtoCancelarBtn;
    private javax.swing.JFormattedTextField produtoCodigoInput;
    private javax.swing.JTextField produtoDecimalInput;
    private javax.swing.JButton produtoDeletarBtn;
    private javax.swing.JTextField produtoDescricaoInput;
    private javax.swing.JButton produtoEnviarBtn;
    private javax.swing.JTextField produtoPrecoInput;
    private javax.swing.JComboBox<String> produtoUnidadeCombo;
    // End of variables declaration//GEN-END:variables
}
