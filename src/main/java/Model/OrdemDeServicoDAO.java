package Model;

import View.OrdemDeServicoView;
import com.google.gson.reflect.TypeToken;
import java.util.Calendar;
import java.util.List;

/**
 * Classe DAO para gerenciar operações de Ordem de Serviço. Responsável pela
 * persistência e manipulação dos dados de ordens de serviço.
 */
public class OrdemDeServicoDAO extends GenericDAO<OrdemDeServico> {

    private static OrdemDeServicoDAO instancia;
    private OrdemDeServicoView viewOrdemServico = new OrdemDeServicoView();
    private FinanceiroDAO financeiroDAO = FinanceiroDAO.getInstancia();

    private OrdemDeServicoDAO() {
        super("data/ordem_servico.json", new TypeToken<List<OrdemDeServico>>() {
        }.getType());
    }

    public static OrdemDeServicoDAO getInstancia() {
        if (instancia == null) {
            instancia = new OrdemDeServicoDAO();
        }
        return instancia;
    }

    @Override
    protected Comparable<?> getChave(OrdemDeServico ordemServico) {
        return ordemServico.getIdOrdemServico();
    }

    public OrdemDeServico buscaOrdemServico(int id) {
        return buscaPorChave(id);
    }

    /**
     * Gera um novo ID para uma ordem de serviço.
     *
     * @return O novo ID da ordem de serviço
     */
    public int geraIdOrdemServico() {
        int maiorId = 0;
        List<OrdemDeServico> ordens = getLista();

        if (ordens != null) {
            for (OrdemDeServico os : ordens) {
                if (os.getIdOrdemServico() > maiorId) {
                    maiorId = os.getIdOrdemServico();
                }
            }
        }
        return maiorId + 1;
    }

    /**
     * Cria uma nova ordem de serviço.
     */
    public void AdicionaOrdemServico() {
        int idCliente = viewOrdemServico.getIdCliente();
        String mecanicoResponsavel = viewOrdemServico.getMecanicoResponsavel();
        Calendar data = viewOrdemServico.getDataOrdemServico();
        TipoServico tipoServico = viewOrdemServico.getTipoServico();
        String descricaoServico = viewOrdemServico.getDescricaoServico();

        double valor = 0;
        List<String> produtos = null;
        double valorProdutos = 0;

        switch (tipoServico) {
            case MANUTENCAO:
                valor = viewOrdemServico.getValorServico();
                break;
            case REVISAO:
                valor = 150.00;
                System.out.println("Valor fixo para revisão: R$ " + String.format("%.2f", valor));
                break;
            case TROCA_PECA:
                valor = viewOrdemServico.getValorServico();
                produtos = viewOrdemServico.getProdutos();
                valorProdutos = viewOrdemServico.getValorProdutos();
                break;
            case OUTRO:
                valor = viewOrdemServico.getValorServico();
                break;
        }

        int idOrdemServico = geraIdOrdemServico();
        OrdemDeServico novaOS;

        if (tipoServico == TipoServico.TROCA_PECA){
            novaOS = new OrdemDeServico(idOrdemServico, idCliente, mecanicoResponsavel,data, StatusOrdemServico.PENDENTE, tipoServico,descricaoServico, valor, produtos, valorProdutos);
        } 
        else{
            novaOS = new OrdemDeServico(idOrdemServico, idCliente, mecanicoResponsavel,data, StatusOrdemServico.PENDENTE, tipoServico,descricaoServico, valor);
        }

        adicionaDados(novaOS);
        System.out.println("Ordem de Serviço criada com sucesso! ID: " + idOrdemServico);
    }

