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

/**
 * Classe abstrata genérica que implementa operações básicas de persistência (CRUD) para objetos
 * do tipo {@code T}, com armazenamento em arquivos JSON utilizando a biblioteca GSON.
 * 
 * @param <T> O tipo da entidade que será manipulada. 
 */
public abstract class GenericDAO<T> {
    
    /**
     * Caminho do JSON onde os dados serão armazenados.
     */
    private final String caminhoDoJson;
    
    /**
     * Representação do tipo de lista genérica usada na desserialização do JSON.
     */
    private final Type tipoDaLista;
    
    /**
     * Lista interna de objetos gerenciada pelo DAO.
     */
    private List<T> listaObjeto;
    
    /**
     * Construtor do DAO genérico.
     * 
     * @param caminhoDoJson Caminho do arquivo JSON onde os dados serão salvos.
     * @param tipoDaLista O tipo da lista usada para desserializar os dados do GSON.
     */
    public GenericDAO(String caminhoDoJson, Type tipoDaLista){
        this.caminhoDoJson = caminhoDoJson;
        this.tipoDaLista = tipoDaLista;
        this.listaObjeto = new ArrayList<>();
        carregaDados();
    } 
    
    /**
     * Retorna a lista atual de objetos armazenados.
     * 
     * @return Lista de objetos do tipo {@code T}
     */
    public List<T> getLista(){
        return listaObjeto;
    };
    
    /** 
     * Deve ser implementado para retornar a chave única de cada objeto.
     * Essa chave é usada em buscas, remoções e adições.
     * 
     * @param objeto O objeto a ser avaliado.
     * @return A chave única do objeto.
     */
    protected abstract Comparable<?> getChave(T objeto);
    
    /**
     * Salva os dados atuais da lista no arquivo JSON.
     * 
     * @return {@code true} se os dados foram salvos com sucesso, {@code false} caso contrário.
     */
    public boolean salvarDados(){
        try (FileWriter writer = new FileWriter(caminhoDoJson)) {
            new Gson().toJson(listaObjeto, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            return false;
        }
    }
    
    /**
     * Carrega os dados do arquivo JSON para uma lista.
     * 
     * @return {@code true} se os dados foram carregados com sucesso, {@code false} caso contrário.
     */
    public boolean carregaDados(){
        try(FileReader reader = new FileReader(caminhoDoJson)){
            this.listaObjeto = new Gson().fromJson(reader, tipoDaLista);
            return true;
            
        } catch (Exception e){
            this.listaObjeto = new ArrayList<>();
            return false;
        }
        
    }
    
    /**
     * Adiciona um novo objeto a lista e persiste os dados no arquivo.
     * 
     * @param dados Os dados do objeto a ser adicionado. 
     */
    public void adicionaDados(T dados){
        getLista().add(dados);
        salvarDados();
    }
    
    
    /**
     * Remove um objeto da lista e atualiza o arquivo JSON.
     * 
     * @param dados Objeto a ser removido.
     * @return {@code true} se o objeto foi removido com sucesso. {@code false} caso contrário.
     */
    public boolean removeDados(T dados){
        boolean dadosRemovidos = getLista().remove(dados);
        return dadosRemovidos && salvarDados();
    }
    
    /**
     * Busca um objeto na lista com base em sua chave.
     * 
     * @param chave A chave identificadora do objeto.
     * @return O objeto correspondente, ou {@code null} se não for encontrado.
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
     * Mostra os dados de um objeto com base em sua chave forecida por um {@link Supplier}, e exibe 
     * a saída atráves de um {@link Consumer}.
     * 
     * @param pegaChave Função que fornece a chave de um objeto.
     * @param mostraObjeto Função que exibe o objeto encontrado.
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
     * Edita os dados de um objeto identificado por sua chave.
     * A lógica de edição é executada pelo consumidor fornecido.
     * 
     * @param pegaChave Função que fornece a chave de um objeto.
     * @param editaObjeto Função que modifica o objeto encontrado.
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
