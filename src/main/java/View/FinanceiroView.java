package View;

import Model.Financeiro;
import Model.OrdemDeServico;
import Model.TipoConta;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pelas interações com o usuário relacionadas ao sistema financeiro.
 * Realiza entradas e saídas de dados para operações financeiras.
 */
public class FinanceiroView {
    
    /**
     * Scanner para leitura de dados inseridos pelo usuário.
     */
    Scanner leituraDados = new Scanner(System.in);

    /**
     * Exibe as opções disponíveis no menu financeiro e retorna a opção escolhida.
     *
     * @return um número inteiro correspondente à opção selecionada pelo usuário
     */
    public int mostraOpcoesFinanceiro(){
        System.out.println("Digite a opção que deseja executar:");
        System.out.println("1 - Adicionar Conta");
        System.out.println("2 - Remover Conta");
        System.out.println("3 - Editar Conta");
        System.out.println("4 - Mostrar Dados da Conta");
        System.out.println("5 - Gerar Relatório de Vendas");
        System.out.println("6 - Gerar Balanço Mensal");
        System.out.println("7 - Listar Todas as Contas");
        System.out.println("8 - Sair");

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    /**
     * Solicita e retorna o ID da conta.
     *
     * @return ID da conta
     */
    public int getIdConta(){
        System.out.println("Digite o ID da conta: ");
        int idConta = leituraDados.nextInt();
        leituraDados.nextLine();
        return idConta;
    }

    /**
     * Solicita e retorna a descrição da conta.
     *
     * @return Descrição da conta
     */
    public String getDescricaoConta(){
        System.out.println("Digite a descrição da conta: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o tipo da conta.
     *
     * @return Tipo da conta
     */
    public TipoConta getTipoConta(){
        System.out.println("Selecione o tipo da conta:");
        System.out.println("1 - Receita");
        System.out.println("2 - Despesa");
        
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        
        TipoConta tipo = TipoConta.converteCodigo(opcao);
        if (tipo == null) {
            System.out.println("Opção inválida! Definindo como Receita por padrão.");
            return TipoConta.RECEITA;
        }
        
        return tipo;
    }

    /**
     * Solicita e retorna o valor da conta.
     *
     * @return Valor da conta
     */
    public double getValorConta(){
        System.out.println("Digite o valor da conta: R$ ");
        double valor = leituraDados.nextDouble();
        leituraDados.nextLine();
        return valor;
    }

    /**
     * Solicita e retorna a data da conta.
     *
     * @return Data da conta
     */
    public Calendar getDataConta(){
        System.out.println("Digite o dia (1-31): ");
        int dia = leituraDados.nextInt();
        
        System.out.println("Digite o mês (1-12): ");
        int mes = leituraDados.nextInt();
        
        System.out.println("Digite o ano: ");
        int ano = leituraDados.nextInt();
        leituraDados.nextLine();
        
        Calendar data = Calendar.getInstance();
        data.set(ano, mes - 1, dia);
        
        return data;
    }

    /**
     * Solicita e retorna o mês para consultas.
     *
     * @return Mês (1-12)
     */
    public int getMes(){
        System.out.println("Digite o mês (1-12): ");
        int mes = leituraDados.nextInt();
        leituraDados.nextLine();
        return mes;
    }

    /**
     * Solicita e retorna o ano para consultas.
     *
     * @return Ano
     */
    public int getAno(){
        System.out.println("Digite o ano: ");
        int ano = leituraDados.nextInt();
        leituraDados.nextLine();
        return ano;
    }

    /**
     * Exibe as opções de edição de conta.
     *
     * @return Opção de edição escolhida
     */
    public int getOpcaoEdicao(){
        System.out.println("O que deseja editar?");
        System.out.println("1 - Descrição");
        System.out.println("2 - Tipo da Conta");
        System.out.println("3 - Valor");
        System.out.println("4 - Data");
        
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    /**
     * Exibe os detalhes de uma conta específica.
     *
     * @param conta Conta a ser exibida
     */
    public void mostraConta(Financeiro conta){
        System.out.println(conta.toString());
    }

    /**
     * Exibe o balanço mensal.
     *
     * @param contas Lista de contas do mês
     * @param totalReceitas Total de receitas
     * @param totalDespesas Total de despesas
     * @param mes Mês do balanço
     * @param ano Ano do balanço
     */
    public void mostraBalancoMensal(List<Financeiro> contas, double totalReceitas, 
                                   double totalDespesas, int mes, int ano, double faturamentoOS) {
        System.out.println("Balanço mensal - " + mes + "/" + ano);
        System.out.println("Receitas Financeiras: R$ " + String.format("%.2f", totalReceitas));
        System.out.println("Faturamento OS: R$ " + String.format("%.2f", faturamentoOS));
        System.out.println("Total Receitas: R$ " + String.format("%.2f", totalReceitas + faturamentoOS));
        System.out.println("Total de Despesas: R$ " + String.format("%.2f", totalDespesas));
        System.out.println("Saldo: R$ " + String.format("%.2f", (totalReceitas + faturamentoOS - totalDespesas)));
        System.out.println("");
        
        System.out.println("Dados mensais");
        for (Financeiro conta : contas) {
            System.out.println(conta.toString());
        }
        System.out.println("Total de movimentações: " + contas.size());
    }

    /**
     * Exibe a lista de todas as contas.
     *
     * @param contas Lista de contas
     */
    public void mostraListaContas(List<Financeiro> contas) {
        
        if (contas == null || contas.isEmpty()) {
            System.out.println("Nenhuma conta registrada.");
            return;
        }

        double totalReceitas = 0;
        double totalDespesas = 0;
        
        for (Financeiro conta : contas) {
            System.out.println(conta.toString());
            
            if (conta.getTipoConta() == TipoConta.RECEITA) {
                totalReceitas += conta.getValor();
            } else {
                totalDespesas += conta.getValor();
            }
        }
        
        System.out.println("----------------------");
        System.out.println("Total de contas: " + contas.size());
        System.out.println("Total de Receitas: R$ " + String.format("%.2f", totalReceitas));
        System.out.println("Total de Despesas: R$ " + String.format("%.2f", totalDespesas));
        System.out.println("Saldo Geral: R$ " + String.format("%.2f", (totalReceitas - totalDespesas)));
    }

    /**
     * Solicita confirmação para remoção de conta.
     *
     * @return String com a confirmação do usuário
     */
    public String confirmaRemocao(){
        System.out.println("Tem certeza que deseja remover esta conta? [S/N]: ");
        return leituraDados.nextLine().trim().toUpperCase();
    }
}