package controller;

import Model.VeiculoDAO;
import View.VeiculoView;

/**
 * Classe responsável pela gerência dos veículos da oficina.
 * Fornece métodos para adicionar e executar um menu para veículos.
 */
public class VeiculoController {
    private VeiculoView viewVeiculo = new VeiculoView();
    private VeiculoDAO veiculoDao = VeiculoDAO.getInstancia();
     
    /**
     * Exibe um menu de opções do veículo e executa a ação solicitada pelo usuário.
     * O menu permanece até que o usuário solicite a opção de sair.
     */
    public void executaMenuVeiculo(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewVeiculo.mostraOpcoesVeiculo();
            
            if (opcao == 5) {
                System.out.println("Saindo do menu de veículos...");
                break; 
            }
            
            switch (opcao){
                case 1 -> {
                    veiculoDao.adicionaVeiculo();
                }
                case 2 -> {
                    veiculoDao.editarVeiculo();
                }
                case 3 -> {
                    veiculoDao.removeVeiculo();
                }
                case 4 -> {
                    veiculoDao.mostrarVeiculo();
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                   }
                }
            }
        }
    
    @Override
    public String toString(){
        return String.format("VeiculoController: %d veículos registrados.", veiculoDao.getLista().size());
    }
    
}
