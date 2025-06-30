package View;

import Model.Funcionario;
import java.util.Scanner;

/**
 * Classe responsável pela interface de usuário para a gestão dos funcionários.
 * Forncendo métodos para incluir, editar, remover e mostrar os dados dos
 * funcionários.
 */
public class FuncionarioView {

    /**
     * Scanner para leitura e entrada do usuário.
     */
    Scanner leituraDados = new Scanner(System.in);

    /**
     * Exibe as opções disponiveis no menu do funcionário e retorna a opção
     * escolhida.
     *
     * @return um número inteiro de acordo com a opção escolhida pelo usuário.
     */
    public int mostraOpcoesFuncionario() {
        System.out.println("Digite a opção que deseja exucutar: ");
        System.out.println("1 - Incluir Funcionário");
        System.out.println("2 - Editar Funcionário");
        System.out.println("3 - Remover Funcionário");
        System.out.println("4 - Mostrar dados de um Funcionário");
        System.out.println("5 - Sair");

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    /**
     * Solicita e retorna o ID do funcionário.
     *
     * @return ID do funcionário.
     */
    public int getIdFuncionario() {
        System.out.println("Digite o ID do FUncionário!");
        int id = leituraDados.nextInt();
        leituraDados.nextLine();
        return id;
    }

    /**
     * Solicita e retorna o nome do funcionário inserido pelo usuário.
     *
     * @return nome do funcionário.
     */
    public String getNomeFunc() {
        System.out.println("Digite o nome do Funcionário: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o endereço do funicionário inserido pelo usuário.
     *
     * @return endereço do funcionário.
     */
    public String getEnderecoFunc() {
        System.out.println("Digite o endereço do Funcionário: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o telefone do funcionário inserido pelo usuário.
     *
     * @return telefone do funcionário.
     */
    public String getFoneFunc() {
        System.out.println("Digite o telefone do Funcionário: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o email do funcionário inserido pelo usuário.
     *
     * @return email do funcionário.
     */
    public String getEmailFunc() {
        System.out.println("Digite o email do Funcionário: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o CPF do funconário inserido pelo usuário.
     *
     * @return CPF do funcionário.
     */
    public String getCpfFunc() {
        System.out.println("Digite o CPF do Funcionário: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o login do funcionário inserido pelo usuário.
     *
     * @return Login do funcionário.
     */
    public String getUsuarioFunc() {
        System.out.println("Digite o login de usuário do funcionário: ");
        return leituraDados.nextLine();
    }

    /**
     * Solcita e retorna a senha do funcionário inserido pelo usuário.
     *
     * @return Senha do funcionário.
     */
    public String getSenhaFunc() {
        System.out.println("Digite a senha do funcionário: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o cargo do funcionário inserido pelo usuário.
     *
     * @return Cargo do funcionário.
     */
    public String getCargoFunc() {
        System.out.println("Digite o cargo do funcionário: ");
        return leituraDados.nextLine();
    }

    /**
     * Solicita e retorna o salário do funcionário inserido pelo usuário.
     *
     * @return Salário do funcionário.
     */
    public double getSalarioFunc() {
        System.out.println("Digite o salário do funcionário: ");
        double salario = leituraDados.nextDouble();
        leituraDados.nextLine();
        return salario;
    }

    /**
     * Exibe as informações de um fincionário.
     *
     * @param funcionario Funcionário a ser exibido.
     */
    public void mostraFuncionario(Funcionario funcionario) {
        System.out.println("ID: " + funcionario.getIdFuncionario() + "\n"
                + "Nome: " + funcionario.getNome() + "\n"
                + "Endereço: " + funcionario.getEndereco() + "\n"
                + "Telefone: " + funcionario.getTelefone() + "\n"
                + "E-mail: " + funcionario.getEmail() + "\n"
                + "CPF: " + funcionario.getCpf() + "\n"
                + "Usuário: " + funcionario.getUsuario() + "\n"
                + "Senha: ******************* \n"
                + "Cargo: " + funcionario.getCargo() + "\n"
                + "Salário: " + funcionario.getSalario());
    }

    /**
     * Confirma a exclusão de um funcionário.
     *
     * @return "S" para confirmar, outro valor para cancelar.
     */
    public String confirmaExclusaoFuncionario() {
        System.out.println("Tem certeza que deseja remover este funcionário? \n"
                + "Digite [S] para confirmar ou [N] para abortar a operação!!");
        return leituraDados.nextLine();
    }

    /**
     * Exibe as opções de campos que podem ser editados e retorna a escolha do
     * usuário.
     *
     * @return número inteiro correspondente ao campo selecionado (1, 2, 3, 4 ou
     * 5).
     */
    public int editaFuncionario() {
        System.out.println("Digite o campo que você gostaria de editar!");
        System.out.println("1 - Endereço");
        System.out.println("2 - Telefone");
        System.out.println("3 - E-mail");
        //System.out.println("4 - Usuário");
        System.out.println("4 - Senha");
        System.out.println("5 - Cargo");
        System.out.println("6 - Salário");

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    /**
     * Exibe opções de edição limitadas para funcionários comuns.
     *
     * @return número inteiro correspondente ao campo selecionado.
     */
    public int editaFuncionarioCargo() {
        System.out.println("Digite o campo que você gostaria de editar:");
        System.out.println("1 - Endereço");
        System.out.println("2 - Telefone");
        System.out.println("3 - E-mail");
        System.out.println("4 - Senha");
        System.out.println("Obs: Apenas gerentes podem alterar cargo e salário.");

        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    /**
     * Método genérico para capturar opção do menu.
     *
     * @return opção escolhida pelo usuário.
     */
    public int getOpcaoMenu() {
        int opcao = leituraDados.nextInt();
        leituraDados.nextLine();
        return opcao;
    }

    @Override
    public String toString() {
        return "- Interface Funcionário -";
    }
}
