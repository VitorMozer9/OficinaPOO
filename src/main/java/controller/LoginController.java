package controller;

import Model.Funcionario;
import Model.FuncionarioDAO;
import View.LoginView;

/**
 * Controlador de login.
 * Verifica se o usuário é gerente ou funcionário comum.
 */
public class LoginController {
    private LoginView loginView = new LoginView();
    private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
    private Funcionario usuarioLogado = null;
    
    /**
     * Realiza o login no sistema.
     * @return true se login bem-sucedido, false caso contrário.
     */
    public boolean realizarLogin() {
        loginView.exibeTelaBemVindo();
        
        String usuario = loginView.getUsuario();
        String senha = loginView.getSenha();
        
        usuarioLogado = autenticarUsuario(usuario, senha);
        
        if (usuarioLogado != null) {
            loginView.exibeSucessoLogin(usuarioLogado);
            return true;
        } else {
            loginView.exibeErroLogin();
            return false;
        }
    }
    
    /**
     * Autentica um usuário com base nas credenciais.
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
     * Verifica se o usuário logado é gerente.
     */
    public boolean isGerente() {
        return usuarioLogado != null && 
               usuarioLogado.getCargo().toUpperCase().contains("GERENTE");
    }
    
    /**
     * Verifica se pode acessar área financeira (apenas gerentes).
     */
    public boolean podeAcessarAreaFinanceira() {
        if (!isGerente()) {
            loginView.exibeAcessoNegado();
            return false;
        }
        return true;
    }
    
    /**
     * Verifica se pode cadastrar funcionário (apenas gerentes).
     */
    public boolean podeCadastrarFuncionario() {
        if (!isGerente()) {
            loginView.exibeAcessoNegado();
            return false;
        }
        return true;
    }
    
    public Funcionario getUsuarioLogado() {
        return usuarioLogado;
    }
}