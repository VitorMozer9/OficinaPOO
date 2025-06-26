package Model;

import java.util.Calendar;

/**
 * Representa uma venda avulsa realizada na loja da oficina.
 * Cada venda é identificada por um ID único e contém informações sobre o cliente, produto e valores.
 */
public class Venda {
    private int idVenda;
    private int idCliente;
    private int idProduto;
    private int quantidade;
    private double valorUnitario;
    private double valorTotal;
    
    /**
     * Construtor da classe Venda.
     * 
     * @param idVenda       ID único da venda
     * @param idCliente     ID do cliente que realizou a compra
     * @param idProduto     ID do produto vendido
     * @param quantidade    Quantidade do produto vendida
     * @param valorUnitario Valor unitário do produto no momento da venda
     */
    public Venda(int idVenda, int idCliente, int idProduto, int quantidade, double valorUnitario, double valorTotal) {
        //super(idCliente, nome, tipo, valor, data);
        this.idVenda = idVenda;
        this.idCliente = idCliente;
        this.idProduto = idProduto;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.valorTotal = quantidade * valorUnitario;
    }
    
    /**
     * Obtém o ID da venda.
     * 
     * @return ID da venda
     */
    public int getIdVenda() {
        return idVenda;
    }
    
    /**
     * Define o ID da venda.
     * 
     * @param idVenda Novo ID da venda
     */
    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }
    
    /**
     * Obtém o ID do cliente.
     * 
     * @return ID do cliente
     */
    public int getIdCliente() {
        return idCliente;
    }
    
    /**
     * Define o ID do cliente.
     * 
     * @param idCliente Novo ID do cliente
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    /**
     * Obtém o ID do produto.
     * 
     * @return ID do produto
     */
    public int getIdProduto() {
        return idProduto;
    }
    
    /**
     * Define o ID do produto.
     * 
     * @param idProduto Novo ID do produto
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }
    
    /**
     * Obtém a quantidade vendida.
     * 
     * @return Quantidade do produto vendida
     */
    public int getQuantidade() {
        return quantidade;
    }
    
    /**
     * Define a quantidade vendida.
     * 
     * @param quantidade Nova quantidade
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
        this.valorTotal = quantidade * valorUnitario; // Recalcula o valor total
    }
    
    /**
     * Obtém o valor unitário do produto.
     * 
     * @return Valor unitário
     */
    public double getValorUnitario() {
        return valorUnitario;
    }
    
    /**
     * Define o valor unitário do produto.
     * 
     * @param valorUnitario Novo valor unitário
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
        this.valorTotal = quantidade * valorUnitario; // Recalcula o valor total
    }
    
    /**
     * Obtém o valor total da venda.
     * 
     * @return Valor total da venda
     */
    public double getValorTotal() {
        return valorTotal;
    }
    
    /**
     * Sobrescrita do método toString para uma representação textual da venda.
     * 
     * @return Descrição da venda
     */
    @Override
    public String toString() {
        return "ID da Venda: " + getIdVenda();
    }
}