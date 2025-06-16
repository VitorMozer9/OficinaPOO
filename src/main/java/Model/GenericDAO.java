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

public abstract class GenericDAO<T> {
    
    private final String caminhoDoJson;
    private final Type tipoDaLista;
    private List<T> listaObjeto;
    
    public GenericDAO(String caminhoDoJson, Type tipoDaLista){
        this.caminhoDoJson = caminhoDoJson;
        this.tipoDaLista = tipoDaLista;
        this.listaObjeto = new ArrayList<>();
        carregaDados();
    }
    
    
    
    protected List<T> getLista(){
        return listaObjeto;
    };
    
    protected abstract Comparable<?> getChave(T objeto);
    
    public boolean salvarDados(){
        try (FileWriter writer = new FileWriter(caminhoDoJson)) {
            new Gson().toJson(listaObjeto, writer);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            return false;
        }
    }
    
    public boolean carregaDados(){
        try(FileReader reader = new FileReader(caminhoDoJson)){
            this.listaObjeto = new Gson().fromJson(reader, tipoDaLista);
            return true;
            
        } catch (Exception e){
            this.listaObjeto = new ArrayList<>();
            return false;
        }
        
    }
    
    public void adicionaDados(T dados){
        getLista().add(dados);
        salvarDados();
    }
    
    public boolean removeDados(T dados){
        boolean dadosRemovidos = getLista().remove(dados);
        return dadosRemovidos && salvarDados();
    }
    
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
    
    public void mostraDados(Supplier<Comparable<?>> pegaChave, Consumer<T> mostraObjeto){       
        Comparable<?> chave = pegaChave.get();
        T objeto = buscaPorChave(chave);
        
        if (objeto == null){
            System.out.println("Registro não foi encontrado! :(");
            return;
        }
        
        mostraObjeto.accept(objeto);
    }
    
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
