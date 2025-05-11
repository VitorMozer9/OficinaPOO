package controller;

import Main.OficinaPOO;
import Model.Cliente;
import Model.Cpf;
import View.ClienteView;
import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;   
import java.util.List;

public class ClienteController {
    //public static int ultimoIdCliente = 0;  
    private ClienteView viewCliente = new ClienteView();
    private List<Cliente> listaClientes = new ArrayList<>();
    //private final String salvaClientes = "data%sclientes.json".formatted(File.separator); 
    
    public int geraIdCliente(){
        List<Cliente> listaClientes = OficinaPOO.getInstancia().getClientes();
        int maiorIdCliente = 0;
        for (Cliente cadaCliente : listaClientes){
            if(cadaCliente.getIdCliente() > maiorIdCliente){
                maiorIdCliente = cadaCliente.getIdCliente();
            }
        }
        return maiorIdCliente + 1;
    }
    
    public void adicionaCliente(){
        int idCliente = geraIdCliente();
        String nome = viewCliente.getNomeCliente();
        String endereco = viewCliente.getEnderecoCliente();
        String telefone = viewCliente.getFoneCliente();
        String email = viewCliente.getEmailCliente();
        String codigoCpf = viewCliente.getCpfCliente();
        if (!Cpf.validaCPF(codigoCpf)){
            System.out.println("CPF inválido!!");
            return;
        }
        
        Cpf cpf = new Cpf(codigoCpf); 

        Cliente novoCliente = new Cliente(nome, endereco, telefone, email, cpf,idCliente);
        listaClientes.add(novoCliente);
        OficinaPOO.getInstancia().addCliente(novoCliente);
        System.out.println("Cliente adicionado com sucesso! ID do cliente: " + idCliente);
         
    }
    
//     private void salvarClientes() {
//        Gson gson = new Gson();
//        try (FileWriter writer = new FileWriter(salvaClientes)) {
//            gson.toJson(listaClientes, writer);
//        } catch (IOException e) {
//            System.out.println("Erro ao salvar cliente: " + e.getMessage());
//        }
//    }
     
    public void executaMenuCliente(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewCliente.mostraOpcoesCliente();
            
            switch (opcao){
                case 1 -> {
                    adicionaCliente();
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

 