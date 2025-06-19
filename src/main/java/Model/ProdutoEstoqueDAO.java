package Model;

import Main.OficinaPOO;
import View.ProdutoEstoqueView;
import controller.ProdutoEstoqueController;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class ProdutoEstoqueDAO extends GenericDAO<Produto> {
    private ProdutoEstoqueView viewProduto = new ProdutoEstoqueView();

    public ProdutoEstoqueDAO() {
        super("data/produtos.json", new TypeToken<List<Produto>>() {}.getType());
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
        for(Produto cadaPeca : getLista()){
            if(cadaPeca.getIdProduto() > maiorIdProduto){
                maiorIdProduto = cadaPeca.getIdProduto();
            }
            
        }
        return maiorIdProduto + 1;
    }

    public void adicionaProduto() {
        int idProduto = geraIdProduto();
        String descricao = viewProduto.getDescricao();
        double valor = viewProduto.getValorProduto();
        int quantidade = viewProduto.getQuantidadeProduto();

        Produto novaPeca = new Produto(idProduto, descricao, valor, quantidade, true);
        adicionaDados(novaPeca);
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
}
