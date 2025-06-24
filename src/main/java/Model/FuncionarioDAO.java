package Model;

import Main.OficinaPOO;
import View.FuncionarioView;
import controller.FuncionarioController;
import com.google.gson.reflect.TypeToken;
import java.util.List;

public class FuncionarioDAO extends GenericDAO<Funcionario> {
    private FuncionarioView viewFuncionario = new FuncionarioView();

    public FuncionarioDAO() {
        super("data/funcionarios.json", new TypeToken<List<Funcionario>>() {}.getType());
    }

    @Override
    protected Comparable<?> getChave(Funcionario funcionario) {
        return funcionario.getIdFuncionario();
    }

    public Funcionario buscaFuncionario(int id) {
        return buscaPorChave(id);
    }
    
    /**
     * Gera um novo ID para funcionário, baseado no maior ID já registrado no sistema.
     * Isso garante que cada novo funcionário tenha um ID único.
     * 
     * @return O novo ID do funcionário, incrementa em relação ao maior ID atual.
     */
    public int geraIdFuncionario(){
        int maiorID = 0;
        for(Funcionario cadaFuncionario : getLista()){
            if (cadaFuncionario.getIdFuncionario() > maiorID){
                maiorID = cadaFuncionario.getIdFuncionario();
            }
        }
        return maiorID + 1;
    }

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

    public void mostrarFuncionario() {
        mostraDados(viewFuncionario::getIdFuncionario, viewFuncionario::mostraFuncionario);
    }

       public void removeFuncionario(){
        int id = viewFuncionario.getIdFuncionario();
        Funcionario funcionario = buscaFuncionario(id);
        
        if (funcionario == null){
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
        } 
        else {
            System.out.println("Falha ao remover Funcionário! :(");
        }       
    }

    public void editaFuncionario() {
        editaDados(viewFuncionario::getIdFuncionario, funcionario -> {
            viewFuncionario.mostraFuncionario(funcionario);
            int opcao = viewFuncionario.editaFuncionario();
            switch (opcao) {
                case 1 -> funcionario.setEndereco(viewFuncionario.getEnderecoFunc());
                case 2 -> funcionario.setTelefone(viewFuncionario.getFoneFunc());
                case 3 -> funcionario.setEmail(viewFuncionario.getEmailFunc());
                case 4 -> funcionario.setSenha(viewFuncionario.getSenhaFunc());
                case 5 -> funcionario.setCargo(viewFuncionario.getCargoFunc());
                case 6 -> funcionario.setSalario(viewFuncionario.getSalarioFunc());
            }
        });
    }
}
