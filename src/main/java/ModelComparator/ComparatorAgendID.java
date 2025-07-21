package ModelComparator;

import Model.Agendamento;
import java.util.Comparator;

/**
 * Classe que implementa um comparador para ordenar agendamentos em ordem crescente
 * com base em seus IDs.
 */
public class ComparatorAgendID implements Comparator<Agendamento> {
    
    /**
     * Compara dois agendamentos com base em seus IDs.
     * 
     * @param a1 Primeiro agendamento a ser comparado
     * @param a2 Segundo agendamento a ser comparado
     * @return Um valor negativo se o ID de a1 é menor que o ID de a2,
     *         um valor positivo se o ID de a1 é maior que o ID de a2,
     *         ou zero se ambos têm o mesmo ID
     */
    @Override
    public int compare(Agendamento a1, Agendamento a2) {
        return a1.getIdAgendamento() - a2.getIdAgendamento();
    }
    
    /**
     * Método que sobrescreve o toString para exibir informações sobre o Comparator
     * 
     * @return informações sobre o Comparator
     */
    @Override
    public String toString() {
        return "Comparator que organiza os agendamentos pelo ID";
    }
}