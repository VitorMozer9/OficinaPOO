package controller;

import Model.VendaDAO;
import View.VendaAvulsaView;

public class VendaController {
    private VendaAvulsaView viewVenda = new VendaAvulsaView();
    private VendaDAO vendaDao = VendaDAO.getInstancia();
     
    /**
     * Exibe o menu de opções para as vendas e executa a ação solicitada pelo usuário.
     * O menu permanece ativo até que o usuário selecione a opção de sair.
     */
    public void executaMenuVendas(){
        int opcao = 0; 
        
        while(opcao != 6){
            opcao = viewVenda.mostraOpcoesVenda();
            
            switch (opcao){
                case 1 -> {
                    vendaDao.mostraCatalogo();
                }
                case 2 -> {
                    vendaDao.realizarVenda();
                }
                case 3 -> {
                    vendaDao.consultarVenda();
                }
                case 4 -> {
                    vendaDao.listarVendas();
                }
                case 5 -> {
                    vendaDao.cancelarVenda();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
}
