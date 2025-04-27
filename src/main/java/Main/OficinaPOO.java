package Main;

import Model.Cpf;
import Model.Pessoa;
import Model.Veiculo;

public class OficinaPOO {

    public static void main(String[] args) {
        String cpfAvulso = "906.884.200-52";
        Cpf cpfPessoa = new Cpf(cpfAvulso);
        
        Veiculo carro = new Veiculo("Modelo", "HEE-7480", "Manutenção", 2005);
        
        System.out.println("Cpf sem anonimização " + cpfPessoa.getNumeroCpf());
        System.out.println("Cpf anonimizado " + cpfPessoa.toString());
        
        System.out.println("Placa do veiculo: " + carro.getPlacaVeiculo());
        System.out.println("A placa é valida? " + Veiculo.validaPlaca(carro.getPlacaVeiculo()));
        
        //System.out.println("Retorno :" + Cpf.validaCPF("906.884.200-52"));
    }
    
}
