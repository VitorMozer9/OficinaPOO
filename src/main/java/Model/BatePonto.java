package Model;

import java.time.LocalDateTime;
import java.time.Duration;

/**
 * Representa um registro de batida de ponto no sistema.
 * Armazena informações sobre entrada, saída e total de horas trabalhadas
 * por um funcionário em um determinado período.
 */
public class BatePonto {
    private int idRegistro;
    private int idFuncionario;
    private LocalDateTime dataHoraEntrada;
    private LocalDateTime dataHoraSaida;
    private double totalHorasTrabalhadas;

    /**
     * Construtor da classe BatePonto.
     * 
     * @param idRegistro ID único do registro de ponto.
     * @param idFuncionario ID do funcionário que está batendo ponto.
     */
    public BatePonto(int idRegistro, int idFuncionario) {
        this.idRegistro = idRegistro;
        this.idFuncionario = idFuncionario;
        this.dataHoraEntrada = null;
        this.dataHoraSaida = null;
        this.totalHorasTrabalhadas = 0.0;
    }

    /**
     * Construtor completo da classe BatePonto.
     * 
     * @param idRegistro ID único do registro de ponto.
     * @param idFuncionario ID do funcionário.
     * @param dataHoraEntrada Data e hora de entrada.
     * @param dataHoraSaida Data e hora de saída.
     */
    public BatePonto(int idRegistro, int idFuncionario, LocalDateTime dataHoraEntrada, LocalDateTime dataHoraSaida) {
        this.idRegistro = idRegistro;
        this.idFuncionario = idFuncionario;
        this.dataHoraEntrada = dataHoraEntrada;
        this.dataHoraSaida = dataHoraSaida;
        this.totalHorasTrabalhadas = calcularHorasTrabalhadas();
    }

    /**
     * Registra uma batida de ponto do tipo especificado.
     * 
     * @param tipoBatida Tipo da batida (ENTRADA ou SAIDA).
     */
    public void registrarBatida(BatidaDePonto tipoBatida) {
        LocalDateTime agora = tipoBatida.capturarHorarioBatida();
        
        if (tipoBatida == BatidaDePonto.ENTRADA) {
            this.dataHoraEntrada = agora;
        } else if (tipoBatida == BatidaDePonto.SAIDA) {
            this.dataHoraSaida = agora;
            this.totalHorasTrabalhadas = calcularHorasTrabalhadas();
        }
    }

    /**
     * Calcula o total de horas trabalhadas com base na entrada e saída.
     * 
     * @return Total de horas trabalhadas em formato decimal.
     */
    private double calcularHorasTrabalhadas() {
        if (dataHoraEntrada == null || dataHoraSaida == null) {
            return 0.0;
        }
        
        Duration duracao = Duration.between(dataHoraEntrada, dataHoraSaida);
        return duracao.toMinutes() / 60.0; // Converte minutos para horas decimais
    }

    /**
     * Obtém o ID do registro.
     * 
     * @return ID do registro de ponto.
     */
    public int getIdRegistro() {
        return idRegistro;
    }

    /**
     * Define o ID do registro.
     * 
     * @param idRegistro Novo ID do registro.
     */
    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    /**
     * Obtém o ID do funcionário.
     * 
     * @return ID do funcionário.
     */
    public int getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * Define o ID do funcionário.
     * 
     * @param idFuncionario Novo ID do funcionário.
     */
    public void setIdFuncionario(int idFuncionario) {
        this.idFuncionario = idFuncionario;
    }

    /**
     * Obtém a data e hora de entrada.
     * 
     * @return Data e hora de entrada.
     */
    public LocalDateTime getDataHoraEntrada() {
        return dataHoraEntrada;
    }

    /**
     * Define a data e hora de entrada.
     * 
     * @param dataHoraEntrada Nova data e hora de entrada.
     */
    public void setDataHoraEntrada(LocalDateTime dataHoraEntrada) {
        this.dataHoraEntrada = dataHoraEntrada;
        this.totalHorasTrabalhadas = calcularHorasTrabalhadas();
    }

    /**
     * Obtém a data e hora de saída.
     * 
     * @return Data e hora de saída.
     */
    public LocalDateTime getDataHoraSaida() {
        return dataHoraSaida;
    }

    /**
     * Define a data e hora de saída.
     * 
     * @param dataHoraSaida Nova data e hora de saída.
     */
    public void setDataHoraSaida(LocalDateTime dataHoraSaida) {
        this.dataHoraSaida = dataHoraSaida;
        this.totalHorasTrabalhadas = calcularHorasTrabalhadas();
    }

    /**
     * Obtém o total de horas trabalhadas.
     * 
     * @return Total de horas trabalhadas.
     */
    public double getTotalHorasTrabalhadas() {
        return totalHorasTrabalhadas;
    }

    /**
     * Define o total de horas trabalhadas.
     * 
     * @param totalHorasTrabalhadas Novo total de horas trabalhadas.
     */
    public void setTotalHorasTrabalhadas(double totalHorasTrabalhadas) {
        this.totalHorasTrabalhadas = totalHorasTrabalhadas;
    }

    /**
     * Verifica se o registro está completo (tem entrada e saída).
     * 
     * @return true se o registro está completo, false caso contrário.
     */
    public boolean registroCompleto() {
        return dataHoraEntrada != null && dataHoraSaida != null;
    }

    /**
     * Sobrescreve o método toString para retornar informações do registro.
     * 
     * @return String com informações básicas do registro.
     */
    @Override
    public String toString() {
        return "Registro ID: " + idRegistro + " - Funcionário ID: " + idFuncionario;
    }
}