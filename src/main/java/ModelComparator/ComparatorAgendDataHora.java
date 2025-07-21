package ModelComparator;

import Model.Agendamento;
import java.util.Comparator;
import java.util.Calendar;

/**
 * Classe que implementa um comparador para ordenar agendamentos em ordem crescente
 * de data e horário. A comparação é feita hierarquicamente considerando ano, mês,
 * dia, hora e minuto.
 */
public class ComparatorAgendDataHora implements Comparator<Agendamento> {
    
    /**
     * Compara dois agendamentos com base em sua data e horário.
     * A comparação é feita na seguinte ordem hierárquica:
     * 1. Ano
     * 2. Mês  
     * 3. Dia
     * 4. Hora
     * 5. Minuto
     *
     * @param a1 Primeiro agendamento a ser comparado
     * @param a2 Segundo agendamento a ser comparado
     * @return Um valor negativo se a1 é anterior a a2,
     *         um valor positivo se a1 é posterior a a2,
     *         ou zero se ambos representam o mesmo momento
     */
    @Override
    public int compare(Agendamento a1, Agendamento a2) {
        Calendar a1DataHora = a1.getDataHora();
        Calendar a2DataHora = a2.getDataHora();
        
        int a1Ano = a1DataHora.get(Calendar.YEAR);
        int a2Ano = a2DataHora.get(Calendar.YEAR);
        
        int a1Mes = a1DataHora.get(Calendar.MONTH);
        int a2Mes = a2DataHora.get(Calendar.MONTH);
        
        int a1Dia = a1DataHora.get(Calendar.DAY_OF_MONTH);
        int a2Dia = a2DataHora.get(Calendar.DAY_OF_MONTH);
        
        int a1Hora = a1DataHora.get(Calendar.HOUR_OF_DAY);
        int a2Hora = a2DataHora.get(Calendar.HOUR_OF_DAY);
        
        int a1Minuto = a1DataHora.get(Calendar.MINUTE);
        int a2Minuto = a2DataHora.get(Calendar.MINUTE);
        
        if (a1Ano != a2Ano) {
            return a1Ano - a2Ano;
        }
        else if (a1Mes != a2Mes) {
            return a1Mes - a2Mes;
        }
        else if (a1Dia != a2Dia) {
            return a1Dia - a2Dia;
        }
        else if (a1Hora != a2Hora) {
            return a1Hora - a2Hora;
        }
        else if (a1Minuto != a2Minuto) {
            return a1Minuto - a2Minuto;
        }
        
        return 0;
    }
    
    /**
     * Método que sobrescreve o toString para exibir informações sobre o Comparator
     * 
     * @return informações sobre o Comparator
     */
    @Override
    public String toString() {
        return "Comparator que organiza os agendamentos pela data/hora";
    }
}
