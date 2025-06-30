package View;

import Model.Funcionario;
import java.util.Scanner;

/**
 * Interface simplificada para login.
 */
public class LoginView {
    private Scanner leituraDados = new Scanner(System.in);
    
    public void exibeTelaBemVindo() {
        System.out.println("===========================================");
        System.out.println("           Bem-vindo!");
        System.out.println("===========================================");
    }
    
    public String getUsuario() {
        System.out.println("Usuário: ");
        return leituraDados.nextLine().trim();
    }
    
    public String getSenha() {
        System.out.println("Senha: ");
        return leituraDados.nextLine().trim();
    }
    
    public void exibeSucessoLogin(Funcionario funcionario) {
        System.out.println("\n✓ LOGIN REALIZADO COM SUCESSO!");
        System.out.println("Bem-vindo(a), " + funcionario.getNome() + "!");
        
        if (funcionario.getCargo().toUpperCase().contains("GERENTE")) {
            System.out.println("Nível: GERENTE (Acesso total)\n");
        } else {
            System.out.println("Nível: FUNCIONÁRIO (Acesso padrão)\n");
        }
    }
    
    public void exibeErroLogin() {
        System.out.println("\nERRO: Usuário ou senha inválidos!\n");
    }
    
    public void exibeAcessoNegado() {
        System.out.println("\nACESSO NEGADO! Apenas gerentes podem acessar esta funcionalidade.\n");
    }
}