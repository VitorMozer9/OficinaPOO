package controller;

import Model.BatePontoDAO;
import View.BatePontoView;

public class BatePontoController {
    private BatePontoView viewBatePonto = new BatePontoView();   
    private BatePontoDAO batePontoDao = new BatePontoDAO();
     
    /**
     * Exibe o menu de opções para os pontos e executa a ação solicitada pelo usuário.
     * O menu permanece ativo até que o usuário selecione a opção de sair.
     */
    public void executaMenuPonto(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewBatePonto.mostraOpcoesBatePonto();
            
            if (opcao == 5) {
                System.out.println("Saindo do menu de pontos...");
                break; 
            }
            
            switch (opcao){
                case 1 -> {
                    batePontoDao.registrarBatidaPonto();
                }
                case 2 -> {
                    batePontoDao.editaRegistroPonto();
                }
                case 3 -> {
                    batePontoDao.removeRegistroPonto();
                }
                case 4 -> {
                    batePontoDao.mostrarRegistroPonto();
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
}
