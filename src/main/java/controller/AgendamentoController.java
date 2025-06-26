package controller;

import Model.Agendamento;
import Model.AgendamentoDAO;
import View.AgendamentoView;
import java.util.Calendar;
import java.util.List;

/**
 * Controlador responsável por gerenciar as operações relacionadas aos agendamentos, como adicionar
 * , confirmar, cancelar, editar e exibir agendamentos. 
 * Também lida com a interação com o usuário através da classe {@link AgendamentoView}.
 */
public class AgendamentoController {
    
    /** Objeto responsável pela interface de entrada e saída com o usuário para agendamentos. */
    AgendamentoView viewAgendamento = new AgendamentoView();
    
    /** Objeto responsável pelo acesso aos dados pesistidos dos agendamentos. */
    AgendamentoDAO agendamentoDao = new AgendamentoDAO();
    
    /**
     * Converte uma string contendo data e hora no formato "DD MM YYYY HH MM" para um objeto {@link Calendar}
     * 
     * @param strDataHora Um objeto {@link Calendar} representado a data e hora formatadas. 
     * @return NumberFormatException se a string não contiver valores numéricos válidos.
     */
    public static Calendar formataDataHora(String strDataHora){
        String[] dataHoraSplit = strDataHora.split(" ");
        int dia = Integer.parseInt(dataHoraSplit[0]);
        int mes = Integer.parseInt(dataHoraSplit[1]) - 1;  
        int ano = Integer.parseInt(dataHoraSplit[2]);
        int hora = Integer.parseInt(dataHoraSplit[3]);
        int minuto = Integer.parseInt(dataHoraSplit[4]);
        
        Calendar dataHorario = Calendar.getInstance();
        dataHorario.set(ano, mes, dia, hora, minuto, 0);
        
        return dataHorario;
    }
    
    /**
     * Executa o menu principal de agendamentos, permitindo ao usuário realizar diversas operações atráves 
     * do terminal.
     * A execução permanece ativa até que o usuário selecione a opção de "Sair".
     */
    public void executaMenuAgendamento(){
        int opcao = 0; 
        
        while(opcao != 7){
            opcao = viewAgendamento.mostraOpcoesAgendamento();
            
            switch (opcao){
                case 1 -> {
                    agendamentoDao.adicionaAgendamento();
                }
                case 2 -> {
                    agendamentoDao.confirmaAgendamento();
                }
                case 3 -> {
                    agendamentoDao.cancelaAgendamento();
                }
                case 4 -> {
                    agendamentoDao.editaAgendamento();
                }
                case 5 -> {
                    agendamentoDao.mostraVagasDisponiveis();
                }
                
                case 6 -> {
                    agendamentoDao.mostraVagasDisponiveis();
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
    
}
