package Model;

import View.VendaAvulsaView;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Classe DAO para gerenciar operações de vendas avulsas. Responsável pela
 * persistência e manipulação dos dados de vendas. Implementa o padrão Singleton
 * para garantir uma única instância.
 */
public class VendaDAO extends GenericDAO<Venda> {

    private static VendaDAO instancia;
    private VendaAvulsaView viewVenda = new VendaAvulsaView();
    private ProdutoEstoqueDAO produtoDAO = ProdutoEstoqueDAO.getInstancia();

    /**
     * Construtor privado para implementar o padrão Singleton. Define o caminho
     * do arquivo JSON e o tipo da lista manipulada.
     */
    private VendaDAO() {
        super("data/vendas.json", new TypeToken<List<Venda>>() {
        }.getType());
    }

    /**
     * Retorna a instância única de {@code VendaDAO}.
     *
     * @return Instância única de {@code VendaDAO}.
     */
    public static VendaDAO getInstancia() {
        if (instancia == null) {
            instancia = new VendaDAO();
        }
        return instancia;
    }

    /**
     * Define a chave de identificação única da venda.
     *
     * @param venda Objeto {@code Venda}.
     * @return ID da venda.
     */
    @Override
    protected Comparable<?> getChave(Venda venda) {
        return venda.getIdVenda();
    }

    /**
     * Busca uma venda pelo ID.
     *
     * @param id ID da venda.
     * @return Objeto {@code Venda} correspondente, ou {@code null} se não
     * encontrado.
     */
    public Venda buscaVenda(int id) {
        return buscaPorChave(id);
    }

    /**
     * Gera um novo ID para uma venda, baseado no maior ID já registrado no
     * sistema.
     *
     * * @return O novo ID da venda, incrementado em relação ao maior ID atual.
     */
    public int geraIdVenda() {
        int maiorIdVenda = 0;
        List<Venda> vendas = getLista();

        if (vendas != null) {
            for (Venda venda : vendas) {
                if (venda.getIdVenda() > maiorIdVenda) {
                    maiorIdVenda = venda.getIdVenda();
                }
            }
        }
        return maiorIdVenda + 1;
    }

    /**
     * Exibe o catálogo de produtos disponíveis para venda.
     */
    public void mostraCatalogo() {
        List<Produto> produtos = produtoDAO.getLista();
        viewVenda.mostraCatalogo(produtos);
    }

    /**
     * Realiza uma nova venda avulsa.
     */
    public void realizarVenda() {
        mostraCatalogo();

        int idCliente = viewVenda.getIdCliente();
        int idProduto = viewVenda.getIdProduto();

        Produto produto = produtoDAO.buscaProduto(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
            return;
        }

        if (!produto.getDisponivel() || produto.getQuantidadeEmEstoque() <= 0) {
            System.out.println("Produto indisponível ou sem estoque!");
            return;
        }

        int quantidade = viewVenda.getQuantidadeVenda();

        if (quantidade > produto.getQuantidadeEmEstoque()) {
            System.out.println("Quantidade solicitada maior que o estoque disponível!");
            System.out.println("Estoque atual: " + produto.getQuantidadeEmEstoque());
            return;
        }

        double valorTotal = quantidade * produto.getValorProduto();
        viewVenda.mostraResumoVenda(produto, quantidade, valorTotal);

        String confirmacao = viewVenda.confirmaVenda();
        if (!confirmacao.equals("S")) {
            System.out.println("Venda cancelada!");
            return;
        }

        int idVenda = geraIdVenda();
        Venda novaVenda = new Venda(idVenda, idCliente, idProduto, quantidade, produto.getValorProduto(), valorTotal);

        adicionaDados(novaVenda);
        produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - quantidade);

        if (produto.getQuantidadeEmEstoque() <= 0) {
            produto.setDisponivel(false);
        }

        if (produtoDAO.salvarDados()) {
            System.out.println("Venda realizada com sucesso! ID da venda: " + idVenda);
            System.out.println("Novo estoque do produto: " + produto.getQuantidadeEmEstoque());
        } else {
            System.out.println("Erro ao atualizar estoque!");
        }
    }

    /**
     * Consulta uma venda específica pelo ID.
     */
    public void consultarVenda() {
        int idVenda = viewVenda.getIdVenda();
        Venda venda = buscaVenda(idVenda);

        if (venda == null) {
            System.out.println("Venda não encontrada!");
            return;
        }

        Produto produto = produtoDAO.buscaProduto(venda.getIdProduto());
        if (produto == null) {
            System.out.println("Produto relacionado à venda não encontrado!");
            return;
        }

        viewVenda.mostraVenda(venda, produto);
    }

    /**
     * Lista todas as vendas realizadas.
     */
    public void listarVendas() {
        List<Venda> vendas = getLista();
        viewVenda.mostraListaVendas(vendas);
    }

    /**
     * Cancela uma venda específica e repõe o estoque.
     */
    public void cancelarVenda() {
        int idVenda = viewVenda.getIdVenda();
        Venda venda = buscaVenda(idVenda);

        if (venda == null) {
            System.out.println("Venda não encontrada!");
            return;
        }

        Produto produto = produtoDAO.buscaProduto(venda.getIdProduto());
        if (produto == null) {
            System.out.println("Produto relacionado à venda não encontrado!");
            return;
        }

        viewVenda.mostraVenda(venda, produto);

        String confirmacao = viewVenda.confirmaCancelamento();
        if (!confirmacao.equals("S")) {
            System.out.println("Operação cancelada!");
            return;
        }

        if (removeDados(venda)) {
            produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + venda.getQuantidade());
            produto.setDisponivel(true);

            if (produtoDAO.salvarDados()) {
                System.out.println("Venda cancelada com sucesso!");
                System.out.println("Estoque reposto. Novo estoque: " + produto.getQuantidadeEmEstoque());
            } else {
                System.out.println("Erro ao repor estoque!");
            }
        } else {
            System.out.println("Erro ao cancelar venda!");
        }
    }

    /**
     * Gera nota fiscal para uma venda.
     */
    public void geraNotaFiscalVenda() {
        int idVenda = viewVenda.getIdVenda();
        Venda venda = buscaVenda(idVenda);
        ClienteDAO clienteDAO = ClienteDAO.getInstancia();

        ProdutoEstoqueDAO produtoDAO = ProdutoEstoqueDAO.getInstancia();

        if (venda == null) {
            System.out.println("Venda não encontrada :(");
            return;
        }

        Cliente cliente = clienteDAO.buscarCliente(venda.getIdCliente());
        
        if (cliente == null) {
            System.out.println("Cliente da venda não encontrado.");
            return;
        }
        
        String cpf = cliente.getCpf().toString();
        String nome = cliente.getNome();

        Produto produto = produtoDAO.buscaProduto(venda.getIdProduto());
        
        if (produto == null) {
            System.out.println("produto da venda não encontrado.");
            return;
        }
        
        String produtoDesc = produto.getDescricao();

        viewVenda.mostraNotaFiscal(venda, cpf, produtoDesc, nome);
    }
    
    @Override
    public String toString(){
        return "VendaDAO | Quantidade vendas: " + getLista().size();
    }
}
    
    
