package Main;

import Model.Cliente;
import Model.Cpf;
import Model.Funcionario;
import Model.Manutencao;
import Model.OrdemDeServico;
import Model.Pessoa;
import Model.Veiculo;

public class OficinaPOO {

    public static void main(String[] args) {
        String cpfAvulso = "906.884.200-52";
        Cpf cpfPessoa = new Cpf(cpfAvulso);
        
        Veiculo carro = new Veiculo("Modelo", "BRA2E19", "Manutenção", 2005,1234.67);
        
        Manutencao OS = new Manutencao("Quase pronto", "carburador com problema","Troca de carburador por injeção");
        
        Cliente cliente = new Cliente("Vitor", "Rua areão", "389765432","vitão@gmail.com",cpfPessoa);
        
        Funcionario func = new Funcionario("Bauru", "Moradia", "3896754321", "Bauru@gmail.com",cpfPessoa, "Bauruzão","123","mecanico",1200.32);
        
        OrdemDeServico OS2 = new OrdemDeServico(cliente, func, OS);
        
        System.out.println("Cpf sem anonimização " + cpfPessoa.getNumeroCpf());
        System.out.println("Cpf anonimizado " + cpfPessoa.toString());
        
        System.out.println("Placa do veiculo: " + carro.getPlacaVeiculo());
        System.out.println("A placa é valida? " + Veiculo.validaPlaca(carro.getPlacaVeiculo()));
        
        System.out.println(OS2.toString());
        
        //System.out.println("Retorno :" + Cpf.validaCPF("906.884.200-52"));
    }
    
}
