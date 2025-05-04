package controller;

import Main.OficinaPOO;
import Model.Cpf;
import Model.Funcionario;
import View.FuncionarioView;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioController {
     private FuncionarioView viewFuncionario = new FuncionarioView();
    private List<Funcionario> listaFuncionario = new ArrayList<>();
    //private final String salvaClientes = "data%sclientes.json".formatted(File.separator); 
    
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
    
//     private void salvarClientes() {
//        Gson gson = new Gson();
//        try (FileWriter writer = new FileWriter(salvaClientes)) {
//            gson.toJson(listaClientes, writer);
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar cliente: " + e.getMessage());
//        }
//    }
     
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
