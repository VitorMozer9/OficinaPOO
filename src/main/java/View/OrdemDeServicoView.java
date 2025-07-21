package View;

import Model.OrdemDeServico;
import Model.StatusOrdemServico;
import Model.TipoServico;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pelas interações com o usuário relacionadas às Ordens de Serviço.
 * Realiza entradas e saídas de dados para operações de OS.
 */
public class OrdemDeServicoView {
    
    /**
     * Scanner para leitura de dados inseridos pelo usuário.
     */
    Scanner leituraDados = new Scanner(System.in);

    /**
     * Exibe as opções disponíveis no menu de Ordem de Serviço e retorna a opção escolhida.
     *
     * @return um número inteiro correspondente à opção selecionada pelo usuário
     */
    public int mostraOpcoesOrdemServico() {
        System.out.println("Digite a opção que deseja executar:");
        System.out.println("1 - Criar Ordem de Serviço");
        System.out.println("2 - Confirmar Ordem de Serviço");
        System.out.println("3 - Cancelar Ordem de Serviço");
        System.out.println("4 - Editar Ordem de Serviço");
        System.out.println("5 - Mostrar Ordem de Serviço");
        System.out.println("6 - Gerar Nota Fiscal");
        System.out.println("7 - Listar Todas as Ordens de Serviço");
        System.out.println("8 - Busca Ordem de Serviço pelo ID do Cliente");
        System.out.println("9 - Sair");

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    /**
     * Solicita e retorna o ID da ordem de serviço.
     *
     * @return ID da ordem de serviço
     */
    public int getIdOrdemServico() {
        System.out.println("Digite o ID da Ordem de Serviço: ");
        int id = leituraDados.nextInt();
        leituraDados.nextLine();
        return id;
    }

    /**
     * Solicita e retorna o ID do cliente.
     *
     * @return ID do cliente
     */
    public int getIdCliente() {
        System.out.println("Digite o ID do Cliente: ");
        int idCliente = leituraDados.nextInt();
        leituraDados.nextLine();
        return idCliente;
    }

    /**
     * Solicita e retorna o nome do mecânico responsável.
     *
     * @return Nome do mecânico
     */
    public String getMecanicoResponsavel() {
        System.out.println("Digite o nome do Mecânico Responsável: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna a data da ordem de serviço.
     *
     * @return Data da ordem de serviço
     */
    public Calendar getDataOrdemServico() {
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
     * Solicita e retorna o tipo de serviço.
     *
     * @return Tipo de serviço
     */
    public TipoServico getTipoServico() {
        System.out.println("Selecione o tipo de serviço:");
        System.out.println("1 - Manutenção");
        System.out.println("2 - Revisão");
        System.out.println("3 - Troca de Peça");
        System.out.println("4 - Outro");
        
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        
        TipoServico tipo = TipoServico.converteCodigo(opcao);
        if (tipo == null) {
            System.out.println("Opção inválida! Definindo como Manutenção por padrão.");
            return TipoServico.MANUTENCAO;
        }
        
        return tipo;
    }

    /**
     * Solicita e retorna a descrição do serviço.
     *
     * @return Descrição do serviço
     */
    public String getDescricaoServico() {
        System.out.println("Digite a descrição do serviço: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o valor do serviço.
     *
     * @return Valor do serviço
     */
    public double getValorServico() {
        System.out.println("Digite o valor do serviço: R$ ");
        double valor = leituraDados.nextDouble();
        leituraDados.nextLine();
        return valor;
    }

    /**
     * Solicita e retorna a lista de produtos para troca de peças.
     *
     * @return Lista de produtos
     */
    public List<String> getProdutos() {
        List<String> produtos = new ArrayList<>();
        System.out.println("Quantos produtos/peças serão utilizados? ");
        int quantidade = leituraDados.nextInt();
        leituraDados.nextLine();
        
        for (int i = 1; i <= quantidade; i++) {
            System.out.println("Digite o nome do produto/peça " + i + ": ");
            String produto = leituraDados.nextLine();
            if (!produto.trim().isEmpty()) {
                produtos.add(produto.trim());
            }
        }
        
        return produtos;
    }

    /**
     * Solicita e retorna o valor dos produtos.
     *
     * @return Valor dos produtos
     */
    public double getValorProdutos() {
        System.out.println("Digite o valor total dos produtos/peças: R$ ");
        double valor = leituraDados.nextDouble();
        leituraDados.nextLine();
        return valor;
    }

    /**
     * Exibe as opções de edição de ordem de serviço.
     *
     * @return Opção de edição escolhida
     */
    public int getOpcaoEdicao() {
        System.out.println("O que deseja editar?");
        System.out.println("1 - Nome do Mecânico");
        System.out.println("2 - Tipo de Serviço");
        System.out.println("3 - Descrição do Serviço");
        System.out.println("4 - Valor do Serviço");
        System.out.println("5 - Produtos (apenas para Troca de Peça)");
        System.out.println("6 - Valor dos Produtos");
        
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    /**
     * Exibe os detalhes de uma ordem de serviço específica.
     *
     * @param os Ordem de serviço a ser exibida
     */
    public void mostraOrdemServico(OrdemDeServico os) {
        System.out.println(os.toString());
    }

    /**
     * Exibe a lista de todas as ordens de serviço.
     *
     * @param ordensServico Lista de ordens de serviço
     */
    public void mostraListaOrdemServico(List<OrdemDeServico> ordensServico) {
        if (ordensServico == null || ordensServico.isEmpty()) {
            System.out.println("Nenhuma ordem de serviço registrada.");
            return;
        }

        double totalFaturamento = 0;
        int totalPendentes = 0;
        int totalConfirmadas = 0;
        int totalCanceladas = 0;
        
        System.out.println("LISTA DE ORDENS DE SERVIÇO");
        
        for (OrdemDeServico os : ordensServico) {
            System.out.println("ID: " + os.getIdOrdemServico() + 
                             " | Cliente: " + os.getIdCliente() + 
                             " | Mecânico: " + os.getMecanicoResponsavel() +
                             " | Status: " + os.getStatus().getDescricao() +
                             " | Valor: R$ " + String.format("%.2f", os.getValorTotal()));
            
            if (os.getStatus() == StatusOrdemServico.CONFIRMADO) {
                totalFaturamento += os.getValorTotal();
                totalConfirmadas++;
            } else if (os.getStatus() == StatusOrdemServico.PENDENTE) {
                totalPendentes++;
            } else if (os.getStatus() == StatusOrdemServico.CANCELADO) {
                totalCanceladas++;
            }
        }
        
        System.out.println("------------------------------------------------------------");
        System.out.println("Total de ordens: " + ordensServico.size());
        System.out.println("Pendentes: " + totalPendentes + 
                         " | Confirmadas: " + totalConfirmadas + 
                         " | Canceladas: " + totalCanceladas);
        System.out.println("Faturamento (apenas confirmadas): R$ " + String.format("%.2f", totalFaturamento));
        System.out.println("============================================================");
    }

    /**
     * Exibe a nota fiscal de uma ordem de serviço.
     *
     * @param os Ordem de serviço
     * @param cpfAnonimizado CPF do cliente
     */
    public void mostraNotaFiscal(OrdemDeServico os, String cpf) {
        if (os.getStatus() != StatusOrdemServico.CONFIRMADO) {
            System.out.println("Nota fiscal só pode ser gerada para ordens de serviço confirmadas!");
            return;
        }
        
        System.out.println("============================================================");
        System.out.println("                      NOTA FISCAL");
        System.out.println("============================================================");
        System.out.println("Ordem de Serviço: #" + os.getIdOrdemServico());
        System.out.println("CPF Cliente: " + cpf);
        System.out.println("Data: " + java.text.DateFormat.getDateInstance().format(os.getData().getTime()));
        System.out.println("------------------------------------------------------------");
        System.out.println("SERVIÇOS PRESTADOS:");
        System.out.println("Tipo: " + os.getTipoServico().getDescricao());
        System.out.println("Descrição: " + os.getDescricaoServico());
        System.out.println("Valor: R$ " + String.format("%.2f", os.getValor()));
        
        if (os.getTipoServico() == TipoServico.TROCA_PECA && !os.getProdutos().isEmpty()) {
            System.out.println("------------------------------------------------------------");
            System.out.println("PRODUTOS/PEÇAS:");
            for (String produto : os.getProdutos()) {
                System.out.println("- " + produto);
            }
            System.out.println("Valor dos Produtos: R$ " + String.format("%.2f", os.getValorProdutos()));
        }
        
        System.out.println("------------------------------------------------------------");
        System.out.println("VALOR TOTAL: R$ " + String.format("%.2f", os.getValorTotal()));
        System.out.println("============================================================");
    }

    /**
     * Solicita confirmação para uma operação.
     *
     * @param mensagem Mensagem de confirmação
     * @return String com a confirmação do usuário
     */
    public String confirmaOperacao(String mensagem) {
        System.out.println(mensagem + " [S/N]: ");
        return leituraDados.nextLine().trim().toUpperCase();
    }
    
    @Override
    public String toString(){
        return "- Interface OS -";
    }
}