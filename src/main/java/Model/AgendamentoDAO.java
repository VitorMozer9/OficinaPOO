package Model;

import View.AgendamentoView;
import com.google.gson.reflect.TypeToken;
import controller.AgendamentoController;
import controller.ElevadorController;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.List;

/**
 * Classe responsável por gerenciar os dados de {@link Agendamento}, incluindo criação, cancelamento,
 * confirmação e edição.
 * Os dados são armazenados em JSON e manipulados com a biblioteca GSON.
 * Utiliza a {@link AgendamentoView} como interface de interação com o usuário.
 */
public class AgendamentoDAO extends GenericDAO<Agendamento> {
    
    /**
     * Interface para entrada e exibição de dados do agendamento.
     */
    AgendamentoView viewAgendamento = new AgendamentoView();
    
    /**
     * Construtor padrão. 
     * Inicializa o DAO com o caminho do arquivo JSON de agendamentos e o tipo da lista de agendamentos.
     */
    public AgendamentoDAO() {
        super("data/agendamentos.json", new TypeToken<List<Agendamento>>() {}.getType());
    }
    
    /**
     * Retorna a chave única (ID) do agendamento.
     *
     * @param agendamento O objeto de agendamento.
     * @return O ID do agendamento.
     */
    @Override
    public Comparable<?> getChave(Agendamento agendamento){
        return agendamento.getIdAgendamento();
    }
    
    /**
     * Gera um novo ID de agendamento com base no maior ID já registrado.
     * 
     * @return Novo ID de agendamento. 
     */
    public int geraIdAgendamento(){
        int maiorId = 0;
        for (Agendamento cadaAgendamento : getLista()) {
            if (cadaAgendamento.getIdAgendamento() > maiorId) {
                maiorId = cadaAgendamento.getIdAgendamento();
            }
        }
        return maiorId + 1;
    }
    
    /**
     * Verifica se o horário fornecido está disponível, considerando um intervalo mínimo de 1 hora.
     * 
     * @param horario O horário desejado.
     * @return {@code true} se o horário estiver livre, {@code false} caso contrário.
     */
    private boolean isHorarioDisponivel(Calendar horario) {
    for (Agendamento a : getLista()) {
        long diffMillis = Math.abs(horario.getTimeInMillis() - a.getDataHora().getTimeInMillis());
        long diffMinutos = diffMillis / (60 * 1000);
        if (diffMinutos < 60) {
            return false; 
        }
    }
    return true;
}
    
    /**
     * Adiciona um novo agendamento, considerando regras de disponibilidade de horário e elevadores.
     */
    public void adicionaAgendamento(){
        int idAgendamento = geraIdAgendamento();
        int idCliente = viewAgendamento.getIdCliente();
        String dataHora = viewAgendamento.getDataHorarioAgendamento();
        Calendar dataHorario = AgendamentoController.formataDataHora(dataHora);
        String statusAgendamento = "PENDENTE";
        String mecanicoResponsavel = viewAgendamento.getMecanicoResponsavel();
        
        int tipoAgendamento = viewAgendamento.getTipoAgendamento();
        boolean usaElevador = tipoAgendamento == 1 || tipoAgendamento == 2;
        
        if (usaElevador && !isHorarioDisponivel(dataHorario)){
            System.out.println("Horário indisponível (atendimento em andamento , verifique as vagas disponíveis).");
            return;
        }
        
        Elevador elevadorAlocado = null;
        if (usaElevador){
            elevadorAlocado = ElevadorController.verificaDisponibilidade(dataHorario);
            if  (elevadorAlocado == null) {
                 System.out.println("Todos os elevadores estão ocupados neste horário.");
                return;
            }
        }
        
        Agendamento novoAgendamento = new Agendamento(idAgendamento, idCliente, elevadorAlocado.getIdElevador(), tipoAgendamento, mecanicoResponsavel,statusAgendamento ,dataHorario);
        
        if (usaElevador){
            novoAgendamento.setIdElevador(elevadorAlocado.getIdElevador());
            ElevadorController.ocupaElevador(elevadorAlocado.getIdElevador());
        }    
        
        if (isHorarioDisponivel(dataHorario)) {
            adicionaDados(novoAgendamento);
            System.out.println("Agendamento adicionado com sucesso. ID: " + idAgendamento);
        } else {
            System.out.println("Horário já ocupado.");
        }
    }
    
    /**
     *  Confirma um agendamento existente, alterando seu status para "CONFIRMADO".
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
     * Cancela um agendamento, se ele não estiver já confirmado ou cancelado.
     * Solicita confirmação do usuário antes de remover os dados.
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
     * Exibe as informações de um agendamento com base no ID fornecido pela view.
     */
    public void mostrarAgendamento(){
        mostraDados(viewAgendamento::getIdAgendamento, viewAgendamento::mostraAgendamento);
    }
    
    /**
     * Permite editar um agendamento existente. 
     * As opções incluem alteração de horário (se disponível) ou do mecânico responsável.
     */
    public void editaAgendamento(){
        editaDados(viewAgendamento::getIdAgendamento, agendamento -> {
        viewAgendamento.mostraAgendamento(agendamento);
        int opcao = viewAgendamento.editaAgendamento();

        switch (opcao) {
            case 1 -> {
                String novoHorarioStr = viewAgendamento.getDataHorarioAgendamento();
                Calendar novoHorario = AgendamentoController.formataDataHora(novoHorarioStr);

                if (isHorarioDisponivel(novoHorario)) {
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
            default -> System.out.println("Opção inválida.");
        }
    });
    }
    
}
