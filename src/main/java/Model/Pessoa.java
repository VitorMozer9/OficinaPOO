package Model;

/**
 * Classe abstrata que representa uma pessoa com informações básicas: nome, endereço, telefone, email e CPF.
 * Esta classe serve como base para classes que representam tipos específicos de pessoas como, funcionários, clientes e gerente.
 */
public abstract class Pessoa {
    private String nome;
    private String endereco;
    private String telefone;
    private String email;
    private Cpf cpf;
    
    
/**
 * Construtor da classe {@code Pessoa}.
 * Cria uma nova instância de pessoa com dados de identificação da mesma.
 *
 * @param nome      Nome da pessoa.
 * @param endereco  Localização da pessoa.
 * @param telefone  Número de telefone da pessoa.
 * @param email     Correio eletrônico da pessoa.
 * @param cpf       Cadastro de Pessoa Física da pessoa.
 */    
    public Pessoa(String nome, String endereco, String telefone, String email, Cpf cpf){
        this.nome = nome;
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }
    
/**
 * Pega o nome da pessoa.
 * 
 * @return nome da pessoa.
 */    
    public String getNome(){
        return nome;
    }
       
/**
 * Determina o nome da pessoa.
 * 
 * @param nome Novo nome da pessoa.
 */
    public void setNome(String nome){
        this.nome = nome;
    }

/**
 * Pega o endereço da pessoa.
 * 
 * @return endereco da pessoa.
 */       
    public String getEndereco(){
        return endereco;
    }

/**
 * Determina o endereço da pessoa.
 * 
 * @param endereco Endereço novo da pessoa.
 */    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
/**
 * Pega o telefone da pessoa.
 * 
 * @return telefone da pessoa.
 */  
    public String getTelefone(){
        return telefone;
    }

/**
 * Determina o telefone da pessoa.
 * 
 * @param telefone Telefone novo da pessoa.
 */      
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
/**
 * Pega o email da pessoa.
 * 
 * @return email da pessoa.
 */  
    public String getEmail(){
        return email;
    }
    
/**
 * Determina o email da pessoa.
 * 
 * @param email Email novo da pessoa.
 */      
    public void setEmail(String email){
        this.email = email;
    }
    
/**
 * Pega o cpf da pessoa.
 * 
 * @return CPF da pessoa.
 */  
    public Cpf getCpf(){
        return cpf;
    }
    
/**
 * Determina o CPF da pessoa.
 * 
 * @param cpf CPF novo da pessoa.
 */      
    public void setCpf(Cpf cpf){
        this.cpf = cpf;
    }
    
/**
* Sobrescreve o método toString para retornar o nome da pessoa.
*
* @return Nome pessoa.
*/
    @Override
    public String toString(){
        return nome;
    }
}
