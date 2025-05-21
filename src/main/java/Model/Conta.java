package Model;

import java.util.Calendar;

public class Conta {
    private int idCliente;
    private String nome;
    private String tipo;
    private double valor;
    private Calendar data;
    
    public Conta(int idCliente, String nome, String tipo, double valor, Calendar data){
        this.idCliente = idCliente;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }
    
    public int getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public double getValor(){
        return valor;
    }
    
    public void setValor(double valor){
        this.valor = valor;
    }
    
    public Calendar getData(){
        return data;
    }
    
    public void setData(Calendar data){
        this.data = data;
    }
    
    @Override
    public String toString(){
        return "Conta: " + getNome() + " Valor: " + getValor();
    }
    
}
