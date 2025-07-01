package controller;

import Model.OrdemDeServicoDAO;
import View.OrdemDeServicoView;

public class OrdemDeServicoController {
    private OrdemDeServicoView viewOS = new OrdemDeServicoView();
    private OrdemDeServicoDAO osDao = OrdemDeServicoDAO.getInstancia();
     
    /**
     * Exibe o menu de opções para as vendas e executa a ação solicitada pelo usuário.
     * O menu permanece ativo até que o usuário selecione a opção de sair.
     */
    public void executaMenuOrdemDeServico(){
        int opcao = 0; 
        
        while(opcao != 9){
            opcao = viewOS.mostraOpcoesOrdemServico();
            
            if (opcao == 9) {
                System.out.println("Saindo do meno de Ordem de Serviço...");
                break; 
            }
            
            switch (opcao){
                case 1 -> {
                    osDao.AdicionaOrdemServico();
                }
                case 2 -> {
                    osDao.confirmarOrdemServico();
                }
                case 3 -> {
                    osDao.cancelarOrdemServico();
                }
                case 4 -> {
                    osDao.editarOrdemServico();
                }
                case 5 -> {
                    osDao.mostrarOrdemServico();
                }
                case 6 -> {
                    osDao.gerarNotaFiscal();
                }
                case 7 -> {
                    osDao.listarOrdensServico();
                }
                case 8 -> {
                    osDao.mostraOsIdCliente();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
    @Override 
    public String toString(){
        return String.format("OrdemDeServicoController: %d OS registrados.", osDao.getLista().size());
    }
    
}
