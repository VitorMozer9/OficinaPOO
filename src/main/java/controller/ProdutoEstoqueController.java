package controller;

import Main.OficinaPOO;
import Model.Produto;
import Model.Veiculo;
import View.ProdutoEstoqueView;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela gerência das peças da oficina.
 * Fornecendo métodos para adicionar e acessar o menu de opções de peças.
 */
public class ProdutoEstoqueController {
    private ProdutoEstoqueView viewProduto = new ProdutoEstoqueView();
    
    public int geraIdProduto(){
        int maiorIdProduto = 0;
        for(Produto cadaPeca : OficinaPOO.getInstancia().getProdutos()){
            if(cadaPeca.getIdProduto() > maiorIdProduto){
                maiorIdProduto = cadaPeca.getIdProduto();
            }
            
        }
        return maiorIdProduto + 1;
    }
    
    /**
     * Coleta as informações sobre a peça a partir da view, adicionando a nova peça ao sistema.
     * Exibe uma mensagem se a peça foi adicionada com sucesso.
     */
    public void adicionaProduto(){
        int idProduto = geraIdProduto();
        String descricao = viewProduto.getDescricao();
        double valor = viewProduto.getValorProduto();
        int quantidade = viewProduto.getQuantidadeProduto();

        Produto novaPeca = new Produto(idProduto,descricao,valor,quantidade,true);
        OficinaPOO.getInstancia().addProduto(novaPeca);
        System.out.println("Produto adicionada com sucesso!" + "ID: " + idProduto);
         
    }
    
    public Produto buscaProduto(int idProduto){
        try{
            for(Produto cadaProduto : OficinaPOO.getInstancia().getProdutos()){
                if(cadaProduto.getIdProduto() == idProduto){
                    return cadaProduto;
                }
            }
            
        }
        catch(Exception e){
            System.out.println("Falha ao buscar produto");
            return null;
        }
        return null;
    }
    
    public void mostrarProduto(){
        int idProduto = viewProduto.getIdProduto();
        Produto produto = buscaProduto(idProduto);
        
        if(produto == null){
            System.out.println("Produto não encontrada!!");
            return;
        }
        
        viewProduto.mostraProduto(produto);
    }
    
    public void removeProduto(){
        int idProduto = viewProduto.getIdProduto();
        Produto produto = buscaProduto(idProduto);
        
        if (produto == null){
            System.out.println("Produto não encontrado!");
            return;
        }
        
        viewProduto.mostraProduto(produto);
        
        String opcaoConfirmacao = viewProduto.confirmaExclusaoProduto();
        if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação abortada!!");
            return;
        }
        
        OficinaPOO.getInstancia().getProdutos().remove(produto);
        
        if(OficinaPOO.salvarDados(OficinaPOO.getInstancia())){
            System.out.println("Produto removida com sucesso!");
        }else{
            System.out.println("Falha ao remover produto! :(");
        }
    }
    
    public void editaProduto(){
        int idProduto = viewProduto.getIdProduto();
        Produto produto = buscaProduto(idProduto);
        
        if (produto == null){
            System.out.println("Produto não encontrado!");
            return;
        }
        
        viewProduto.mostraProduto(produto);
        
        
        int opcaoModPeca = viewProduto.editaProduto();
        
        switch(opcaoModPeca){
            case 1 -> {
                String novaDescricao = viewProduto.getDescricao();
                editaDesc(produto, novaDescricao);
            }
            case 2 -> {
                double novoValor = viewProduto.getValorProduto();
                editaValor(produto, novoValor);
            }    
    
    
        }
        
        if (OficinaPOO.salvarDados(OficinaPOO.getInstancia())) {
            System.out.println("Alterações salvas com sucesso.");
        } else {
            System.out.println("Erro ao salvar alterações.");
        }
    }
    
    public void editaDesc(Produto produto, String novaDescricao){
        produto.setDescricao(novaDescricao);
    }
    
    public void editaValor(Produto produto, double novoValor){
        produto.setValorProduto(novoValor);
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
                    adicionaProduto();
                }
                case 2 -> {
                    editaProduto();
                }
                case 3 -> {
                    removeProduto();
                }
                case 4 -> {
                    mostrarProduto();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
}
