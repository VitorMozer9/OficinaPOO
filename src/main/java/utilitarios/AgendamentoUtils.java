package utilitarios;

import Model.Agendamento;
import java.util.Calendar;
import java.util.List;

public class AgendamentoUtils {
    public void mostraAgenda(List<Agendamento> agendamentos) {
    System.out.println("\nAGENDAMENTOS");
    for (int hora = 8; hora <= 18; hora++) {
        boolean ocupado = false;
        for (Agendamento agendamento : agendamentos) {
            int agendamentoHora = agendamento.getDataHora().get(Calendar.HOUR_OF_DAY);
            if (agendamentoHora == hora) {
                System.out.println(hora + "Horário OCUPADO pelo Cliente " + agendamento.getIdCliente()); //+ " (Elevador " + agendamento.getElevadorId() + ")");
                ocupado = true;
                break;
            }
        }
        if (!ocupado) {
            System.out.println(hora + "Horário DISPONÍVEL");
        }
    }
}
    
}
