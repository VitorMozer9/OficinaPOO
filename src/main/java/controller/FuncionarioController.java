package controller;

import Main.OficinaPOO;
import Model.Cpf;
import Model.Funcionario;
import View.FuncionarioView;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela gerencia dos funcionários.
 * Fornece métodos para adicionar e mostrar o menu de informações de funcionário.
 */
public class FuncionarioController {
    private FuncionarioView viewFuncionario = new FuncionarioView();
    private List<Funcionario> listaFuncionario = new ArrayList<>();
    
    /**
     * Coleta os dados do funcionário a partir da view, válida o seu CPF e adicina o novo funcionário ao sistema.
     * Exibe uma mensagem de sucesso ou erro de validação para o usuário. 
     */
    public void adicionaFuncionario(){
        String nome = viewFuncionario.getNomeFunc();
        String endereco = viewFuncionario.getEnderecoFunc();
        String telefone = viewFuncionario.getFoneFunc();
        String email = viewFuncionario.getEmailFunc();
        String codigoCpf = viewFuncionario.getCpfFunc();
        if (!Cpf.validaCPF(codigoCpf)){
            System.out.println("CPF inválido!!");
            return;
        }
        
        String usuario = viewFuncionario.getUsuarioFunc();
        String senha = viewFuncionario.getSenhaFunc();
        String cargo = viewFuncionario.getCargoFunc();
        double salario = viewFuncionario.getSalarioFunc();
        
        Cpf cpf = new Cpf(codigoCpf); 

        Funcionario novoFuncionario = new Funcionario(nome, endereco, telefone, email, cpf,usuario,senha,cargo,salario);
        listaFuncionario.add(novoFuncionario);
        OficinaPOO.getInstancia().addFuncionario(novoFuncionario);
        System.out.println("Funcionário adicionado com sucesso!");
         
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
                    adicionaFuncionario();
                }
//                case 2 -> {
//                    editaCliente();
//                }
//                case 3 -> {
//                    removeCliente();
//                }
//                case 4 -> {
//                    mostraCliente();
                }
            }
        }
    
}
