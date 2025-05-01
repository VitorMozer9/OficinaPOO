package Model;

import java.util.ArrayList;
import java.util.List;

public class Manutencao {
    private int idManutencao;
    private String statusVeiculo;
    private String descricaoManutencao;
    private String solucaoManutencao;
    private double valorTotal;
    private List<Pecas> pecasUtilizadas;
    
    public static int ultimoIdManutencao = 0;

    public Manutencao(String statusVeiculo, String descricaoManutencao, String solucaoManutencao) {
        this.idManutencao = ++ultimoIdManutencao;
        this.statusVeiculo = statusVeiculo;
        this.descricaoManutencao = descricaoManutencao;
        this.solucaoManutencao = solucaoManutencao;
        this.pecasUtilizadas = new ArrayList<>();
    }
    
    public void adcionaPeca(Pecas peca){
        this.pecasUtilizadas.add(peca);
    }
    
    public List<String> getListaDePecas(){
        ArrayList<String> listaPecas = new ArrayList<>();
        for(Pecas peca : pecasUtilizadas){
            listaPecas.add(peca.getDescicao());
        }
        return listaPecas; 
    }
    
    public String getStatusVeiculo() {
        return statusVeiculo;
    }

    public void setStatusVeiculo(String statusVeiculo) {
        this.statusVeiculo = statusVeiculo;
    }

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
