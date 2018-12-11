package com.arionporfirio;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.text.MaskFormatter;

public class Cliente {
    private String CPF;
    private String nome;
    private HashMap<String, String> telefones;
    private String endereco;
    
    Cliente() {
        this.CPF = "";
        this.nome = "";
        this.telefones = new HashMap();
        this.endereco = "";
    }
    
    Cliente(String CPF, String nome, HashMap<String, String> telefones, 
            String endereco) {
        this.CPF = CPF;
        this.nome = nome;
        this.telefones = telefones;
        this.endereco = endereco;
    }
    
    public String getCPF () {
        return this.CPF;
    }
    
    public String getCpfFormatado() {           
        try {
            MaskFormatter formatador = new MaskFormatter("AAA.AAA.AAA-AA");
            formatador.setValueContainsLiteralCharacters(false);
            return formatador.valueToString(this.CPF);
        } catch (ParseException ex) {
            System.out.println(ex);
            return "";
        }
    }
    
    public String getNome () {
        return this.nome;
    }
    
    public HashMap<String, String> getTelefones () {
        return this.telefones;
    }
    
    public String getFoneFormatado() {
        try {
            MaskFormatter formatador = new MaskFormatter("(AA) AAAAA-AAAA");
            formatador.setValueContainsLiteralCharacters(false);
            return formatador.valueToString(this.telefones.keySet().toArray()[0]);
        } catch (Exception ex) {
            System.out.println(ex);
            return "";
        }
    }
    
    public String getEndereco () {
        return this.endereco;
    }
    
    public void setCPF (String CPF) throws IllegalArgumentException {
        if (CPF.length() != 11) {
            throw new IllegalArgumentException("O campo CPF digitado é inválido!");
        }
        this.CPF = CPF;
    }
    
    public void setNome (String nome) throws IllegalArgumentException {
        if(nome.isEmpty()) {
            throw new IllegalArgumentException("O campo nome não ser vazio!");
        }
        
        if(!nome.matches("[a-zA-Z\\u00C0-\\u00FF ]+")) {
            throw new IllegalArgumentException("O campo nome só deve conter letras!");
        }

        String[] arr = nome.split(" ");
        StringBuilder sb = new StringBuilder();

        for (String arr1 : arr)
        {
            sb.append(Character.toUpperCase(arr1.charAt(0))).append(arr1.substring(1).toLowerCase()).append(" ");
        }
        
        this.nome = sb.toString().trim();        
    }
    
    public void setTelefones (HashMap<String, String> telefones)
            throws IllegalArgumentException {
        if (telefones.size() < 0) {
            throw new IllegalArgumentException("Pelo menos um telefone deve ser adicionado!");
        }
        for(Map.Entry fone: telefones.entrySet()) {
            if(((String)fone.getKey()).length() != 11) {
                throw new IllegalArgumentException("Telefone inválido!");
            }              
        }
        this.telefones = telefones;
    }
    
    public void setEndereco (String endereco) throws IllegalArgumentException {
        if (endereco.isEmpty()) {
            throw new IllegalArgumentException("O campo endereço não deve ser vazio!");            
        }
        
        if (!endereco.matches("[0-9a-zA-Z,\\u00C0-\\u00FF ]+")) {
            throw new IllegalArgumentException("O campo endereço digitado é inválido!");
        }
        
        String[] end;
        end = endereco.split(",");
        
        if (end[0].equals(" ")) {
            throw new IllegalArgumentException("O campo rua não deve ser vazio!");
        }
        if (end[1].equals(" ")) {
            throw new IllegalArgumentException("O campo complemento não deve ser vazio!");
        }
        if (end[2].equals(" ")) {
            throw new IllegalArgumentException("O campo setor não deve ser vazio!");
        }
        if (end[3].equals(" ")) {
            throw new IllegalArgumentException("O campo cidade não deve ser vazio!");
        }
        if (end[4].equals(" ")) {
            throw new IllegalArgumentException("O campo UF não deve ser vazio!");
        }
        if (end[5].equals(" ")) {
            throw new IllegalArgumentException("O campo CEP não deve ser vazio!");
        }        
        
        this.endereco = endereco;
    }
    
    public static String formatarFone(String fone) {
        try {
            MaskFormatter formatador = new MaskFormatter("(AA) AAAAA-AAAA");
            formatador.setValueContainsLiteralCharacters(false);
            return formatador.valueToString(fone);
        } catch (Exception ex) {
            return fone;
        }
    }
}
