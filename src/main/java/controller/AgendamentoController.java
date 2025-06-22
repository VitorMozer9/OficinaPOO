package controller;

import Model.AgendamentoDAO;
import View.AgendamentoView;
import java.util.Calendar;

public class AgendamentoController {
    AgendamentoView viewAgendamento = new AgendamentoView();
    AgendamentoDAO agendamentoDao = new AgendamentoDAO();
    
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
    
    public void executaMenuAgendamento(){
        int opcao = 0; 
        
        while(opcao != 6){
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
                    agendamentoDao.mostrarAgendamento();
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
    
}
