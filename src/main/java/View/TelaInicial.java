package View;

import java.util.Scanner;

/**
 * Classe responsável pela tela inicial do sistema.
 * Definindo o menu principal que permite acesso a algumas áreas do sistema e mostrando mensagens relacionadas a manipulação dos dados.
 */
public class TelaInicial {
    
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
        System.out.println("1 - Cadastro clientes");
        System.out.println("2 - Cadastro funcionários");
        System.out.println("3 - Cadastro de veículos");
        System.out.println("4 - Cadastro de Peças");
        System.out.println("5 - Loja de Peças");
        System.out.println("6 - Agendamento");
        System.out.println("7 - Gerar Ordem de Serviço");
        System.out.println("8 - Financeiro");
        System.out.println("9 - Sair");
        System.out.println("Digite para qual espaço deseja ser redirecionado \n");
        
        return leituraDados.nextInt();
    }
}
