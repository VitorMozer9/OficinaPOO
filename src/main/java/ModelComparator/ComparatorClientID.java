package ModelComparator;

import Model.Cliente;
import java.util.Comparator;

/**
 * Classe que implementa um comparador para ordenar clientes em ordem crescente
 * com base em seus IDs.
 */
public class ComparatorClientID implements Comparator<Cliente> {
    
    /**
     * Compara dois clientes com base em seus IDs.
     * 
     * @param c1 Primeiro cliente a ser comparado
     * @param c2 Segundo cliente a ser comparado
     * @return Um valor negativo se o ID de c1 é menor que o ID de c2,
     *         um valor positivo se o ID de c1 é maior que o ID de c2,
     *         ou zero se ambos têm o mesmo ID
     */
    @Override
    public int compare(Cliente c1, Cliente c2) {
        return c1.getIdCliente() - c2.getIdCliente();
    }
    
    /**
     * Método que sobrescreve o toString para exibir informações sobre o Comparator
     * 
     * @return informações sobre o Comparator
     */
    @Override
    public String toString() {
        return "Comparator que organiza os clientes pelo ID";
    }
}