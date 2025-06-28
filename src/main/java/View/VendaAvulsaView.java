package View;

import Model.Produto;
import Model.Venda;
import java.util.List;
import java.util.Scanner;

/**
 * Classe responsável pelas interações com o usuário relacionadas às vendas
 * avulsas. Realizando entradas e saídas de dados para operações de venda na
 * loja.
 */
public class VendaAvulsaView {

    /**
     * Scanner para leitura de dados inseridos pelo usuário.
     */
    Scanner leituraDados = new Scanner(System.in);

    /**
     * Exibe as opções disponíveis no menu de vendas avulsas e retorna a opção
     * escolhida.
     *
     * @return um número inteiro correspondente à opção selecionada pelo
     * usuário.
     */
    public int mostraOpcoesVenda() {
        System.out.println("Digite a opção que deseja executar: ");
        System.out.println("1 - Ver catálogo de produtos");
        System.out.println("2 - Realizar venda");
        System.out.println("3 - Consultar venda");
        System.out.println("4 - Listar todas as vendas");
        System.out.println("5 - Cancelar venda");
        System.out.println("6 - Sair");

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    /**
     * Exibe o catálogo de produtos disponíveis para venda.
     *
     * @param produtos Lista de produtos em estoque
     */
    public void mostraCatalogo(List<Produto> produtos) {
        System.out.println("Produtos disponíveis em estoque:");

        if (produtos == null) {
            System.out.println("Nenhum produto disponível no momento.");
            return;
        }

        for (Produto produto : produtos) {
            if (produto.getQuantidadeEmEstoque() > 0) {
                System.out.println("ID: " + produto.getIdProduto() + "\n"
                        + "Descrição: " + produto.getDescricao() + "\n"
                        + "Quantidade: " + produto.getQuantidadeEmEstoque() + "\n"
                        + "Disponível em Estoque: " + produto.getDisponivel() + "\n"
                        + "Valor: R$" + produto.getValorProduto() + "\n");
            }
        }
    }

    /**
     * Solicita e retorna o ID do cliente.
     *
     * @return ID do cliente
     */
    public int getIdCliente() {
        System.out.println("Digite o ID do cliente: ");

        int idCliente = leituraDados.nextInt();
        leituraDados.nextLine();
        return idCliente;
    }

    /**
     * Solicita e retorna o ID do produto para venda.
     *
     * @return ID do produto
     */
    public int getIdProduto() {
        System.out.println("Digite o ID do produto que deseja comprar: ");

        int idProduto = leituraDados.nextInt();
        leituraDados.nextLine();
        return idProduto;
    }

    /**
     * Solicita e retorna a quantidade desejada do produto.
     *
     * @return Quantidade do produto
     */
    public int getQuantidadeVenda() {
        System.out.println("Digite a quantidade desejada: ");

        int quantidade = leituraDados.nextInt();
        leituraDados.nextLine();
        return quantidade;
    }

    /**
     * Solicita e retorna o ID da venda para consulta.
     *
     * @return ID da venda
     */
    public int getIdVenda() {
        System.out.println("Digite o ID da venda: ");

        int idVenda = leituraDados.nextInt();
        leituraDados.nextLine();
        return idVenda;
    }

    /**
     * Exibe os detalhes de uma venda específica.
     *
     * @param venda Objeto venda a ser exibido
     * @param produto Produto relacionado à venda
     */
    public void mostraVenda(Venda venda, Produto produto) {
        System.out.println("ID Venda: " + venda.getIdVenda());
        System.out.println("ID Cliente: " + venda.getIdCliente());
        System.out.println("Produto: " + produto.getDescricao());
        System.out.println("Quantidade: " + venda.getQuantidade());
        System.out.println("Valor Unitário: R$ " + String.format("%.2f", venda.getValorUnitario()));
        System.out.println("Valor Total: R$ " + String.format("%.2f", venda.getValorTotal()));
    }
    
    /**
     * Exibe um resumo da venda antes da confirmação.
     * 
     * @param produto Produto a ser vendido
     * @param quantidade Quantidade a ser vendida
     * @param valorTotal Valor total da venda
     */
    public void mostraResumoVenda(Produto produto, int quantidade, double valorTotal) {
        System.out.println("Resumo Venda");
        System.out.println("Produto: " + produto.getDescricao());
        System.out.println("Valor Unitário: R$ " + String.format("%.2f", produto.getValorProduto()));
        System.out.println("Quantidade: " + quantidade);
        System.out.println("Valor Total: R$ " + String.format("%.2f", valorTotal));
    }

    /**
     * Solicita confirmação para realizar a venda.
     *
     * @return String com a confirmação do usuário
     */
    public String confirmaVenda() {
        System.out.println("Confirma a venda? [S/N]: ");
        return leituraDados.nextLine().trim().toUpperCase();
    }

    /**
     * Solicita confirmação para cancelar uma venda.
     *
     * @return String com a confirmação do usuário
     */
    public String confirmaCancelamento() {
        System.out.println("Tem certeza que deseja cancelar esta venda? [S/N]: ");
        return leituraDados.nextLine().trim().toUpperCase();
    }

    /**
     * Exibe a lista de todas as vendas realizadas.
     *
     * @param vendas Lista de vendas
     */
    public void mostraListaVendas(List<Venda> vendas) {

        if (vendas == null) {
            System.out.println("Nenhuma venda registrada.");
            return;
        }

        for (Venda venda : vendas) {
            System.out.println(venda.toString());
        }
        System.out.println("Total de vendas: " + vendas.size());
    }
    
    @Override
    public String toString(){
        return "- Interface de Vendas -";
    }

}
