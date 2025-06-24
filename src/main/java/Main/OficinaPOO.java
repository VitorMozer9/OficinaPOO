package Main;

import controller.Sistema;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * A classe OficinaPOO representa o ponto de entrada e o gerenciador central de dados da aplicação da oficina.
 * Implementa o padrão Singleton para garantir que exista apenas uma instância da oficina em toda a aplicação.
 * É responsável por armazenar as listas de clientes, funcionários, veículos e produtos, além de gerenciar o armazenamento
 * desses dados em um arquivo JSON.
 */
public class OficinaPOO {
    
    /**
     * Ponto de entrada principal da aplicação da oficina.
     * Configura a codificação de saída do console para UTF-8 e inicia o sistema principal através da classe (@link Sistema).
     *
     * @param args Argumentos da linha de comando (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {
         // Configura a saída do console para UTF-8, garantindo a exibição correta de caracteres especiais
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        // Cria uma nova instância do controlador do sistema
        Sistema sistemaOficina = new Sistema();
        
        // Inicia o fluxo principal do sistema
        sistemaOficina.iniciaSistema();
    }
    
}
