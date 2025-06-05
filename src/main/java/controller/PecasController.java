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
    
    public int geraIdPeca(){
        int maiorIdPeca = 0;
        for(Pecas cadaPeca : OficinaPOO.getInstancia().getPecas()){
            if(cadaPeca.getIdPeca() > maiorIdPeca){
                maiorIdPeca = cadaPeca.getIdPeca();
            }
            
        }
        return maiorIdPeca + 1;
    }
    
    /**
     * Coleta as informações sobre a peça a partir da view, adicionando a nova peça ao sistema.
     * Exibe uma mensagem se a peça foi adicionada com sucesso.
     */
    public void adicionaPecas(){
        int idPeca = geraIdPeca();
        String descricao = viewPecas.getDescricao();
        double valor = viewPecas.getValorPeca();

        Pecas novaPeca = new Pecas(idPeca,descricao,valor,1,true);
        OficinaPOO.getInstancia().addPeca(novaPeca);
        System.out.println("Peça adicionada com sucesso!" + "ID: " + idPeca);
         
    }
    
    public Pecas buscaPecas(int idPeca){
        try{
            for(Pecas cadaPeca : OficinaPOO.getInstancia().getPecas()){
                if(cadaPeca.getIdPeca() == idPeca){
                    return cadaPeca;
                }
            }
            
        }
        catch(Exception e){
            System.out.println("Falha ao buscar peça");
            return null;
        }
        return null;
    }
    
    public void mostrarPeca(){
        int idPeca = viewPecas.getIdPeca();
        Pecas peca = buscaPecas(idPeca);
        
        if(peca == null){
            System.out.println("Peça não encontrada!!");
            return;
        }
        
        viewPecas.mostraPeca(peca);
    }
    
    public void removePeca(){
        int idPeca = viewPecas.getIdPeca();
        Pecas peca = buscaPecas(idPeca);
        
        if (peca == null){
            System.out.println("Peça não encontrado!");
            return;
        }
        
        viewPecas.mostraPeca(peca);
        
        String opcaoConfirmacao = viewPecas.confirmaExclusaoPeca();
        if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação abortada!!");
            return;
        }
        
        OficinaPOO.getInstancia().getPecas().remove(peca);
        
        if(OficinaPOO.salvarDados(OficinaPOO.getInstancia())){
            System.out.println("Peça removida com sucesso!");
        }else{
            System.out.println("Falha ao remover peça! :(");
        }
    }
    
    public void editaPeca(){
        int idPeca = viewPecas.getIdPeca();
        Pecas peca = buscaPecas(idPeca);
        
        if (peca == null){
            System.out.println("Peça não encontrado!");
            return;
        }
        
        viewPecas.mostraPeca(peca);
        
        
        int opcaoModPeca = viewPecas.editaPeca();
        
        switch(opcaoModPeca){
            case 1 -> {
                String novaDescricao = viewPecas.getDescricao();
                editaDesc(peca, novaDescricao);
            }
            case 2 -> {
                double novoValor = viewPecas.getValorPeca();
                editaValor(peca, novoValor);
            }    
        }
        
        if (OficinaPOO.salvarDados(OficinaPOO.getInstancia())) {
            System.out.println("Alterações salvas com sucesso.");
        } else {
            System.out.println("Erro ao salvar alterações.");
        }
    }
    
    public void editaDesc(Pecas peca, String novaDescricao){
        peca.setDescricao(novaDescricao);
    }
    
    public void editaValor(Pecas peca, double novoValor){
        peca.setValorPeca(novoValor);
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
                case 2 -> {
                    editaPeca();
                }
                case 3 -> {
                    removePeca();
                }
                case 4 -> {
                    mostrarPeca();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
}
