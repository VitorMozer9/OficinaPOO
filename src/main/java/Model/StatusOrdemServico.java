package Model;

/**
 * Enum que representa os possíveis status de uma Ordem de Serviço.
 */
public enum StatusOrdemServico {
    PENDENTE(1, "Pendente"),
    CANCELADO(2, "Cancelado"),
    CONFIRMADO(3, "Confirmado");
    
    private final int codigo;
    private final String descricao;
    
    StatusOrdemServico(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }
    
    public int getCodigo() {
        return codigo;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Converte um código numérico para o enum correspondente.
     * 
     * @param codigo Código numérico do status
     * @return StatusOrdemServico correspondente ou null se inválido
     */
    public static StatusOrdemServico converteCodigo(int codigo) {
        for (StatusOrdemServico status : StatusOrdemServico.values()) {
            if (status.getCodigo() == codigo) {
                return status;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}