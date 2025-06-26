package controller;

import Main.OficinaPOO;
import Model.AgendamentoDAO;
import Model.ClienteDAO;
import Model.FuncionarioDAO;
import Model.ProdutoEstoqueDAO;
import Model.VeiculoDAO;
import Model.VendaDAO;
import View.TelaInicial;

/**
 * A classe Sistema é a principal classe de controle e gerenciamento da oficina.
 * Ela orquestra a interaçõa entre as diferentes funcionalidades (clientes, funcionários, veículos, produtos) e gerencia
 * o fluxo geral do programa, exibindo o menu principal e delegando as operações para os respectivos controladores.
 * O sistema carrega os dados persistidos no ínicio e salva-os ao ser encerrado.
 */
public class Sistema {
    
    private TelaInicial telaInicial = new TelaInicial();
    private ClienteController clienteController = new ClienteController();
    private FuncionarioController funcionarioController = new FuncionarioController();
    private VeiculoController veiculoController = new VeiculoController();
    private ProdutoEstoqueController produtoController = new ProdutoEstoqueController();
    private AgendamentoController agendamentoController = new AgendamentoController();
    private VendaController vendaController = new VendaController();
    
    private ClienteDAO clienteDao = new ClienteDAO();
    private FuncionarioDAO funcionarioDao = new FuncionarioDAO();
    private VeiculoDAO veiculoDao = new VeiculoDAO();
    private ProdutoEstoqueDAO produtoDao = ProdutoEstoqueDAO.getInstancia();
    private VendaDAO vendaDao = VendaDAO.getInstancia();
    private AgendamentoDAO agendamentoDao = new AgendamentoDAO();
    
    /**
     * Inicia o sistema da oficina.
     * Este método carrega os dados persistidos, exibe o menu de opções principal para o usuário e, com base na seleção,
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
                    clienteController.executaMenuCliente();
                }
                
                case 2 -> {
                    funcionarioController.executaMenuFuncionario();
                }
                
                case 3 -> {
                    veiculoController.executaMenuVeiculo();
                }
                
                case 4 -> {
                    produtoController.executaMenuProdutos();
                }
                
                case 5 -> {
                    vendaController.executaMenuVendas();
                }
                
                case 6 ->{
                    agendamentoController.executaMenuAgendamento();
                }
//                
//                case 7 -> {
//                    os.executaMenuOrdemDeServico();
//                }
//                
                
//                case 8 -> {
//                    financeiroController.executaMenuFinanceiro();
//                }    
               
                case 9 -> {
                    System.out.println("Encerrando sistema...");
                    clienteDao.salvarDados(); 
                    funcionarioDao.salvarDados();
                    veiculoDao.salvarDados();
                    produtoDao.salvarDados();
                    agendamentoDao.salvarDados();
                    vendaDao.salvarDados();
                    rodando = false;
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
}
