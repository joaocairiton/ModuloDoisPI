package com.arionporfirio;

import java.sql.Connection;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
//import java.util.Arrays;
import javax.swing.JOptionPane;
//import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;

public class GerenciarCliente extends javax.swing.JDialog {
    
    public static final int OPERACAO_ENVIAR = 0;
    public static final int OPERACAO_DELETAR = 1;
    public static final int OPERACAO_CANCELAR = 2;    
    
    private Connection bdConnection;
    private Cliente clienteAberto;
    private boolean registrando;
    private final FramePrincipal framePrincipal;
    
    private int operacao = OPERACAO_CANCELAR;
    
    private HashMap<String, String> fonesTmp = new HashMap();

    public GerenciarCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        MaskFormatter maskCpf = null;
        MaskFormatter maskFone = null;
        MaskFormatter maskUF = null;
        MaskFormatter maskCEP = null;
        try {
            maskCpf = new MaskFormatter("###.###.###-##");
            maskCpf.setPlaceholderCharacter('_');
            maskFone = new MaskFormatter("(##)#####-####");
            maskFone.setPlaceholderCharacter('_');
            maskUF = new MaskFormatter("UU");
            maskUF.setPlaceholderCharacter('_');
            maskCEP = new MaskFormatter("#####-###");
            maskCEP.setPlaceholderCharacter('_');

        } catch (ParseException e) {
            e.printStackTrace();
        }
        //clienteCpfInput.setFormatterFactory(new DefaultFormatterFactory(maskCpf));
        maskCpf.install(clienteCpfInput);
        maskFone.install(clienteFoneInput);
        maskUF.install(clienteUFInput);
        maskCEP.install(clienteCEPInput);
        
        bdConnection = null;
        clienteAberto = new Cliente();
        registrando = true;
        framePrincipal = (FramePrincipal) parent; 
        
