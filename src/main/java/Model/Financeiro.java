package Model;

import java.util.Calendar;

/**
 * Classe que representa uma conta financeira, que pode ser de diferentes tipos (receita ou despesa),
 * armazenando informações como ID da conta, descrição, tipo de conta, valor e data.
 */
public class Financeiro {
    private int idConta;
    private String descricao;
    private TipoConta tipoConta;
    private double valor;
    private Calendar data;
    
    /**
     * Construtor que cria uma nova instância de Financeiro.
     * 
     * @param idConta ID único da conta
     * @param descricao Descrição da conta
     * @param tipoConta Tipo da conta (RECEITA ou DESPESA)
     * @param valor Valor da conta
     * @param data Data da conta
     */
    public Financeiro(int idConta, String descricao, TipoConta tipoConta, double valor, Calendar data) {
        this.idConta = idConta;
        this.descricao = descricao;
        this.tipoConta = tipoConta;
        this.valor = valor;
        this.data = data;
    }
    
    /**
     * Retorna o ID da conta.
     * 
     * @return O ID da conta
     */
    public int getIdConta() {
        return idConta;
    }
    
    /**
     * Define o ID da conta.
     * 
     * @param idConta O novo ID da conta
     */
    public void setIdConta(int idConta) {
        this.idConta = idConta;
    }
    
    /**
     * Retorna a descrição da conta.
     * 
     * @return A descrição da conta
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Define a descrição da conta.
     * 
     * @param descricao A nova descrição da conta
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Retorna o tipo da conta.
     * 
     * @return O tipo da conta
     */
    public TipoConta getTipoConta() {
        return tipoConta;
    }
    
    /**
     * Define o tipo da conta.
     * 
     * @param tipoConta O novo tipo da conta
     */
    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }
    
    /**
     * Retorna o valor da conta.
     * 
     * @return O valor da conta
     */
    public double getValor() {
        return valor;
    }
    
    /**
     * Define o valor da conta.
     * 
     * @param valor O novo valor da conta
     */
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    /**
     * Retorna a data da conta.
     * 
     * @return A data da conta
     */
    public Calendar getData() {
        return data;
    }
    
    /**
     * Define a data da conta.
     * 
     * @param data A nova data da conta
     */
    public void setData(Calendar data) {
        this.data = data;
    }
    
    /**
     * Retorna uma representação em String desta conta financeira.
     * O formato inclui ID, descrição, tipo, valor e data.
     * 
     * @return Uma representação em String da conta
     */
    @Override
    public String toString() {
        return String.format("ID: %d | Descrição: %s | Tipo: %s | Valor: R$ %.2f | Data: %02d/%02d/%d",
                idConta, descricao, tipoConta.getDescricao(), valor,
                data.get(Calendar.DAY_OF_MONTH),
                data.get(Calendar.MONTH) + 1,
                data.get(Calendar.YEAR));
    }
}