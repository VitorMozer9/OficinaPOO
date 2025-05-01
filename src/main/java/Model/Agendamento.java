package Model;

import java.util.Calendar;

public class Agendamento {
    
    private int idAgendamento;
    private int idCliente;
    private double valorInspecao;
    private String mecanicoResponsavel;
    public Calendar dataHora;
    
    public Agendamento(int idAgendamento, int idCliente, double valorInspecao, String mecanicoResponsavel, Calendar dataHora){
        this.idAgendamento = idAgendamento;
        this.idCliente = idCliente;
        this.mecanicoResponsavel = mecanicoResponsavel;
        this.dataHora = dataHora;
    }
    
    public int geIdAgendamento(){
        return idAgendamento;
    }
    
    public void setIdAgendamento(int idAgendamento){
        this.idAgendamento = idAgendamento;       
    }
    
    public int getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    public double getValorInspecao(){
        return valorInspecao;
    }
    
    public void setValorInspecao(double valorInspecao){
        this.valorInspecao = valorInspecao;
    }
    
    public String getMecanicoResponsavel(){
        return mecanicoResponsavel;
    }
    
    public void setMecanicoResponsavel(String mecanicoResponsavel){
        this.mecanicoResponsavel = mecanicoResponsavel;
    }
    
    public Calendar getDataHora(){
        return dataHora;
    } 
            
    public void setDataHora(Calendar dataHora){
        this.dataHora = dataHora;
    }
    
    @Override
    public String toString() {
        
        return "Agendamento realizado. Traga o veícilo no dia" + this.dataHora; 
                
    }
    
}
