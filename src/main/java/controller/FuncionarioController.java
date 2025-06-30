package controller;

import Main.OficinaPOO;
import Model.Funcionario;
import Model.FuncionarioDAO;
import View.FuncionarioView;

/**
 * Classe responsável pela gerencia dos funcionários com controle de permissões.
 * Fornece métodos para adicionar e mostrar o menu de informações de funcionário.
 */
public class FuncionarioController {
    private FuncionarioView viewFuncionario = new FuncionarioView();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private LoginController loginController;
    
    /**
     * Construtor que recebe o controlador de login para verificação de permissões.
     * 
     * @param loginController Controlador de login para verificar permissões.
     */
    public FuncionarioController(LoginController loginController) {
        this.loginController = loginController;
    }
    
    /**
     * Exibe o menu de opções do funcionário e executa a ação selecionada pelo usuário.
     * O menu permanece até que o usuário escolha a opção de sair.
     * Verifica permissões antes de executar certas ações.
     */
    public void executaMenuFuncionario(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewFuncionario.mostraOpcoesFuncionario();
            
            if (opcao == 5) {
                System.out.println("Saindo do menu de Funcionários...");
                break; 
            }
            
            switch (opcao){
                case 1 -> {
                    if (loginController.podeCadastrarFuncionario()) {
                        funcionarioDAO.adicionaFuncionario();
                    }
                }
                case 2 -> {
                    editarFuncionarioComPermissao();
                }
                case 3 -> {
                    if (loginController.podeRemoverFuncionario()) {
                        funcionarioDAO.removeFuncionario();
                    }
                }
                case 4 -> {
                    funcionarioDAO.mostrarFuncionario();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
    /**
     * Gerencia a edição de funcionário com controle de permissões.
     * Gerentes podem editar qualquer funcionário, funcionários comuns só podem editar a si mesmos.
     */
    private void editarFuncionarioComPermissao() {
        if (loginController.isGerente()) {
            funcionarioDAO.editaFuncionario();
            return;
        }
        
        int idFuncionarioLogado = loginController.getUsuarioLogado().getIdFuncionario();
        System.out.println("Como funcionário, você só pode editar seus próprios dados.");
        System.out.println("Editando seus dados pessoais...\n");
        
        funcionarioDAO.editaFuncionarioCargo(idFuncionarioLogado);
    }
    
    /**
     * Exibe menu simplificado para funcionários comuns (sem opções restritas).
     */
    public void executaMenuFuncionarioLimitado() {
        int opcao = 0;
        
        while(opcao != 3) {
            System.out.println("1 - Editar dados");
            System.out.println("2 - Ver dados");
            System.out.println("3 - Sair");
            System.out.print("Escolha uma opção: ");
            
            opcao = viewFuncionario.getOpcaoMenu();
            
            switch (opcao) {
                case 1 -> {
                    int idFuncionarioLogado = loginController.getUsuarioLogado().getIdFuncionario();
                    funcionarioDAO.editaFuncionarioCargo(idFuncionarioLogado);
                }
                case 2 -> {
                    int idFuncionarioLogado = loginController.getUsuarioLogado().getIdFuncionario();
                    Funcionario funcionario = funcionarioDAO.buscaFuncionario(idFuncionarioLogado);
                    if (funcionario != null) {
                        viewFuncionario.mostraFuncionario(funcionario);
                    }
                }
                case 3 -> {
                    System.out.println("Saindo...");
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
    @Override
    public String toString(){
        return String.format("FuncionarioController: %d funcionários registrados.", funcionarioDAO.getLista().size());
    }
}