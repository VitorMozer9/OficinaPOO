package Model;

import View.AgendamentoView;
import com.google.gson.reflect.TypeToken;
import controller.AgendamentoController;
import controller.ElevadorController;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe responsável por gerenciar os dados dos agendamentos, realizando operações 
 * de persistência, verificação de disponibilidade e controle das interações com a view.
 */
public class AgendamentoDAO extends GenericDAO<Agendamento> {    
    private static AgendamentoDAO instancia;
    AgendamentoView viewAgendamento = new AgendamentoView();

    /**
     * Construtor padrão da classe.
     * Inicializando o DAO com o caminho e tipo de dados utilizados.
     */
    private AgendamentoDAO() {
        super("data/agendamentos.json", new TypeToken<List<Agendamento>>() {}.getType());
    }
    
    public static AgendamentoDAO getInstancia(){
        if (instancia == null) {
            instancia = new AgendamentoDAO();
        }
        return instancia;
    }

    /**
     * Retorna a chave única do agendamento para a indentificação do mesmo.
     * 
     * @param agendamento Agendamento a ser analisado.
     * @return ID do agendamento.
     */
    @Override
    public Comparable<?> getChave(Agendamento agendamento) {
        return agendamento.getIdAgendamento();
    }

    /**
     * Gera um novo ID único baseado nos IDs existentes.
     * 
     * @return Novo ID gerado.
     */
    public int geraIdAgendamento() {
        int maiorId = 0;
        for (Agendamento cadaAgendamento : getLista()) {
            if (cadaAgendamento.getIdAgendamento() > maiorId) {
                maiorId = cadaAgendamento.getIdAgendamento();
            }
        }
        return maiorId + 1;
    }

    /**
     * Verifica se há disponibilidade para agendar no horário informado.
     * 
     * @param horario Data e hora desejados.
     * @param tipoAgendamento Tipo do agendamento (1, 2 usam elevador).
     * @return true se o horário está disponível, false caso contrário. 
     */
    private boolean isHorarioDisponivel(Calendar horario, int tipoAgendamento) {
        int totalAgendamentosNoHorario = 0;
        int agendamentosComElevador = 0;

        for (Agendamento agendamento : getLista()) {
            Calendar agHora = agendamento.getDataHora();

            long diffMillis = Math.abs(horario.getTimeInMillis() - agHora.getTimeInMillis());
            long diffMinutos = diffMillis / (60 * 1000);

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
            }

            if ((tipoAgendamento == 1 || tipoAgendamento == 2) && diffMinutos < 60) {
                if (agendamento.getTipoAgendamento() == 1 || agendamento.getTipoAgendamento() == 2) {
                    return false;
                }
            }
        }

        if (tipoAgendamento == 1 || tipoAgendamento == 2) {
            return agendamentosComElevador < 3;
        } else {
            return totalAgendamentosNoHorario < 10;
        }
    }

    /**
     * Adiciona um novo agendamento com base nas entradas do usuário.
     * Validando o horário, disponibilidade e aloca o elevador se necessário.
     */
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

    /**
     * Confirma um agendamento existente, alterando o seu status.
     */
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

    /**
     * Cancela um agendamento, solicitando a confirmação do usuário.
     */
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

    /**
     * Exibe um agendamento com base no ID informado pelo usuário.
     */
    public void mostrarAgendamento() {
        mostraDados(viewAgendamento::getIdAgendamento, viewAgendamento::mostraAgendamento);
    }

    /**
     * Permite editar os dados de um agendamento: horário ou mecânico responsável.
     */
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

    /**
     * Mostra a agenda do dia atual com os horários disponíveis e ocupados, indicando também a quantidade
     * de vagas com elevador e totais.
     */
    public void mostraVagasDisponiveis() {
        System.out.println("AGENDA");
        List<Agendamento> agendamentos = getLista();

        Map<String, List<Agendamento>> agendamentosPorData = new HashMap<>();

        for (Agendamento ag : agendamentos) {
            Calendar dataAg = ag.getDataHora();
            String chaveData = String.format("%04d-%02d-%02d",
                    dataAg.get(Calendar.YEAR),
                    dataAg.get(Calendar.MONTH) + 1,
                    dataAg.get(Calendar.DAY_OF_MONTH));

            agendamentosPorData.computeIfAbsent(chaveData, k -> new ArrayList<>()).add(ag);
        }

        if (agendamentosPorData.isEmpty()) {
            System.out.println("Nenhum agendamento encontrado.");
            mostrarHorariosVazios();
            return;
        }

        for (Map.Entry<String, List<Agendamento>> entry : agendamentosPorData.entrySet()) {
            System.out.println("\n DATA: " + entry.getKey());
            mostrarAgendamentosDaData(entry.getValue());
        }
    }

    private void mostrarHorariosVazios() {
        for (int hora = 8; hora <= 18; hora++) {
            System.out.println(hora + "h - DISPONÍVEL | Vagas restantes: 10 | Vagas com elevador: 3");
        }
    }

    private void mostrarAgendamentosDaData(List<Agendamento> agendamentosData) {
        for (int hora = 8; hora <= 18; hora++) {
            int totalClientesNoHorario = 0;
            int comElevador = 0;
            List<Agendamento> agendamentosNoHorario = new ArrayList<>();

            for (Agendamento agendamento : agendamentosData) {
                Calendar agendamentoHora = agendamento.getDataHora();
                if (agendamentoHora.get(Calendar.HOUR_OF_DAY) == hora) {
                    totalClientesNoHorario++;
                    agendamentosNoHorario.add(agendamento);
                    if (agendamento.getTipoAgendamento() == 1 || agendamento.getTipoAgendamento() == 2) {
                        comElevador++;
                    }
                }
            }

            int vagasElevador = Math.max(0, 3 - comElevador);
            int vagasRestantes = Math.max(0, 10 - totalClientesNoHorario);

            if (totalClientesNoHorario >= 10) {
                System.out.println(hora + "h - COMPLETO");
            } else {
                System.out.println(hora + "h - DISPONÍVEL | Vagas restantes: " + vagasRestantes
                        + " | Vagas com elevador: " + vagasElevador);
            }

            if (!agendamentosNoHorario.isEmpty()) {
                for (Agendamento ag : agendamentosNoHorario) {
                    System.out.println("    → Cliente ID: " + ag.getIdCliente()
                            + ", Tipo: " + ag.retornaTipoAgendamento(ag.getTipoAgendamento())
                            + ", Mecânico: " + ag.getMecanicoResponsavel()
                            + ", Status: " + ag.getStatusAgendamento()
                            + ", Elevador: " + (ag.getIdElevador() > 0 ? ag.getIdElevador() : "Sem elevador"));
                }
            }
        }
    }

    
    @Override
    public String toString() {
        return "AgendamentoDAO | Total de agendamentos: " + getLista().size();
    }
}


