package controller;

import Main.OficinaPOO;
import Model.Cpf;
import Model.Funcionario;
import View.FuncionarioView;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe responsável pela gerencia dos funcionários.
 * Fornece métodos para adicionar e mostrar o menu de informações de funcionário.
 */
public class FuncionarioController {
    private FuncionarioView viewFuncionario = new FuncionarioView();
    
    /**
     * Gera um novo ID para funcionário, baseado no maior ID já registrado no sistema.
     * Isso garante que cada novo funcionário tenha um ID único.
     * 
     * @return O novo ID do funcionário, incrementa em relação ao maior ID atual.
     */
    public int geraIdFuncionario(){
        int maiorID = 0;
        for(Funcionario cadaFuncionario : OficinaPOO.getInstancia().getFuncionario()){
            if (cadaFuncionario.getIdFuncionario() > maiorID){
                maiorID = cadaFuncionario.getIdFuncionario();
            }
        }
        return maiorID + 1;
    }
    
    
    /**
     * Busca um funcionário no sistema com base no ID informado.
     * 
     * @param idFuncionario O ID do funcionário a ser buscado.
     * @return O objeto (@link Funcionario) relacionado ao ID fornecido, ou (@code null) se nenhum funcionário for encontrado.
     */
    public Funcionario buscaFuncionario(int idFuncionario){
        try{    
            for(Funcionario funcionario : OficinaPOO.getInstancia().getFuncionario()){
                if (funcionario.getIdFuncionario() == idFuncionario) {
                    return funcionario;
                }
            }
        }
        catch(Exception e){
            System.out.println("Falha ao buscar Funcionário!!");
            return null;
        }
        return null;
    }
    /**
     * Coleta os dados do funcionário a partir da view, válida o seu CPF e adicina o novo funcionário ao sistema.
     * Exibe uma mensagem de sucesso ou erro de validação para o usuário. 
     */
    public void adicionaFuncionario(){
        int idFunc = geraIdFuncionario();
        String nome = viewFuncionario.getNomeFunc();
        String endereco = viewFuncionario.getEnderecoFunc();
        String telefone = viewFuncionario.getFoneFunc();
        String email = viewFuncionario.getEmailFunc();
        String codigoCpf = viewFuncionario.getCpfFunc();
        if (!Cpf.validaCPF(codigoCpf)){
            System.out.println("CPF inválido!!");
            return;
        }
        
        String usuario = viewFuncionario.getUsuarioFunc();
        String senha = viewFuncionario.getSenhaFunc();
        String cargo = viewFuncionario.getCargoFunc();
        double salario = viewFuncionario.getSalarioFunc();
        
        Cpf cpf = new Cpf(codigoCpf); 

        Funcionario novoFuncionario = new Funcionario(nome, endereco, telefone, email, cpf,usuario,senha,cargo,salario,idFunc);
        OficinaPOO.getInstancia().addFuncionario(novoFuncionario);
        if(OficinaPOO.salvarDados(OficinaPOO.getInstancia()))
            System.out.println("Funcionário adicionado com sucesso!" + "id: " + idFunc);
        else{
            System.out.println("Falha ao adicionar Funcionário!");
        }
         
    }
    
    /**
     * Solicita um ID de funcionário ao úsuario através da (@link FuncionarioView), busca o funcionário correspondente 
     * e exibe suas informações detalhadas.
     * Se o funcionário não for encontrado, uma mensagem de "Funcionário Não encontrado!" é exibido.
     */
    public void mostrarFuncionario(){
        int id = viewFuncionario.getIdFuncionario();
        Funcionario funcionario = buscaFuncionario(id);
        
        if (funcionario == null){
            System.out.println("Funcionário não encontrado!");
            return;
        }
        
        viewFuncionario.mostraFuncionario(funcionario);
    }
    
    /**
     * Remove o funcionário do sistema.
     * Primeiramente, solicita o ID do funcionário, busca-o, e exibe suas informações para confirmação.
     * Após a confirmação do usuário, o funcionário é removido da lista de funcionários e os dados são salvos.
     * Exibe mensagens de erro, confirmação ou de aborto da operação
     */
    public void removeFuncionario(){
        int idFunc = viewFuncionario.getIdFuncionario();
        Funcionario funcionario = buscaFuncionario(idFunc);
        
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
        
        OficinaPOO.getInstancia().getFuncionario().remove(funcionario);
        
        if(OficinaPOO.salvarDados(OficinaPOO.getInstancia())){
            System.out.println("Funcionário removido com sucesso!");
        }else{
            System.out.println("Falha ao remover funcionário! :(");
        }
    }
    
