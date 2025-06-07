package Model;

import java.util.Calendar;

/**
 * Classe que representa uma conta, que pode ser de diferentes tipos, associada a um cliente.
 * Armazenando informações como ID do cliente, nome, tipo de conta, valor e data.
 */
public class Conta {
    private int idCliente;
    private String nome;
    private String tipo;
    private double valor;
    private Calendar data;
    
    
    /**
     * Construtor que cria uma nova instância de @Code Conta.
     * 
     * @param idCliente
     * @param nome
     * @param tipo
     * @param valor
     * @param data 
     */
    public Conta(int idCliente, String nome, String tipo, double valor, Calendar data){
        this.idCliente = idCliente;
        this.nome = nome;
        this.tipo = tipo;
        this.valor = valor;
        this.data = data;
    }
    
    /**
     * Retorna o ID do cliente associado a essa conta. 
     * 
     * @return O ID do cliente.
     */
    public int getIdCliente(){
        return idCliente;
    }
    
    /**
     * Define o ID do cliente para esta conta
     * 
     * @param idCliente O novo ID do cliente.
     */
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    /**
     * Retorna o nome do dono da conta.
     * 
     * @return O nome do dono da conta.
     */
    public String getNome(){
        return nome;
    }
    
    /**
     * Define o nome do dono da conta
     * 
     * @param nome O novo nome do dono da conta.
     */
    public void setNome(String nome){
        this.nome = nome;
    }
    
    /**
     * Retorna o tipo da conta.
     * 
     * @return O tipo da conta.
     */
    public String getTipo(){
        return tipo;
    }
    
    /**
     * Define o tipo da conta.
     * 
     * @param tipo O novo tipo da conta.
     */
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    /**
     * Retorna o valor atual associado a essa conta.
     * 
     * @return O valor da conta.
     */
    public double getValor(){
        return valor;
    }
    
    /**
     * Define o valor para essa conta.
     * 
     * @param valor O valor da conta.
     */
    public void setValor(double valor){
        this.valor = valor;
    }
    
    /**
     * Retorna a data associada a essa conta.
     * 
     * @return A data da conta.
     */
    public Calendar getData(){
        return data;
    }
    
    /**
     * Define a data para essa conta.
     * 
     * @param data A noca data da conta.
     */
    public void setData(Calendar data){
        this.data = data;
    }
    
    /**
     * Retorna uma representação em String desta conta.
     * O formato da String é "Conta: [nome da conta] Valor: [valor da conta]".
     * 
     * @return Uma representação em String da conta.
     */
    @Override
    public String toString(){
        return "Conta: " + getNome() + " Valor: " + getValor();
    }
    
}
