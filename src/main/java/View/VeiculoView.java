package View;

import java.util.Scanner;

/**
 * Classe respnsável pelas interações com o usuário relacionadas ao veículo.
 * Realizando entradas e saída de dados para operações como incluir, editar, remover e mostrar dados de um veículo.
 */
public class VeiculoView {
    /**
     * Scanner para leitura de dados inseridos pelo usuário.
     */
    Scanner leituraDados = new Scanner(System.in);
    
    /**
     * Exibe as opções disponiveis no menu do veículo e retorna a opção escolhida.
     * 
     * @return um número inteiro correspondente à opção selecionada pelo usuário.
     */
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
    
    /**
     * Solicita e retorna o ID do cliente inserido pelo usuário.
     * 
     * @return ID do cliente dono do veículo.
     */
    public int getIdCliente(){
        System.out.println("Digite o ID do cliente dono do veículo: ");
        return leituraDados.nextInt();
        }
    
    /**
     * Solicita e retorna o modelo do veículo inserido pelo usuário.
     * 
     * @return Modelo do veículo.
     */
    public String getModeloVeiculo(){
        System.out.println("Digite o modelo do veículo: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita e retorna a placa do veículo inserido pelo usuário.
     * 
     * @return Placa do veículo.
     */
    public String getPlacaVeiculo(){
        System.out.println("Digite a placa do veículo: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita e retorna o status do veículo inserido pelo usuário.
     * 
     * @return Status do veículo.
     */
    public String getStatusVeiculo(){
        System.out.println("Digite o status do veículo: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita e retorna o ano de fabricação do veículo inserido pelo usuário.
     * 
     * @return Ano de fabricação do veículo.
     */
    public int getAnoDeFabricacao(){
        System.out.println("Digite o ano de fabricação do veículo: ");
        return leituraDados.nextInt();
    }
    
    /**
     * Solicita e retorna o peso do veículo inserido pelo usuário.
     * 
     * @return Peso do veículo.
     */
    public double getPesoVeiculo() {
        System.out.println("Digite o peso do veículo: ");
        return leituraDados.nextDouble();
    }
    
    /**
     * Exibe as opções de campos que podem ser editados e retorna a escolha do usuário.
     * 
     * @return número inteiro correspondente ao campo selecionado (1, 2, 3, 4 ou 5). 
     */
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

    

