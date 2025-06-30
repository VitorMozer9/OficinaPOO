package View;

import Model.Funcionario;
import java.util.Scanner;

/**
 * Classe responsável pela interface de usuário para o sistema de login.
 * Fornece métodos para capturar credenciais de usuário e exibir mensagens de autenticação.
 */
public class LoginView {
    
    /**
     * Scanner para leitura de entrada do usuário.
     */
    private Scanner leituraDados = new Scanner(System.in);
    
    /**
     * Exibe a tela de boas-vindas do sistema.
     */
    public void exibeTelaBemVindo() {
        System.out.println("===========================================");
        System.out.println("           Bem-vindo ao sistema!");
        System.out.println("===========================================");
    }
    
    /**
     * Solicita e retorna o nome de usuário.
     * 
     * @return nome de usuário inserido pelo usuário.
     */
    public String getUsuario() {
        System.out.print("Digite seu usuário: ");
        return leituraDados.nextLine().trim();
    }
    
    /**
     * Solicita e retorna a senha do usuário.
     * 
     * @return senha inserida pelo usuário.
     */
    public String getSenha() {
        System.out.print("Digite sua senha: ");
        return leituraDados.nextLine().trim();
    }
    
    /**
     * Exibe mensagem de sucesso no login com informações do usuário logado.
     * 
     * @param funcionario Funcionário que realizou o login com sucesso.
     */
    public void exibeSucessoLogin(Funcionario funcionario) {
        System.out.println("LOGIN REALIZADO COM SUCESSO!\n Bem-vindo(a), " + funcionario.getNome() + "!");
        System.out.println("Cargo: " + funcionario.getCargo());
        
        if (isGerente(funcionario)) {
            System.out.println("Nível de acesso: GERENTE (Acesso total ao sistema)");
        } else {
            System.out.println("Nível de acesso: FUNCIONÁRIO (Acesso limitado)\n");
        }
    }
    
    /**
     * Exibe mensagem de erro de login.
     */
    public void exibeErroLogin() {
        System.out.println("ERRO DE AUTENTICAÇÃO! Usuário ou senha inválidos!");
    }
    
    /**
     * Exibe mensagem de acesso negado para funcionários sem permissão.
     */
    public void exibeAcessoNegado() {
        System.out.println("ACESSO NEGADO! Você não possui permissão para acessar");
    }
    
    /**
     * Pergunta se o usuário deseja tentar fazer login novamente.
     * 
     * @return true se o usuário deseja tentar novamente, false caso contrário.
     */
    public boolean perguntaTentarNovamente() {
        System.out.print("Deseja tentar novamente? (S/N): ");
        String resposta = leituraDados.nextLine().trim().toUpperCase();
        return resposta.equals("S") || resposta.equals("SIM");
    }
    
    /**
     * Verifica se um funcionário é gerente baseado no cargo.
     * 
     * @param funcionario Funcionário a ser verificado.
     * @return true se for gerente, false caso contrário.
     */
    private boolean isGerente(Funcionario funcionario) {
        return funcionario.getCargo().toUpperCase().contains("GERENTE");
    }
    
    @Override
    public String toString() {
        return "- Interface de Login -";
    }
}