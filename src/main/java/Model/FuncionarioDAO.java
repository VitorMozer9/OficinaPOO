package Model;

import Main.OficinaPOO;
import View.FuncionarioView;
import controller.FuncionarioController;
import com.google.gson.reflect.TypeToken;
import java.util.List;

/**
 * Classe responsável por gerenciar os dados dos funcionários no sistesma.
 * Estende a classe {@code GenericDAO<Funcionario>} e fornece operações para
 * cadastrar, buscar, editar, remover e exibir funcionários.
 */
public class FuncionarioDAO extends GenericDAO<Funcionario> {
    private static FuncionarioDAO instancia;
    private FuncionarioView viewFuncionario = new FuncionarioView();

    /**
     * Contrutor padrão da classe {@code FuncionarioDAO}. Inicializa o DAO com o
     * caminho do arquivo de dados dos funcionários e o tipo da lista de
     * funcionários utilizado pelo Gson.
     */
    private FuncionarioDAO() {
        super("data/funcionarios.json", new TypeToken<List<Funcionario>>() {}.getType());
    }
    
    public static FuncionarioDAO getInstancia(){
        if (instancia == null) {
            instancia = new FuncionarioDAO();
        }
        return instancia;
    }

    /**
     * Obtém a chave identificadora de um funcionário.
     *
     * @param funcionario Objeto {@code Funcionario} a ser identificado.
     * @return ID do funcionário.
     */
    @Override
    protected Comparable<?> getChave(Funcionario funcionario) {
        return funcionario.getIdFuncionario();
    }

    /**
     * Busca um funcionário pelo ID
     *
     * @param id ID do funcionário a ser localizado.
     * @return Objeto {@code Funcionario} correspondente ao ID, ou {@code null}
     * se não encontrado.
     */
    public Funcionario buscaFuncionario(int id) {
        return buscaPorChave(id);
    }

    /**
     * Gera um novo ID para funcionário, baseado no maior ID já registrado no
     * sistema. Isso garante que cada novo funcionário tenha um ID único.
     *
     * @return O novo ID do funcionário, incrementa em relação ao maior ID
     * atual.
     */
    public int geraIdFuncionario() {
        int maiorID = 0;
        for (Funcionario cadaFuncionario : getLista()) {
            if (cadaFuncionario.getIdFuncionario() > maiorID) {
                maiorID = cadaFuncionario.getIdFuncionario();
            }
        }
        return maiorID + 1;
    }

    /**
     * Adiciona um novo funcionário ao sistema. Os dados são coletados pela
     * inteface de visualização {@code FuncionarioView}. CPF é validado antes de
     * adicionar o funcionário.
     */
    public void adicionaFuncionario() {
        int idFunc = geraIdFuncionario();
        String nome = viewFuncionario.getNomeFunc();
        String endereco = viewFuncionario.getEnderecoFunc();
        String telefone = viewFuncionario.getFoneFunc();
        String email = viewFuncionario.getEmailFunc();
        String codigoCpf = viewFuncionario.getCpfFunc();
        if (!Cpf.validaCPF(codigoCpf)) {
            System.out.println("CPF inválido!!");
            return;
        }

        String usuario = viewFuncionario.getUsuarioFunc();
        String senha = viewFuncionario.getSenhaFunc();
        String cargo = viewFuncionario.getCargoFunc();
        double salario = viewFuncionario.getSalarioFunc();
        Cpf cpf = new Cpf(codigoCpf);

        Funcionario novoFuncionario = new Funcionario(nome, endereco, telefone, email, cpf, usuario, senha, cargo, salario, idFunc);
        adicionaDados(novoFuncionario);
        System.out.println("Funcionário adicionado com sucesso! ID: " + idFunc);
    }

    /**
     * Mostra os dados de um funcionário com base no ID informado pela view.
     */
    public void mostrarFuncionario() {
        mostraDados(viewFuncionario::getIdFuncionario, viewFuncionario::mostraFuncionario);
    }

    /**
     * Remove um funcionário do sistema ápos confirmação do usuário. Exibe a
     * mensagem de sucesso, falha ou cancelamento da operação.
     */
    public void removeFuncionario() {
        int id = viewFuncionario.getIdFuncionario();
        Funcionario funcionario = buscaFuncionario(id);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        viewFuncionario.mostraFuncionario(funcionario);

        String opcaoConfirmacao = viewFuncionario.confirmaExclusaoFuncionario();
        if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação abortada!!");
            return;
        }

        if (removeDados(funcionario)) {
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Falha ao remover Funcionário! :(");
        }
    }

    /**
     * Edita os dados de um funcionário existente. Permite alterar endereço,
     * telefone, e-mail, senha, cargo ou salário com base na opção escolhida.
     */
    public void editaFuncionario() {
        editaDados(viewFuncionario::getIdFuncionario, funcionario -> {
            viewFuncionario.mostraFuncionario(funcionario);
            int opcao = viewFuncionario.editaFuncionario();
            switch (opcao) {
                case 1 ->
                    funcionario.setEndereco(viewFuncionario.getEnderecoFunc());
                case 2 ->
                    funcionario.setTelefone(viewFuncionario.getFoneFunc());
                case 3 ->
                    funcionario.setEmail(viewFuncionario.getEmailFunc());
                case 4 ->
                    funcionario.setSenha(viewFuncionario.getSenhaFunc());
                case 5 ->
                    funcionario.setCargo(viewFuncionario.getCargoFunc());
                case 6 ->
                    funcionario.setSalario(viewFuncionario.getSalarioFunc());
            }
        });
    }

    /**
     * Edita um funcionário específico pelo ID (usado para funcionários editarem
     * seus próprios dados).
     *
     * @param idFuncionario ID do funcionário a ser editado.
     */
    public void editaFuncionarioCargo(int idFuncionario) {
        Funcionario funcionario = buscaFuncionario(idFuncionario);

        if (funcionario == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        viewFuncionario.mostraFuncionario(funcionario);
        int opcao = viewFuncionario.editaFuncionarioCargo();

        switch (opcao) {
            case 1 ->
                funcionario.setEndereco(viewFuncionario.getEnderecoFunc());
            case 2 ->
                funcionario.setTelefone(viewFuncionario.getFoneFunc());
            case 3 ->
                funcionario.setEmail(viewFuncionario.getEmailFunc());
            case 4 ->
                funcionario.setSenha(viewFuncionario.getSenhaFunc());
            default -> {
                System.out.println("Opção inválida!");
                return;
            }
        }

        if (salvarDados()) {
            System.out.println("Dados atualizados com sucesso!");
        } else {
            System.out.println("Erro ao salvar os dados!");
        }
    }
    
     /**
     * Sobrescreve o método toString para retornar informações dos funcionários.
     * 
     * @return String com informações básicas do registro.
     */
    @Override
    public String toString() {
        return "FuncionarioDAO | Total de funcionários: " + getLista().size();
    }
}
