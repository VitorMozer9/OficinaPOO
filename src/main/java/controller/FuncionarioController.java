package controller;

import Main.OficinaPOO;
import Model.Funcionario;
import Model.FuncionarioDAO;
import View.FuncionarioView;

/**
 * Classe responsável pela gerencia dos funcionários.
 * Fornece métodos para adicionar e mostrar o menu de informações de funcionário.
 */
public class FuncionarioController {
    private FuncionarioView viewFuncionario = new FuncionarioView();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    
    /**
     * Exibe o menu de opções do funcionário e executa a ação selecionada pelo usuário.
     * O menu permanece até que o usuário escolha a opção de sair.
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
                    funcionarioDAO.adicionaFuncionario();
                }
                case 2 -> {
                    funcionarioDAO.editaFuncionario();
                }
                case 3 -> {
                    funcionarioDAO.removeFuncionario();
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
    
    @Override
    public String toString(){
        return String.format("FuncionarioController: %d funcionários registrados.", funcionarioDAO.getLista().size());
    }
}
