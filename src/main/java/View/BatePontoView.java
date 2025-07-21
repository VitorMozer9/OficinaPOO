package View;

import Model.BatePonto;
import Model.BatidaDePonto;
import java.util.Scanner;

/**
 * Classe responsável pela interface de usuário para o sistema de batimento de ponto.
 * Fornece métodos para registrar entrada/saída, consultar e gerenciar registros de ponto.
 */
public class BatePontoView {
    
    /**
     * Scanner para leitura de entrada do usuário.
     */
    Scanner leituraDados = new Scanner(System.in);
    
    /**
     * Exibe as opções disponíveis no menu de batimento de ponto e retorna a opção escolhida.
     * 
     * @return Um número inteiro de acordo com a opção escolhida pelo usuário.
     */
    public int mostraOpcoesBatePonto() {
        System.out.println("Digite a opção que deseja executar:");
        System.out.println("1 - Registrar Batida de Ponto");
        System.out.println("2 - Editar Registro de Ponto");
        System.out.println("3 - Remover Registro de Ponto");
        System.out.println("4 - Mostrar Relatório de Horas");
        System.out.println("5 - Sair");
        
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    /**
     * Solicita e retorna o ID do funcionário para batimento de ponto.
     * 
     * @return ID do funcionário.
     */
    public int getIdFuncionario() {
        System.out.println("Digite o ID do funcionário:");
        int id = leituraDados.nextInt();
        leituraDados.nextLine();
        return id;
    }
    
    /**
     * Solicita e retorna o ID do registro de ponto.
     * 
     * @return ID do registro de ponto.
     */
    public int getIdRegistro() {
        System.out.println("Digite o ID do registro de ponto:");
        int id = leituraDados.nextInt();
        leituraDados.nextLine();
        return id;
    }
    
    /**
     * Exibe as opções de tipo de batida e retorna a escolha do usuário.
     * 
     * @return Tipo de batida escolhido (ENTRADA ou SAIDA).
     */
    public BatidaDePonto getTipoBatida() {
        System.out.println("Selecione o tipo de batida:");
        System.out.println("1 - " + BatidaDePonto.ENTRADA.getDescricao());
        System.out.println("2 - " + BatidaDePonto.SAIDA.getDescricao());
        
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        
        return opcao == 1 ? BatidaDePonto.ENTRADA : BatidaDePonto.SAIDA;
    }
    
    /**
     * Exibe as informações detalhadas de um registro de batida de ponto.
     * 
     * @param batePonto Registro de ponto a ser exibido.
     */
    public void mostrarBatePonto(BatePonto batePonto) {
        System.out.println("ID do Registro: " + batePonto.getIdRegistro());
        System.out.println("ID do Funcionário: " + batePonto.getIdFuncionario());
        System.out.println("Entrada: " + BatidaDePonto.formatarHorario(batePonto.getDataHoraEntrada()));
        System.out.println("Saída: " + BatidaDePonto.formatarHorario(batePonto.getDataHoraSaida()));
        System.out.println("Total de Horas: " + String.format("%.2f", batePonto.getTotalHorasTrabalhadas()) + "h");
        System.out.println("Status: " + (batePonto.registroCompleto() ? "Completo" : "Incompleto"));
    }
    
    /**
     * Exibe um resumo simplificado do registro de ponto.
     * 
     * @param batePonto Registro de ponto a ser exibido.
     */
    public void mostrarResumoRegistro(BatePonto batePonto) {
        System.out.println("ID: " + batePonto.getIdRegistro() + 
                          " | Funcionário: " + batePonto.getIdFuncionario() + 
                          " | Horas: " + String.format("%.2f", batePonto.getTotalHorasTrabalhadas()) + "h" +
                          " | Status: " + (batePonto.registroCompleto() ? "Completo" : "Incompleto"));
    }
    
    /**
     * Confirma a exclusão de um registro de ponto.
     * 
     * @return "S" para confirmar, outro valor para cancelar.
     */
    public String confirmaExclusaoRegistro() {
        System.out.println("Tem certeza que deseja remover este registro de ponto?");
        System.out.println("Digite [S] para confirmar ou [N] para abortar a operação!!");
        return leituraDados.nextLine();
    }
    
    /**
     * Exibe as opções de campos que podem ser editados e retorna a escolha do usuário.
     * 
     * @return Número inteiro correspondente ao campo selecionado.
     */
    public int editaRegistroPonto() {
        System.out.println("Digite o campo que você gostaria de editar:");
        System.out.println("1 - Registrar/Alterar Entrada");
        System.out.println("2 - Registrar/Alterar Saída");
        System.out.println("3 - Alterar ID do Funcionário");
        
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    /**
     * Solicita confirmação do usuário para prosseguir com uma operação.
     * 
     * @param mensagem Mensagem de confirmação.
     * @return true se o usuário confirmar, false caso contrário.
     */
    public boolean confirmarOperacao(String mensagem) {
        System.out.println(mensagem);
        System.out.println("Digite [S] para confirmar ou [N] para cancelar:");
        String resposta = leituraDados.nextLine();
        return resposta.equalsIgnoreCase("S");
    }
    
    @Override
    public String toString(){
        return "- Interface do registro de pontos -";
    }
    
}