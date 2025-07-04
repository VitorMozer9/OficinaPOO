package controller;

import Main.OficinaPOO;
import Model.Produto;
import Model.ProdutoEstoqueDAO;
import View.ProdutoEstoqueView;

/**
 * Classe responsável pela gerência das peças da oficina.
 * Fornecendo métodos para adicionar e acessar o menu de opções de peças.
 */
public class ProdutoEstoqueController {
    private ProdutoEstoqueView viewProduto = new ProdutoEstoqueView();
    private ProdutoEstoqueDAO produtoEstoqueDao = ProdutoEstoqueDAO.getInstancia();
     
    /**
     * Exibe o menu de opções para as Peças e executa a ação solicitada pelo usuário.
     * O menu permanece ativo até que o usuário selecione a opção de sair.
     */
    public void executaMenuProdutos(){
        int opcao = 0; 
        
        while(opcao != 7){
            opcao = viewProduto.mostraOpcoesProduto();
            
            if (opcao == 7) {
                System.out.println("Saindo do controle de estoque...");
                break; 
            }
            
            switch (opcao){
                case 1 -> {
                    produtoEstoqueDao.adicionaProduto();
                }
                case 2 -> {
                    produtoEstoqueDao.editaProduto();
                }
                case 3 -> {
                    produtoEstoqueDao.removeProduto();
                }
                case 4 -> {
                    produtoEstoqueDao.mostrarProduto();
                }
                case 5 -> {
                    produtoEstoqueDao.alteraQuantidadeProduto();
                }
                case 6 -> {
                    produtoEstoqueDao.mostraEstoqueCompleto();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
}
