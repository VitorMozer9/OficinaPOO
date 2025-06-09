package controller;

import Main.OficinaPOO;
import Model.Cliente;
import Model.ClienteDAO;
import Model.Cpf;
import View.ClienteView;

/**
 * Classe responsável pela gerência dos clientes.
 * Fornece métodos para adicionar, remover, buscar e exibir iformações dos clientes.
 */
public class ClienteController {
    private ClienteDAO clienteDao = new ClienteDAO();
    private ClienteView viewCliente = new ClienteView();
    
    /**
     * Gera um novo ID de cliente, baseado no maior ID já registrado.
     * 
     * @return novo ID do cliente (incrementado em relação ao maior ID atual).
     */
    public static int geraIdCliente(){
        int maiorIdCliente = 0;
        for (Cliente cadaCliente : OficinaPOO.getInstancia().getClientes()){
            if(cadaCliente.getIdCliente() > maiorIdCliente){
                maiorIdCliente = cadaCliente.getIdCliente();
            }
        }
        return maiorIdCliente + 1;
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
                    clienteDao.adicionaCliente();
                }
                case 2 -> {
                    clienteDao.editarCliente();
                }
                case 3 -> {
                    clienteDao.removeCliente();
                }
                case 4 -> {
                    clienteDao.mostrarCliente();
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
} 