package Model;

import Main.OficinaPOO;
import View.ProdutoEstoqueView;
import controller.ProdutoEstoqueController;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class ProdutoEstoqueDAO extends GenericDAO<Produto> {
    private static ProdutoEstoqueDAO instancia;
    private ProdutoEstoqueView viewProduto = new ProdutoEstoqueView();

    private ProdutoEstoqueDAO() {
        super("data/produtos.json", new TypeToken<List<Produto>>() {}.getType());
    }
    
    public static ProdutoEstoqueDAO getInstancia(){
        if (instancia == null) {
            instancia = new ProdutoEstoqueDAO();
        }
        return instancia;
    }

    @Override
    protected Comparable<?> getChave(Produto produto) {
        return produto.getIdProduto();
    }

    public Produto buscaProduto(int id) {
        return buscaPorChave(id);
    }
    
    /**
     * Gera um novo ID para um produto, baseado no maior ID já registrado no sistema.
     * Isso garante que cada novo produto tenha um ID único.
     * * @return O novo ID do produto, incrementado em relação ao maior ID atual.
     */
    public int geraIdProduto(){
        int maiorIdProduto = 0;
        for(Produto cadaProd : getLista()){
            if(cadaProd.getIdProduto() > maiorIdProduto){
                maiorIdProduto = cadaProd.getIdProduto();
            }
            
        }
        return maiorIdProduto + 1;
    }

    public void adicionaProduto() {
        int idProduto = geraIdProduto();
        String descricao = viewProduto.getDescricao();
        double valor = viewProduto.getValorProduto();
        int quantidade = viewProduto.getQuantidadeProduto();

        Produto novoProduto = new Produto(idProduto, descricao, valor, quantidade, true);
        adicionaDados(novoProduto);
        System.out.println("Produto adicionado com sucesso! ID: " + idProduto);
    }

    public void mostrarProduto() {
        mostraDados(viewProduto::getIdProduto, viewProduto::mostraProduto);
    }

    public void removeProduto(){
        int id = viewProduto.getIdProduto();
        Produto produto = buscaProduto(id);
        
        if (produto == null){
            System.out.println("Produto não encontrado!");
            return;
        }
        
        viewProduto.mostraProduto(produto);
        
        String opcaoConfirmacao = viewProduto.confirmaExclusaoProduto();
        if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação abortada!!");
            return;
        }
        
         if (removeDados(produto)) {
        System.out.println("Produto removido com sucesso!");
        } 
        else {
            System.out.println("Falha ao remover produto! :(");
        }       
    }

    public void editaProduto() {
        editaDados(viewProduto::getIdProduto, produto -> {
            viewProduto.mostraProduto(produto);
            int opcao = viewProduto.editaProduto();
            switch (opcao) {
                case 1 -> produto.setDescricao(viewProduto.getDescricao());
                case 2 -> produto.setValorProduto(viewProduto.getValorProduto());
            }
        });
    }
    
    public void alteraQuantidadeProduto(){
        int idProduto = viewProduto.getIdProduto();
        Produto produto = buscaProduto(idProduto);
        
        if (produto == null) {
            System.out.println("Produto não encontrado! :(");
            return;
        }
        
        int opcao = viewProduto.editaQuantidadeProduto(produto);
        
        switch (opcao) {
            case 1 -> {
                produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() + viewProduto.getQuantidadeProduto());
            }
            case 2 -> {
                produto.setQuantidadeEmEstoque(produto.getQuantidadeEmEstoque() - viewProduto.getQuantidadeProduto());
            }
            default -> {
                System.out.println("Opção inválida, tente novamente");
            }
        }
        
        if (salvarDados()) {
            System.out.println("Quantidade alterada com sucesso!");
        } else {
            System.out.println("Erro ao salvar alterações! :(");
    }
    }
    
    public void mostraEstoqueCompleto() {
    List<Produto> listaProdutos = getLista();
    
    if (listaProdutos == null) {
        System.out.println("Nenhum produto encontrado no estoque! :(");
        return;
    }
    
    System.out.println("\nESTOQUE:");
    System.out.println("Total de produtos: " + listaProdutos.size());
    
    for (Produto produto : listaProdutos) {
        viewProduto.mostraEstoque(produto);
    }
}
}
