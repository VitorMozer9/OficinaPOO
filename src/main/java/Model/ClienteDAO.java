package Model;

import Main.OficinaPOO;
import View.ClienteView;
import controller.ClienteController;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Classe reponsável por gerenciar os dados dos clientes no sistema.
 * Realiza operações como adicionar, remover, editar e buscar clientes.
 * Herda os métodos genéricos de persistência da classe GenericDAO.
 */
public class ClienteDAO extends GenericDAO<Cliente>{
    
    private static ClienteDAO instancia;
    private ClienteView viewCliente = new ClienteView();
    
    /**
     * Contrutor padrão.
     * Inicializa o DAO com o caminho do JSON e o tipo da lista de cliente. 
     */
    private ClienteDAO() {
        super("data/clientes.json", new TypeToken<List<Cliente>>() {}.getType());
    }
    
    public static ClienteDAO getInstancia(){
        if (instancia == null) {
            instancia = new ClienteDAO();
        }
        return instancia;
    }
    
    /**
     * Obtém a chave única (ID) do cliente.
     * 
     * @param cliente Objeto cliente a ser analisado.
     * @return ID do cliente.
     */
    @Override
    protected Comparable<?> getChave(Cliente cliente){
        return cliente.getIdCliente();
    }
    
    /**
     * Gera um novo ID de cliente, baseado no maior ID já registrado.
     * 
     * @return novo ID do cliente (incrementado em relação ao maior ID atual).
     */
    public int geraIdCliente(){
        int maiorIdCliente = 0;
        for (Cliente cadaCliente : getLista()){
            if(cadaCliente.getIdCliente() > maiorIdCliente){
                maiorIdCliente = cadaCliente.getIdCliente();
            }
        }
        return maiorIdCliente + 1;
    }
    
    /**
     * Busca um cliente pelo seu ID.
     * 
     * @param id ID do cliente a ser buscado.
     * @return Cliente correspondente ao ID, ou null se não encontrado.
     */
    public Cliente buscarCliente(int id) {
        return buscaPorChave(id); 
    }
    
    /**
     * Coleta os dados da view, valida o CPF e adiciona um novo cliente ao sistema.
     * Caso o CPF seja inválido, a operação é cancelada.
     */
    public void adicionaCliente(){
        int idCliente = geraIdCliente();
        
        String nome = viewCliente.getNomeCliente();
        String endereco = viewCliente.getEnderecoCliente();
        String telefone = viewCliente.getFoneCliente();
        String email = viewCliente.getEmailCliente();
        String codigoCpf = viewCliente.getCpfCliente();
        if (!Cpf.validaCPF(codigoCpf)){
            System.out.println("CPF inválido!!");
            return;
        }
        
        Cpf cpf = new Cpf(codigoCpf); 

        Cliente novoCliente = new Cliente(nome, endereco, telefone, email, cpf,idCliente);
            
        adicionaDados(novoCliente);
        
        System.out.println("Cliente adicionado com sucesso! ID do cliente: " + idCliente);
    
    }
    
    /**
     * Mostra as informações dos clientes com base no ID informado pela view 
     */
    public void mostrarCliente(){
        mostraDados(viewCliente::getIdCliente, viewCliente::mostraCliente);
    }
    
    /**
     * Remove um cliente do sistema após confirmação do usuário.
     * Exibe mensagem de erro, confirmação ou falha na operação.
     */
    public void removeCliente(){
        int id = viewCliente.getIdCliente();
        Cliente cliente = buscarCliente(id);
        
        if (cliente == null){
            System.out.println("Cliente não encontrado!");
            return;
        }
        
        viewCliente.mostraCliente(cliente);
        
        String opcaoConfirmacao = viewCliente.confirmaExclusaoCliente();
        if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação abortada!!");
            return;
        }
        
        if (removeDados(cliente)) {
            System.out.println("Cliente removido com sucesso!");
        } 
        else {
            System.out.println("Falha ao remover cliente! :(");
        }       
    }
    
    /**
     * Edita os dados anteriores fornecidos para Cliente.
     * Selecionando de acordo com a opção selecionada pelo usuário.
     */
    public void editarCliente(){
        editaDados(viewCliente::getIdCliente, cliente -> {
            viewCliente.mostraCliente(cliente);
            int opcao = viewCliente.editaCliente();
            
            switch (opcao){
                case 1 -> cliente.setEndereco(viewCliente.getEnderecoCliente());
                case 2 -> cliente.setTelefone(viewCliente.getFoneCliente());
                case 3 -> cliente.setEmail(viewCliente.getEmailCliente());
            }
        });
            
    }

    @Override
    public String toString(){
        return "ClienteDAO | Total de Clientes: " + getLista().size();
    }
}
