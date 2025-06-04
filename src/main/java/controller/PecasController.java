package controller;

import Main.OficinaPOO;
import Model.Pecas;
import Model.Veiculo;
import View.PecasView;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela gerência das peças da oficina.
 * Fornecendo métodos para adicionar e acessar o menu de opções de peças.
 */
public class PecasController {
    private PecasView viewPecas = new PecasView();
    private List<Pecas> listaPecas = new ArrayList<>();
    
    /**
     * Coleta as informações sobre a peça a partir da view, adicionando a nova peça ao sistema.
     * Exibe uma mensagem se a peça foi adicionada com sucesso.
     */
    public void adicionaPecas(){
        int idPeca = viewPecas.getIdPeca();
        String descricao = viewPecas.getDescricao();
        double valor = viewPecas.getValorPeca();

        Pecas novaPeca = new Pecas(idPeca,descricao,valor,1,true);
        listaPecas.add(novaPeca);
        OficinaPOO.getInstancia().addPeca(novaPeca);
        System.out.println("Peça adicionada com sucesso!");
         
    }
     
    /**
     * Exibe o menu de opções para as Peças e executa a ação solicitada pelo usuário.
     * O menu permanece ativo até que o usuário selecione a opção de sair.
     */
    public void executaMenuPecas(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewPecas.mostraOpcoesPeca();
            
            switch (opcao){
                case 1 -> {
                    adicionaPecas();
                }
//                case 2 -> {
//                    editaCliente();
//                }
//                case 3 -> {
//                    removeCliente();
//                }
//                case 4 -> {
//                    mostraCliente();
                }
            }
        }
    
}
