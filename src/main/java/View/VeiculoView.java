package View;

import java.util.Scanner;

public class VeiculoView {
    Scanner leituraDados = new Scanner(System.in);
    
    public int mostraOpcoesFuncionario(){
        System.out.println("Digite a opção que deseja exucutar: \n");
        System.out.println("1 - Incluir Veículo");
        System.out.println("2 - Editar Veículo");
        System.out.println("3 - Remover Veículo");
        System.out.println("4 - Mostrar dados de um Veículo");
        System.out.println("5 - Sair");
        System.out.println();

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    public int getIdCliente(){
        System.out.println("Digite o ID do cliente dono do veículo: ");
        return leituraDados.nextInt();
        }
    
    public String getModeloVeiculo(){
        System.out.println("Digite o modelo do veículo: ");
        return leituraDados.nextLine();
    }
    
    public String getPlacaVeiculo(){
        System.out.println("Digite a placa do veículo: ");
        return leituraDados.nextLine();
    }
    
    public String getStatusVeiculo(){
        System.out.println("Digite o status do veículo: ");
        return leituraDados.nextLine();
    }
    
    public int getAnoDeFabricacao(){
        System.out.println("Digite o ano de fabricação do veículo: ");
        return leituraDados.nextInt();
    }
    
    public double getPesoVeiculo() {
        System.out.println("Digite o peso do veículo: ");
        return leituraDados.nextDouble();
    }
    
    public int editaCliente(){
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - ID Cliente");
        System.out.println("2 - Modelo");
        System.out.println("3 - Status veículo");
        System.out.println("4 - Ano de Fabricação");
        System.out.println("5 - Peso");
      
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
}

    

