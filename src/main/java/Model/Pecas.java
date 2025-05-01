package Model;

public class Pecas {
    private int idPeca;
    private String descricao;
    private double valorPeca;
    private int quantidadeEmEstoque;
    private boolean disponivel;
    
    public static int ultimoIdPeca = 0;
    
    public Pecas(int idPeca, String descricao, double valorPeca, int quantidadeEmEstoque,boolean disponivel){
        this.idPeca = ++ultimoIdPeca;
        this.descricao = descricao;
        this.valorPeca = valorPeca;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.disponivel = disponivel;
        
    }
    
    public int getIdPeca(){
        return idPeca;
    }
    
    public void setIdPeca(int idPeca){
        this.idPeca = idPeca;
    }
    
    public String getDescicao(){
        return descricao;
    }
    
    public void setDescricao(String descricao){
        this.descricao = descricao;
    }
    
    public double getValorPeca(){
        return valorPeca;
    }
    
    public void setValorPeca(double valorPeca){
        this.valorPeca = valorPeca;
    }
    
    public int getQuantidadeEmEstoque(){
        return quantidadeEmEstoque;
    }
    
    public void setQuantidadeEmEstoque(int quantidadeEmEstoque){
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    
    public boolean getDisponivel(){
        return disponivel;
    }
    
    public void setDisponivel(boolean disponivel){
        this.disponivel = disponivel;
    }
    
    @Override
    public String toString(){
        return descricao;
    }
}
