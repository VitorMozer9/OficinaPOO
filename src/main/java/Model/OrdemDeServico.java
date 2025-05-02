package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Representação de um chamado para uma ordem de serviço, que irá aparecer no sistema indicando o nome do cliente, o funcionário responsável
 * pelo atendimento e análise inicial do veículo, o tipo da manutenção, e a data em que esse serviço foi solicitado.
 * 
 */
public class OrdemDeServico {
    private Cliente cliente;
    private Funcionario tecnicoResponsavel;
    private Manutencao manutencao;
    private Date dataServico;

    /**
     * Construtor da classe (@code OrdemDeServico).
     * Inicia uma nova ordem de serviço com as informações forncecidas pelo usuário.
     * 
     * @param cliente               Pega as informações do cliente.
     * @param tecnicoResponsavel    Nome do funcionário reponsável.
     * @param manutencao            Pega as informações da manutenção.
     */
    public OrdemDeServico(Cliente cliente, Funcionario tecnicoResponsavel, Manutencao manutencao) {
        this.cliente = cliente;
        this.tecnicoResponsavel = tecnicoResponsavel;
        this.manutencao = manutencao;
        this.dataServico = new Date();
    }

    /**
     * Sobrescreve o método toString para retornar no sistema uma representação da ordem de serviço com suas informações separadas e detalhadas
     * para cada um dos pontos (cliente, tecnico responsavel e manutenção).
     * 
     * @return Informações da ordem de serviço.
     */
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
