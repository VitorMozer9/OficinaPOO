package Model;

import Main.OficinaPOO;
import View.ClienteView;
import controller.ClienteController;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class ClienteDAO extends GenericDAO<Cliente>{
    private ClienteView viewCliente = new ClienteView();
    
    public ClienteDAO() {
        super("data/clientes.json", new TypeToken<List<Cliente>>() {}.getType());
    }
    
    
    @Override
    
    protected Comparable<?> getChave(Cliente cliente){
        return cliente.getIdCliente();
    }
    
    /**
     * Busca cliente com base no ID informado.
     * 
     * 
     * @param id ID do cliente a ser buscado.
     * @return Objeto Cliente relacionado ao ID, ou null se não encontrado. 
     */
    public Cliente buscarCliente(int id) {
        return buscaPorChave(id); 
    }
    
    /**
     * Coleta dados do cliente a partir da view, valida o CPF e adiciona o novo cliente ao sistema.
     * Exibe mensagem de sucesso ou erro de validação.
     */
    public void adicionaCliente(){
        int idCliente = ClienteController.geraIdCliente();
        
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
}
