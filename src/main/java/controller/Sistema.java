package controller;

import Main.OficinaPOO;
import Model.AgendamentoDAO;
import Model.BatePontoDAO;
import Model.ClienteDAO;
import Model.FinanceiroDAO;
import Model.FuncionarioDAO;
import Model.OrdemDeServicoDAO;
import Model.ProdutoEstoqueDAO;
import Model.VeiculoDAO;
import Model.VendaDAO;
import View.ViewPrincipal;

/**
 * A classe Sistema é a principal classe de controle e gerenciamento da oficina.
 * Ela orquestra a interação entre as diferentes funcionalidades (clientes, funcionários, veículos, produtos) e gerencia
 * o fluxo geral do sistema, exibindo o menu principal e delegando as operações para os respectivos controladores.
 * O sistema carrega os dados persistidos no ínicio e salva-os ao ser encerrado.
 */
public class Sistema {
    
    private ViewPrincipal telaInicial = new ViewPrincipal();
    private ClienteController clienteController = new ClienteController();
    private FuncionarioController funcionarioController = new FuncionarioController();
    private VeiculoController veiculoController = new VeiculoController();
    private ProdutoEstoqueController produtoController = new ProdutoEstoqueController();
    private AgendamentoController agendamentoController = new AgendamentoController();
    private VendaController vendaController = new VendaController();
    private FinanceiroController financeiroController = new FinanceiroController();
    private OrdemDeServicoController osController = new OrdemDeServicoController();
    private BatePontoController pontoController = new BatePontoController();
    
    private ClienteDAO clienteDao = new ClienteDAO();
    private FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    private VeiculoDAO veiculoDao = new VeiculoDAO();
    private ProdutoEstoqueDAO produtoDao = ProdutoEstoqueDAO.getInstancia();
    private VendaDAO vendaDao = VendaDAO.getInstancia();
    private AgendamentoDAO agendamentoDao = new AgendamentoDAO();
    private FinanceiroDAO financeiroDAO = FinanceiroDAO.getInstancia();
    private OrdemDeServicoDAO osDAO = OrdemDeServicoDAO.getInstancia();
    private BatePontoDAO pontoDAO = new BatePontoDAO();
    
    /**
     * Inicia o sistema da oficina.
     * Este método, exibe o menu de opções principal para o usuário e, com base na seleção,
     * delegando a execução para o controlador correspondente.
     * O loop continua até que o usuário escolha a opção de sair.
     * Ao sair, os dados são salvos.
     */
    public void iniciaSistema() {
        boolean rodando = true;
        
        while (rodando) {
            int opcaoDisponivel = telaInicial.mostraOpcoesDisponiveis();
            
            switch (opcaoDisponivel) {
                case 1 -> {
                    pontoController.executaMenuPonto();
                }
                case 2 -> {
                    clienteController.executaMenuCliente();
                }
                
                case 3 -> {
                    funcionarioController.executaMenuFuncionario();
                }
                
                case 4 -> {
                    veiculoController.executaMenuVeiculo();
                }
                
                case 5 -> {
                    produtoController.executaMenuProdutos();
                }
                
                case 6 -> {
                    vendaController.executaMenuVendas();
                }
                
                case 7 ->{
                    agendamentoController.executaMenuAgendamento();
                }
                
                case 8 -> {
                    osController.executaMenuOrdemDeServico();
                }
                
                case 9 -> {
                    financeiroController.executaMenuFinanceiro();
                }    
               
                case 10 -> {
                    System.out.println("Encerrando sistema...");
                    clienteDao.salvarDados(); 
                    funcionarioDao.salvarDados();
                    veiculoDao.salvarDados();
                    produtoDao.salvarDados();
                    agendamentoDao.salvarDados();
                    vendaDao.salvarDados();
                    financeiroDAO.salvarDados();
                    osDAO.salvarDados();
                    pontoDAO.salvarDados();
                    rodando = false;
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
}
