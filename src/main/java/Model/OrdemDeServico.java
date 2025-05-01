package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrdemDeServico {
    private Cliente cliente;
    private Funcionario tecnicoResponsavel;
    private Manutencao manutencao;
    private Date dataServico;

    public OrdemDeServico(Cliente cliente, Funcionario tecnicoResponsavel, Manutencao manutencao) {
        this.cliente = cliente;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.manutencao = manutencao;
        this.dataServico = new Date();
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy 'às' HH:mm");
        StringBuilder ordemServico = new StringBuilder();

        ordemServico.append("============================================================\n");
        ordemServico.append("  Ordem de Serviço ").append(this.manutencao).append("\n");
        ordemServico.append("============================================================\n");
        ordemServico.append("CLIENTE:\n");
        ordemServico.append("Nome: ").append(cliente.getNome()).append("\n");
        ordemServico.append("Endereço: ").append(cliente.getEndereco()).append("\n");
        ordemServico.append("Telefone: ").append(cliente.getTelefone()).append("\n");
        ordemServico.append("------------------------------------------------------------\n");
        ordemServico.append("DIAGNÓSTICO:\n");
        ordemServico.append(manutencao.getDescricaoManutencao()).append("\n");
        ordemServico.append("------------------------------------------------------------\n");
        ordemServico.append("SOLUÇÃO APLICADA:\n");
        ordemServico.append(manutencao.getSolucaoManutencao()).append("\n");
        ordemServico.append("------------------------------------------------------------\n");
        ordemServico.append("PEÇAS TROCADAS:\n");
        for (String descricaoPecas : manutencao.getListaDePecas()) {
            ordemServico.append("- ").append(descricaoPecas).append("\n");
        }
        ordemServico.append("------------------------------------------------------------\n");
        ordemServico.append("Data do Serviço: ").append(formato.format(dataServico)).append("\n");
        ordemServico.append("Técnico responsável: ").append(tecnicoResponsavel.getNome()).append("\n");
        ordemServico.append("Total do Serviço: R$ xxxxx\n");
        ordemServico.append("============================================================\n");

        return ordemServico.toString();
    }
}
