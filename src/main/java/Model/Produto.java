package Model;

/**
 * Representação das peças, identificadas por um número, contidas no estoque da loja da oficina, que irão ser vendidas ou utilizadas para o reparo dos Veículos que estão nela.
 */
public class Produto {
    private int idProduto;
    private String descricao;
    private double valorProduto;
    private int quantidadeEmEstoque;
    private boolean disponivel;
    
    /**
     * Construtor da classe (@code Pecas).Innicia uma nova verificação da peça no estoque, mostrando seu código, valor, quantidade no estoque, se ainda há no estoque e uma breve descrição sobre ela.
     * 
     * @param idPeca                Código de identificação da peça.
     * @param descricao             Descrição da peça.
     * @param valorProduto             Valor da peça.
     * @param quantidadeEmEstoque   Quantidade existente da peça no estoque.
     * @param disponivel            Verificação de disponibilidade da peça.
     */
    public Produto(int idProduto, String descricao, double valorProduto, int quantidadeEmEstoque,boolean disponivel){
        this.idProduto = idProduto;
        this.descricao = descricao;
        this.valorProduto = valorProduto;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.disponivel = disponivel;
        
    }
    
    /**
     * Obtém o número de identificação da peça.
     * 
     * @return Código da peça.
     */
    public int getIdProduto(){
        return idProduto;
    }
    
    /**
     * Define o número de identificação da peça.
     * 
     * @param idProduto Novo número de uma nova peça.
     */
    public void setIdProduto(int idProduto){
        this.idProduto = idProduto;
    }
    
    /**
     * Obtém uma breve descrição da peça.
     * 
     * @return Descrição da peça.
     */
    public String getDescicao(){
        return descricao;
    }
    
    /**
     * Define uma descrição para a peça.
     * 
     * @param descricao Descrição para uma nova peça.
     */
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    /**
     * Obtém o valor da peça.
     * 
     * @return Valor da peça.
     */
    public double getValorProduto(){
        return valorProduto;
    }
    
    /**
     * Define o valor de uma peça.
     * 
     * @param valorProduto Valor para uma nova peça.
     */
    public void setValorProduto(double valorProduto){
        this.valorProduto = valorProduto;
    }
    
    /**
     * Obtém a quantidade no estoque de uma determinada peça.
     * 
     * @return Quantidade da peça no estoque.
     */
    public int getQuantidadeEmEstoque(){
        return quantidadeEmEstoque;
    }
    
    /**
     * Define a quantidade em estoque de uma peça.
     * 
     * @param quantidadeEmEstoque  Nova quantidade de peças no estoque.
     */
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque){
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    
    /**
     * Verifica se a peça está no estoque ou não
     * 
     * @return Disponível ou Não disponível.
     */
    public boolean getDisponivel(){
        return disponivel;
    }
    
    /**
     * Define se uma peça está disponível no estoque ou não.
     * 
     * @param disponivel Nova peça disponível ou nova peça não disponível.
     */
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }
    
    /**
     * Sobrescrição do método toString para retornar uma breve descriçõ da peça, para ficar mais fácil a compreensão de que peça se trata e suas funcionalidades.
     * 
     * @return Descrição da peça.
     */
    @Override
    public String toString(){
        return descricao;
    }
}
