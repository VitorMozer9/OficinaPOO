package View;

import Model.Cliente;
import java.util.Scanner;

/**
 * Classe responsável por interações com o usuário relacionadas ao cliente.
 * Realiza entradas e saídas de dados para operações como cadastro, edição e remoção.
 */
public class ClienteView {
    
    /**
     * Scanner para leitura de dados inseridos pelo usuário.
     */
    Scanner leituraDados = new Scanner(System.in);
    
    /**
     * Exibe as opções disponiveis no menu do cliente e retorna a opção escolhida.
     * 
     * @return um número inteiro correspondente à opção selecionada pelo usuário.
     */
    public int mostraOpcoesCliente(){
        System.out.println("Digite a opção que deseja exucutar: \n");
        System.out.println("1 - Incluir Cliente");
        System.out.println("2 - Editar Cliente");
        System.out.println("3 - Remover Cliente");
        System.out.println("4 - Mostrar dados de um Cliente");
        System.out.println("5 - Sair \n");
        System.out.println();

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    /**
     * Solicita e retorna o nome do cliente inserido pelo usuário.
     * 
     * @return nome do cliente.
     */
    public String getNomeCliente(){
        System.out.println("Digite o nome do cliente: ");
        return leituraDados.nextLine();
        }
    
    /**
     * Solicita e retorna o endereço do cliente inserido pelo usuário.
     * 
     * @return endereço do cliente.
     */
    public String getEnderecoCliente(){
        System.out.println("Digite o endereço do cliente: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita e retorna o telefone do cliente inserido pelo usuário.
     * 
     * @return telefone do cliente.
     */
    public String getFoneCliente(){
        System.out.println("Digite o telefone do cliente: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita e retorna o email do cliente inserido pelo usuário.
     * 
     * @return email do cliente.
     */
    public String getEmailCliente(){
        System.out.println("Digite o email do cliente: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita e retorna o CPF do cliente inserido pelo usuário.
     * 
     * @return  CPF do cliente.
     */
    public String getCpfCliente(){
        System.out.println("Digite o CPF do cliente: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicida e retorna o ID do cliente digitado pelo usuário.
     * 
     * @return ID do cliente.
     */
    public int getIdCliente(){
        System.out.println("Digite o ID do cliente: ");
        int idCliente = leituraDados.nextInt();
        leituraDados.nextLine();
        return idCliente;
    }
    
    /**
     * Exibe os dados completos do cliente inserido.
     * 
     * @param cliente objeto Cliente o qual os dados serão inseridos.
     */
    public void mostraCliente(Cliente cliente){
        System.out.println("ID: " + cliente.getIdCliente()      + "\n" + 
                           "Nome: " + cliente.getNome()         + "\n" +
                           "Endereço: " + cliente.getEndereco() + "\n" +
                           "Telefone: " + cliente.getTelefone() + "\n" +
                           "E-Mail: " + cliente.getEmail()      + "\n" + 
                           "CPF: " + cliente.getCpf()           + "\n");
    }
    
    /**
     * Solicita a confirmação do usuário para excluir um cliente.
     * 
     * @return Resposta do usuário  ("S" ou "N").
     */
    public String confirmaExclusaoCliente(){
        System.out.println("Tem certeza que deseja remover este cliente? \n"
                         + "Digite [S] para confirmar ou [N] para abortar a operação!!");
        return leituraDados.nextLine();
    }
    
    /**
     * Exibe as opções de campos que podem ser editados e retorna a escolha do usuário.
     * 
     * @return número inteiro correspondente ao campo selecionado (1,2 ou 3).
     */
    public int editaCliente(){
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - Endereço");
        System.out.println("2 - Telefone");
        System.out.println("3 - E-mail");
      
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

}
