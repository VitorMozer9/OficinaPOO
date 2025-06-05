package controller;

import Main.OficinaPOO;
import Model.Veiculo;
import View.VeiculoView;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela gerência dos veículos da oficina.
 * Fornece métodos para adicionar e executar um menu para veículos.
 */
public class VeiculoController {
    private VeiculoView viewVeiculo = new VeiculoView();
    
    public Veiculo buscaVeiculo(String placaVeiculoBusca){
        try{
            if (placaVeiculoBusca == null) {
            System.out.println("Veículo não encontrado!");
            return null;
        }
            
            for(Veiculo veiculo : OficinaPOO.getInstancia().getVeiculo()){
                
                String placaNaLista = veiculo.getPlacaVeiculo();
                
                if(placaNaLista != null && placaVeiculoBusca.equals(placaNaLista)){
                    return veiculo;
                }
            }    
        }
        catch(Exception e){
             System.out.println("Falha ao buscar cliente!!!");
            return null;
        }
        return null;
    }
    
    /**
     * Reponsável por coletar os dados de veículo utilizando a view, validando a placa do veículo de acordo com o seu padrão
     * e adicionado o novo veículo ao sistema.
     * Exibe uma mensagem de erro ou sucesso de adição do novo veículo. 
     */ 
    public void adicionaVeiculo(){
        int idCliente = viewVeiculo.getIdCliente();
        String modelo = viewVeiculo.getModeloVeiculo();
        String placa = viewVeiculo.getPlacaVeiculo();
        String status = viewVeiculo.getStatusVeiculo();
        int anoFabricacao = viewVeiculo.getAnoDeFabricacao();
        double peso = viewVeiculo.getPesoVeiculo();
        if (!Veiculo.validaPlaca(placa)){
            System.out.println("placa inválida!");
            return;
        }

        Veiculo novoVeiculo = new Veiculo(idCliente, modelo, placa, status, anoFabricacao, peso);
        OficinaPOO.getInstancia().addVeiculo(novoVeiculo);
        if (OficinaPOO.salvarDados(OficinaPOO.getInstancia())){
            System.out.println("Veículo adicionado com sucesso!");
        }else{
            System.out.println("Falha ao adicionar veículo!");
        }
    }
    
      public void mostrarVeiculo(){
        String placaVeiculo = viewVeiculo.getPlacaVeiculo();
        Veiculo veiculo = buscaVeiculo(placaVeiculo);
        
        if (veiculo == null){
            System.out.println("Veículo não encontrado!");
            return;
        }
        
        viewVeiculo.mostraVeiculo(veiculo);
    }
      
    public void removeVeiculo(){
        String placaVeiculo = viewVeiculo.getPlacaVeiculo();
        Veiculo veiculo = buscaVeiculo(placaVeiculo);
        
        if (veiculo == null){
            System.out.println("Veículo não encontrado!");
            return;
        }
        
        viewVeiculo.mostraVeiculo(veiculo);
        
        String opcaoConfirmacao = viewVeiculo.confirmaExclusaoVeiculo();
        if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação abortada!!");
            return;
        }
        
        OficinaPOO.getInstancia().getVeiculo().remove(veiculo);
        
        if(OficinaPOO.salvarDados(OficinaPOO.getInstancia())){
            System.out.println("Veículo removido com sucesso!");
        }else{
            System.out.println("Falha ao remover veículo :(");
        }
            
    }
    
    public void editarVeiculo(){
        String placaVeiculo = viewVeiculo.getPlacaVeiculo();
        Veiculo veiculo = buscaVeiculo(placaVeiculo);
        
        if (veiculo == null){
            System.out.println("Veículo não encontrado!");
            return;
        }
        
        viewVeiculo.mostraVeiculo(veiculo);
        
        System.out.println("Alteração de status do veículo");
        String novoStatusVeiculo = viewVeiculo.getStatusVeiculo();
        editaStatusVeiculo(veiculo, novoStatusVeiculo);
        
        if (OficinaPOO.salvarDados(OficinaPOO.getInstancia())) {
            System.out.println("Alterações salvas com sucesso.");
        } else {
            System.out.println("Erro ao salvar alterações.");
        }
    }
    
    public void editaStatusVeiculo(Veiculo veiculo, String novoStatus){
        veiculo.setStatstusVeiculo(novoStatus);
    }
     
    /**
     * Exibe um menu de opções do veículo e executa a ação solicitada pelo usuário.
     * O menu permanece até que o usuário solicite a opção de sair.
     */
    public void executaMenuVeiculo(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewVeiculo.mostraOpcoesFuncionario();
            
            switch (opcao){
                case 1 -> {
                    adicionaVeiculo();
                }
                case 2 -> {
                    editarVeiculo();
                }
                case 3 -> {
                    removeVeiculo();
                }
                case 4 -> {
                    mostrarVeiculo();
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                   }
                }
            }
        }
    
}
