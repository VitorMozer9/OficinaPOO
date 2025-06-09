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
     * Gera um novo ID para funcionário, baseado no maior ID já registrado no sistema.
     * Isso garante que cada novo funcionário tenha um ID único.
     * 
     * @return O novo ID do funcionário, incrementa em relação ao maior ID atual.
     */
    public static int geraIdFuncionario(){
        int maiorID = 0;
        for(Funcionario cadaFuncionario : OficinaPOO.getInstancia().getFuncionario()){
            if (cadaFuncionario.getIdFuncionario() > maiorID){
                maiorID = cadaFuncionario.getIdFuncionario();
            }
        }
        return maiorID + 1;
    }
    
    /**
     * Exibe o menu de opções do funcionário e executa a ação selecionada pelo usuário.
     * O menu permanece até que o usuário escolha a opção de sair.
     */
    public void executaMenuFuncionario(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewFuncionario.mostraOpcoesFuncionario();
            
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
}