        String[] foneTipos = {"Celular", "Celular Residencial", "Celular Comercial"};
        DefaultComboBoxModel dcm = new DefaultComboBoxModel(foneTipos);
        foneTipoCombo.setModel(dcm);
    }
    
    public void setConnection(Connection bdConnection){
        this.bdConnection = bdConnection;
    }
    
    public int getOperacao() {
        return operacao;
    }
    
    public Cliente getCliente(){
        return clienteAberto;
    }
    
    public void setCliente(Cliente cliente){
        if (cliente != null) {
            clienteAberto = new Cliente(cliente.getCPF(), cliente.getNome(),
                    cliente.getTelefones(), cliente.getEndereco());
            clienteCpfInput.setText(cliente.getCPF());
            clienteNomeInput.setText(cliente.getNome());
            
            ArrayList<String> fones = new ArrayList();
            for(Map.Entry fone: cliente.getTelefones().entrySet()) {
                fones.add(String.format("%s: %s", (String) fone.getValue(),
                        Cliente.formatarFone((String) fone.getKey())));
            }
            fonesLista.setListData(fones.toArray(new String[fones.size()]));
            //fonesLista.setListData(cliente.getTelefones().keySet().toArray(
            //    new String[clienteAberto.getTelefones().size()]));
            //clienteFoneInput.setText(cliente.getTelefones());
            
            String[] endereco = cliente.getEndereco().split(",");
            //System.out.println(Arrays.toString(endereco));
           
            clienteRuaInput.setText(endereco[0]);
            clienteComplementoInput.setText(endereco[1]);
            clienteSetorInput.setText(endereco[2]);
            clienteCidadeInput.setText(endereco[3]);
            clienteUFInput.setText(endereco[4]);
            clienteCEPInput.setText(endereco[5]);            
        }
    }
    
    public void registrandoCliente(boolean novoCliente) {
        registrando = novoCliente;
        
        if (novoCliente) {
            clienteDeletarBtn.setEnabled(false);
            
        } else {
            //clienteCpfInput.setText(clienteAberto.getCPF());
            //clienteNomeInput.setText(clienteAberto.getNome());
            //clienteFoneInput.setText(clienteAberto.getTelefone());
            
            clienteCpfInput.setEditable(false);
            clienteCpfInput.setFocusable(false);
            clienteNomeInput.setEditable(false);
            clienteNomeInput.setFocusable(false);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        clienteNomeInput = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        clienteCpfInput = new javax.swing.JFormattedTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        clienteComplementoInput = new javax.swing.JTextField();
        clienteRuaInput = new javax.swing.JTextField();
        clienteSetorInput = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        clienteCidadeInput = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        clienteUFInput = new javax.swing.JFormattedTextField();
        clienteCEPInput = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        clienteFoneInput = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        foneTipoCombo = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        fonesLista = new javax.swing.JList<>();
        jLabel11 = new javax.swing.JLabel();
        addFoneBtn = new javax.swing.JButton();
        removerFoneBtn = new javax.swing.JButton();
        alterarFoneBtn = new javax.swing.JButton();
        limparNumBtn = new javax.swing.JButton();
        clienteEnviarBtn = new javax.swing.JButton();
        clienteCancelarBtn = new javax.swing.JButton();
        clienteDeletarBtn = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Controle de Cliente");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados do Cliente"));

        jLabel1.setText("Nome Completo: ");

        clienteNomeInput.setToolTipText("Digite o nome completo do cliente ...");

        jLabel2.setText("CPF: ");

        clienteCpfInput.setToolTipText("Digite o CPF do cliente ...");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Endereço"));

        jLabel5.setText("Rua: ");

        jLabel6.setText("Complemento:");

        jLabel7.setText("Setor:");

        clienteComplementoInput.setToolTipText("Digite o complemento ...");

        clienteRuaInput.setToolTipText("Digite a rua ...");

        clienteSetorInput.setToolTipText("Digite o setor ...");

        jLabel8.setText("Cidade:");

        clienteCidadeInput.setToolTipText("Digite a cidade...");

        jLabel9.setText("UF:");

        clienteUFInput.setToolTipText("Digite a UF...");

        clienteCEPInput.setToolTipText("Digite o CEP...");

        jLabel10.setText("CEP:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8)
                            .addComponent(jLabel6)
                            .addComponent(jLabel5))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clienteRuaInput)
                            .addComponent(clienteComplementoInput)
                            .addComponent(clienteCidadeInput)
                            .addComponent(clienteSetorInput)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel9))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clienteUFInput, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(clienteCEPInput, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(clienteRuaInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(clienteComplementoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(clienteSetorInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(clienteCidadeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(clienteUFInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(clienteCEPInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Telefones"));

        jLabel3.setText("Número:");

        clienteFoneInput.setToolTipText("Digite o telefone do cliente ...");

        jLabel4.setText("Tipo:");

        foneTipoCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        fonesLista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fonesListaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(fonesLista);

        jLabel11.setText("Números Adicionados:");

        addFoneBtn.setText("Adicionar");
        addFoneBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                addFoneBtnMouseReleased(evt);
            }
        });

        removerFoneBtn.setText("Remover");
        removerFoneBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                removerFoneBtnMouseReleased(evt);
            }
        });

        alterarFoneBtn.setText("Alterar");
        alterarFoneBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                alterarFoneBtnMouseReleased(evt);
            }
        });

        limparNumBtn.setText("Limpar");
        limparNumBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                limparNumBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(removerFoneBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(alterarFoneBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addFoneBtn))
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel11)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(clienteFoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(limparNumBtn)
                                    .addGap(0, 0, Short.MAX_VALUE))
                                .addComponent(foneTipoCombo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(clienteFoneInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limparNumBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(foneTipoCombo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addFoneBtn)
                    .addComponent(removerFoneBtn)
                    .addComponent(alterarFoneBtn))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clienteNomeInput, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                            .addComponent(clienteCpfInput))
                        .addGap(12, 12, 12))
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(clienteNomeInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(clienteCpfInput, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        clienteEnviarBtn.setText("Enviar");
        clienteEnviarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clienteEnviarBtnMouseReleased(evt);
            }
        });

        clienteCancelarBtn.setText("Cancelar");
        clienteCancelarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clienteCancelarBtnMouseReleased(evt);
            }
        });

        clienteDeletarBtn.setText("Deletar");
        clienteDeletarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clienteDeletarBtnMouseReleased(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel12.setText("Gerenciar Cliente");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clienteDeletarBtn)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(clienteCancelarBtn)
                .addGap(18, 18, 18)
                .addComponent(clienteEnviarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteDeletarBtn)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteEnviarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clienteCancelarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clienteCancelarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteCancelarBtnMouseReleased
        if(operacao != OPERACAO_ENVIAR)
            operacao = OPERACAO_CANCELAR;
        setVisible(false);
        dispose();
    }//GEN-LAST:event_clienteCancelarBtnMouseReleased

    private void clienteDeletarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteDeletarBtnMouseReleased
        if (framePrincipal.filtrarClientes(clienteAberto.getCPF()) != null) {
            boolean clienteDeletado = ClienteModel.deletarCliente(
                    bdConnection, clienteAberto.getCPF());
            
            if(clienteDeletado){ 
                operacao = OPERACAO_DELETAR ;
                setVisible(false);
                dispose();
            }
        }
    }//GEN-LAST:event_clienteDeletarBtnMouseReleased

    private void clienteEnviarBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteEnviarBtnMouseReleased
        String cpf = clienteCpfInput.getText().replaceAll("[\\s_.-]", "");
        String nome = clienteNomeInput.getText();
        //String telefone = clienteFoneInput.getText().replaceAll("[\\s)(_-]", "");
        
        String[] ePartes = {
            clienteRuaInput.getText(),
            clienteComplementoInput.getText(),
            clienteSetorInput.getText(),
            clienteCidadeInput.getText(),
            clienteUFInput.getText().replaceAll("[\\s_]", ""),
            clienteCEPInput.getText().replaceAll("[\\s_-]", "")            
        };
        
        String endereco = String.join(",",
            ePartes[0].length() > 0? ePartes[0]: " ",
            ePartes[1].length() > 0? ePartes[1]: " ",
            ePartes[2].length() > 0? ePartes[2]: " ",
            ePartes[3].length() > 0? ePartes[3]: " ",
            ePartes[4].length() > 0? ePartes[4]: " ",
            ePartes[5].length() > 0? ePartes[5]: " "            
        );
         
        if (registrando) {
            try {
                clienteAberto.setNome(nome);
                clienteAberto.setCPF(cpf);
            } catch(IllegalArgumentException ex) {
                if(ex.getMessage().contains("campo nome"))clienteNomeInput.requestFocus();
                else if(ex.getMessage().contains("campo CPF"))clienteCpfInput.requestFocus();
                
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                    "Campo Inválido!", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        
        try {
            //clienteAberto.setTelefones(telefone);
            clienteAberto.setEndereco(endereco);
        } catch(IllegalArgumentException ex) {
            if(ex.getMessage().contains("campo telefone"))clienteFoneInput.requestFocus();
            else if(ex.getMessage().contains("campo rua"))clienteRuaInput.requestFocus();
            else if(ex.getMessage().contains("campo complemento"))clienteComplementoInput.requestFocus();
            else if(ex.getMessage().contains("campo setor"))clienteSetorInput.requestFocus();
            else if(ex.getMessage().contains("campo cidade"))clienteCidadeInput.requestFocus();
            else if(ex.getMessage().contains("campo UF"))clienteUFInput.requestFocus();
            else if(ex.getMessage().contains("campo CEP"))clienteCEPInput.requestFocus();            
            
            JOptionPane.showMessageDialog(null, ex.getMessage(),
                "Campo Inválido!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(registrando) {
            clienteAberto.setTelefones(fonesTmp);
        } else {
            for(Map.Entry fone: fonesTmp.entrySet()) {
                clienteAberto.getTelefones().put((String) fone.getKey(),
                        (String) fone.getValue());
            }
        }
        if(clienteAberto.getTelefones().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Não é possível prosseguir "
                    + "sem nenhum telefone!",
                "Nenhum telefone!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if(registrando) {
            if (framePrincipal.filtrarClientes(clienteAberto.getCPF()) != null) {
                JOptionPane.showMessageDialog(null, "O cliente inserido já existe!",
                    "O Cliente já existe!", JOptionPane.ERROR_MESSAGE);
                clienteCpfInput.requestFocus();
                return;
            }
            
            boolean clienteCriado = ClienteModel.criarCliente(bdConnection, clienteAberto);
            if(clienteCriado) {
                operacao = OPERACAO_ENVIAR;
                setVisible(false);
                dispose();
            }
        } else {
            if (framePrincipal.filtrarClientes(clienteAberto.getCPF()) == null) {
                JOptionPane.showMessageDialog(null, "O cliente inserido não existe!",
                    "O Cliente não existe!", JOptionPane.ERROR_MESSAGE);
                clienteCpfInput.requestFocus();
                return;                
            }
            boolean clienteAlterado = ClienteModel.alterarCliente(bdConnection, clienteAberto);
            if (clienteAlterado) {
                System.out.println("Cliente alterado!");
                if (!fonesTmp.isEmpty()) {
                    ClienteModel.adicionarFonesCliente(bdConnection, clienteAberto.getCPF(), fonesTmp);
                }                 
                operacao = OPERACAO_ENVIAR;
                setVisible(false);
                dispose();
            }
        }
    }//GEN-LAST:event_clienteEnviarBtnMouseReleased

    private void addFoneBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addFoneBtnMouseReleased
        String fone = clienteFoneInput.getText().replaceAll("[\\s_)(-]", "");
        if(fone.length() != 11) {
            JOptionPane.showMessageDialog(null, "Telefone inválido",
                "Campo Inválido!", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String tipo = (String) foneTipoCombo.getSelectedItem();
        fonesTmp.put(fone, tipo);
        ArrayList<String> fones = new ArrayList();
        
        for(Map.Entry f: clienteAberto.getTelefones().entrySet()) {
            fones.add(String.format("%s: %s", (String) f.getValue(),
                Cliente.formatarFone((String) f.getKey())));        
        }
        for(Map.Entry f: fonesTmp.entrySet()) {
            fones.add(String.format("%s: %s", (String) f.getValue(),
                Cliente.formatarFone((String) f.getKey())));
        }        

        fonesLista.setListData(fones.toArray(new String[fones.size()]));
        clienteFoneInput.setText("");
    }//GEN-LAST:event_addFoneBtnMouseReleased

    private void removerFoneBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_removerFoneBtnMouseReleased
        if(!((fonesTmp.size() + clienteAberto.getTelefones().size()) > 1) || 
                fonesLista.getSelectedValue() == null) return;
        String fone = fonesLista.getSelectedValue();
        if(!registrando && clienteAberto.getTelefones().containsKey(fone)) {
            boolean r = ClienteModel.removerFoneCliente(bdConnection, fone, 
                    clienteAberto.getCPF());
            if(r)clienteAberto.getTelefones().remove(fone);
            
        }
        if(clienteAberto.getTelefones().containsKey(fone))
            clienteAberto.getTelefones().remove(fone);
        if(fonesTmp.containsKey(fone))
            fonesTmp.remove(fone);
        ArrayList<String> fones = new ArrayList();
        
        for(Map.Entry f: clienteAberto.getTelefones().entrySet()) {            
            fones.add(String.format("%s: %s", (String) f.getValue(),
                        Cliente.formatarFone((String) f.getKey())));
        }
        for(Map.Entry f: fonesTmp.entrySet()) {
            fones.add(String.format("%s: %s", (String) f.getValue(),
                        Cliente.formatarFone((String) f.getKey())));
        }        

        fonesLista.setListData(fones.toArray(new String[fones.size()]));
    }//GEN-LAST:event_removerFoneBtnMouseReleased

    private void alterarFoneBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_alterarFoneBtnMouseReleased
        String foneNovo = clienteFoneInput.getText().replaceAll("[\\s()_-]", "");
        if(fonesLista.getSelectedValue() == null || foneNovo.length() != 11) return;
        String foneAntigo = fonesLista.getSelectedValue().split(":")[1].replaceAll("[\\s()_-]", "");
        String tipoNovo = (String) foneTipoCombo.getSelectedItem();

        if(fonesTmp.containsKey(foneAntigo)){
            fonesTmp.remove(foneAntigo);
            fonesTmp.put(foneNovo, tipoNovo);
        }
        if(clienteAberto.getTelefones().containsKey(foneAntigo)) {
            clienteAberto.getTelefones().remove(foneAntigo);
            clienteAberto.getTelefones().put(foneNovo, tipoNovo);
            if(!registrando) {
                boolean r = ClienteModel.alterarFoneCliente(bdConnection, foneNovo, tipoNovo, 
                        clienteAberto.getCPF(), foneAntigo);
                if(r){
                    System.out.println("Alteração do telefone salva!");
                    operacao = OPERACAO_ENVIAR;
                }
            }
        }

        ArrayList<String> fones = new ArrayList();

        for (Map.Entry f : clienteAberto.getTelefones().entrySet()) {
            fones.add(String.format("%s: %s", (String) f.getValue(),
                    Cliente.formatarFone((String) f.getKey())));
        }
        for (Map.Entry f : fonesTmp.entrySet()) {
            fones.add(String.format("%s: %s", (String) f.getValue(),
                    Cliente.formatarFone((String) f.getKey())));
        }

        fonesLista.setListData(fones.toArray(new String[fones.size()]));            

        clienteFoneInput.setText("");
    }//GEN-LAST:event_alterarFoneBtnMouseReleased

    private void fonesListaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_fonesListaMouseClicked
        if(fonesLista.getSelectedValue() == null) return;
        if (evt.getClickCount() >= 2) {
            String[] fonePartes = fonesLista.getSelectedValue().split(":");
            String fone;
            if (fonePartes.length > 0) {
                fone = fonePartes[1].replaceAll("[\\s()_-]", "");                
            } else {
                fone = fonesLista.getSelectedValue();
            }
            clienteFoneInput.setText(fone);
        }
    }//GEN-LAST:event_fonesListaMouseClicked

    private void limparNumBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limparNumBtnMouseReleased
        clienteFoneInput.setText("");
    }//GEN-LAST:event_limparNumBtnMouseReleased

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
            java.util.logging.Logger.getLogger(GerenciarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GerenciarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GerenciarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GerenciarCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GerenciarCliente dialog = new GerenciarCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton addFoneBtn;
    private javax.swing.JButton alterarFoneBtn;
    private javax.swing.JFormattedTextField clienteCEPInput;
    private javax.swing.JButton clienteCancelarBtn;
    private javax.swing.JTextField clienteCidadeInput;
    private javax.swing.JTextField clienteComplementoInput;
    private javax.swing.JFormattedTextField clienteCpfInput;
    private javax.swing.JButton clienteDeletarBtn;
    private javax.swing.JButton clienteEnviarBtn;
    private javax.swing.JFormattedTextField clienteFoneInput;
    private javax.swing.JTextField clienteNomeInput;
    private javax.swing.JTextField clienteRuaInput;
    private javax.swing.JTextField clienteSetorInput;
    private javax.swing.JFormattedTextField clienteUFInput;
    private javax.swing.JComboBox<String> foneTipoCombo;
    private javax.swing.JList<String> fonesLista;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limparNumBtn;
    private javax.swing.JButton removerFoneBtn;
    // End of variables declaration//GEN-END:variables
}
