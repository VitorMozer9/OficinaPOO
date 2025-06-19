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