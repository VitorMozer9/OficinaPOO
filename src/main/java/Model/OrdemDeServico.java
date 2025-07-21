package Model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe que representa uma Ordem de Serviço na oficina.
 * Contém todas as informações necessárias para o controle de serviços.
 */
public class OrdemDeServico {
    private int idOrdemServico;
    private int idCliente;
    private String mecanicoResponsavel;
    private Calendar data;
    private StatusOrdemServico status;
    private TipoServico tipoServico;
    private String descricaoServico;
    private double valor;
    private List<String> produtos;
    private double valorProdutos;
    
    /**
     * Construtor completo da Ordem de Serviço.
     */
    public OrdemDeServico(int idOrdemServico, int idCliente, String mecanicoResponsavel,
                         Calendar data, StatusOrdemServico status, TipoServico tipoServico,
                         String descricaoServico, double valor) {
        this.idOrdemServico = idOrdemServico;
        this.idCliente = idCliente;
        this.mecanicoResponsavel = mecanicoResponsavel;
        this.data = data;
        this.status = status;
        this.tipoServico = tipoServico;
        this.descricaoServico = descricaoServico;
        this.valor = valor;
        this.produtos = new ArrayList<>();
        this.valorProdutos = 0.0;
    }
    
    /**
     * Construtor para serviços de troca de peças.
     */
    public OrdemDeServico(int idOrdemServico, int idCliente, String mecanicoResponsavel,
                         Calendar data, StatusOrdemServico status, TipoServico tipoServico,
                         String descricaoServico, double valor, List<String> produtos, double valorProdutos) {
        this(idOrdemServico, idCliente, mecanicoResponsavel, data, status, tipoServico, descricaoServico, valor);
        this.produtos = produtos != null ? new ArrayList<>(produtos) : new ArrayList<>();
        this.valorProdutos = valorProdutos;
    }
    
    public int getIdOrdemServico() {
        return idOrdemServico;
    }
    
    public int getIdCliente() {
        return idCliente;
    }
    
    public String getMecanicoResponsavel() {
        return mecanicoResponsavel;
    }
    
    public Calendar getData() {
        return data;
    }
    
    public StatusOrdemServico getStatus() {
        return status;
    }
    
    public TipoServico getTipoServico() {
        return tipoServico;
    }
    
    public String getDescricaoServico() {
        return descricaoServico;
    }
    
    public double getValor() {
        return valor;
    }
    
    public List<String> getProdutos() {
        return new ArrayList<>(produtos);
    }
    
    public double getValorProdutos() {
        return valorProdutos;
    }
    
    /**
     * Calcula o valor total da ordem de serviço (serviço + produtos).
     * 
     * @return Valor total
     */
    public double getValorTotal() {
        return valor + valorProdutos;
    }
    
    public void setIdOrdemServico(int idOrdemServico) {
        this.idOrdemServico = idOrdemServico;
    }
    
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    public void setMecanicoResponsavel(String mecanicoResponsavel) {
        this.mecanicoResponsavel = mecanicoResponsavel;
    }
    
    public void setData(Calendar data) {
        this.data = data;
    }
    
    public void setStatus(StatusOrdemServico status) {
        this.status = status;
    }
    
    public void setTipoServico(TipoServico tipoServico) {
        this.tipoServico = tipoServico;
    }
    
    public void setDescricaoServico(String descricaoServico) {
        this.descricaoServico = descricaoServico;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public void setProdutos(List<String> produtos) {
        this.produtos = produtos != null ? new ArrayList<>(produtos) : new ArrayList<>();
    }
    
    public void setValorProdutos(double valorProdutos) {
        this.valorProdutos = valorProdutos;
    }
    
    /**
     * Adiciona um produto à lista de produtos.
     * 
     * @param produto Nome do produto
     */
    public void adicionarProduto(String produto) {
        if (produto != null) {
            this.produtos.add(produto.trim());
        }
    }
    
    /**
     * Remove um produto da lista de produtos.
     * 
     * @param produto Nome do produto a ser removido
     * @return true se o produto foi removido, false caso contrário
     */
    public boolean removerProduto(String produto) {
        return this.produtos.remove(produto);
    }
    
    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        StringBuilder sb = new StringBuilder();
        
        sb.append("============================================================\n");
        sb.append("                    ORDEM DE SERVIÇO #").append(idOrdemServico).append("\n");
        sb.append("============================================================\n");
        sb.append("Cliente ID: ").append(idCliente).append("\n");
        sb.append("Mecânico Responsável: ").append(mecanicoResponsavel).append("\n");
        sb.append("Data: ").append(formato.format(data.getTime())).append("\n");
        sb.append("Status: ").append(status.getDescricao()).append("\n");
        sb.append("------------------------------------------------------------\n");
        sb.append("SERVIÇO:\n");
        sb.append("Tipo: ").append(tipoServico.getDescricao()).append("\n");
        sb.append("Descrição: ").append(descricaoServico).append("\n");
        sb.append("Valor do Serviço: R$ ").append(String.format("%.2f", valor)).append("\n");
        
        if (tipoServico == TipoServico.TROCA_PECA && !produtos.isEmpty()) {
            sb.append("------------------------------------------------------------\n");
            sb.append("PRODUTOS/PEÇAS:\n");
            for (String produto : produtos) {
                sb.append("- ").append(produto).append("\n");
            }
            sb.append("Valor dos Produtos: R$ ").append(String.format("%.2f", valorProdutos)).append("\n");
        }
        
        sb.append("------------------------------------------------------------\n");
        sb.append("VALOR TOTAL: R$ ").append(String.format("%.2f", getValorTotal())).append("\n");
        sb.append("============================================================\n");
        
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        OrdemDeServico that = (OrdemDeServico) obj;
        return idOrdemServico == that.idOrdemServico;
    }
    
    @Override
    public int hashCode() {
        return Integer.hashCode(idOrdemServico);
    }
}