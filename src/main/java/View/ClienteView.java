package View;

import java.util.Scanner;

public class ClienteView {
    Scanner leituraDados = new Scanner(System.in);
    
    public int exibirMenu(){
        int opcao = 0;
        
        while (opcao != 5){
            System.out.println("1 - Incluir Cliente (Fazer Reserva)");
            System.out.println("2 - Editar Cliente");
            System.out.println("3 - Remover Cliente");
            System.out.println("4 - Mostrar dados de um Cliente");
            System.out.println("5 - Sair");
            System.out.println();
            
            System.out.println("Digite a opcao: ");
            opcao = leituraDados.nextInt();
            leituraDados.nextLine();
            return opcao;
            
        }
        return 4;
    }
    
    public String getNomeCliente(){
        System.out.println("Digite o nome do cliente: ");
        return leituraDados.nextLine();
        }
    
}
