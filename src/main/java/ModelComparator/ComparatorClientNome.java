package ModelComparator;

import Model.Cliente;
import java.util.Comparator;

/**
 * Classe que implementa um comparador para ordenar clientes em ordem alfabética crescente
 * com base em seus nomes.
 */
public class ComparatorClientNome implements Comparator<Cliente> {
    
    /**
     * Compara dois clientes com base em seus nomes em ordem alfabética.
     * Utiliza o método compareToIgnoreCase para realizar a comparação
     * alfabética ignorando diferenças de maiúsculas e minúsculas.
     * 
     * @param c1 Primeiro cliente a ser comparado
     * @param c2 Segundo cliente a ser comparado
     * @return Um valor negativo se o nome de c1 vem antes do nome de c2 alfabeticamente,
     *         um valor positivo se o nome de c1 vem depois do nome de c2 alfabeticamente,
     *         ou zero se os nomes são iguais
     */
    @Override
    public int compare(Cliente c1, Cliente c2) {
        return c1.getNome().compareToIgnoreCase(c2.getNome());
    }
    
    /**
     * Método que sobrescreve o toString para exibir informações sobre o Comparator
     * 
     * @return informações sobre o Comparator
     */
    @Override
    public String toString() {
        return "Comparator que organiza os clientes pelo nome de forma alfabética";
    }
}