    /**
     * Confirma uma ordem de serviço e adiciona ao balanço financeiro.
     */
    public void confirmarOrdemServico() {
        int idOrdemServico = viewOrdemServico.getIdOrdemServico();
        OrdemDeServico os = buscaOrdemServico(idOrdemServico);

        if (os == null) {
            System.out.println("Ordem de Serviço não encontrada!");
            return;
        }

        if (os.getStatus() == StatusOrdemServico.CONFIRMADO) {
            System.out.println("Ordem de Serviço já está confirmada!");
            return;
        }

        if (os.getStatus() == StatusOrdemServico.CANCELADO) {
            System.out.println("Não é possível confirmar uma Ordem de Serviço cancelada!");
            return;
        }

        viewOrdemServico.mostraOrdemServico(os);
        String confirmacao = viewOrdemServico.confirmaOperacao("Confirmar esta Ordem de Serviço?");

        if (!confirmacao.equals("S")) {
            System.out.println("Operação cancelada!");
            return;
        }

        os.setStatus(StatusOrdemServico.CONFIRMADO);

        String descricaoFinanceira = "Receita OS #" + os.getIdOrdemServico() + " - "
                + os.getTipoServico().getDescricao();
        Financeiro receita = new Financeiro(financeiroDAO.geraIdConta(),descricaoFinanceira,TipoConta.RECEITA,os.getValorTotal(),os.getData());

        financeiroDAO.adicionaDados(receita);

        if (salvarDados()) {
            System.out.println("Ordem de Serviço confirmada com sucesso!");
            System.out.println("Receita adicionada ao balanço financeiro!");
        } else {
            System.out.println("Erro ao confirmar Ordem de Serviço!");
        }
    }

    /**
     * Cancela uma ordem de serviço.
     */
    public void cancelarOrdemServico() {
        int idOrdemServico = viewOrdemServico.getIdOrdemServico();
        OrdemDeServico os = buscaOrdemServico(idOrdemServico);

        if (os == null) {
            System.out.println("Ordem de Serviço não encontrada!");
            return;
        }

        if (os.getStatus() == StatusOrdemServico.CANCELADO) {
            System.out.println("Ordem de Serviço já está cancelada!");
            return;
        }

        viewOrdemServico.mostraOrdemServico(os);
        String confirmacao = viewOrdemServico.confirmaOperacao("Cancelar esta Ordem de Serviço?");

        if (!confirmacao.equals("S")) {
            System.out.println("Operação cancelada!");
            return;
        }

        os.setStatus(StatusOrdemServico.CANCELADO);

        if (salvarDados()) {
            System.out.println("Ordem de Serviço cancelada com sucesso!");
        } else {
            System.out.println("Erro ao cancelar Ordem de Serviço!");
        }
    }

    /**
     * Edita uma ordem de serviço.
     */
    public void editarOrdemServico() {
        editaDados(
                () -> viewOrdemServico.getIdOrdemServico(),
                (os) -> {
                    if (os.getStatus() == StatusOrdemServico.CONFIRMADO) {
                        System.out.println("Não é possível editar uma Ordem de Serviço confirmada!");
                        return;
                    }

                    if (os.getStatus() == StatusOrdemServico.CANCELADO) {
                        System.out.println("Não é possível editar uma Ordem de Serviço cancelada!");
                        return;
                    }

                    viewOrdemServico.mostraOrdemServico(os);
                    int opcao = viewOrdemServico.getOpcaoEdicao();

                    switch (opcao) {
                        case 1:
                            String novoMecanico = viewOrdemServico.getMecanicoResponsavel();
                            os.setMecanicoResponsavel(novoMecanico);
                            break;
                        case 2:
                            TipoServico novoTipo = viewOrdemServico.getTipoServico();
                            os.setTipoServico(novoTipo);

                            if (novoTipo == TipoServico.REVISAO) {
                                os.setValor(150.00);
                                System.out.println("Valor ajustado para revisão: R$ 150,00");
                            }
                            break;
                        case 3:
                            String novaDescricao = viewOrdemServico.getDescricaoServico();
                            os.setDescricaoServico(novaDescricao);
                            break;
                        case 4:
                            double novoValor = viewOrdemServico.getValorServico();
                            os.setValor(novoValor);
                            break;
                        case 5:
                            if (os.getTipoServico() == TipoServico.TROCA_PECA) {
                                List<String> novosProdutos = viewOrdemServico.getProdutos();
                                os.setProdutos(novosProdutos);
                            } else {
                                System.out.println("Produtos só podem ser editados em serviços de 'Troca de Peça'!");
                                return;
                            }
                            break;
                        case 6:
                            if (os.getTipoServico() == TipoServico.TROCA_PECA) {
                                double novoValorProdutos = viewOrdemServico.getValorProdutos();
                                os.setValorProdutos(novoValorProdutos);
                            } else {
                                System.out.println("Valor de produtos só pode ser editado em serviços de 'Troca de Peça'!");
                                return;
                            }
                            break;
                        default:
                            System.out.println("Opção inválida!");
                            return;
                    }
                }
        );
    }

