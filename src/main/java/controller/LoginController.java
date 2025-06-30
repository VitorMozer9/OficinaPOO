package controller;

import Model.Funcionario;
import Model.FuncionarioDAO;
import View.LoginView;

/**
 * Classe responsável por gerenciar o sistema de autenticação e autorização.
 * Controla o login, logout e verificação de permissões dos usuários.
 */
public class LoginController {
    private LoginView loginView = new LoginView();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Funcionario usuarioLogado = null;
    
    /**
     * Realiza o processo de login do sistema.
     * Continua tentando até que o login seja bem-sucedido ou o usuário desista.
     * 
     * @return true se o login foi bem-sucedido, false se o usuário desistiu.
     */
    public boolean realizarLogin() {
        loginView.exibeTelaBemVindo();
        
        boolean loginSucesso = false;
        int tentativas = 0;
        final int MAX_TENTATIVAS = 3;
        
        while (!loginSucesso && tentativas < MAX_TENTATIVAS) {
            String usuario = loginView.getUsuario();
            String senha = loginView.getSenha();
            
            usuarioLogado = autenticarUsuario(usuario, senha);
            
            if (usuarioLogado != null) {
                loginView.exibeSucessoLogin(usuarioLogado);
                loginSucesso = true;
            } else {
                tentativas++;
                loginView.exibeErroLogin();
                
                if (tentativas < MAX_TENTATIVAS) {
                    if (!loginView.perguntaTentarNovamente()) {
                        break;
                    }
                } else {
                    System.out.println("Número máximo de tentativas excedido. Sistema bloqueado.");
                }
            }
        }
        
        return loginSucesso;
    }
    
    /**
     * Autentica um usuário com base nas credenciais fornecidas.
     * 
     * @param usuario Nome de usuário.
     * @param senha Senha do usuário.
     * @return Objeto Funcionario se a autenticação foi bem-sucedida, null caso contrário.
     */
    private Funcionario autenticarUsuario(String usuario, String senha) {
        if (usuario == null || senha == null || usuario.isEmpty() || senha.isEmpty()) {
            return null;
        }
        
        for (Funcionario funcionario : funcionarioDAO.getLista()) {
            if (funcionario.getUsuario().equals(usuario) && 
                funcionario.getSenha().equals(senha)) {
                return funcionario;
            }
        }
        
        return null;
    }
    
    /**
     * Verifica se há um usuário logado no sistema.
     * 
     * @return true se há um usuário logado, false caso contrário.
     */
    public boolean isUsuarioLogado() {
        return usuarioLogado != null;
    }
    
    /**
     * Retorna o funcionário atualmente logado.
     * 
     * @return Funcionario logado ou null se ninguém estiver logado.
     */
    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }
    
    /**
     * Verifica se o usuário logado é um gerente.
     * 
     * @return true se o usuário logado for gerente, false caso contrário.
     */
    public boolean isGerente() {
        if (usuarioLogado == null) {
            return false;
        }
        return usuarioLogado.getCargo().toUpperCase().contains("GERENTE");
    }
    
    /**
     * Verifica se o usuário tem permissão para acessar funcionalidades de gerente.
     * Exibe mensagem de acesso negado se não tiver permissão.
     * 
     * @return true se tem permissão, false caso contrário.
     */
    public boolean verificarPermissaoGerente() {
        if (!isGerente()) {
            loginView.exibeAcessoNegado();
            return false;
        }
        return true;
    }
    
    /**
     * Verifica se o usuário pode editar dados de funcionário.
     * Gerentes podem editar qualquer funcionário, funcionários comuns só podem editar a si mesmos.
     * 
     * @param idFuncionario ID do funcionário a ser editado.
     * @return true se pode editar, false caso contrário.
     */
    public boolean podeEditarFuncionario(int idFuncionario) {
        if (usuarioLogado == null) {
            return false;
        }
        
        if (isGerente()) {
            return true;
        }
        
        return usuarioLogado.getIdFuncionario() == idFuncionario;
    }
    
    /**
     * Verifica se o usuário pode acessar a área financeira.
     * Apenas gerentes têm acesso.
     * 
     * @return true se pode acessar, false caso contrário.
     */
    public boolean podeAcessarAreaFinanceira() {
        return verificarPermissaoGerente();
    }
    
    /**
     * Verifica se o usuário pode cadastrar novos funcionários.
     * Apenas gerentes podem cadastrar funcionários.
     * 
     * @return true se pode cadastrar, false caso contrário.
     */
    public boolean podeCadastrarFuncionario() {
        return verificarPermissaoGerente();
    }
    
    /**
     * Verifica se o usuário pode remover funcionários.
     * Apenas gerentes podem remover funcionários.
     * 
     * @return true se pode remover, false caso contrário.
     */
    public boolean podeRemoverFuncionario() {
        return verificarPermissaoGerente();
    }
    
    /**
     * Retorna informações sobre o controle de login.
     * 
     * @return String com informações do usuário logado.
     */
    @Override
    public String toString() {
        if (usuarioLogado != null) {
            return String.format("LoginController: Usuário logado - %s (%s)", 
                               usuarioLogado.getNome(), 
                               usuarioLogado.getCargo());
        }
        return "LoginController: Nenhum usuário logado";
    }
}