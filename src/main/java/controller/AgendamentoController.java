package controller;

import View.AgendamentoView;

public class AgendamentoController {
    AgendamentoView viewAgendamento = new AgendamentoView();
    
    public void executaMenuCliente(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewAgendamento.mostraOpcoesAgendamento();
            
            switch (opcao){
//                case 1 -> {
//                    clienteDao.adicionaCliente();
//                }
//                case 2 -> {
//                    clienteDao.editarCliente();
//                }
//                case 3 -> {
//                    clienteDao.removeCliente();
//                }
//                case 4 -> {
//                    clienteDao.mostrarCliente();
//                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
    
}
