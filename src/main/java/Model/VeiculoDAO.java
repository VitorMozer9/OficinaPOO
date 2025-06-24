package Model;

import Main.OficinaPOO;
import View.VeiculoView;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class VeiculoDAO extends GenericDAO<Veiculo> {
    private VeiculoView viewVeiculo = new VeiculoView();

    public VeiculoDAO() {
        super("data/veiculos.json", new TypeToken<List<Veiculo>>() {}.getType());
    }

    @Override
    protected Comparable<?> getChave(Veiculo veiculo) {
        return veiculo.getPlacaVeiculo();
    }

    public Veiculo buscaVeiculo(String placa) {
        return buscaPorChave(placa);
    }

    public void adicionaVeiculo() {
        int idCliente = viewVeiculo.getIdCliente();
        String modelo = viewVeiculo.getModeloVeiculo();
        String placa = viewVeiculo.getPlacaVeiculo();
        String status = viewVeiculo.getStatusVeiculo();
        int anoFabricacao = viewVeiculo.getAnoDeFabricacao();
        double peso = viewVeiculo.getPesoVeiculo();

        if (!Veiculo.validaPlaca(placa)) {
            System.out.println("Placa inválida!");
            return;
        }

        Veiculo novoVeiculo = new Veiculo(idCliente, modelo, placa, status, anoFabricacao, peso);
        adicionaDados(novoVeiculo);
        System.out.println("Veículo adicionado com sucesso!");
    }

    public void mostrarVeiculo() {
        mostraDados(viewVeiculo::getPlacaVeiculo, viewVeiculo::mostraVeiculo);
    }

    public void removeVeiculo(){
        String placa = viewVeiculo.getPlacaVeiculo();
        Veiculo veiculo = buscaVeiculo(placa);
        
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
        
         if (removeDados(veiculo)) {
        System.out.println("Veículo removido com sucesso!");
        } 
        else {
            System.out.println("Falha ao remover veículo! :(");
        }       
    }

    public void editarVeiculo() {
        editaDados(viewVeiculo::getPlacaVeiculo, veiculo -> {
            viewVeiculo.mostraVeiculo(veiculo);
            veiculo.setStatstusVeiculo(viewVeiculo.getStatusVeiculo());
        });
    }
}