    /**
     * Prepara a edição dos dados de um funcionário existente. 
     * O usuário é solicitado a informar o ID do funcionário, e após a busca e exibição dos dados atuais, 
     * a lógica de edição será iniciada.
     * As alterações devem ser salvas após a conclusão da edição em métodos subsequentes.
     * Exibe uma mensagem se o funcionário não for encontrado.
     */
    public void editaFuncionario(){
        int idfuncionario = viewFuncionario.getIdFuncionario();
        Funcionario funcionario = buscaFuncionario(idfuncionario);
        
        if (funcionario == null){
            System.out.println("Funcionário não encontrado!!");
            return;
        }
        
        viewFuncionario.mostraFuncionario(funcionario);
        
        
        int opcaoModCliente = viewFuncionario.editaCliente();
        
        switch(opcaoModCliente){
            case 1 -> {
                String novoEndereco = viewFuncionario.getEnderecoFunc();
                editaEndereco(funcionario, novoEndereco);
            }
            case 2 -> {
                String novoTelefone = viewFuncionario.getFoneFunc();
                editaTelefone(funcionario, novoTelefone);
            }
            case 3 -> {
                String novoEmail = viewFuncionario.getEmailFunc();
                editaEmail(funcionario, novoEmail);
            }
            case 4 ->{
                String novaSenha = viewFuncionario.getSenhaFunc();
                editaSenha(funcionario, novaSenha);
            }
            case 5 ->{
                String novoCargo = viewFuncionario.getCargoFunc();
                editaCargo(funcionario, novoCargo);
            }
            case 6 ->{
                double novoSalario = viewFuncionario.getSalarioFunc();
                editaSalario(funcionario, novoSalario);
            }
            
        }
        
        if (OficinaPOO.salvarDados(OficinaPOO.getInstancia())) {
            System.out.println("Alterações salvas com sucesso.");
        } else {
            System.out.println("Erro ao salvar alterações.");
        }
    }
    
    /**
     * Edita o endereço de um funcionário específico.
     * 
     * @param funcionario O objeto (@link Funcionario) cujos dads serão utilizados.
     * @param novoEndereco O novo  endereço a ser atribuído ao funcionário.
     */
    public void editaEndereco(Funcionario funcionario, String novoEndereco){
        funcionario.setEndereco(novoEndereco);
    }

    /**
     * Edita o telefone de um funcionário específico.
     *
     * @param funcionario O objeto (@link Funcionario) cujos dados serão atualizados.
     * @param novoTelefone O novo telefone a ser atribuído ao funcionário.
     */
    public void editaTelefone(Funcionario funcionario, String novoTelefone){
        funcionario.setTelefone(novoTelefone);
    }

     /**
     * Edita o e-mail de um funcionário específico.
     *
     * @param funcionario O objeto (@link Funcionario) cujos dados serão atualizados.
     * @param novoEmail O novo e-mail a ser atribuído ao funcionário.
     */
    public void editaEmail(Funcionario funcionario, String novoEmail){
        funcionario.setEmail(novoEmail);
    }
    
     /**
     * Edita a senha de acesso de um funcionário específico.
     *
     * @param funcionario O objeto {@link Funcionario} cujos dados serão atualizados.
     * @param novaSenha A nova senha a ser atribuída ao funcionário.
     */
    public void editaSenha(Funcionario funcionario, String novaSenha){
        funcionario.setSenha(novaSenha);
    }
    
    /**
     * Edita o cargo de um funcionário específico.
     *
     * @param funcionario O objeto {@link Funcionario} cujos dados serão atualizados.
     * @param novoCargo O novo cargo a ser atribuído ao funcionário.
     */
    public void editaCargo(Funcionario funcionario, String novoCargo){
        funcionario.setCargo(novoCargo);
    }
    
      /**
     * Edita o salário de um funcionário específico.
     *
     * @param funcionario O objeto {@link Funcionario} cujos dados serão atualizados.
     * @param novoSalario O novo salário a ser atribuído ao funcionário.
     */
    public void editaSalario(Funcionario funcionario, double novoSalario){
        funcionario.setSalario(novoSalario);
    }
    /**
     * Exibe o menu de opções do funcionário e executa a ação selecionada pelo usuário.
     * O menu permanece até que o usuário escolha a opção de sair.
     */
    public void executaMenuFuncionario(){
        int opcao = 0; 
        
        while(opcao != 5){
            opcao = viewFuncionario.mostraOpcoesFuncionario();
            
            switch (opcao){
                case 1 -> {
                    adicionaFuncionario();
                }
                case 2 -> {
                    editaFuncionario();
                }
                case 3 -> {
                    removeFuncionario();
                }
                case 4 -> {
                    mostrarFuncionario();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            
            }
        }
    }
}
