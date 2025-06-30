package controller;

import Model.VendaDAO;
import View.VendaAvulsaView;

/**
 * Controlador responsável por gerenciar as interações relacionadas ao processo de vendas avulsas.
 * Ele atua como intermediário entre a {@link VendaAvulsaView} (interface do usuário) e o {@link VendaDAO} (acesso a dados de vendas). 
 * @author Estudo
 */
public class VendaController {
    
    /** Interface responsável por capturar e exibir informações da venda para o usuário. */
    private VendaAvulsaView viewVenda = new VendaAvulsaView();
    
    /** Objeto DAO responsável por persistir e manipular os dados de venda. */
    private VendaDAO vendaDao = VendaDAO.getInstancia();
     
    /**
     * Exibe o menu de opções para as vendas e executa a ação solicitada pelo usuário.
     * O menu permanece ativo até que o usuário selecione a opção de sair.
     */
    public void executaMenuVendas(){
        int opcao = 0; 
        
        while(opcao != 7){
            opcao = viewVenda.mostraOpcoesVenda();
            
            if (opcao == 7) {
                System.out.println("Saindo do menu de vendas...");
                break; 
            }
            
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
                case 6 -> {
                    vendaDao.geraNotaFiscalVenda();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
}
