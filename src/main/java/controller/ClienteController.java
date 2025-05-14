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
    private ClienteView viewCliente = new ClienteView();
    //List<Cliente> listaClientes = OficinaPOO.getInstancia().getClientes();
    //private List<Cliente> listaClientes = new ArrayList<>();
    //private final String salvaClientes = "data%sclientes.json".formatted(File.separator); 
    
    public int geraIdCliente(){
        //List<Cliente> listaClientes = OficinaPOO.getInstancia().getClientes();
        int maiorIdCliente = 0;
        for (Cliente cadaCliente : OficinaPOO.getInstancia().getClientes()){
            if(cadaCliente.getIdCliente() > maiorIdCliente){
                maiorIdCliente = cadaCliente.getIdCliente();
            }
        }
        return maiorIdCliente + 1;
    }
    
    public Cliente buscarCliente(int id){
        for(Cliente clientes : OficinaPOO.getInstancia().getClientes()){
            if (clientes.getIdCliente() == id){
                return clientes;
            }    
        }
        return null;
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
        //listaClientes.add(novoCliente);
        OficinaPOO.getInstancia().addCliente(novoCliente);
        System.out.println("Cliente adicionado com sucesso! ID do cliente: " + idCliente);
         
    }
    
    public void mostrarCliente(){
        int id = viewCliente.getIdCliente();
        Cliente cliente = buscarCliente(id);
        viewCliente.mostraCliente(cliente);
    }
    
    public void removeCliente(){
        int id = viewCliente.getIdCliente();
        Cliente cliente = buscarCliente(id);
        
        if (cliente == null){
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        viewCliente.mostraCliente(cliente);
        
        String opcaoConfirmacao = viewCliente.confirmaExclusaoCliente();
        if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação abortada!!");
            return;
        }
        
        OficinaPOO.getInstancia().getClientes().remove(cliente);
        
        if(OficinaPOO.salvarDados(OficinaPOO.getInstancia())){
            System.out.println("Cliente removido com sucesso!");
        }else{
            System.out.println("Falha ao remover cliente! :(");
        }
        
        
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
                case 3 -> {
                    removeCliente();
                }
                case 4 -> {
                    mostrarCliente();
                }
            }
        }
    }
} 