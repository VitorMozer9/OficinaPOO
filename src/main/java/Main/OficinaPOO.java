package Main;

import Model.Cpf;
import Model.Pessoa;

public class OficinaPOO {

    public static void main(String[] args) {
        String cpfAvulso = "906.884.200-52";
        Cpf cpfPessoa = new Cpf(cpfAvulso);
        
        System.out.println("Cpf sem anonimização " + cpfPessoa.getNumeroCpf());
        System.out.println("Cpf anonimizado " + cpfPessoa.toString());
        
        //System.out.println("Retorno :" + Cpf.validaCPF("906.884.200-52"));
    }
    
}
