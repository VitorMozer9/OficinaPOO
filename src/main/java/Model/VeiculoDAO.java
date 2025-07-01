package Model;

import Main.OficinaPOO;
import View.VeiculoView;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * DAO responsável pela persistência e manipulação de dados da entidade {@code Veiculo}.
 * Permite operações como adicionar, buscar, editar, remover e exibir veículos.
 */
public class VeiculoDAO extends GenericDAO<Veiculo> {
    
    private static VeiculoDAO instancia;
    private VeiculoView viewVeiculo = new VeiculoView();

    /**
     * Construtor padrão que define o caminho do arquivo JSON e o tipo da lista de veículos. 
     */
    private VeiculoDAO() {
        super("data/veiculos.json", new TypeToken<List<Veiculo>>() {}.getType());
    }
    
    public static VeiculoDAO getInstancia(){
        if (instancia == null) {
            instancia = new VeiculoDAO();
        }
        return instancia;
    }

    /**
     * Define a chave única usada para buscar veículos: a placa
     * 
     * @param veiculo O veículo a ser comparado com a lista.
     * @return A placa do veículo que está sendo comparado.
     */
    @Override
    protected Comparable<?> getChave(Veiculo veiculo) {
        return veiculo.getPlacaVeiculo();
    }

    /**
     * Busca um veículo a partir de sua placa.
     * 
     * @param placa Placa do veículo.
     * @return Veículo correspondente ou {@code null} se não encontrado.
     */
    public Veiculo buscaVeiculo(String placa) {
        return buscaPorChave(placa);
    }

    /**
     * Adiciona um novo veículo ao sistema.
     * Realiza validação da placa antes de persistir.
     */
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

    /**
     * Mostra os dados de um veículo com base na placa informada pela view. 
     */
    public void mostrarVeiculo() {
        mostraDados(viewVeiculo::getPlacaVeiculo, viewVeiculo::mostraVeiculo);
    }

    /**
     * Remove um veículo do sistema, após confirmação do usuário.
     * A placa é usada como chave para localizar o registro.
     */
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

    /**
     * Permite editar o status do veículo com base na placa informada.
     */
    public void editarVeiculo() {
        editaDados(viewVeiculo::getPlacaVeiculo, veiculo -> {
            viewVeiculo.mostraVeiculo(veiculo);
            veiculo.setStatstusVeiculo(viewVeiculo.getStatusVeiculo());
        });
    }
    
    @Override
    public String toString(){
        return "VeiculoDAO | Veículos cadastrados: " + getLista().size();
    }
}
