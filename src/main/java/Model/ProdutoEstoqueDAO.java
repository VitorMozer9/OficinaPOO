package Model;

import Main.OficinaPOO;
import View.ProdutoEstoqueView;
import controller.ProdutoEstoqueController;

public class ProdutoEstoqueDAO {
    private ProdutoEstoqueView viewProduto = new ProdutoEstoqueView();
    
    /**
     * Coleta as informações sobre a peça a partir da view, adicionando a nova peça ao sistema.
     * Exibe uma mensagem se a peça foi adicionada com sucesso.
     */
    public void adicionaProduto(){
        int idProduto = ProdutoEstoqueController.geraIdProduto();
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
    
    
}
