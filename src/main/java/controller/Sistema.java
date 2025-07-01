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
 * Ela orquestra a interação entre as diferentes funcionalidades (clientes,
 * funcionários, veículos, produtos) e gerencia o fluxo geral do sistema,
 * exibindo o menu principal e delegando as operações para os respectivos
 * controladores. O sistema carrega os dados persistidos no ínicio e salva-os ao
 * ser encerrado.
 */
public class Sistema {

    // Estrategia A 
    private static int quantVeiculosPrivado = 0;
    
    // Estrategia B C
    protected static int quantClientesProtegido = 0;

    private LoginController loginController = new LoginController();
    private ViewPrincipal telaInicial = new ViewPrincipal();
    private ClienteController clienteController = new ClienteController();
    private FuncionarioController funcionarioController = new FuncionarioController(loginController);
    private VeiculoController veiculoController = new VeiculoController();
    private ProdutoEstoqueController produtoController = new ProdutoEstoqueController();
    private AgendamentoController agendamentoController = new AgendamentoController();
    private VendaController vendaController = new VendaController();
    private FinanceiroController financeiroController = new FinanceiroController();
    private OrdemDeServicoController osController = new OrdemDeServicoController();
    private BatePontoController pontoController = new BatePontoController();

    private ClienteDAO clienteDao = ClienteDAO.getInstancia();
    private FuncionarioDAO funcionarioDao = FuncionarioDAO.getInstancia();
    private VeiculoDAO veiculoDao = VeiculoDAO.getInstancia();
    private ProdutoEstoqueDAO produtoDao = ProdutoEstoqueDAO.getInstancia();
    private VendaDAO vendaDao = VendaDAO.getInstancia();
    private AgendamentoDAO agendamentoDao = AgendamentoDAO.getInstancia();
    private FinanceiroDAO financeiroDAO = FinanceiroDAO.getInstancia();
    private OrdemDeServicoDAO osDAO = OrdemDeServicoDAO.getInstancia();
    private BatePontoDAO pontoDAO = BatePontoDAO.getInstancia();

    // Métodos para Estratégia A - Encapsulamento (private static)
    
    /**
     * Obtém a quantidade de veículos cadastrados no sistema.
     * 
     * @return Quantidade de veículos privados.
     */
    public static int getQuantVeiculosPrivado() {
        return quantVeiculosPrivado;
    }

    /**
     * Define a quantidade de veículos cadastrados no sistema.
     * 
     * @param quantVeiculosPrivado Nova quantidade de veículos.
     */
    public static void setQuantVeiculosPrivado(int quantVeiculosPrivado) {
        Sistema.quantVeiculosPrivado = quantVeiculosPrivado;
    }
    
    /**
     * Incrementa o contador de veículos.
     */
    public static void incrementarVeiculos() {
        quantVeiculosPrivado++;
    }

    // Métodos para Estratégia B - Protected (acesso direto limitado)
    
    /**
     * Obtém a quantidade de clientes cadastrados no sistema.
     * 
     * @return Quantidade de clientes protegidos.
     */
    public static int getQuantClientesProtegido() {
        return quantClientesProtegido;
    }

    /**
     * Define a quantidade de clientes cadastrados no sistema.
     * 
     * @param quantClientesProtegido Nova quantidade de clientes.
     */
    public static void setQuantClientesProtegido(int quantClientesProtegido) {
        Sistema.quantClientesProtegido = quantClientesProtegido;
    }

    /**
     * Inicia o sistema com tela de login.
     */
    public void iniciaSistema() {
        if (!loginController.realizarLogin()) {
            System.out.println("Falha no login. Sistema encerrado.");
            return;
        }

        boolean rodando = true;

        while (rodando) {
            int opcaoDisponivel = telaInicial.mostraOpcoesDisponiveis();

            switch (opcaoDisponivel) {
                case 1 ->
                    pontoController.executaMenuPonto();
                case 2 ->
                    clienteController.executaMenuCliente();
                case 3 -> {
                    if (loginController.podeCadastrarFuncionario()) {
                    funcionarioController.executaMenuFuncionario();
                    }
                }
                case 4 ->
                    veiculoController.executaMenuVeiculo();
                case 5 ->
                    produtoController.executaMenuProdutos();
                case 6 ->
                    vendaController.executaMenuVendas();
                case 7 ->
                    agendamentoController.executaMenuAgendamento();
                case 8 ->
                    osController.executaMenuOrdemDeServico();
                case 9 -> {
                    if (loginController.podeAcessarAreaFinanceira()) {
                        financeiroController.executaMenuFinanceiro();
                    }
                }
                case 10 -> {
                    System.out.println("Encerrando sistema...");
                    System.out.println("Estatísticas do sistema:");
                    System.out.println("Total de veículos cadastrados: " + veiculoDao.toString());
                    System.out.println("Total de clientes cadastrados: " + clienteDao.toString());
                    
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
                default ->
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }

    @Override
    public String toString() {
        return "Sistema da Oficina POO - Usuário logado: "
                + (loginController.getUsuarioLogado() != null
                ? loginController.getUsuarioLogado().getNome() : "Nenhum")
                + " | Veículos: " + quantVeiculosPrivado 
                + " | Clientes: " + quantClientesProtegido;
    }
}