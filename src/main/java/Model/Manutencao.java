package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Representação da manutenção realizada pelo mecanico especialista da Oficina, aprsentando o status da manutenção, a descrição dela, suas soluções,
 * o seu número para identificação, seu valor total e uma lista com todas as peças que foram necessárias na manutenção.
 */
public class Manutencao {
    private int idManutencao;
    private String statusVeiculo;
    private String descricaoManutencao;
    private String solucaoManutencao;
    private double valorTotal;
    private List<Pecas> pecasUtilizadas;
    
    public static int ultimoIdManutencao = 0;

    /**
     * Construtor da classe (@code Manutencao).
     * Inicializa o processo de manutenção com as informações do status do Veículo, a descrição dessa manutenção
     * e a solução utilizada para a manutenção. 
     * 
     * @param statusVeiculo         Define o status atual do Veículo.
     * @param descricaoManutencao   Descreve o que será feito para resolver o problema do veículo, antes da manutenção.
     * @param solucaoManutencao     Descreve as etapas que foram feitas para consertar o vículo, após o mesmo ficar pronto.
     */
    public Manutencao(String statusVeiculo, String descricaoManutencao, String solucaoManutencao) {
        this.idManutencao = ++ultimoIdManutencao;
        this.statusVeiculo = statusVeiculo;
        this.descricaoManutencao = descricaoManutencao;
        this.solucaoManutencao = solucaoManutencao;
        this.pecasUtilizadas = new ArrayList<>();
    }
    
    /**
     * Adiciona em uma lista todas as peças que foram utilizadas para concluir a manutenção.
     * 
     * @param peca 
     */
    public void adcionaPeca(Pecas peca){
        this.pecasUtilizadas.add(peca);
    }
    
    /**
     * Lista de String das descrições das peças. 
     * 
     * @return Uma lista com as descrições das peças.
     */
    public List<String> getListaDePecas(){
        ArrayList<String> listaPecas = new ArrayList<>();
        for(Pecas peca : pecasUtilizadas){
            listaPecas.add(peca.getDescicao());
        }
        return listaPecas; 
    }
    
    /**
     * Obtém o status atual do Veículo.
     * 
     * @return Status do Veículo.
     */
    public String getStatusVeiculo() {
        return statusVeiculo;
    }

    /**
     * Define o status do Veículo
     * 
     * @param statusVeiculo Novo status do Veículo.
     */
    public void setStatusVeiculo(String statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

    /**
     * Obtém a descrição do que será feito para a realização da manutenção.]
     * 
     * @return Uma descrição 
     */
    public String getDescricaoManutencao() {
        return descricaoManutencao;
    }

    public void setDescricaoManutencao(String descricaoManutencao) {
        this.descricaoManutencao = descricaoManutencao;
    }

    public String getSolucaoManutencao() {
        return solucaoManutencao;
    }

    public void setSolucaoManutencao(String solucaoManutencao) {
        this.solucaoManutencao = solucaoManutencao;
    }

    public List<Pecas> getPecasUtilizadas() {
        return pecasUtilizadas;
    }
               
}