    /**
     * Mostra os dados de uma ordem de serviço específica.
     */
    public void mostrarOrdemServico() {
        mostraDados(() -> viewOrdemServico.getIdOrdemServico(), (os) -> viewOrdemServico.mostraOrdemServico(os));
    }

    /**
     * Gera nota fiscal para uma ordem de serviço. 
     */
    public void gerarNotaFiscal() {
        int idOrdemServico = viewOrdemServico.getIdOrdemServico();
        OrdemDeServico os = buscaOrdemServico(idOrdemServico);
        ClienteDAO clienteDAO = new ClienteDAO();

        if (os == null) {
            System.out.println("Ordem de Serviço não encontrada!");
            return;
        }

        if (os.getStatus() != StatusOrdemServico.CONFIRMADO) {
            System.out.println("Nota fiscal só pode ser gerada para ordens confirmadas!");
            return;
        }

        Cliente cliente = clienteDAO.buscarCliente(os.getIdCliente());
        String cpf = cliente.getCpf().toString();

        viewOrdemServico.mostraNotaFiscal(os, cpf);
    }

    /**
     * Lista todas as ordens de serviço.
     */
    public void listarOrdensServico() {
        List<OrdemDeServico> ordens = getLista();
        viewOrdemServico.mostraListaOrdemServico(ordens);
    }

    /**
     * Busca ordens de serviço por status.
     *
     * @param status Status desejado
     * @return Lista de ordens com o status especificado
     */
    public List<OrdemDeServico> buscarPorStatus(StatusOrdemServico status) {
        return getLista().stream().filter(os -> os.getStatus() == status).collect(java.util.stream.Collectors.toList());
    }

    /**
     * Busca ordens de serviço por cliente.
     *
     * @param idCliente ID do cliente
     * @return Lista de ordens do cliente especificado
     */
    public List<OrdemDeServico> buscarPorCliente(int idCliente) {
        return getLista().stream().filter(os -> os.getIdCliente() == idCliente).collect(java.util.stream.Collectors.toList());
    }

    /**
     * Calcula o faturamento total de ordens confirmadas.
     *
     * @return Valor total do faturamento
     */
    public double calcularFaturamentoTotal() {
        return getLista().stream().filter(os -> os.getStatus() == StatusOrdemServico.CONFIRMADO).mapToDouble(OrdemDeServico::getValorTotal).sum();
    }

    /**
     * Calcula o faturamento de um período específico.
     *
     * @param mes Mês (1-12)
     * @param ano Ano
     * @return Valor do faturamento no período
     */
    public double calcularFaturamentoPeriodo(int mes, int ano) {
        return getLista().stream().filter(os -> os.getStatus() == StatusOrdemServico.CONFIRMADO).filter(os -> {
            Calendar data = os.getData();
            return data.get(Calendar.MONTH) + 1 == mes && data.get(Calendar.YEAR) == ano;
        }).mapToDouble(OrdemDeServico::getValorTotal).sum();
    }
}
