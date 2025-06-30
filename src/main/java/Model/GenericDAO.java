package Model;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import Main.OficinaPOO;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import com.google.gson.GsonBuilder;
import utilitarios.AdaptadorLocalDateTime;
import java.time.LocalDateTime;

/**
 * Classe abstrata que implementa o padrão DAO (Data Acess Object) de forma genérica.
 * Permite operações básicas de persistência (adicionar, remover, buscar, editar e carregar) utilizando arquivos
 * JSON como armazenamento.
 * 
 * @param <T> Tipo da entidade a ser manipulada (ex: Cliente, Funcionario, Agendamento, etc.) 
 */
public abstract class GenericDAO<T> {
    
    /** Caminho do arquivo JSON que armazana os dados da entidade. */
    private final String caminhoDoJson;
    
    /** Tipo da lista usado para desserialização com Gson. */
    private final Type tipoDaLista;
    
    /** Lista em memória contendo os objetos da entidade. */
    private List<T> listaObjeto;
    
    private final Gson gson;
    
    /**
     * Contrutor da classe GenericDAO.
     * Inicializa o DAO com caminho do JSON e o tipo da lista usada pelo Gson.
     * Carrega os dados do arquivo no momento da criação.
     * 
     * @param caminhoDoJson Caminho do arquivo JSON.
     * @param tipoDaLista Tipo da lista (utilizada pelo Gson para desserialização).
     */
    public GenericDAO(String caminhoDoJson, Type tipoDaLista){
        this.caminhoDoJson = caminhoDoJson;
        this.tipoDaLista = tipoDaLista;
        this.listaObjeto = new ArrayList<>();
        this.gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new AdaptadorLocalDateTime()).setPrettyPrinting().create();
        carregaDados();
    } 
    
    /**
     * Obtém a lista de objetos carregados em memória.
     * 
     * @return Lista com os objetos da entidade. 
     */
    public List<T> getLista(){
        return listaObjeto;
    };
    
    /**
     * Define a chave identificadora de um objeto.
     * Deve ser implementado pelas subclasses para definir como o objetos são identificados.
     * 
     * @param objeto Objeto do tipo T.
     * @return Chave única identificadora (ex: ID)
     */
    protected abstract Comparable<?> getChave(T objeto);
    
    /**
     * Salva os dados atuais da lista no arquivo JSON
     * 
     * @return {@code true} se os dados foram salvos com sucesso, {@code false} caso contrário. 
     */
    public boolean salvarDados(){
        try (FileWriter writer = new FileWriter(caminhoDoJson)) {
            gson.toJson(listaObjeto, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Carrega os dados do arquivo JSON para a lista em memória.
     * Se o arquivo não existir ou estiver corrompido, a lista será inicializada vazia.
     * 
     * @return {@code true} se os dados foram carregados com succeso, {@code false} caso contrário. 
     */
    public boolean carregaDados(){
        try(FileReader reader = new FileReader(caminhoDoJson)){
            this.listaObjeto = gson.fromJson(reader, tipoDaLista);
            return true;
            
        } catch (Exception e){
            this.listaObjeto = new ArrayList<>();
            return false;
        }
        
    }
    
    /**
     * Adiciona um novo objeto a lista e salva os dados.
     * 
     * @param dados Objeto do tipo T a ser adicionado. 
     */
    public void adicionaDados(T dados){
        getLista().add(dados);
        salvarDados();
    }
    
    /**
     * Remove um objeto da lista e salva os dados.
     * 
     * @param dados Objeto do tipo T a ser removido.
     * @return {@code true} se os objeto foi removido com sucesso e os dados salvos, {@code false} caso contrário.  
     */
    public boolean removeDados(T dados){
        boolean dadosRemovidos = getLista().remove(dados);
        return dadosRemovidos && salvarDados();
    }
    
    /**
     * Busca um objeto na lista com base em sua chave.
     * 
     * @param chave Chave identificadora do objeto.
     * @return Objeto encontrado ou {@code null} se não for encontrado.
     */
    public T buscaPorChave (Comparable<?> chave) {   
        try{
            for(T objetoEntidade : getLista()){
                if (getChave(objetoEntidade).equals(chave)){
                    return objetoEntidade; 
                }
            }   
        }
        catch(Exception e){
            System.out.println("Falha ao buscar dados!");
            return null;
        }
        return null;
    }
    
    /**
     * Mostra os dados de um objeto com base na chave fornecida.
     * 
     * @param pegaChave Função que retorna a chave a ser buscada.
     * @param mostraObjeto Função que exibe o objeto a ser encontrado.
     */
    public void mostraDados(Supplier<Comparable<?>> pegaChave, Consumer<T> mostraObjeto){       
        Comparable<?> chave = pegaChave.get();
        T objeto = buscaPorChave(chave);
        
        if (objeto == null){
            System.out.println("Registro não foi encontrado! :(");
            return;
        }
        
        mostraObjeto.accept(objeto);
    }
    
    /**
     * Edita os dados de um objeto encontrado com base em sua chave.
     * 
     * @param pegaChave Função que fornece a chave do onjeto.
     * @param editaObjeto Função que realiza a edição do objeto.
     */
    public void editaDados(Supplier<Comparable<?>> pegaChave, Consumer<T> editaObjeto){
        Comparable<?> chave = pegaChave.get();
        T objeto = buscaPorChave(chave);
        
        if (objeto == null){
            System.out.println("Registro não foi encontrado! :(");
            return;
        }
        
        editaObjeto.accept(objeto);
        
        if (salvarDados()){
            System.out.println("Dados alterados com sucesso!");
        }else {
            System.out.println("Não foi possível editar os dados! :(");
        }
    }
    
}
