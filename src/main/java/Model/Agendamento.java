package Model;

import java.util.Calendar;

/**
 * Representação do agendamento da manutenção dos veiculos, contendo o número do agendammento, o número do cliente, valor da inspeção inicial realizada,
 * o nome do mecânico esepecifíco que efetuou a inspeção e a data e a hora que foi agendada para a manutenção do veículo.
 * 
 */
public class Agendamento {
    
    private int idAgendamento;
    private int idCliente;
    private int idElevador;
    private static final double VALOR_AGENDAMENTO = 50.00;
    private String mecanicoResponsavel;
    private int tipoAgendamento;
    private String statusAgendamento;
    public Calendar dataHora;
    
    /**
     * Contrutor da classe Agendamento.
     * Inicializa um novo agendamento com as informações necessárias.
     * 
     * @param idAgendamento         Código do agendamento.
     * @param idCliente             Códico de identificação do cliente.  
     * @param valorInspecao         Valor cobrado para inspeção inicial.
     * @param mecanicoResponsavel   Nome do mecânico que fez a inspeção.
     * @param dataHora              Data e hora de retorno para manutenção do veículo.
     */
    public Agendamento(int idAgendamento, int idCliente,int idElevador ,int tipoAgendamento, String mecanicoResponsavel,String statusAgendamento ,Calendar dataHora){
        this.idAgendamento = idAgendamento;
        this.idCliente = idCliente;
        this.idElevador = idElevador;
        this.tipoAgendamento = tipoAgendamento;
        this.mecanicoResponsavel = mecanicoResponsavel;
        this.statusAgendamento = statusAgendamento;
        this.dataHora = dataHora;
    }
    
    /**
     * Obtém um número de agendamento.
     * 
     * @return Número de agendamento.
     */
    public int getIdAgendamento(){
        return idAgendamento;
    }
    
    public int getIdElevador(){
        return idElevador;
    }
    
    public void setIdElevador(int idElevador){
        this.idElevador = idElevador;
    }
    
    /**
     * Define um número de agendamento.
     * 
     * @param idAgendamento Novo número de agendamento.
     */
    public void setIdAgendamento(int idAgendamento){
        this.idAgendamento = idAgendamento;       
    }
    
    /**
     * Obtém um número de identificação para o cliente
     * 
     * @return Número de identificação do cliente.
     */
    public int getIdCliente(){
        return idCliente;
    }
    
    /**
     * Define um número de identificação de cliente.
     * 
     * @param idCliente Novo número de identificação para cliente.
     */
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    /**
     * Obtém o valor que foi cobrado pela inspeção do veículo.
     * 
     * @return Valor da inspeção.
     */
    public double getValorAgendamento(){
        return VALOR_AGENDAMENTO;
    }
    
    public int getTipoAgendamento(){
        return tipoAgendamento;
    }
    
    public void setTipoAgendamento(int tipoAgendamento){
        this.tipoAgendamento = tipoAgendamento;
    } 
    
    
    /**
     * Obtém o nome do mecânico responsável pela inspeção.
     * 
     * @return Nome do mecânico.
     */
    public String getMecanicoResponsavel(){
        return mecanicoResponsavel;
    }
    
    /**
     * Deine o nome do mecânico responsável pela inspeção.
     * 
     * @param mecanicoResponsavel Novo nome do mecânico.
     */
    public void setMecanicoResponsavel(String mecanicoResponsavel){
        this.mecanicoResponsavel = mecanicoResponsavel;
    }
    
    /**
     * Obtém a data e a hora marcadas no agendamento para o retorno do cliente que irá fazer a manutenção do veículo.
     * 
     * @return Data e Hora marcadas para retorno.
     */
    public Calendar getDataHora(){
        return dataHora;
    } 
    
    /**
     * Define a data e a hora marcadas no agendamento para o retorno do cliente que vai fazer a manutenção. 
     * 
     * @param dataHora Nova data e hora marcadas para retorno.
     */
    public void setDataHora(Calendar dataHora){
        this.dataHora = dataHora;
    }
    
    public String getStatusAgendamento(){
        return statusAgendamento;
    }
    
    public void setStatusAgendamento(String statusAgendamento){
        this.statusAgendamento = statusAgendamento;
    }
    
    public String retornaTipoAgendamento(int tipoAgendamento){
        if (tipoAgendamento == 1) {
            return "Manutenção"; 
        } 
        else if (tipoAgendamento == 2) {
            return "Revisão";
        }
        else if (tipoAgendamento == 3) {
            return "Outro: ";
        }
        return null;
    }
    
    /**
     * Sobrescreve o método toString para mostrar uma mensagem confirmando o agendamento e dizendo a data e a hora em que foi agendada a manutenção do veículo.
     * 
     * @return Mensagem dizendo a data e a hora para retorno. 
     */
    @Override
    public String toString() {
        
        return "Agendamento realizado. Traga o veícilo no dia" + this.dataHora; 
                
    }
    
}
