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
    private ProdutoEstoqueDAO produtoEstoqueDao = new ProdutoEstoqueDAO();
    
    /**
     * Gera um novo ID para um produto, baseado no maior ID já registrado no sistema.
     * Isso garante que cada novo produto tenha um ID único.
     * * @return O novo ID do produto, incrementado em relação ao maior ID atual.
     */
    public static int geraIdProduto(){
        int maiorIdProduto = 0;
        for(Produto cadaPeca : OficinaPOO.getInstancia().getProdutos()){
            if(cadaPeca.getIdProduto() > maiorIdProduto){
                maiorIdProduto = cadaPeca.getIdProduto();
            }
            
        }
        return maiorIdProduto + 1;
    }
     
    /**
     * Exibe o menu de opções para as Peças e executa a ação solicitada pelo usuário.
     * O menu permanece ativo até que o usuário selecione a opção de sair.
     */
    public void executaMenuProdutos(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewProduto.mostraOpcoesProduto();
            
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
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
}
