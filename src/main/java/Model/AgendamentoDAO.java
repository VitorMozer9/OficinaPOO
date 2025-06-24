package Model;

import View.AgendamentoView;
import com.google.gson.reflect.TypeToken;
import controller.AgendamentoController;
import controller.ElevadorController;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AgendamentoDAO extends GenericDAO<Agendamento> {

    AgendamentoView viewAgendamento = new AgendamentoView();

    public AgendamentoDAO() {
        super("data/agendamentos.json", new TypeToken<List<Agendamento>>() {
        }.getType());
    }

    @Override
    public Comparable<?> getChave(Agendamento agendamento) {
        return agendamento.getIdAgendamento();
    }

    public int geraIdAgendamento() {
        int maiorId = 0;
        for (Agendamento cadaAgendamento : getLista()) {
            if (cadaAgendamento.getIdAgendamento() > maiorId) {
                maiorId = cadaAgendamento.getIdAgendamento();
            }
        }
        return maiorId + 1;
    }

    private boolean isHorarioDisponivel(Calendar horario, int tipoAgendamento) {
        int totalAgendamentosNoHorario = 0;
        int agendamentosComElevador = 0;

        for (Agendamento agendamento : getLista()) {
            Calendar agHora = agendamento.getDataHora();

            boolean mesmoDiaHora
                    = agHora.get(Calendar.YEAR) == horario.get(Calendar.YEAR)
                    && agHora.get(Calendar.MONTH) == horario.get(Calendar.MONTH)
                    && agHora.get(Calendar.DAY_OF_MONTH) == horario.get(Calendar.DAY_OF_MONTH)
                    && agHora.get(Calendar.HOUR_OF_DAY) == horario.get(Calendar.HOUR_OF_DAY);

            if (mesmoDiaHora) {
                totalAgendamentosNoHorario++;

                if (agendamento.getTipoAgendamento() == 1 || agendamento.getTipoAgendamento() == 2) {
                    agendamentosComElevador++;
                }

                if (tipoAgendamento == 1 || tipoAgendamento == 2) {
                    long diffMillis = Math.abs(horario.getTimeInMillis() - agHora.getTimeInMillis());
                    long diffMinutos = diffMillis / (60 * 1000);
                    if (diffMinutos < 60) {
                        return false;
                    }
                }
            }
        }

        if (tipoAgendamento == 1 || tipoAgendamento == 2) {
            return agendamentosComElevador < 3;
        }

        return totalAgendamentosNoHorario < 10;
    }

    public void adicionaAgendamento() {
        int idAgendamento = geraIdAgendamento();
        int idElevador = 0;      
        int idCliente = viewAgendamento.getIdCliente();
        String mecanicoResponsavel = viewAgendamento.getMecanicoResponsavel();
        String dataHora = viewAgendamento.getDataHorarioAgendamento();
        Calendar dataHorario = AgendamentoController.formataDataHora(dataHora);
        String statusAgendamento = "PENDENTE";

        int tipoAgendamento = viewAgendamento.getTipoAgendamento();
        boolean usaElevador = tipoAgendamento == 1 || tipoAgendamento == 2;

        if (tipoAgendamento == 1 || tipoAgendamento == 2) {
            idElevador = viewAgendamento.getIdElevador();
        }

        if (usaElevador && !isHorarioDisponivel(dataHorario, tipoAgendamento)) {
            System.out.println("Horário indisponível (atendimento em andamento , verifique as vagas disponíveis).");
            return;
        }

        Elevador elevadorAlocado = null;
        if (usaElevador) {
            elevadorAlocado = ElevadorController.verificaDisponibilidade(dataHorario);
            if (elevadorAlocado == null) {
                System.out.println("Todos os elevadores estão ocupados neste horário.");
                return;
            }
        }

        Agendamento novoAgendamento = new Agendamento(idAgendamento, idCliente, idElevador, tipoAgendamento, mecanicoResponsavel, statusAgendamento, dataHorario);

        if (usaElevador) {
            novoAgendamento.setIdElevador(elevadorAlocado.getIdElevador());
            ElevadorController.ocupaElevador(elevadorAlocado.getIdElevador());
        }

        if (isHorarioDisponivel(dataHorario, tipoAgendamento)) {
            adicionaDados(novoAgendamento);
            System.out.println("Agendamento adicionado com sucesso. ID: " + idAgendamento);
        } else {
            System.out.println("Horário já ocupado.");
        }
    }

    public void confirmaAgendamento() {
        int idAgendamento = viewAgendamento.getIdAgendamento();

        Agendamento agendamento = buscaPorChave(idAgendamento);

        if (agendamento == null) {
            System.out.println("Agendamento não foi encontrado!!");
            return;
        }

        agendamento.setStatusAgendamento("CONFIRMADO");
        salvarDados();
        System.out.println("Agendamento confirmado!");
    }

    public void cancelaAgendamento() {
        int idAgendamento = viewAgendamento.getIdAgendamento();

        Agendamento agendamento = buscaPorChave(idAgendamento);

        if (agendamento == null) {
            System.out.println("Agendamento não foi encontrado!!");
            return;
        }

        switch (agendamento.getStatusAgendamento()) {
            case "CONFIRMADO" -> {
                System.out.println("Não é possível cancelar um agendamento confirmado!!");
            }

            case "CANCELADO" -> {
                System.out.println("Agendamento já foi cancelado!");
            }

            default -> {

                viewAgendamento.mostraAgendamento(agendamento);

                String opcaoConfirmacao = viewAgendamento.confirmaExclusaoDoAgendamento();
                if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
                    System.out.println("Operação abortada!!");
                    return;
                }

                if (removeDados(agendamento)) {
                    agendamento.setStatusAgendamento("CANCELADO");
                    System.out.println("Agendamento cancelado!");
                }
            }
        }

    }

    public void mostrarAgendamento() {
        mostraDados(viewAgendamento::getIdAgendamento, viewAgendamento::mostraAgendamento);
    }

    public void editaAgendamento() {
        editaDados(viewAgendamento::getIdAgendamento, agendamento -> {
            viewAgendamento.mostraAgendamento(agendamento);
            int opcao = viewAgendamento.editaAgendamento();

            switch (opcao) {
                case 1 -> {
                    String novoHorarioStr = viewAgendamento.getDataHorarioAgendamento();
                    Calendar novoHorario = AgendamentoController.formataDataHora(novoHorarioStr);

                    if (isHorarioDisponivel(novoHorario, agendamento.getTipoAgendamento())) {
                        agendamento.setDataHora(novoHorario);
                        System.out.println("Horário atualizado com sucesso.");
                    } else {
                        System.out.println("Horário indisponível. Escolha outro.");
                    }
                }
                case 2 -> {
                    String novoResponsavel = viewAgendamento.getMecanicoResponsavel();
                    agendamento.setMecanicoResponsavel(novoResponsavel);
                    System.out.println("Mecânico responsável atualizado com sucesso.");
                }
                default ->
                    System.out.println("Opção inválida.");
            }
        });
    }

    public void mostraVagasDisponiveis() {
        System.out.println("AGENDA");
        List<Agendamento> agendamentos = getLista();

        for (int hora = 8; hora <= 18; hora++) {
            int totalClientesNoHorario = 0;
            int comElevador = 0;
            
            List<Agendamento> agendamentosNoHorario = new ArrayList<>();

            for (Agendamento agendamento : agendamentos) {
                Calendar agendamentoHora = agendamento.getDataHora();

                if (agendamentoHora.get(Calendar.HOUR_OF_DAY) == hora
                        && agendamentoHora.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)
                        && agendamentoHora.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)
                        && agendamentoHora.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH)) {

                    totalClientesNoHorario++;
                    agendamentosNoHorario.add(agendamento);

                    if (agendamento.getTipoAgendamento() == 1 || agendamento.getTipoAgendamento() == 2) {
                        comElevador++;
                    }
                }
            }

            int vagasElevador = 3 - comElevador;
            int vagasRestantes = 10 - totalClientesNoHorario;

            if (totalClientesNoHorario >= 10) {
                System.out.println(hora + "h - COMPLETO");
            } else {
                System.out.println(hora + "h - DISPONÍVEL | Vagas restantes: " + vagasRestantes
                        + " | Vagas com elevador: " + vagasElevador);
            }
        }
        for (Agendamento ag : agendamentos) {
            System.out.println("    (Cliente ID: " + ag.getIdCliente() +
                    ", Tipo: " + ag.retornaTipoAgendamento(ag.getTipoAgendamento()) +
                    ", Mecânico: " + ag.getMecanicoResponsavel() +
                    ", Status: " + ag.getStatusAgendamento() +
                    ", Elevador: " + (ag.getIdElevador() > 0 ? ag.getIdElevador() : "Sem elevador") + ")");
        }
    }

}
