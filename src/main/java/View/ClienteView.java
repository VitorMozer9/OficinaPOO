package View;

import Model.Cliente;
import java.util.Scanner;

public class ClienteView {
    Scanner leituraDados = new Scanner(System.in);
    
    public int mostraOpcoesCliente(){
        System.out.println("Digite a opção que deseja exucutar: \n");
        System.out.println("1 - Incluir Cliente");
        System.out.println("2 - Editar Cliente");
        System.out.println("3 - Remover Cliente");
        System.out.println("4 - Mostrar dados de um Cliente");
        System.out.println("5 - Sair");
        System.out.println();

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    public String getNomeCliente(){
        System.out.println("Digite o nome do cliente: ");
        return leituraDados.nextLine();
        }
    
    public String getEnderecoCliente(){
        System.out.println("Digite o endereço do cliente: ");
        return leituraDados.nextLine();
    }
    
    public String getFoneCliente(){
        System.out.println("Digite o telefone do cliente: ");
        return leituraDados.nextLine();
    }
    
    public String getEmailCliente(){
        System.out.println("Digite o email do cliente: ");
        return leituraDados.nextLine();
    }
    
    public String getCpfCliente(){
        System.out.println("Digite o CPF do cliente: ");
        return leituraDados.nextLine();
    }
    
    public int getIdCliente(){
        System.out.println("Digite o ID do cliente: ");
        int idCliente = leituraDados.nextInt();
        leituraDados.nextLine();
        return idCliente;
    }
    
    public void mostraCliente(Cliente cliente){
        System.out.println("ID: " + cliente.getIdCliente()      + "\n" + 
                           "Nome: " + cliente.getNome()         + "\n" +
                           "Endereço: " + cliente.getEndereco() + "\n" +
                           "Telefone: " + cliente.getEndereco() + "\n" +
                           "E-Mail: " + cliente.getEmail()      + "\n" + 
                           "CPF: " + cliente.getCpf());
    }
    
    public String confirmaExclusaoCliente(){
        System.out.println("Tem certeza que deseja remover este cliente? \n"
                         + "Digite [S] para confirmar ou [N] para abortar a operação!!");
        return leituraDados.nextLine();
    }
    
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
