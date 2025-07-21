package Model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Enum que representa os tipos de batida de ponto disponíveis no sistema.
 * Permite registrar entrada e saída dos funcionários, capturando automaticamente
 * o horário atual da batida.
 */
public enum BatidaDePonto {
    ENTRADA("Entrada"),
    SAIDA("Saída");
    
    private final String descricao;
    
    /**
     * Construtor do enum BatidaDePonto.
     * 
     * @param descricao Descrição textual do tipo de batida.
     */
    BatidaDePonto(String descricao) {
        this.descricao = descricao;
    }
    
    /**
     * Obtém a descrição do tipo de batida.
     * 
     * @return Descrição da batida de ponto.
     */
    public String getDescricao() {
        return descricao;
    }
    
    /**
     * Captura o horário atual da batida de ponto.
     * 
     * @return Data e hora atual formatada como LocalDateTime.
     */
    public LocalDateTime capturarHorarioBatida() {
        return LocalDateTime.now();
    }
    
    /**
     * Retorna o horário formatado para exibição.
     * 
     * @param dataHora Data e hora a ser formatada.
     * @return String formatada da data e hora (dd/MM/yyyy HH:mm:ss).
     */
    public static String formatarHorario(LocalDateTime dataHora) {
        if (dataHora == null) return "Não registrado";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return dataHora.format(formatter);
    }
}