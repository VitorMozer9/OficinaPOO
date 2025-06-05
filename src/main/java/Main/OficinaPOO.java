package Main;

import Model.Cliente;
import Model.Cpf;
import Model.Funcionario;
import Model.Manutencao;
import Model.OrdemDeServico;
import Model.Produto;
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
    private ArrayList<Produto> produtos;
    
    private OficinaPOO(){
        clientes = new ArrayList<>();
        funcionario = new ArrayList<>();
        veiculos = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    
    private static OficinaPOO instanciaOficina = new OficinaPOO();
    
    public static OficinaPOO getInstancia(){
        return instanciaOficina; 
    }
    
    
    private static final String caminhoJson = "data%soficina.json".formatted(File.separator);

    public static boolean salvarDados(OficinaPOO oficina) {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(caminhoJson)) {
            gson.toJson(instanciaOficina, writer);
        } 
        catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            return false;
        }

        return true;
    }

    public static boolean carregaDados() {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(caminhoJson)) {
            TypeToken tipo = new TypeToken<OficinaPOO>() {};           
            instanciaOficina = (OficinaPOO)gson.fromJson(reader,tipo);            
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
    
    public void addVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }
    
    public void addProduto(Produto produto){
        produtos.add(produto);
    }
    
    public ArrayList<Cliente> getClientes(){
        return clientes;
    }
    
    public ArrayList<Veiculo> getVeiculo(){
        return veiculos;
    }
    
    public ArrayList<Funcionario> getFuncionario(){
        return funcionario;
    }
    
    public ArrayList<Produto> getProdutos(){
        return produtos;
    }
    
    public static void main(String[] args) {
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        Sistema sistemaOficina = new Sistema();
        
        sistemaOficina.iniciaSistema();
    }
    
}
