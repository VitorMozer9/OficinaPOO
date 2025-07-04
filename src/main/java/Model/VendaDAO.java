package Model;

import View.VendaAvulsaView;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Classe DAO para gerenciar operações de vendas avulsas. Responsável pela
 * persistência e manipulação dos dados de vendas.
 */
public class VendaDAO extends GenericDAO<Venda> {
    private static VendaDAO instancia;
    private VendaAvulsaView viewVenda = new VendaAvulsaView();
    private ProdutoEstoqueDAO produtoDAO = ProdutoEstoqueDAO.getInstancia();

    private VendaDAO() {
        super("data/vendas.json", new TypeToken<List<Venda>>() {}.getType());
    }

    public static VendaDAO getInstancia() {
        if (instancia == null) {
            instancia = new VendaDAO();
        }
        return instancia;
    }

    @Override
    protected Comparable<?> getChave(Venda venda) {
        return venda.getIdVenda();
    }

    public Venda buscaVenda(int id) {
        return buscaPorChave(id);
    }

    /**
     * Gera um novo ID para uma venda, baseado no maior ID já registrado no sistema.
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
}