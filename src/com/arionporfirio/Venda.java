package com.arionporfirio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.swing.text.MaskFormatter;

public class Venda {
    private String notaFiscal;
    private String clienteCpf;
    private HashMap<String, Integer> produtos;
    private double valorTotal;
    private double desconto;
    private String pagamento;
    private Date dataDaVenda;
    
    public Venda(){
        notaFiscal = "";
        clienteCpf = "";
        produtos = new HashMap();
        valorTotal = 0.00;
        desconto = 0.00;
        pagamento = "";
        dataDaVenda = null;
    }
    
    public Venda(String notaFiscal, String clienteCpf,
            HashMap<String,Integer> produtos, double valorTotal,
            double desconto, String pagamento, Date dataDaVenda) {
        this.notaFiscal = notaFiscal;
        this.clienteCpf = clienteCpf;
        this.produtos = produtos;
        this.valorTotal = valorTotal;
        this.desconto = desconto;
        this.pagamento = pagamento;
        this.dataDaVenda = dataDaVenda;        
    }
    
    public String getNotaFiscal() {
        return this.notaFiscal;
    }
    
    public String getClienteCpf() {
        return this.clienteCpf;
    }
    
    public String getClienteCpfFormatado() {
        try {
            MaskFormatter formatador = new MaskFormatter("AAA.AAA.AAA-AA");
            formatador.setValueContainsLiteralCharacters(false);
            return formatador.valueToString(this.clienteCpf);
        } catch (ParseException ex) {
            return this.clienteCpf;
        }
    }
    
    public HashMap<String,Integer> getProdutos() {
        return this.produtos;
    }
    
    public double getValorTotal() {
        return this.valorTotal;
    }
    
    public String getValorTotalFormatado() {
        return String.format("R$ %.2f", this.valorTotal);
    }
    
    public double getDesconto() {
        return this.desconto;
    }
    
    public String getDescontoFormatado() {
        return String.format("R5 %.2f", this.desconto);
    }
    
    public String getPagamento() {
        return this.pagamento;
    }
    
    public Date getDataDaVenda() {
        return this.dataDaVenda;
    }
    
    public String getDataDaVendaFormatada() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(this.dataDaVenda);
    }
    
    public void setNotaFiscal(String notaFiscal) throws IllegalArgumentException {
        if(notaFiscal.length() != 44) {
            throw new IllegalArgumentException(
                    "A nota fiscal digitada é inválida!");
        }
        this.notaFiscal = notaFiscal;
    }
    
    public void setClienteCpf(String clienteCpf) throws IllegalArgumentException {
        if(clienteCpf.length() != 11) {
            throw new IllegalArgumentException(
                    "O CPF do cliente digitado é inválido!");
        }
        this.clienteCpf = clienteCpf;
    }
    
    public void setProdutos(HashMap<String,Integer> produtos) throws IllegalArgumentException {
        if(produtos.isEmpty()) {
            throw new IllegalArgumentException("Lista de produtos inválida!");
        }
        this.produtos = produtos;
    }
    
    public void setValorTotal(double valorTotal) throws IllegalArgumentException {
        if(valorTotal <= 0.00) {
            throw new IllegalArgumentException("O valor total é inválido!");
        }
        this.valorTotal = valorTotal;
    }
    
    public void setDesconto(double desconto) throws IllegalArgumentException {
        if(desconto < 0.00) {
            throw new IllegalArgumentException("O desconto é inválido!");
        }
        this.desconto = desconto;
    }
    
    public void setPagamento(String pagamento) throws IllegalArgumentException {
        if(pagamento.isEmpty()) {
            throw new IllegalArgumentException("O pagamento é inválido!");
        }
        this.pagamento = pagamento;
    }
    
    public void setDataDaVenda(Date dataDaVenda) throws IllegalArgumentException {
        if(dataDaVenda == null) {
            throw new IllegalArgumentException("A data da venda é inválida!");
        }
        this.dataDaVenda = dataDaVenda;
    }       
}
