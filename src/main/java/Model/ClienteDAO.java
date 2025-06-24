package Model;

import Main.OficinaPOO;
import View.ClienteView;
import controller.ClienteController;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Classe responsável por gerenciar operações de persistência e manipulação de dados
 * relacionados a objetos {@link Cliente}. 
 * Estende {@link GenericDAO} para utilizar operações genéricas de CRUD com armazenamento no JSON.
 */
public class ClienteDAO extends GenericDAO<Cliente>{
    
    /**
     * Instância da View para interação com o usuário.
     */
    private ClienteView viewCliente = new ClienteView();
    
    /**
     * Construtor da classe{@code ClienteDAO}.
     * Inicializando o DAO com o caminho do JSON e o tipo da lista de Cliente.
     */
    public ClienteDAO() {
        super("data/clientes.json", new TypeToken<List<Cliente>>() {}.getType());
    }
    
    /**
     * Obtém a chave primária de um objeto {@link Cliente}, utilizada nas operações de busca e remoção.
     * 
     * @param cliente o cliente do qual será extraído o ID.
     * @return a chave(ID) do cliente.
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
     * @param id o ID do cliente a ser buscado.
     * @return o cliente correspondente, ou {@code null} se não for encontrado.
     */
    public Cliente buscarCliente(int id) {
        return buscaPorChave(id); 
    }
    
    /**
     * Solicita os dados de um novo cliente via interface de visualização, valida o CPF e 
     * adiciona o cliente ao repositório.
     * Exibe mensagens apropriadas em caso de sucesso ou erro.
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
     * Permite a edição de dados específicos (endereço, telefone ou e-mail) de um cliente.
     * O cliente é selecionado por ID e a edição é feita com base na opção escolhida pelo usuário.
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
}
