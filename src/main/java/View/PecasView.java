package View;

import java.util.Scanner;

public class PecasView {
    Scanner leituraDados = new Scanner(System.in);
    
    public int mostraOpcoesPeca(){
        System.out.println("Digite a opção que deseja exucutar: \n");
        System.out.println("1 - Incluir Peça");
        System.out.println("2 - Editar Peça");
        System.out.println("3 - Remover Peça");
        System.out.println("4 - Mostrar dados de uma Peça");
        System.out.println("5 - Sair");
        System.out.println();

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    public int getIdPeca(){
        System.out.println("Digite o ID da Peça: ");
        return leituraDados.nextInt();
    }
    
    public String getDescricao(){
        System.out.println("Digite a descrição da peça: ");
        return leituraDados.nextLine();
    }
    
    public double getValorPeca(){
        System.out.println("Digite o valor da peça: ");
        return leituraDados.nextDouble();
    }
    
    public int editaCliente(){
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - Descrição");
        System.out.println("2 - Valor");
      
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
}
