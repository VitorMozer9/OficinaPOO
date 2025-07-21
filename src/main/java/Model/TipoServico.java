package Model;

/**
 * Enum que representa os tipos de serviços disponíveis na oficina.
 */
public enum TipoServico {
    MANUTENCAO(1, "Manutenção"),
    REVISAO(2, "Revisão"),
    TROCA_PECA(3, "Troca de Peça"),
    OUTRO(4, "Outro");
    
    private final int codigo;
    private final String descricao;
    
    TipoServico(int codigo, String descricao) {
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
     * @param codigo Código numérico do tipo de serviço
     * @return TipoServico correspondente ou null se inválido
     */
    public static TipoServico converteCodigo(int codigo) {
        for (TipoServico tipo : TipoServico.values()) {
            if (tipo.getCodigo() == codigo) {
                return tipo;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
}