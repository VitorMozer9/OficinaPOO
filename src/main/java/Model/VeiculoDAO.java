package Model;

import Main.OficinaPOO;
import View.VeiculoView;
public class VeiculoDAO {
    private VeiculoView viewVeiculo = new VeiculoView();
    
     /**
     * Busca um veículo no sistema com base na placa informada.
     * Realiza uma busca linear na lista de veículos registrados na (@link OficinaPOO).
     *
     * @param placaVeiculoBusca A placa do veículo a ser buscado. Pode ser (@code null).
     * @return O objeto (@link Veiculo) associado à placa, ou (@code null) se o veículo
     * não for encontrado ou se ocorrer uma falha durante a busca.
     */
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
    
    /**
     * Solicita a placa de um veículo ao usuário através da (@link VeiculoView), busca o veículo correspondente e 
     * exibe suas informações detalhadas.
     * Se o veículo não for encontrado, uma mensagem de "Veículo não encontrado!" é exibida.
     */
      public void mostrarVeiculo(){
        String placaVeiculo = viewVeiculo.getPlacaVeiculo();
        Veiculo veiculo = buscaVeiculo(placaVeiculo);
        
        if (veiculo == null){
            System.out.println("Veículo não encontrado!");
            return;
        }
        
        viewVeiculo.mostraVeiculo(veiculo);
    }
      
    /**
     * Remove um veículo do sistema. 
     * Primeiramente, solicita a placa do veículo, busca-o, e exibe suas informações para confirmação. 
     * Após a confirmação do usuário, o veículo é removido da lista de veículos e os dados são salvos.
     * Exibe mensagens de erro, confirmação ou de aborto da operação se não for efetuado com sucesso.
     */
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
    
    /**
     * Permite a edição de dados de um veículo existente, especificando o seu status.
     * Primeiro, solicita a placa do veículo, busca-o e exibe seus dados atuais.
     * Em seguida, solicita o novo status do veículo e atualiza o objeto (@link Veiculo).
     * As alterações são persistidas no sistema.
     * Exibe uma mensagem se o veículo não for encontrado ou se ocorrer um erro ao salvar as alterações.
     */
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
    
    /**
     * Edita o status de um veículo específico.
     *
     * @param veiculo O objeto (@link Veiculo) cujos dados serão atualizados.
     * @param novoStatus O novo status a ser atribuído ao veículo.
     */
    public void editaStatusVeiculo(Veiculo veiculo, String novoStatus){
        veiculo.setStatstusVeiculo(novoStatus);
    }
    
}
