package com.arionporfirio;

import java.awt.CardLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class FramePrincipal extends javax.swing.JFrame {
    ArrayList<Cliente> listaDeClientes = null;
    ArrayList<Produto> listaDeProdutos = null;
    ArrayList<Venda> listaDeVendas = null;
    BD bd = new BD();
        
    public FramePrincipal() {
        initComponents();
        
        bd.connect();
        listaDeClientes = ClienteModel.listarClientes(bd.getConnection());
        listaDeProdutos = ProdutoModel.listarProdutos(bd.getConnection());
        listaDeVendas = VendaModel.listarVendas(bd.getConnection());
        
        clienteTabela.setAutoCreateRowSorter(true);
        produtoTabela.setAutoCreateRowSorter(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        principalPanel = new javax.swing.JPanel();
        inicioPanel = new javax.swing.JPanel();
        tabelasPanel = new javax.swing.JPanel();
        clientesPgBtn = new javax.swing.JButton();
        produtosPgBtn = new javax.swing.JButton();
        vendasPgBtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        EstatisticaPanel = new javax.swing.JPanel();
        freqPanel = new javax.swing.JPanel();
        qtdVendasEstBtn = new javax.swing.JButton();
        preVendasEstBtn = new javax.swing.JButton();
        clienteEstPanel = new javax.swing.JPanel();
        qtdComprasEstBtn = new javax.swing.JButton();
        preCompasEstBtn = new javax.swing.JButton();
        vendaEstPanel = new javax.swing.JPanel();
        ctlValoresEstBtn = new javax.swing.JButton();
        pgtFormasEstBtn = new javax.swing.JButton();
        dadosExpPanel = new javax.swing.JPanel();
        impDadosBtn = new javax.swing.JButton();
        expDadosBtn = new javax.swing.JButton();
        clientesPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        clienteTabela = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        buscarClienteInput = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        clientesVoltarBtn = new javax.swing.JButton();
        produtosPanel = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        produtoTabela = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        buscarProdutoInput = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        produtosVoltarBtn = new javax.swing.JButton();
        produtoRegistrarVendaBtn = new javax.swing.JButton();
        vendasPanel = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        vendaTabela = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        vendaFiltrarInput = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        vendaVoltarBtn = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        vendaAtePeriodoInput = new javax.swing.JFormattedTextField();
        vendaDePeriodoInput = new javax.swing.JFormattedTextField();
        jLabel10 = new javax.swing.JLabel();
        buscarVendaInput = new javax.swing.JTextField();
        vendaBuscarBtn = new javax.swing.JButton();
        vendaFiltrarBtn = new javax.swing.JButton();
        vendaLimparBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        registrarClienteMenuBtn = new javax.swing.JMenuItem();
        registrarProdutoMenuBtn = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle de Vendas");

        principalPanel.setLayout(new java.awt.CardLayout());

        tabelasPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Tabelas"));

        clientesPgBtn.setText("Clientes");
        clientesPgBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clientesPageGo(evt);
            }
        });

        produtosPgBtn.setText("Produtos");
        produtosPgBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                produtosPageGo(evt);
            }
        });

        vendasPgBtn.setText("Vendas");
        vendasPgBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendasPageGo(evt);
            }
        });

        javax.swing.GroupLayout tabelasPanelLayout = new javax.swing.GroupLayout(tabelasPanel);
        tabelasPanel.setLayout(tabelasPanelLayout);
        tabelasPanelLayout.setHorizontalGroup(
            tabelasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelasPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(clientesPgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100)
                .addComponent(produtosPgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(100, 100, 100)
                .addComponent(vendasPgBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(31, 31, 31))
        );
        tabelasPanelLayout.setVerticalGroup(
            tabelasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabelasPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(tabelasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(produtosPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(clientesPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendasPgBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel1.setText("Controle de Vendas: Bem-Vindo");

        EstatisticaPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Relatórios Estatísticos"));

        freqPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Distribuição de Frequência"));

        qtdVendasEstBtn.setText("Quantidade de Vendas");
        qtdVendasEstBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                qtdVendasEstBtnMouseReleased(evt);
            }
        });

        preVendasEstBtn.setText("Preço das Vendas");
        preVendasEstBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                preVendasEstBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout freqPanelLayout = new javax.swing.GroupLayout(freqPanel);
        freqPanel.setLayout(freqPanelLayout);
        freqPanelLayout.setHorizontalGroup(
            freqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(freqPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(freqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, freqPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(qtdVendasEstBtn))
                    .addComponent(preVendasEstBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        freqPanelLayout.setVerticalGroup(
            freqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(freqPanelLayout.createSequentialGroup()
                .addComponent(qtdVendasEstBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(preVendasEstBtn)
                .addContainerGap())
        );

        clienteEstPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Estatísticas do Cliente"));

        qtdComprasEstBtn.setText("Quantidade das Compras");
        qtdComprasEstBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                qtdComprasEstBtnMouseReleased(evt);
            }
        });

        preCompasEstBtn.setText("Preço das Compras  ");
        preCompasEstBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                preCompasEstBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout clienteEstPanelLayout = new javax.swing.GroupLayout(clienteEstPanel);
        clienteEstPanel.setLayout(clienteEstPanelLayout);
        clienteEstPanelLayout.setHorizontalGroup(
            clienteEstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clienteEstPanelLayout.createSequentialGroup()
                .addComponent(preCompasEstBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(qtdComprasEstBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        clienteEstPanelLayout.setVerticalGroup(
            clienteEstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clienteEstPanelLayout.createSequentialGroup()
                .addComponent(qtdComprasEstBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(preCompasEstBtn)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        vendaEstPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Informações de Vendas"));

        ctlValoresEstBtn.setText("Controle de Valores");
        ctlValoresEstBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                ctlValoresEstBtnMouseReleased(evt);
            }
        });

        pgtFormasEstBtn.setText("Formas de Pagamento");
        pgtFormasEstBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                pgtFormasEstBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout vendaEstPanelLayout = new javax.swing.GroupLayout(vendaEstPanel);
        vendaEstPanel.setLayout(vendaEstPanelLayout);
        vendaEstPanelLayout.setHorizontalGroup(
            vendaEstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ctlValoresEstBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pgtFormasEstBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 201, Short.MAX_VALUE)
        );
        vendaEstPanelLayout.setVerticalGroup(
            vendaEstPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendaEstPanelLayout.createSequentialGroup()
                .addComponent(ctlValoresEstBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pgtFormasEstBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout EstatisticaPanelLayout = new javax.swing.GroupLayout(EstatisticaPanel);
        EstatisticaPanel.setLayout(EstatisticaPanelLayout);
        EstatisticaPanelLayout.setHorizontalGroup(
            EstatisticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstatisticaPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(freqPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clienteEstPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(vendaEstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        EstatisticaPanelLayout.setVerticalGroup(
            EstatisticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(EstatisticaPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(EstatisticaPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(freqPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(clienteEstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(vendaEstPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        dadosExpPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Dados"));

        impDadosBtn.setText("Importar");
        impDadosBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                impDadosBtnMouseReleased(evt);
            }
        });

        expDadosBtn.setText("Exportar");
        expDadosBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                expDadosBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout dadosExpPanelLayout = new javax.swing.GroupLayout(dadosExpPanel);
        dadosExpPanel.setLayout(dadosExpPanelLayout);
        dadosExpPanelLayout.setHorizontalGroup(
            dadosExpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dadosExpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dadosExpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(impDadosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(expDadosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        dadosExpPanelLayout.setVerticalGroup(
            dadosExpPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dadosExpPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(impDadosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(expDadosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout inicioPanelLayout = new javax.swing.GroupLayout(inicioPanel);
        inicioPanel.setLayout(inicioPanelLayout);
        inicioPanelLayout.setHorizontalGroup(
            inicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(inicioPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(inicioPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabelasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(inicioPanelLayout.createSequentialGroup()
                        .addComponent(EstatisticaPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dadosExpPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        inicioPanelLayout.setVerticalGroup(
            inicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inicioPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(tabelasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(inicioPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(dadosExpPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(EstatisticaPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(36, 36, 36))
        );

        principalPanel.add(inicioPanel, "card1");

        clienteTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CPF", "Nome", "Telefone", "Endereço"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        clienteTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteTabelaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(clienteTabela);

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel2.setText("Tabela de Clientes");

        buscarClienteInput.setToolTipText("Buscar pelo nome, cpf, ou fone ...");

        jLabel3.setText("Buscar Cliente:");

        clientesVoltarBtn.setText("Voltar");
        clientesVoltarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                clientesBackInicio(evt);
            }
        });

        javax.swing.GroupLayout clientesPanelLayout = new javax.swing.GroupLayout(clientesPanel);
        clientesPanel.setLayout(clientesPanelLayout);
        clientesPanelLayout.setHorizontalGroup(
            clientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(clientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, clientesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(buscarClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)
                        .addGap(498, 498, 498))
                    .addGroup(clientesPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(clientesVoltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        clientesPanelLayout.setVerticalGroup(
            clientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(clientesPanelLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(clientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(clientesVoltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(clientesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarClienteInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE))
        );

        principalPanel.add(clientesPanel, "card2");

        produtoTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Código", "Descrição", "Preço Unitário", "Tipo de Unidade", "Incluir em Venda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        produtoTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                produtoTabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(produtoTabela);

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel4.setText("Tabela de Produtos");

        buscarProdutoInput.setToolTipText("Buscar um código de produto ...");

        jLabel5.setText("Buscar Produto:");

        produtosVoltarBtn.setText("Voltar");
        produtosVoltarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                produtosBackInicio(evt);
            }
        });

        produtoRegistrarVendaBtn.setText("Registrar Nova Venda");
        produtoRegistrarVendaBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                produtoRegistrarVendaBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout produtosPanelLayout = new javax.swing.GroupLayout(produtosPanel);
        produtosPanel.setLayout(produtosPanelLayout);
        produtosPanelLayout.setHorizontalGroup(
            produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produtosPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, produtosPanelLayout.createSequentialGroup()
                        .addGroup(produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(produtosPanelLayout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(buscarProdutoInput, javax.swing.GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)))
                        .addGap(289, 289, 289)
                        .addGroup(produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(produtoRegistrarVendaBtn, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(produtosVoltarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        produtosPanelLayout.setVerticalGroup(
            produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(produtosPanelLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(produtosVoltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(produtosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buscarProdutoInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(produtoRegistrarVendaBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE))
        );

        principalPanel.add(produtosPanel, "card3");

        vendaTabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nota Fiscal", "Cliente (CPF)", "Total da Compra", "Valor Desconto", "Met.Pagamento", "Data da Venda"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        vendaTabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                vendaTabelaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(vendaTabela);

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel6.setText("Tabela de Vendas");

        vendaFiltrarInput.setToolTipText("Nota Fiscal, ou CPF do cliente.");

        jLabel7.setText("Filtrar Venda:");

        vendaVoltarBtn.setText("Voltar");
        vendaVoltarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendasBackInicio(evt);
            }
        });

        jLabel8.setText("Período de Vendas :            De:");

        jLabel9.setText("Até:");

        vendaAtePeriodoInput.setToolTipText("Data final.");

        vendaDePeriodoInput.setToolTipText("Data inicial.");

        jLabel10.setText("Filtrar por Produto:");

        buscarVendaInput.setToolTipText("Códido do Produto.");

        vendaBuscarBtn.setText("Filtrar ");
        vendaBuscarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buscarVendaPorProdutoBtn(evt);
            }
        });

        vendaFiltrarBtn.setText("Filtrar por Data");
        vendaFiltrarBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                filtrarVendasPorDataBtn(evt);
            }
        });

        vendaLimparBtn.setText("Limpar Filtros");
        vendaLimparBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                vendaLimparBtnMouseReleased(evt);
            }
        });

        javax.swing.GroupLayout vendasPanelLayout = new javax.swing.GroupLayout(vendasPanel);
        vendasPanel.setLayout(vendasPanelLayout);
        vendasPanelLayout.setHorizontalGroup(
            vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendasPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addGroup(vendasPanelLayout.createSequentialGroup()
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vendaDePeriodoInput, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addGap(65, 65, 65)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(vendaAtePeriodoInput, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE)
                        .addGap(83, 83, 83)
                        .addComponent(vendaFiltrarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 158, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, vendasPanelLayout.createSequentialGroup()
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vendaFiltrarInput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buscarVendaInput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vendaBuscarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(vendasPanelLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(vendaLimparBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(vendaVoltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        vendasPanelLayout.setVerticalGroup(
            vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(vendasPanelLayout.createSequentialGroup()
                .addGroup(vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(vendasPanelLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vendaVoltarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(vendaLimparBtn))
                        .addGap(18, 18, 18))
                    .addGroup(vendasPanelLayout.createSequentialGroup()
                        .addContainerGap(14, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)))
                .addGroup(vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(vendaBuscarBtn)
                    .addComponent(buscarVendaInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendaFiltrarInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(vendasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(vendaAtePeriodoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(vendaFiltrarBtn)
                    .addComponent(vendaDePeriodoInput, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE))
        );

        principalPanel.add(vendasPanel, "card4");

        jMenu1.setText("Registros");

        registrarClienteMenuBtn.setText("Resgistrar novo cliente");
        registrarClienteMenuBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                registrarNovoCliente(evt);
            }
        });
        jMenu1.add(registrarClienteMenuBtn);

        registrarProdutoMenuBtn.setText("Registrar novo produto");
        registrarProdutoMenuBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                registrarNovoProduto(evt);
            }
        });
        jMenu1.add(registrarProdutoMenuBtn);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sistema");

        jMenuItem4.setText("Sair");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseReleased(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(principalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(principalPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Gerenciamento da Tabela de Clientes
    protected ArrayList<Cliente> filtrarClientes(String filtro) {
        if ((filtro == null) || filtro.isEmpty())           
            return listaDeClientes;
        ArrayList<Cliente> clientesFiltrados = new ArrayList();
        String filtroLower = filtro.toLowerCase();       
      
        listaDeClientes.forEach((cliente) -> {
            if (cliente.getNome().toLowerCase().contains(filtroLower) ||
                    cliente.getCPF().contains(filtroLower)) {
                
                clientesFiltrados.add(cliente);
            }
        });
        if (clientesFiltrados.isEmpty()) {
            return null;
        }
        return clientesFiltrados;
    }
    
    private void buscarClienteFiltrado() {
        ArrayList<Cliente> clientesFiltrados = filtrarClientes(
                buscarClienteInput.getText());
        exibirClientes(clientesFiltrados);
    }
    
    private void exibirClientes(ArrayList<Cliente> clientes) {
        try {
            DefaultTableModel tbModel = (DefaultTableModel) clienteTabela.getModel();
            tbModel.setRowCount(0);
            
            if (clientes == null || clientes.isEmpty()) {
                return;
            }
            
            clientes.forEach((cliente) -> {
                tbModel.addRow(new Object[] {cliente.getCpfFormatado(),
                    cliente.getNome(), cliente.getFoneFormatado(), 
                    cliente.getEndereco()});
            });
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar tabela\n" + ex.getMessage(),
                                          "Listar Clientes", JOptionPane.ERROR_MESSAGE);            
        }
    }
    // Fim do gerenciamento da Tabela de Clientes
    
    // Gerencimaneto da Tabela de Produtos
    protected ArrayList<Produto> filtrarProdutos(String filtro) {
        if ((filtro == null) || filtro.isEmpty())           
            return listaDeProdutos;
        ArrayList<Produto> produtosFiltrados = new ArrayList();
        String filtroLower = filtro.toLowerCase();       
      
        listaDeProdutos.forEach((produto) -> {
            if (produto.getDescricao().toLowerCase().contains(filtroLower) ||
                    produto.getCodigo().contains(filtroLower)) {
                
                produtosFiltrados.add(produto);
            }
        });
        if (produtosFiltrados.isEmpty()) {
            return null;
        }
        return produtosFiltrados;
    }    
    
    private void buscarProdutoFiltrado() {
        ArrayList<Produto> produtosFiltrados = filtrarProdutos(
                buscarProdutoInput.getText());
        exibirProdutos(produtosFiltrados);
    }
    
    private void exibirProdutos(ArrayList<Produto> produtos) {
        try {
            DefaultTableModel tbModel = (DefaultTableModel) produtoTabela.getModel();
            tbModel.setRowCount(0);            
            if (produtos == null || produtos.isEmpty()) {
                return;
            }            
            produtos.forEach((produto) -> {
                tbModel.addRow(new Object[] {produto.getCodigo(),
                    produto.getDescricao(), produto.getPrecoFormatado(), 
                    produto.getUnidade()});
            });
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar tabela\n" + ex.getMessage(),
                                          "Listar Produtos", JOptionPane.ERROR_MESSAGE);            
        }
    }    
    // Fim do Gerenciamento da Tabela de Produtos
    
    // Gerenciamento da Tabela de Vendas
    protected ArrayList<Venda> filtrarVendas(String filtro) {
        if ((filtro == null) || filtro.isEmpty())           
            return listaDeVendas;
        ArrayList<Venda> vendasFiltradas = new ArrayList();
      
        listaDeVendas.forEach((venda) -> {
            if (venda.getNotaFiscal().contains(filtro) ||
                    venda.getClienteCpf().contains(filtro)) {
                
                vendasFiltradas.add(venda);
            }
        });
        if (vendasFiltradas.isEmpty()) {
            return null;
        }
        return vendasFiltradas;
    }
    
    private void buscarVendaFiltrada() {
        ArrayList<Venda> vendasFiltradas = filtrarVendas(
                vendaFiltrarInput.getText());
        exibirVendas(vendasFiltradas);
    }
    
    private void exibirVendas(ArrayList<Venda> vendas) {        
        try {            
            DefaultTableModel tbModel = (DefaultTableModel) vendaTabela.getModel();
            tbModel.setRowCount(0);
            if (vendas == null || vendas.isEmpty()) {
                return;
            }                        
            vendas.forEach((venda) -> {
                tbModel.addRow(new Object[] {venda.getNotaFiscal(),
                    venda.getClienteCpfFormatado(), venda.getValorTotalFormatado(),
                    venda.getDescontoFormatado(), venda.getPagamento(),
                    venda.getDataDaVendaFormatada()});
            });
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Erro ao atualizar a tabela de vendas\n" + 
                    ex.getMessage(), "Listar Vendas", JOptionPane.ERROR_MESSAGE);            
        }
    }
    // Fim do gerenciamento da Tabela de Vendas 
    
    private void clientesPageGo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesPageGo
        
        buscarClienteInput.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarClienteFiltrado();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarClienteFiltrado();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarClienteFiltrado();
            }

        });
               
        exibirClientes(listaDeClientes);
        
        CardLayout cl = (CardLayout)principalPanel.getLayout();
        cl.show(principalPanel, "card2");
    }//GEN-LAST:event_clientesPageGo

    private void clientesBackInicio(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clientesBackInicio
        CardLayout cl = (CardLayout)principalPanel.getLayout();
        cl.show(principalPanel, "card1");
    }//GEN-LAST:event_clientesBackInicio

    private void produtosPageGo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtosPageGo
        buscarProdutoInput.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarProdutoFiltrado();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarProdutoFiltrado();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarProdutoFiltrado();
            }            
        });
        
        exibirProdutos(listaDeProdutos);
        
        CardLayout cl = (CardLayout)principalPanel.getLayout();
        cl.show(principalPanel, "card3");
    }//GEN-LAST:event_produtosPageGo

    private void vendasPageGo(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendasPageGo
        vendaFiltrarInput.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                buscarVendaFiltrada();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                buscarVendaFiltrada();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                buscarVendaFiltrada();
            }            
        });

        exibirVendas(listaDeVendas);
        
        MaskFormatter maskDePeriodo = new MaskFormatter();
        MaskFormatter maskAtePeriodo = new MaskFormatter();
        try {
            maskDePeriodo =  new MaskFormatter("##/##/####");
            maskDePeriodo.setPlaceholderCharacter('_');
            maskAtePeriodo = new MaskFormatter("##/##/####");
            maskAtePeriodo.setPlaceholderCharacter('_');
        } catch (ParseException ex){}
        maskDePeriodo.install(vendaDePeriodoInput);
        maskAtePeriodo.install(vendaAtePeriodoInput);        
        vendaDePeriodoInput.setText(new SimpleDateFormat("ddMMyyyy").format(
            new Date()));
        vendaAtePeriodoInput.setText(new SimpleDateFormat("ddMMyyyy").format(
            new Date()));               
        
        CardLayout cl = (CardLayout)principalPanel.getLayout();
        cl.show(principalPanel, "card4");
    }//GEN-LAST:event_vendasPageGo

    private void vendasBackInicio(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendasBackInicio
        CardLayout cl = (CardLayout)principalPanel.getLayout();
        cl.show(principalPanel, "card1");
    }//GEN-LAST:event_vendasBackInicio

    private void produtosBackInicio(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtosBackInicio
        System.out.println("Voltando para o painel inicial!");
        CardLayout cl = (CardLayout)principalPanel.getLayout();
        cl.show(principalPanel, "card1");
    }//GEN-LAST:event_produtosBackInicio

    private void registrarNovoCliente(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarNovoCliente
        GerenciarCliente gc = new GerenciarCliente(this, true);
        gc.setTitle("Registrando novo Cliente");
        gc.setConnection(bd.getConnection());
        gc.registrandoCliente(true);
        gc.setVisible(true);
        
        if (gc.getOperacao() == GerenciarCliente.OPERACAO_ENVIAR) {
           listaDeClientes.add(gc.getCliente());
           buscarClienteInput.setText("");
           exibirClientes(listaDeClientes);
        }
    }//GEN-LAST:event_registrarNovoCliente

    private void clienteTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteTabelaMouseClicked
        if (evt.getClickCount() >= 2) {
            int rowModel = clienteTabela.getSelectedRow();
            int rowView = clienteTabela.convertRowIndexToModel(rowModel);

            DefaultTableModel model = (DefaultTableModel) clienteTabela.getModel();
            ArrayList<Cliente> lista = filtrarClientes(
                    ((String)model.getValueAt(rowView, 0)).replaceAll("[\\s.-]",""));
            if(lista == null)return;
            Cliente clienteSelecionado = lista.get(0);

            GerenciarCliente gc = new GerenciarCliente(this, true);
            gc.setTitle("Gerenciar Cliente");
            gc.setConnection(bd.getConnection());
            gc.setCliente(clienteSelecionado);
            gc.registrandoCliente(false);
            
            gc.setVisible(true);
            
            switch(gc.getOperacao()) {
                case GerenciarCliente.OPERACAO_ENVIAR:
                    listaDeClientes.forEach((cliente) -> {
                        Cliente alterado = gc.getCliente();
                        if(cliente.getCPF().equals(alterado.getCPF())){
                            cliente.setTelefones(alterado.getTelefones());                                
                            cliente.setEndereco(alterado.getEndereco());
                        }
                    });
                    buscarClienteInput.setText("");
                    exibirClientes(listaDeClientes);                  
                    break;
                case GerenciarCliente.OPERACAO_DELETAR:
                    listaDeClientes.remove(clienteSelecionado);
                    model.removeRow(rowView);     
                    break;
            }
        }
    }//GEN-LAST:event_clienteTabelaMouseClicked

    private void produtoTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoTabelaMouseClicked
        if(evt.getClickCount() >= 2) {
            GerenciarProduto gp = new GerenciarProduto(this, true);
            gp.setTitle("Alterar Dados do Produto");
            gp.setConnection(bd.getConnection());
            
            int rowModel = produtoTabela.getSelectedRow();
            int rowView = produtoTabela.convertRowIndexToModel(rowModel);
            DefaultTableModel model = (DefaultTableModel) produtoTabela.getModel();
            ArrayList<Produto> lista = filtrarProdutos(
                (String)model.getValueAt(rowView, 0));            
            if(lista == null) return;
            Produto emAlteracao = lista.get(0);
                        
            gp.setProduto(emAlteracao);
            gp.registrandoProduto(false);
            gp.setVisible(true);
            
            switch(gp.getOperacao()) {
                case GerenciarProduto.OPERACAO_ENVIAR:
                    listaDeProdutos.forEach((produto) -> {
                        if(produto.getCodigo().contains(emAlteracao.getCodigo())){
                            Produto alterado = gp.getProduto();
                            produto.setDescricao(alterado.getDescricao());
                            produto.setPreco(alterado.getPreco());
                            produto.setUnidade(alterado.getUnidade());                                                                                   
                        }                        
                    });
                    buscarProdutoInput.setText("");
                    exibirProdutos(listaDeProdutos);
                    break;
                case GerenciarProduto.OPERACAO_DELETAR:
                    listaDeProdutos.remove(gp.getProduto());
                    model.removeRow(rowView);
                    break;
            }
        }
    }//GEN-LAST:event_produtoTabelaMouseClicked

    private void registrarNovoProduto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_registrarNovoProduto
        GerenciarProduto gp = new GerenciarProduto(this, true);
        gp.setTitle("Registrar Novo Produto");
        gp.setConnection(bd.getConnection());
        gp.registrandoProduto(true);
        gp.setVisible(true);
        
        if(gp.getOperacao() == gp.OPERACAO_ENVIAR) {
            listaDeProdutos.add(gp.getProduto());
            buscarProdutoInput.setText("");
            exibirProdutos(listaDeProdutos);
        }
    }//GEN-LAST:event_registrarNovoProduto

    private void produtoRegistrarVendaBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_produtoRegistrarVendaBtnMouseReleased
        ArrayList<Produto> vendaProdutos = new ArrayList();

        for(int row = 0; row < produtoTabela.getRowCount(); row++) {
            DefaultTableModel ptm = (DefaultTableModel) produtoTabela.getModel();
            if(ptm.getValueAt(row, 4) != null) {
                Produto p = new Produto(
                        (String)ptm.getValueAt(row, 0),
                        (String)ptm.getValueAt(row, 1),
                        Double.parseDouble(((String)ptm.getValueAt(row, 2)).replaceAll(
                                "[\\sR$]", "").replace(",", ".")),
                        (String)ptm.getValueAt(row, 3)
                );
                vendaProdutos.add(p);
            }
        }
        
        if (!vendaProdutos.isEmpty()) {                    
            GerenciarVenda gv = new GerenciarVenda(this, true);
            gv.setProdutosVenda(vendaProdutos);
            gv.setTitle("Registrando Nova Venda");
            gv.setConnection(bd.getConnection());
            gv.registrandoVenda(true);
            gv.setVisible(true);
            
            if(gv.getOperacao() == GerenciarVenda.OPERACAO_ENVIAR) {
                listaDeVendas.add(gv.getVenda());
            }
            
        } else {
            System.out.println("Impossível registrar uma venda sem produtos!");
        }
    }//GEN-LAST:event_produtoRegistrarVendaBtnMouseReleased

    private void buscarVendaPorProdutoBtn(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_buscarVendaPorProdutoBtn
        ArrayList<Venda> vendasPorProduto = new ArrayList();
        String filtro = buscarVendaInput.getText();
        if(filtro.length() != 13) {
            return;
        }
        listaDeVendas.forEach((venda) -> {
            for (Map.Entry p: venda.getProdutos().entrySet()) {
                if (((String) p.getKey()).contains(filtro)) {
                    vendasPorProduto.add(venda);
                }
            }
        });

        exibirVendas(vendasPorProduto);
    }//GEN-LAST:event_buscarVendaPorProdutoBtn

    private void filtrarVendasPorDataBtn(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filtrarVendasPorDataBtn
        ArrayList<Venda> vendasFiltradas = new ArrayList();
        Date dataI;
        Date dataF;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        try {            
            dataI = sdf.parse(vendaDePeriodoInput.getText());
            dataF = sdf.parse(vendaAtePeriodoInput.getText());
        } catch(ParseException ex) {
            dataI = new Date();
            dataF = new Date();
        }
        
        if(dataI.compareTo(dataF) > 0 ) {
            Date tmp;
            tmp = dataI;
            dataI = dataF;
        }

        for(Venda venda: listaDeVendas){
            Date dataDaVenda = venda.getDataDaVenda();
            if(dataDaVenda.compareTo(dataI) >= 0 && dataDaVenda.compareTo(dataF) <= 0) {
                vendasFiltradas.add(venda);
            }
        }
        
        exibirVendas(vendasFiltradas);                  
    }//GEN-LAST:event_filtrarVendasPorDataBtn

    private void vendaTabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendaTabelaMouseClicked
        if(evt.getClickCount() >= 2) {           
            int rowModel = vendaTabela.getSelectedRow();
            int rowView = vendaTabela.convertRowIndexToModel(rowModel);
            DefaultTableModel dtm = (DefaultTableModel) vendaTabela.getModel();
            
            ArrayList<Venda> lista = filtrarVendas(
                (String)dtm.getValueAt(rowView, 0));            
            
            if(lista == null) return;
            
            Venda emAlteracao = lista.get(0);
            
            GerenciarVenda gv = new GerenciarVenda(this, true);
            gv.setTitle("Alterar Dados da Venda");
            gv.setConnection(bd.getConnection());            
                        
            gv.setVenda(emAlteracao);
            gv.registrandoVenda(false);
            gv.setVisible(true);
            
            switch(gv.getOperacao()) {
                case GerenciarVenda.OPERACAO_ENVIAR:
                    for(int vI = 0; vI < listaDeVendas.size(); vI++) {
                        Venda antiga = listaDeVendas.get(vI);
                        if(antiga.getNotaFiscal().contains(emAlteracao.getNotaFiscal())){
                            listaDeVendas.set(vI, gv.getVenda());
                        }                          
                    }
                    vendaFiltrarInput.setText("");
                    exibirVendas(listaDeVendas);
                    break;
                case GerenciarVenda.OPERACAO_DELETAR:
                    listaDeVendas.remove(gv.getVenda());
                    dtm.removeRow(rowView);
                    break;
            }
        }
    }//GEN-LAST:event_vendaTabelaMouseClicked

    private void vendaLimparBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_vendaLimparBtnMouseReleased
        buscarVendaInput.setText("");
        vendaDePeriodoInput.setText(new SimpleDateFormat("ddMMyyyy").format(
            new Date()));
        vendaAtePeriodoInput.setText(new SimpleDateFormat("ddMMyyyy").format(
            new Date()));          
        
        exibirVendas(listaDeVendas);
    }//GEN-LAST:event_vendaLimparBtnMouseReleased

    private void impDadosBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_impDadosBtnMouseReleased
        GerenciarDados gd = new GerenciarDados(this, true);
        gd.setConnection(bd.getConnection());
        gd.setAcao(GerenciarDados.ACAO_IMPORTAR);
        gd.setTitle("Importar Dados");
        gd.setVisible(true);
        listaDeClientes = ClienteModel.listarClientes(bd.getConnection());
        listaDeProdutos = ProdutoModel.listarProdutos(bd.getConnection());
        listaDeVendas = VendaModel.listarVendas(bd.getConnection());        
    }//GEN-LAST:event_impDadosBtnMouseReleased

    private void expDadosBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expDadosBtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_expDadosBtnMouseReleased

    private void qtdVendasEstBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtdVendasEstBtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_qtdVendasEstBtnMouseReleased

    private void preVendasEstBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preVendasEstBtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_preVendasEstBtnMouseReleased

    private void qtdComprasEstBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_qtdComprasEstBtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_qtdComprasEstBtnMouseReleased

    private void preCompasEstBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_preCompasEstBtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_preCompasEstBtnMouseReleased

    private void ctlValoresEstBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ctlValoresEstBtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_ctlValoresEstBtnMouseReleased

    private void pgtFormasEstBtnMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pgtFormasEstBtnMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_pgtFormasEstBtnMouseReleased

    private void jMenuItem4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseReleased
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4MouseReleased

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
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel EstatisticaPanel;
    private javax.swing.JTextField buscarClienteInput;
    private javax.swing.JTextField buscarProdutoInput;
    private javax.swing.JTextField buscarVendaInput;
    private javax.swing.JPanel clienteEstPanel;
    private javax.swing.JTable clienteTabela;
    private javax.swing.JPanel clientesPanel;
    private javax.swing.JButton clientesPgBtn;
    private javax.swing.JButton clientesVoltarBtn;
    private javax.swing.JButton ctlValoresEstBtn;
    private javax.swing.JPanel dadosExpPanel;
    private javax.swing.JButton expDadosBtn;
    private javax.swing.JPanel freqPanel;
    private javax.swing.JButton impDadosBtn;
    private javax.swing.JPanel inicioPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton pgtFormasEstBtn;
    private javax.swing.JButton preCompasEstBtn;
    private javax.swing.JButton preVendasEstBtn;
    private javax.swing.JPanel principalPanel;
    private javax.swing.JButton produtoRegistrarVendaBtn;
    private javax.swing.JTable produtoTabela;
    private javax.swing.JPanel produtosPanel;
    private javax.swing.JButton produtosPgBtn;
    private javax.swing.JButton produtosVoltarBtn;
    private javax.swing.JButton qtdComprasEstBtn;
    private javax.swing.JButton qtdVendasEstBtn;
    private javax.swing.JMenuItem registrarClienteMenuBtn;
    private javax.swing.JMenuItem registrarProdutoMenuBtn;
    private javax.swing.JPanel tabelasPanel;
    private javax.swing.JFormattedTextField vendaAtePeriodoInput;
    private javax.swing.JButton vendaBuscarBtn;
    private javax.swing.JFormattedTextField vendaDePeriodoInput;
    private javax.swing.JPanel vendaEstPanel;
    private javax.swing.JButton vendaFiltrarBtn;
    private javax.swing.JTextField vendaFiltrarInput;
    private javax.swing.JButton vendaLimparBtn;
    private javax.swing.JTable vendaTabela;
    private javax.swing.JButton vendaVoltarBtn;
    private javax.swing.JPanel vendasPanel;
    private javax.swing.JButton vendasPgBtn;
    // End of variables declaration//GEN-END:variables
}
