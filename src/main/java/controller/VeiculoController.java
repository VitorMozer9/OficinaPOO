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
    private List<Veiculo> listaVeiculo = new ArrayList<>();
    
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

        Veiculo novoVeiculo = new Veiculo(idCliente,modelo,placa,status,anoFabricacao,peso);
        listaVeiculo.add(novoVeiculo);
        OficinaPOO.getInstancia().addVeiculo(novoVeiculo);
        System.out.println("Veículo adicionado com sucesso!");
         
    }
     
    /**
     * Exibe um menu de opções do funcionário e executa a ação solicitada pelo usuário.
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
