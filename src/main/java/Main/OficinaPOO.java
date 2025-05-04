package Main;

import Model.Cliente;
import Model.Cpf;
import Model.Funcionario;
import Model.Manutencao;
import Model.OrdemDeServico;
import Model.Pecas;
import Model.Pessoa;
import Model.Veiculo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import controller.Sistema;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class OficinaPOO {
    
    private ArrayList<Cliente> clientes;
    private ArrayList<Funcionario> funcionario;
    private ArrayList<Veiculo> veiculos;
    private ArrayList<Pecas> pecas;
    
    private OficinaPOO(){
        clientes = new ArrayList<>();
        funcionario = new ArrayList<>();
        veiculos = new ArrayList<>();
        pecas = new ArrayList<>();
    }
    
    private static OficinaPOO instancia = new OficinaPOO();
    
    public static OficinaPOO getInstancia(){
        return instancia; //Singleton
    }
    
    
     private static final String caminhoJson = "data%soficina.json".formatted(File.separator);

    public static boolean salvarDados(OficinaPOO oficina) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(caminhoJson)) {
            gson.toJson(instancia, writer);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean carregaDados() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(caminhoJson)) {
            TypeToken tipo = new TypeToken<OficinaPOO>() {
            };           
            instancia = (OficinaPOO)gson.fromJson(reader,tipo);            
        } catch (IOException e) {
            return false;
        }
        return true;
    }
    
    public void addCliente(Cliente cliente){
        clientes.add(cliente);
    }
    
    public void addFuncionario(Funcionario funcionario){
        this.funcionario.add(funcionario);
    }
    
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Sistema sistemaOficina = new Sistema();
        
        sistemaOficina.iniciaSistema();
        
    
        
//          Gson teste = new Gson();
//          ArrayList<Integer> alan = teste.fromJson("[123,123,345]", new TypeToken<ArrayList<Integer>>(){} );
//          
//          ArrayList<Integer> alan2 = new ArrayList<>(List.of(alan));
//          
//          
//          
//          
//          System.out.println(alan);
                            
//        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
//        String cpfAvulso = "906.884.200-52";
//        Cpf cpfPessoa = new Cpf(cpfAvulso);
//        
//        Veiculo carro = new Veiculo("Modelo", "BRA2E19", "Manutenção", 2005,1234.67);
//        
//        Manutencao OS = new Manutencao("Quase pronto", "carburador com problema","Troca de carburador por injeção");
//         
//        Cliente cliente = new Cliente("Vitor", "Rua areão", "389765432","vitão@gmail.com",cpfPessoa);
//        
//        Funcionario func = new Funcionario("Bauru", "Moradia", "3896754321", "Bauru@gmail.com",cpfPessoa, "Bauruzão","123","mecanico",1200.32);
//        
//        OrdemDeServico OS2 = new OrdemDeServico(cliente, func, OS);
//        
//        System.out.println("Cpf sem anonimização " + cpfPessoa.getNumeroCpf());
//        System.out.println("Cpf anonimizado " + cpfPessoa.toString());
//        
//        System.out.println("Placa do veiculo: " + carro.getPlacaVeiculo());
//        System.out.println("A placa é valida? " + Veiculo.validaPlaca(carro.getPlacaVeiculo()));
//        
//        System.out.println(OS2.toString());
//        ;
        
        
        //System.out.println("Retorno :" + Cpf.validaCPF("906.884.200-52"));
    }
    
}
