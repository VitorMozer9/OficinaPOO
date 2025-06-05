package Model;

/**
 * Representa um funcionário, que tem todas as propriedades de pessoa e mais algumas informações adicionais como
 * usuário, senha, cargo e salário deste funcionário em questão
 * 
 * Esta classe herda as propriedades básicas da classe {@code Pessoa}.
 */
public class Funcionario extends Pessoa{
    private int idFuncionario;
    private String usuario;
    private String senha;
    private String cargo;
    private double salario;
    
/**
 * Construtor da classe {@code Funcionario}.
 * Inicializa um novo funcionário com as informações básicas de uma pessoa e dados específicos de um funcinário.
 *
 * @param nome     Nome do funcionário.
 * @param endereco Endereço do funcionário.
 * @param telefone Telefone do funcionário.
 * @param email    Email do funcionário.
 * @param cpf      CPF do funcionário.
 * @param usuario  usuario do funcionário.
 * @param senha    Senha do funcionário.
 * @param cargo    Cargo do funcionário (mecânico generalista ou mecânico especifico ou atendente).
 * @param salario  Salário do funcionário.
 */    
    
    public Funcionario(String nome, String endereco, String telefone, String email, Cpf cpf, String usuario, String senha, String cargo, double salario, int idFuncionario) {
        super(nome, endereco, telefone, email, cpf);
        this.idFuncionario = idFuncionario;
        this.usuario = usuario;
        this.senha = senha;
        this.cargo = cargo;
        this.salario = salario;
        
    }
    
    public int getIdFuncionario(){
        return idFuncionario;
    }
    
 /**
 * Pega o usuário do Funcionário
 * 
 * @return usuário do Funcionário.
 */ 
    public String getUsuario(){
        return usuario;
    }
    
/**
 * Determina o usuário do Funcionário.
 * 
 * @param usuario Novo usuário do Funcionário.
 */ 
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
     
/**
 * Pega a senha do Funcionário
 * 
 * @return senha do Funcionário.
 */
    public String getSenha(){
        return senha;
    }
    
/**
 * Determina a senha do Funcionário.
 * 
 * @param senha Nova senha do Funcionário.
 */ 
    public void setSenha(String senha){
        this.senha = senha;
    } 
    
/**
 * Pega o cargo do Funcionário
 * 
 * @return cargo do Funcionário.
 */
    public String getCargo(){
        return cargo;
    }
    
/**
 * Determina o cargo do Funcionário.
 * 
 * @param cargo Novo cargo do Funcionário.
 */ 
    public void setCargo(String cargo){
        this.cargo = cargo;
    }
    
/**
 * Pega o salário do Funcionário
 * 
 * @return salário do Funcionário.
 */
    public double getSalario(){
        return salario;
    }
    
/**
 * Determina o salário do Funcionário.
 * 
 * @param salario Novo salário do Funcionário.
 */ 
    public void setSalario(double salario){
        this.salario = salario;
    }
    
/**
* Sobrescreve o método toString para retornar o nome do funcionário.
* Consegue o nome do funcionário pois a classe Funcionario é subvlasse de Pessoa, logo posso chamar o metodo getNome().
* @return Nome do funcionário.
*/
    @Override
    public String toString() {
        return this.getNome();
    }
}
