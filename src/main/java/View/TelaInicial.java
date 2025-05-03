package View;

import java.util.Scanner;

public class TelaInicial {
    
    Scanner leituraDados = new Scanner(System.in);
    
    public int mostraOpcoesDisponiveis(){
        System.out.println("1 - Cadastro clientes");
        System.out.println("2 - Cadastro funcionários");
        System.out.println("3 - Cadastro autopeças");
        System.out.println("4 - Gerar Ordem de Serviço");
        System.out.println("5 - Financeiro");
        System.out.println("6 - Sair");
        System.out.println("Digite para qual espaço deseja ser redirecionado ");
        
        return leituraDados.nextInt();
    }
}
