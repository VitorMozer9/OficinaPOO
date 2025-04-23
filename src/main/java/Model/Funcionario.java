package Model;

/**
 * Representa um funcionário, que tem todas as propriedades de pessoa e mais algumas informações adicionais como
 * usuário, senha, cargo e salário deste funcionário em questão
 * 
 * Esta classe herda as propriedades básicas da classe {@code Pessoa}.
 */
public class Funcionario extends Pessoa{
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
    
    public Funcionario(String nome, String endereco, String telefone, String email, Cpf cpf, String usuario, String senha, String cargo, double salario) {
        super(nome, endereco, telefone, email, cpf);
        this.usuario = usuario;
        this.senha = senha;
        this.cargo = cargo;
        this.salario = salario;
        
    }
    
    public String getUsuario(){
        return usuario;
    }
    
    public void setUsuario(String usuario){
        this.usuario = usuario;
    }
    
    public String getSenha(){
        return senha;
    }
    
    public void setSenha(String senha){
        this.senha = senha;
    }
    
    public String getCargo(){
        return cargo;
    }
    
    public void setCargo(String cargo){
        this.cargo = cargo;
    }
    
    public double getSalario(){
        return salario;
    }
    
    public void setSalario(double salario){
        this.salario = salario;
    }
}
