package Model;

import Main.OficinaPOO;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

public abstract class GenericDAO<T> { //<T> seria um parametro de tipo, ele indica uma classe generica ou metodo generioco, pode assumir qualquer valor
    
    protected abstract List<T> getLista();
    
    protected abstract Comparable<?> getChave(T objeto);
    
    protected boolean salvarDados(){
        return OficinaPOO.salvarDados(OficinaPOO.getInstancia());
    }
    
    public void adicionaDados(T dados){
        getLista().add(dados);
        salvarDados();
    }
    
    public boolean removeDados(T dados){
        boolean dadosRemovidos = getLista().remove(dados);
        return dadosRemovidos && salvarDados();
    }
    
    public T buscaPorChave (Comparable<?> chave) {   //<String>  ||| <?> wildcard aceita qualquer tipo
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
