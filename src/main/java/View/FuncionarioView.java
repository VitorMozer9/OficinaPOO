package View;

import java.util.Scanner;

public class FuncionarioView {
    Scanner leituraDados = new Scanner(System.in);
    
    public int mostraOpcoesFuncionario(){
        System.out.println("Digite a opção que deseja exucutar: \n");
        System.out.println("1 - Incluir Funcionário");
        System.out.println("2 - Editar Funcionário");
        System.out.println("3 - Remover Funcionário");
        System.out.println("4 - Mostrar dados de um Funcionário");
        System.out.println("5 - Sair");
        System.out.println();

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    public String getNomeFunc(){
        System.out.println("Digite o nome do Funcionário: ");
        return leituraDados.nextLine();
        }
    
    public String getEnderecoFunc(){
        System.out.println("Digite o endereço do Funcionário: ");
        return leituraDados.nextLine();
    }
    
    public String getFoneFunc(){
        System.out.println("Digite o telefone do Funcionário: ");
        return leituraDados.nextLine();
    }
    
    public String getEmailFunc(){
        System.out.println("Digite o email do Funcionário: ");
        return leituraDados.nextLine();
    }
    
    public String getCpfFunc(){
        System.out.println("Digite o CPF do Funcionário: ");
        return leituraDados.nextLine();
    }
    
    public String getUsuarioFunc() {
        System.out.println("Digite o login de usuário do funcionário: ");
        return leituraDados.nextLine();
    }
    
    public String getSenhaFunc() {
        System.out.println("Digite a senha do funcionário: ");
        return leituraDados.nextLine();
    }
    
    public String getCargoFunc() {
       System.out.println("Digite o cargo do funcionário: ");
       return leituraDados.nextLine();
    }
    
     public double getSalarioFunc() {
        System.out.println("Digite o salário do funcionário: ");
        return leituraDados.nextDouble();
    }
    
    public int editaCliente(){
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - Endereço");
        System.out.println("2 - Telefone");
        System.out.println("3 - E-mail");
        System.out.println("4 - Usuário");
        System.out.println("5 - Senha");
      
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
}
