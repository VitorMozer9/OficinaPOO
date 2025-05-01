package Model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    
    public String geraOrdemDeServiço(Cliente infoCliente, Manutencao infoManutencao, Funcionario infoFunc){
        
    StringBuilder ordemServico = new StringBuilder();
    Date dataAtual = new Date();
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm");
    String dataFormatada = formato.format(dataAtual);

    ordemServico.append("============================================================\n");
    ordemServico.append("  Ordem de Serviço ").append(this.idManutencao).append("\n");
    ordemServico.append("============================================================\n");
    ordemServico.append("CLIENTE:\n");
    ordemServico.append("Nome: ").append(infoCliente.getNome()).append("\n");
    ordemServico.append("Endereço: ").append(infoCliente.getEndereco()).append("\n");
    ordemServico.append("Telefone: ").append(infoCliente.getTelefone()).append("\n");
//    ordemServico.append("CPF: ").append(infoCliente.getCpf().toString()).append("\n");
    ordemServico.append("------------------------------------------------------------\n");
    ordemServico.append("DIAGNÓSTICO:\n");
    ordemServico.append(infoManutencao.getDescricaoManutencao()).append("\n");
    ordemServico.append("------------------------------------------------------------\n");
    ordemServico.append("SOLUÇÃO APLICADA:\n");
    ordemServico.append(infoManutencao.getSolucaoManutencao()).append("\n");
    ordemServico.append("------------------------------------------------------------\n");
    ordemServico.append("PEÇAS TROCADAS:\n");
    for (String descricaoPecas : infoManutencao.getListaDePecas()) {
        ordemServico.append("- ").append(descricaoPecas).append("\n");
    }
    ordemServico.append("------------------------------------------------------------\n");
    ordemServico.append("Data do Serviço: ").append(dataFormatada).append("\n");
    ordemServico.append("Técnico responsável: ").append(infoFunc.getNome()).append("\n");
    ordemServico.append("Total do Serviço: R$ xxxxx\n");
    ordemServico.append("============================================================\n");

    return ordemServico.toString();
        
    }
            
    
}
