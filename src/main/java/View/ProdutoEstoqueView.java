package View;

import Model.Produto;
import java.util.Scanner;

/**
 * Classe responsável pelas interações com o usuário relacionadas as peças.
 * Realizando entradas e saídas de dados para operações como incluir, editar, remover, e mostrar dados da peça.
 */
public class ProdutoEstoqueView {
    
    /**
     * Scanner para leitura de dados inseridos pelo usuário.
     */
    Scanner leituraDados = new Scanner(System.in);
    
    /**
     * Exibe as opções disponiveis no menu das peças e retorna a opção escolhida.
     * 
     * @return um número inteiro correspondente à opção selecionada pelo usuário.
     */
    public int mostraOpcoesProduto(){
        System.out.println("Digite a opção que deseja exucutar: ");
        System.out.println("1 - Incluir produto");
        System.out.println("2 - Editar produto");
        System.out.println("3 - Remover produto");
        System.out.println("4 - Mostrar dados de um produto");
        System.out.println("5 - Alterar quantidade produto (Aumentar/diminuir)");
        System.out.println("6 - Conferir estoque completo");
        System.out.println("7 - Sair");

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    /**
     * Solicita e retorna o ID da peça inserido pelo usuário.
     * 
     * @return ID da peça.
     */
    public int getIdProduto(){
        System.out.println("Digite o ID do produto: ");
        int idProduto = leituraDados.nextInt();
        leituraDados.nextLine();
        return idProduto;
    }
    
    /**
     * Solicita e retorna a descrição da peça inserido pelo usuário.
     * 
     * @return Descrição da peça.
     */
    public String getDescricao(){
        System.out.println("Digite a descrição do produto: ");
        return leituraDados.nextLine();
    }
    
    /**
     * Solicita e retorna a quantidade do produto.
     * 
     * @return Quantidade em estoque. 
     */
    public int getQuantidadeProduto(){
        System.out.println("Digite a quantidade do produto: ");
        int quantidadeProd = leituraDados.nextInt();
        leituraDados.nextLine();
        return quantidadeProd; 
    }
    
    /**
     * Solicita e retorna o valor da peça inserido pelo usuário.
     * 
     * @return Valor da peça.
     */
    public double getValorProduto(){
        System.out.println("Digite o valor do produto: ");
        double valorProd = leituraDados.nextDouble();
        leituraDados.nextLine();
        return valorProd;
    }
    
    /**
     * Exibe os dados de um produto.
     * 
     * @param produto Produto cujos dados serão exibidos.
     */
    public void mostraProduto(Produto produto){
        System.out.println("ID: " +  produto.getIdProduto()                     + "\n" + 
                           "Descrição: " + produto.getDescricao()             + "\n" +
                           "Quantidade: " + produto.getQuantidadeEmEstoque() + "\n" +
                           "Disponível em Estoque: " + produto.getDisponivel()          + "\n" + 
                           "Valor: R$" + produto.getValorProduto()                + "\n");
    }
    
    /**
     * Solicita confirmação para exclusão do produto.
     * 
     * @return "S" para confirmar, qualquer outra tecla para cancelar.
     */
    public String confirmaExclusaoProduto(){
        System.out.println("Tem certeza que deseja remover este produto? \n"
                         + "Digite [S] para confirmar ou [N] para abortar a operação!!");
        return leituraDados.nextLine();
    }
    
    /**
     * Exibe as opções de campos que podem ser editados e retorna a escolha do usuário.
     * 
     * @return número inteiro correspondente ao campo selecionado (1 ou 2). 
     */
    public int editaProduto(){
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - Descrição");
        System.out.println("2 - Valor");
      
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    /**
     * Exibe opções para alterar a quantidade do produto (aumentar ou diminuir).
     * 
     * @param produto Produto cuja quantidade será alterada.
     * @return Número inteiro correspondente à opção selecionada.
     */
    public int editaQuantidadeProduto(Produto produto){
        System.out.println("Quantidade atual: " + produto.getQuantidadeEmEstoque());
        System.out.println("Como deseja alterar a quantidade do produto?");
        System.out.println("1 - Aumentar");
        System.out.println("2 - Diminuir");
        
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }
    
    /**
     * Exibe uma linha simplificada com a descrição e quantidade de um produto no estoque.
     * 
     * @param produto Produto a ser exibido.
     */
    public void mostraEstoque(Produto produto){
        System.out.println(produto.getDescricao() + "| Quantidade: " + produto.getQuantidadeEmEstoque());
    }
    
}
