package Model;

import View.FinanceiroView;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Classe DAO para gerenciar operações financeiras.
 * Responsável pela persistência e manipulação dos dados de contas financeiras.
 */
public class FinanceiroDAO extends GenericDAO<Financeiro> {
    private static FinanceiroDAO instancia;
    private FinanceiroView viewFinanceiro = new FinanceiroView();
    private VendaDAO vendaDAO = VendaDAO.getInstancia();

    private FinanceiroDAO() {
        super("data/financeiro.json", new TypeToken<List<Financeiro>>() {}.getType());
    }

    public static FinanceiroDAO getInstancia(){
        if (instancia == null) {
            instancia = new FinanceiroDAO();
        }
        return instancia;
    }

    @Override
    protected Comparable<?> getChave(Financeiro financeiro){
        return financeiro.getIdConta();
    }

    public Financeiro buscaConta(int id){
        return buscaPorChave(id);
    }

    /**
     * Gera um novo ID para uma conta financeira.
     * 
     * @return O novo ID da conta
     */
    public int geraIdConta(){
        int maiorIdConta = 0;
        List<Financeiro> contas = getLista();

        if (contas != null){
            for (Financeiro conta : contas){
                if (conta.getIdConta() > maiorIdConta) {
                    maiorIdConta = conta.getIdConta();
                }
            }
        }
        return maiorIdConta + 1;
    }

    /**
     * Adiciona uma nova conta financeira ao sistema.
     */
    public void adicionarConta(){
        String descricao = viewFinanceiro.getDescricaoConta();
        TipoConta tipoConta = viewFinanceiro.getTipoConta();
        double valor = viewFinanceiro.getValorConta();
        Calendar data = viewFinanceiro.getDataConta();
        
        int idConta = geraIdConta();
        Financeiro novaConta = new Financeiro(idConta, descricao, tipoConta, valor, data);
        
        adicionaDados(novaConta);
        System.out.println("Conta adicionada com sucesso! ID: " + idConta);
    }

    /**
     * Remove uma conta financeira do sistema.
     */
    public void removerConta(){
        int idConta = viewFinanceiro.getIdConta();
        Financeiro conta = buscaConta(idConta);

        if (conta == null) {
            System.out.println("Conta não encontrada!");
            return;
        }

        viewFinanceiro.mostraConta(conta);
        String confirmacao = viewFinanceiro.confirmaRemocao();
        
        if (!confirmacao.equals("S")){
            System.out.println("Operação cancelada!");
            return;
        }

        if (removeDados(conta)){
            System.out.println("Conta removida com sucesso!");
        } else {
            System.out.println("Erro ao remover conta!");
        }
    }

    /**
     * Edita uma conta financeira existente.
     */
    public void editarConta(){
        editaDados(
            () -> viewFinanceiro.getIdConta(),
            (conta) -> {
                viewFinanceiro.mostraConta(conta);
                int opcao = viewFinanceiro.getOpcaoEdicao();
                
                switch (opcao){
                    case 1:
                        String novaDescricao = viewFinanceiro.getDescricaoConta();
                        conta.setDescricao(novaDescricao);
                        break;
                    case 2:
                        TipoConta novoTipo = viewFinanceiro.getTipoConta();
                        conta.setTipoConta(novoTipo);
                        break;
                    case 3:
                        double novoValor = viewFinanceiro.getValorConta();
                        conta.setValor(novoValor);
                        break;
                    case 4:
                        Calendar novaData = viewFinanceiro.getDataConta();
                        conta.setData(novaData);
                        break;
                    default:
                        System.out.println("Opção inválida!");
                        return;
                }
            }
        );
    }

    /**
     * Mostra os dados de uma conta específica.
     */
    public void mostrarDadosConta(){
        mostraDados(
            () -> viewFinanceiro.getIdConta(),
            (conta) -> viewFinanceiro.mostraConta(conta)
        );
    }

    /**
     * Gera um relatório baseado nas vendas realizadas.
     */
    public void gerarRelatorioVendas(){
        List<Venda> vendas = vendaDAO.getLista();
        
        if (vendas == null || vendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada no sistema.");
            return;
        }

        double totalVendas = 0;
        int quantidadeVendas = vendas.size();
        
        System.out.println("Relatório vendas");
        System.out.println("Total de vendas realizadas: " + quantidadeVendas);
        
        for (Venda venda : vendas) {
            totalVendas += venda.getValorTotal();
            System.out.println("Venda ID: " + venda.getIdVenda() + 
                             " | Cliente: " + venda.getIdCliente() + 
                             " | Valor: R$ " + String.format("%.2f", venda.getValorTotal()));
        }
        
        System.out.println("Valor total das vendas: R$ " + String.format("%.2f", totalVendas));
        System.out.println("Valor médio por venda: R$ " + String.format("%.2f", totalVendas / quantidadeVendas));
    }

    /**
     * Gera um balanço mensal das contas financeiras.
     */
    public void gerarBalancoMensal(){
        int mes = viewFinanceiro.getMes();
        int ano = viewFinanceiro.getAno();
        
        List<Financeiro> contas = getLista();
        
        if (contas == null){
            System.out.println("Nenhuma conta registrada no sistema.");
            return;
        }

        double totalReceitas = 0;
        double totalDespesas = 0;
        List<Financeiro> contasDoMes = new ArrayList<>();
        
        for (Financeiro conta : contas) {
            Calendar dataConta = conta.getData();
            if (dataConta.get(Calendar.MONTH) + 1 == mes && dataConta.get(Calendar.YEAR) == ano) {
                contasDoMes.add(conta);
                
                if (conta.getTipoConta() == TipoConta.RECEITA) {
                    totalReceitas += conta.getValor();
                } else {
                    totalDespesas += conta.getValor();
                }
            }
        }
        
        if (contasDoMes.isEmpty()) {
            System.out.println("Nenhuma movimentação encontrada para " + mes + "/" + ano);
            return;
        }
        
        viewFinanceiro.mostraBalancoMensal(contasDoMes, totalReceitas, totalDespesas, mes, ano);
    }

    /**
     * Lista todas as contas financeiras.
     */
    public void listarContas() {
        List<Financeiro> contas = getLista();
        viewFinanceiro.mostraListaContas(contas);
    }
    
    @Override
    public String toString(){
       return String.format("FinanceiroDAO | Contas cadastradas: %d", getLista().size());
    }
}