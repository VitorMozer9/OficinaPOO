package Model;

/**
 * Representação das peças, identificadas por um número, contidas no estoque da loja da oficina, que irão ser vendidas ou utilizadas para o reparo dos Veículos que estão nela.
 */
public class Pecas {
    private int idPeca;
    private String descricao;
    private double valorPeca;
    private int quantidadeEmEstoque;
    private boolean disponivel;
    
    public static int ultimoIdPeca = 0;
    
    /**
     * Construtor da classe (@code Pecas).
     * Innicia uma nova verificação da peça no estoque, mostrando seu código, valor, quantidade no estoque, se ainda há no estoque e uma breve descrição sobre ela.
     * 
     * @param idPeca                Código de identificação da peça.
     * @param descricao             Descrição da peça.
     * @param valorPeca             Valor da peça.
     * @param quantidadeEmEstoque   Quantidade existente da peça no estoque.
     * @param disponivel            Verificação de disponibilidade da peça.
     */
    public Pecas(int idPeca, String descricao, double valorPeca, int quantidadeEmEstoque,boolean disponivel){
        this.idPeca = ++ultimoIdPeca;
        this.descricao = descricao;
        this.valorPeca = valorPeca;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.disponivel = disponivel;
        
    }
    
    /**
     * Obtém o número de identificação da peça.
     * 
     * @return Código da peça.
     */
    public int getIdPeca(){
        return idPeca;
    }
    
    /**
     * Define o número de identificação da peça.
     * 
     * @param idPeca Novo número de uma nova peça.
     */
    public void setIdPeca(int idPeca){
        this.idPeca = idPeca;
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
    public double getValorPeca(){
        return valorPeca;
    }
    
    /**
     * Define o valor de uma peça.
     * 
     * @param valorPeca Valor para uma nova peça.
     */
    public void setValorPeca(double valorPeca){
        this.valorPeca = valorPeca;
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
