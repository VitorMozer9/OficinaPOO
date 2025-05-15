package View;

import java.util.Scanner;

/**
 * Classe responsável pelas interações com o usuário relacionadas as peças.
 * Realizando entradas e saídas de dados para operações como incluir, editar, remover, e mostrar dados da peça.
 */
public class PecasView {
    
    /**
     * Scanner para leitura de dados inseridos pelo usuário.
     */
    Scanner leituraDados = new Scanner(System.in);
    
    /**
     * Exibe as opções disponiveis no menu das peças e retorna a opção escolhida.
     * 
     * @return um número inteiro correspondente à opção selecionada pelo usuário.
     */
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
    
    /**
     * Solicita e retorna o ID da peça inserido pelo usuário.
     * 
     * @return ID da peça.
     */
    public int getIdPeca(){
        System.out.println("Digite o ID da Peça: ");
        return leituraDados.nextInt();
    }
    
    /**
     * Solicita e retorna a descrição da peça inserido pelo usuário.
     * 
     * @return Descrição da peça.
     */
    public String getDescricao(){
        System.out.println("Digite a descrição da peça: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita e retorna o valor da peça inserido pelo usuário.
     * 
     * @return Valor da peça.
     */
    public double getValorPeca(){
        System.out.println("Digite o valor da peça: ");
        return leituraDados.nextDouble();
    }
    
    /**
     * Exibe as opções de campos que podem ser editados e retorna a escolha do usuário.
     * 
     * @return número inteiro correspondente ao campo selecionado (1 ou 2). 
     */
    public int editaCliente(){
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - Descrição");
        System.out.println("2 - Valor");
      
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
}
