package Model;

/**
 * Enum que representa os tipos de conta financeira disponíveis no sistema.
 * Define se uma conta é uma receita ou despesa.
 */
public enum TipoConta {
    RECEITA(1, "Receita"),
    DESPESA(2, "Despesa");
    
    private final int codigo;
    private final String descricao;
    
    /**
     * Construtor do enum TipoConta.
     * 
     * @param codigo Código numérico do tipo de conta
     * @param descricao Descrição textual do tipo de conta
     */
    TipoConta(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    /**
     * Retorna o código numérico do tipo de conta.
     * 
     * @return O código do tipo de conta
     */
    public int getCodigo() {
        return codigo;
    }
    
    /**
     * Retorna a descrição do tipo de conta.
     * 
     * @return A descrição do tipo de conta
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Converte um código numérico para o tipo de conta correspondente.
     * 
     * @param codigo O código a ser convertido
     * @return O TipoConta correspondente ou null se código inválido
     */
    public static TipoConta converteCodigo(int codigo) {
        for (TipoConta tipo : TipoConta.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        return null;
    }
    
    /**
     * Retorna uma representação em String do tipo de conta.
     * 
     * @return String com código e descrição
     */
    @Override
    public String toString() {
        return codigo + " - " + descricao;
    }
}
