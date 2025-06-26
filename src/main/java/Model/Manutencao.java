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
    private List<Produto> pecasUtilizadas;
    
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
    public void adcionaPeca(Produto peca){
        this.pecasUtilizadas.add(peca);
    }
    
    /**
     * Lista de String das descrições das peças. 
     * 
     * @return Uma lista com as descrições das peças.
     */
    public List<String> getListaDePecas(){
        ArrayList<String> listaPecas = new ArrayList<>();
        for(Produto peca : pecasUtilizadas){
            listaPecas.add(peca.getDescricao());
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
     * Obtém a descrição do que será feito para a realização da manutenção.
     * 
     * @return Uma descrição da pré manutenção.
     */
    public String getDescricaoManutencao() {
        return descricaoManutencao;
    }

    /**
     * Define o quais passos serão tomados paraa conclusão da manutenção.
     * 
     * @param descricaoManutencao  Nova descrição da pré manutenção.
     */
    public void setDescricaoManutencao(String descricaoManutencao) {
        this.descricaoManutencao = descricaoManutencao;
    }

    /**
     * Obtém após a manutenção, o que foi feito para consertar o Veículo.
     * 
     * @return Descrição para a solução da manutenção.
     */
    public String getSolucaoManutencao() {
        return solucaoManutencao;
    }

    /**
     * Define após a manutenção, o que foi feito para consertar o Veículo.
     * 
     * @param solucaoManutencao Nova descrição de solução da manutenção. 
     */
    public void setSolucaoManutencao(String solucaoManutencao) {
        this.solucaoManutencao = solucaoManutencao;
    }

    /**
     * Obtém a lista das peças que foram usadas para o reparo do Veículo.
     * 
     * @return Lista de peças usadas na manutenção.
     */
    public List<Produto> getPecasUtilizadas() {
        return pecasUtilizadas;
    }
    
    @Override
    public String toString(){
        return descricaoManutencao;
    }
               
}
