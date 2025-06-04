package controller;

import Main.OficinaPOO;
import Model.Cliente;
import Model.Cpf;
import View.ClienteView;
<<<<<<< HEAD
=======
import com.google.gson.Gson;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;   
import java.util.List;

/**
 * Classe responsável pela gerência dos clientes.
 * Fornece métodos para adicionar, remover, buscar e exibir iformações dos clientes.
 */
>>>>>>> 368e60068557d442e68f49c6984b8a4b905c92d7
public class ClienteController { 
    private ClienteView viewCliente = new ClienteView();
    
    /**
     * Gera um novo ID de cliente, baseado no maior ID já registrado.
     * 
     * @return novo ID do cliente (incrementado em relação ao maior ID atual).
     */
    public int geraIdCliente(){
        int maiorIdCliente = 0;
        for (Cliente cadaCliente : OficinaPOO.getInstancia().getClientes()){
            if(cadaCliente.getIdCliente() > maiorIdCliente){
                maiorIdCliente = cadaCliente.getIdCliente();
            }
        }
        return maiorIdCliente + 1;
    }
    
    /**
     * Busca cliente com base no ID informado.
     * 
     * @param id ID do cliente a ser buscado.
     * @return Objeto Cliente relacionado ao ID, ou null se não encontrado. 
     */
    public Cliente buscarCliente(int id){
        try{
            for(Cliente clientes : OficinaPOO.getInstancia().getClientes()){
                if (clientes.getIdCliente() == id){
                    return clientes;
                }
            }   
        }
        catch(Exception e){
            System.out.println("Falha ao buscar cliente!!!");
            return null;
        }
        return null;
    }
    
    /**
     * Coleta dados do cliente a partir da view, valida o CPF e adiciona o novo cliente ao sistema.
     * Exibe mensagem de sucesso ou erro de validação.
     */
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
        OficinaPOO.getInstancia().addCliente(novoCliente);
        if (OficinaPOO.salvarDados(OficinaPOO.getInstancia())){
            System.out.println("Cliente adicionado com sucesso! ID do cliente: " + idCliente);
        }else{
            System.out.println("Erro ao adicionar cliente!");
        }
    }
    
    /**
     * Mostra as informações dos clientes com base no ID informado pela view 
     */
    public void mostrarCliente(){
        int id = viewCliente.getIdCliente();
        Cliente cliente = buscarCliente(id);
        
        if (cliente == null){
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        viewCliente.mostraCliente(cliente);
    }
    
    /**
     * Remove um cliente do sistema após confirmação do usuário.
     * Exibe mensagem de erro, confirmação ou falha na operação.
     */
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
    
    /**
     * Edita os dados anteriores fornecidos para Cliente.
     * Selecionando de acordo com a opção selecionada pelo usuário.
     */
    public void editarCliente(){
        int idCliente = viewCliente.getIdCliente();
        Cliente cliente = buscarCliente(idCliente);
        
        if (cliente == null){
            System.out.println("Cliente não encontrado!!");
            return;
        }
        
        viewCliente.mostraCliente(cliente);
        
        
        int opcaoModCliente = viewCliente.editaCliente();
        
        switch(opcaoModCliente){
            case 1 -> {
                String novoEndereco = viewCliente.getEnderecoCliente();
                editaEndereco(cliente, novoEndereco);
            }
            case 2 -> {
                String novoTelefone = viewCliente.getFoneCliente();
                editaTelefone(cliente, novoTelefone);
            }
            case 3 -> {
                String novoEmail = viewCliente.getEmailCliente();
                editaEmail(cliente, novoEmail);
            }
            
        }
        
        if (OficinaPOO.salvarDados(OficinaPOO.getInstancia())) {
            System.out.println("Alterações salvas com sucesso.");
        } else {
            System.out.println("Erro ao salvar alterações.");
        }
    }
    
    /**
     * Edita o endereço que foi fornecido anteriormente para cliente.
     * 
     * @param cliente Cliente selecionado para ser editado.
     * @param novoEndereco Novo endereço fornecido para o Cliente, sobrescrevendo o anterior.
     */
    public void editaEndereco(Cliente cliente, String novoEndereco){
        cliente.setEndereco(novoEndereco);
    }
    
    /**
     * Edita o telefone que foi fornecido anteriormente para cliente.
     * 
     * @param cliente Cliente selecionado para ser editado.
     * @param novoTelefone Novo telefone fornecido para o Cliente, sobrescrevendo o anterior.
     */
    public void editaTelefone(Cliente cliente, String novoTelefone){
        cliente.setTelefone(novoTelefone);
    }
    
    /**
     * Edita o email que foi anteriormente fornecido para cliente.
     * 
     * @param cliente Cliente selecinado para ser editado.
     * @param novoEmail Novo email fornecido para o Cliente, sobrescrevendo o anterior.
     */
    public void editaEmail(Cliente cliente, String novoEmail){
        cliente.setEmail(novoEmail);
    }   
    
     
    /**
     * Exibe o menu de opções do cliente e executa a ação selecionada pelo usuário.
     * O menu permanece ativo até que o usuário escolha a opção de sair.
     */
    public void executaMenuCliente(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewCliente.mostraOpcoesCliente();
            
            switch (opcao){
                case 1 -> {
                    adicionaCliente();
                }
                case 2 -> {
                    editarCliente();
                }
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