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
    
    /**
     * Gera um novo ID para um produto, baseado no maior ID já registrado no sistema.
     * Isso garante que cada novo produto tenha um ID único.
     * * @return O novo ID do produto, incrementado em relação ao maior ID atual.
     */
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
    
     /**
     * Busca um produto no sistema com base no ID informado.
     *
     * @param idProduto O ID do produto a ser buscado.
     * @return O objeto (@link Produto) relacionado ao ID fornecido, ou (@code null) se nenhum produto for encontrado
     * ou se ocorrer uma falha durante a busca.
     */
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
    
     /**
     * Solicita um ID de produto ao usuário através da (@link ProdutoEstoqueView), busca o produto correspondente e exibe
     * suas informações detalhadas.
     * Se o produto não for encontrado, uma mensagem de "Produto não encontrada!!" é exibida.
     */
    public void mostrarProduto(){
        int idProduto = viewProduto.getIdProduto();
        Produto produto = buscaProduto(idProduto);
        
        if(produto == null){
            System.out.println("Produto não encontrada!!");
            return;
        }
        
        viewProduto.mostraProduto(produto);
    }
    
    /**
     * Remove um produto do sistema.
     * Primeiro, solicita o ID do produto, busca-o, e exibe suas informações para confirmação.
     * Após a confirmação do usuário, o produto é removido da lista de produtos e os dados são salvos.
     * Exibe mensagens de erro, confirmação ou de aborto da operação.
     */
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
    
     /**
     * Permite a edição de dados de um produto existente. 
     * Primeiro, solicita o ID do produto, busca-o e exibe seus dados atuais.
     * Em seguida, um menu de opções de edição é apresentado e, de acordo com a escolha do usuário, 
     * os dados específicos (descrição ou valor) são atualizados.
     * As alterações são persistidas no sistema.
     * Exibe uma mensagem se o produto não for encontrado ou se ocorrer um erro ao salvar as alterações.
     */
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
    
     /**
     * Edita a descrição de um produto específico.
     *
     * @param produto O objeto (@link Produto) cujos dados serão atualizados.
     * @param novaDescricao A nova descrição a ser atribuída ao produto.
     */
    public void editaDesc(Produto produto, String novaDescricao){
        produto.setDescricao(novaDescricao);
    }
    
      /**
     * Edita o valor de um produto específico.
     *
     * @param produto O objeto (@link Produto) cujos dados serão atualizados.
     * @param novoValor O novo valor a ser atribuído ao produto.
     */
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
