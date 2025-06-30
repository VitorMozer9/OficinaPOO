package View;

import java.util.Scanner;

/**
 * Classe responsável pela tela inicial do sistema.
 * Definindo o menu principal que permite acesso a algumas áreas do sistema e mostrando mensagens relacionadas a manipulação dos dados.
 */
public class ViewPrincipal {
    
    /**
     * Scanner para leitura e entrada do usuário.
     */
    Scanner leituraDados = new Scanner(System.in);
    
    /**
     * Exibe o menu principal com todas as areas disponiveis do sistema.
     * Apresenta algumas funcionalidades como: cadastro de clientes, cadastro de funcionários, cadastro de peças, gerar ordem de serviço e
     * acessar a área financeira do sistema.
     * 
     * @return Número inteiro representando a área selecionada pelo usuário.
     */
    public int mostraOpcoesDisponiveis(){
        System.out.println("1  - Controle de pontos");
        System.out.println("2  - Cadastro clientes");
        System.out.println("3  - Cadastro funcionários");
        System.out.println("4  - Cadastro de veículos");
        System.out.println("5  - Controle de estoque");
        System.out.println("6  - Controle de vendas (Loja)");
        System.out.println("7  - Agendamento");
        System.out.println("8  - Gerar Ordem de Serviço");
        System.out.println("9  - Financeiro");
        System.out.println("10 - Sair");
        System.out.println("Digite para qual espaço deseja ser redirecionado \n");
        
        return leituraDados.nextInt();
    }
}
