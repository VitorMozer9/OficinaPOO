package View;

import Model.Agendamento;
import java.util.Scanner;

/**
 * Classe responsável por interações com o usuário relacionadas ao agendamento de serviços.
 * Exibe menus, solicita dados e apresenta informações sobre agendamentos.
 */
public class AgendamentoView {
    Scanner leituraDados = new Scanner(System.in);
    
    /**
     * Exibe as opções disponíveis para o usuário e retorna a opção escolhida.
     * 
     * @return Número da opção escolhida. 
     */
    public int mostraOpcoesAgendamento(){
        System.out.println("Digite a opção que deseja exucutar: ");
        System.out.println("1 - Realizar agendamento");
        System.out.println("2 - Confirmar agendamento");
        System.out.println("3 - Cancelar agendamento");
        System.out.println("4 - Editar agendamento");
        System.out.println("5 - Mostrar agendamento");
        System.out.println("6 - Mostrar vagas disponíveis");
        System.out.println("7 - Sair");

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
        
    }
    
    /**
     * Solicita o ID do agendamento ao usuário.
     * 
     * @return ID do agendamento informado. 
     */
    public int getIdAgendamento(){
        System.out.println("Digite o ID do agendamento: ");
        return leituraDados.nextInt();
        }
    
    /** 
     * Solicita o ID do cliente ao usuário.
     * 
     * @return ID do cliente informado.
     */
    public int getIdCliente(){
        System.out.println("Digite o ID do cliente: ");
        int idCliente = leituraDados.nextInt();
        leituraDados.nextLine();
        return idCliente;
    }
    
    /**
     * Solicita o noem do mecânico responsável.
     * 
     * @return Nome do profissional informado. 
     */
    public String getMecanicoResponsavel(){
        System.out.println("Digite o nome do profissional responsável: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita a data e o horário do agendamento.
     * O formato esperado é: "DD MM AAAA HH MM".
     * 
     * @return String com a dara e hora digitados.
     */
    public String getDataHorarioAgendamento(){
        System.out.println("Digite o Horario: [DD MM AAAA HH MM]");
        return leituraDados.nextLine();
    }
    
    /**
     * Exibe e solicita o tipo do agendamento ao usuário.
     * 
     * @return Código do tipo selecionado (1, 2 ou 3).
     */
    public int getTipoAgendamento(){
        System.out.println("Digite a opção do tipo do agendamento");
        System.out.println("1 - Manutenção");
        System.out.println("2 - Revisão");
        System.out.println("3 - Outro");
        
        return leituraDados.nextInt();
//        leituraDados.nextLine();
//        return opcao;
    }
    
    /**
     * Caso o tipo de agendamento seja "Outro", solicita uma descrição ao usuário.
     * 
     * @return Descrição personalizada do tipo de agendamento ou null se não aplicável.
     */
    public String getDescricaoOutro(){
        int opcao = getTipoAgendamento();
        if (opcao == 3) {
            System.out.println("Você selecionou a opção 'outro', favor escrever a descrição do agendamento manualmente: ");
        return leituraDados.nextLine(); 
        }
        return null;
    }
    
    /**
     * Solicita ao usuário o ID do elevador desejado para o serviço.
     * 
     * @return Número do elevador escolhido.
     */
    public int getIdElevador(){
        System.out.println(
                "[ELEVADOR 1 - alinhamento | ELEVADOR 2 - Normal | Elevador 3 - Normal]\nDigite o ID do elevador necessário: ");
        int idElevador = leituraDados.nextInt();
        leituraDados.nextLine();
        return idElevador;
    }
    
    /**
     * Exibe todos os detalhes de um agendamento.
     * 
     * @param agendamento Objeto Agendamento a ser exibido.
     */
    public void mostraAgendamento(Agendamento agendamento){
        String tipoTexto = agendamento.retornaTipoAgendamento(agendamento.getTipoAgendamento());
        String descricaoOutro = "";
        
        if (agendamento.getTipoAgendamento() == 3) {
            descricaoOutro = " (" + getDescricaoOutro() + ")";
        }
        
        System.out.println("ID do agendamento: " + agendamento.getIdAgendamento() + "\n" + 
                           "ID do cliente: " + agendamento.getIdCliente() + "\n" +
                           "Mecânico responsável: " + agendamento.getMecanicoResponsavel() + "\n" +
                           "Status agendamento: " + agendamento.getStatusAgendamento() + "\n" +
                           "Agendamento marcado para: " + agendamento.getDataHora().getTime() + "\n" +
                           "Tipo do agendamento: " + tipoTexto + descricaoOutro  + "\n" +       
                           "\nOBSERVAÇÃO: AGENDAMENTOS CONFIRMADOS TEM A TAXA PADRÃO DE R$50,00" + "\n");
    }
    
    /**
     * Solicita confirmação de cancelamento do agendamento ao usuário.
     * 
     * @return String com a resposta do usuário (S/N).
     */
    public String confirmaExclusaoDoAgendamento(){
        System.out.println("Tem certeza que deseja cancelar este agendamento? \n"
                         + "Digite [S] para confirmar ou [N] para abortar a operação!! \n");
        leituraDados.nextLine();
        return leituraDados.nextLine();
    }
    
    /**
     * Exibe opções de edição do agendamento e retorna a escolhida.
     * 
     * @return Número da opção escolhida para a edição. 
     */
    public int editaAgendamento(){
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - Horário");
        System.out.println("2 - Mecânico responsável");
      
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
}
