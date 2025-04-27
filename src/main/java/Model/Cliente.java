package Model;

/**
 * Representa um cliente, tem todas as informações de uma pessoa, com a adição de um id para verificação e facilitar a consulta
 * Esta classe herda as propriedades básicas da classe {@code Pessoa}.
 */
public class Cliente extends Pessoa{
/**
 * Atributo estático com o intuito de incrementar um digito cada vez que é criado um novo cliente. 
 */
    public static int ultimoIdCliente = 0;
    
    private int idCliente;
    
/**
* Construtor da classe {@code Cliente}.
* Inicializa um novo cliente com as informações básicas de uma pessoa e os dados específicos do cliente.
* Incrementa o idCliente a cada nova criação de um novo cliente (objeto de cliente)
*
* @param nome          Nome do cliente.
* @param endereco      Endereço do cliente.
* @param telefone      Telefone do cliente.
* @param email         Email do cliente.
* @param cpf           CPF do cliente.
* @param idCliente     Identificador único do cliente.
*/
    public Cliente(String nome, String endereco, String telefone, String email, Cpf cpf, int idCliente) {
        super(nome, endereco, telefone, email, cpf);
        
        this.idCliente = ++ultimoIdCliente;
    }
    
/**
 * Pega o ID do cliente em questão.
 * 
 * Atributo não necesita de um set, vito que o idCliente é determinado pela propria classe, a cada criação de um objeto de Cliente
 * @return ID do cliente.
 */
    public int getIdCliente(){
        return idCliente;
    }
    
/**
* Sobrescreve o método toString para retornar o nome do cliente concatenado ao seu id de referencia.
*
* @return Nome do cliente + ID do Cliente.
*/
    @Override
    public String toString() {
        return this.getNome() + ": " + idCliente;
    }

}
