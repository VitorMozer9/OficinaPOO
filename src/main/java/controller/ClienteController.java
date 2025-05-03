package controller;

import Model.Cliente;
import Model.Cpf;
import View.ClienteView;
import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    private ClienteView viewCliente = new ClienteView();
    private List<Cliente> listaClientes = new ArrayList<>();
    private final String salvaClientes = "clientes.json"; 
    
    public void adicionaCliente(){
        String nome = viewCliente.getNomeCliente();
        String endereco = viewCliente.getEnderecoCliente();
        String telefone = viewCliente.getFoneCliente();
        String email = viewCliente.getEmailCliente();
        String codigoCpf = viewCliente.getCpfCliente();

        Cpf cpf = new Cpf(codigoCpf); 

        Cliente novoCliente = new Cliente(nome, endereco, telefone, email, cpf);
        listaClientes.add(novoCliente);
        salvarClientes();
        System.out.println("Cliente adicionado com sucesso!");
         
    }
    
     private void salvarClientes() {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(salvaClientes)) {
            gson.toJson(listaClientes, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar clientes: " + e.getMessage());
        }
    }
     
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

 