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

/**
 * A classe OficinaPOO representa o ponto de entrada e o gerenciador central de dados da aplicação da oficina.
 * Implementa o padrão Singleton para garantir que exista apenas uma instância da oficina em toda a aplicação.
 * É responsável por armazenar as listas de clientes, funcionários, veículos e produtos, além de gerenciar o armazenamento
 * desses dados em um arquivo JSON.
 */
public class OficinaPOO {
    
    private ArrayList<Cliente> clientes;
    private ArrayList<Funcionario> funcionario;
    private ArrayList<Veiculo> veiculos;
    private ArrayList<Produto> produtos;
    
    /**
     * Construtor privado da classe (@code OficinaPOO).
     * Inicializa as listas de clientes, funcionários, veículos e produtos como (@link ArrayList) vazios.
     * Este construtor é privado para usar o padrão Singleton.
     */
    private OficinaPOO(){
        clientes = new ArrayList<>();
        funcionario = new ArrayList<>();
        veiculos = new ArrayList<>();
        produtos = new ArrayList<>();
    }
    
    
    private static OficinaPOO instanciaOficina = new OficinaPOO();
    
    /**
     * Retorna a única instância da classe (@code OficinaPOO) (padrão Singleton).
     *
     * @return A instância única de (@code OficinaPOO).
     */
    public static OficinaPOO getInstancia(){
        return instanciaOficina; 
    }
    
    
    private static final String caminhoJson = "data%soficina.json".formatted(File.separator);
    
    /**
     * Salva todos os dados da instância atual de (@code OficinaPOO) em um arquivo JSON.
     * Os dados incluem clientes, funcionários, veículos e produtos.
     *
     * @param oficina A instância de (@code OficinaPOO) contendo os dados a serem salvos.
     * @return (@code true) se os dados foram salvos com sucesso, (@code false) caso contrário.
     */
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
    
    /**
     * Carrega os dados persistidos de um arquivo JSON para a instância de (@code OficinaPOO).
     * Se o arquivo não existir ou houver um erro de leitura, as listas de dados permanecerão vazias.
     *
     * @return (@code true) se os dados foram carregados com sucesso, (@code false) caso contrário.
     */
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
    
     /**
     * Adiciona um novo cliente à lista de clientes da oficina.
     *
     * @param cliente O objeto (@link Cliente) a ser adicionado.
     */
    public void addCliente(Cliente cliente){
        clientes.add(cliente);
    }
    
    /**
     * Adiciona um novo funcionário à lista de funcionários da oficina.
     *
     * @param funcionario O objeto (@link Funcionario) a ser adicionado.
     */
    public void addFuncionario(Funcionario funcionario){
        this.funcionario.add(funcionario);
    }
    
    /**
     * Adiciona um novo veículo à lista de veículos da oficina.
     *
     * @param veiculo O objeto (@link Veiculo) a ser adicionado.
     */
    public void addVeiculo(Veiculo veiculo){
        veiculos.add(veiculo);
    }
    
    /**
     * Adiciona um novo produto (peça) à lista de produtos da oficina.
     *
     * @param produto O objeto (@link Produto) a ser adicionado.
     */
    public void addProduto(Produto produto){
        produtos.add(produto);
    }
    
    /**
     * Retorna a lista de todos os clientes registrados na oficina.
     *
     * @return Um (@link ArrayList) de objetos (@link Cliente).
     */
    public ArrayList<Cliente> getClientes(){
        return clientes;
    }
    
    /**
     * Retorna a lista de todos os veículos registrados na oficina.
     *
     * @return Um (@link ArrayList) de objetos (@link Veiculo).
     */
    public ArrayList<Veiculo> getVeiculo(){
        return veiculos;
    }
    
    /**
     * Retorna a lista de todos os funcionários registrados na oficina.
     *
     * @return Um (@link ArrayList) de objetos (@link Funcionario).
     */
    public ArrayList<Funcionario> getFuncionario(){
        return funcionario;
    }
    
     /**
     * Retorna a lista de todos os produtos (peças) disponíveis na oficina.
     *
     * @return Um (@link ArrayList) de objetos (@link Produto).
     */
    public ArrayList<Produto> getProdutos(){
        return produtos;
    }
    
    /**
     * Ponto de entrada principal da aplicação da oficina.
     * Configura a codificação de saída do console para UTF-8 e inicia o sistema principal através da classe (@link Sistema).
     *
     * @param args Argumentos da linha de comando (não utilizados nesta aplicação).
     */
    public static void main(String[] args) {
         // Configura a saída do console para UTF-8, garantindo a exibição correta de caracteres especiais
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));
        
        // Cria uma nova instância do controlador do sistema
        Sistema sistemaOficina = new Sistema();
        
        // Inicia o fluxo principal do sistema
        sistemaOficina.iniciaSistema();
    }
    
}
