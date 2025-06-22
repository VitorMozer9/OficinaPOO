package View;

import Model.Agendamento;
import java.util.Scanner;

public class AgendamentoView {
    Scanner leituraDados = new Scanner(System.in);
    
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
    
    public int getIdAgendamento(){
        System.out.println("Digite o ID do agendamento: ");
        return leituraDados.nextInt();
        }
    
    public int getIdCliente(){
        System.out.println("Digite o ID do cliente: ");
        return leituraDados.nextInt();
    }
    
    public String getMecanicoResponsavel(){
        System.out.println("Digite o nome do profissional responsável: ");
        return leituraDados.nextLine();
    }
    
    public int getTipoAgendamento(){
        System.out.println("Digite a opção do tipo do agendamento");
        System.out.println("1 - Manutenção");
        System.out.println("2 - Revisão");
        System.out.println("3 - Outro");
        
        return leituraDados.nextInt();
//        leituraDados.nextLine();
//        return opcao;
    }
    
    public String getDescricaoOutro(){
        int opcao = getTipoAgendamento();
        if (opcao == 3) {
            System.out.println("Você selecionou a opção 'outro', favor escrever a descrição do agendamento manualmente: ");
        return leituraDados.nextLine(); 
        }
        return null;
    }
    
    public String getDataHorarioAgendamento(){
        System.out.println("Digite o Horario: [DD MM AAAA HH MM]");
        return leituraDados.nextLine();
    }
    
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
    
    public String confirmaExclusaoDoAgendamento(){
        System.out.println("Tem certeza que deseja cancelar este agendamento? \n"
                         + "Digite [S] para confirmar ou [N] para abortar a operação!! \n");
        leituraDados.nextLine();
        return leituraDados.nextLine();
    }
    
    public int editaAgendamento(){
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - Horário");
        System.out.println("2 - Mecânico responsável");
      
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
}
