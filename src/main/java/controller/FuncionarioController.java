package controller;

import Model.FuncionarioDAO;
import View.FuncionarioView;

public class FuncionarioController {
    private FuncionarioView viewFuncionario = new FuncionarioView();
    private FuncionarioDAO funcionarioDAO = FuncionarioDAO.getInstancia();
    private LoginController loginController;
    
    public FuncionarioController(LoginController loginController) {
        this.loginController = loginController;
    }
    
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
                case 2 -> funcionarioDAO.editaFuncionario();
                case 3 -> {
                    if (loginController.isGerente()) {
                        funcionarioDAO.removeFuncionario();
                    } else {
                        System.out.println("Apenas gerentes podem remover funcionários.");
                    }
                }
                case 4 -> funcionarioDAO.mostrarFuncionario();
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    @Override 
    public String toString(){
        return String.format("FuncionarioController: %d funcionários registrados.", funcionarioDAO.getLista().size());
    }
}