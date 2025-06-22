package Model;

/**
 * Representação dos elevadores da oficina, divididos por tipos(balanceamento, normal), apresentando suas capacidades máximas que conseguem levantar, seu número de identificação
 * e também o seu estado atual, se está sendo usado ou não.
 * 
 */
public class Elevador {
    private int idElevador;
    private String tipoElevador;
    private boolean estaOcupado;
    private double pesoMaximo;
    
    /**
     * Construtor da classe(@code Elevador).
     * Inicia um novo elevador com seus atributos (código, tipo, disponibilidade, peso máximo).
     * 
     * @param idElevador    Número de identificação do elevador.
     * @param tipoElevador  Tipo do elevador.
     * @param estaOcupado   Situação atual do elevador 
     * @param pesoMaximo    Peso maxímo que o elevador consegue carregar.
     */
    public Elevador(int idElevador, String tipoElevador, boolean estaOcupado, double pesoMaximo){
        this.idElevador = idElevador;
        this.estaOcupado = estaOcupado;
        this.pesoMaximo = pesoMaximo;
        this.tipoElevador = tipoElevador;
    }
    
    public int getIdElevador(){
        return idElevador;
    }

    /**
     * Obtém o tipo do elevador.
     * 
     * @return Tipo do elevador.
     */
    public String getTipoElevador() {
        return tipoElevador;
    }
    
    /**
     * Define o tipo do elevador.
     * 
     * @param tipoElevador Novo tipo de elevador.
     */
    public void setTipoElevador(String tipoElevador) {
        this.tipoElevador = tipoElevador;
    }

    /**
     * Obtém o estado atual do elevador.
     * 
     * @return Ocupado ou Disponível.
     */
    public boolean getEstaOcupado() {
        return estaOcupado;
    }
    
    /**
     * Define o estado do eleador.
     * 
     * @param estaOcupado Novo estado.
     */
    public void setEstaOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
    }

    /**
     * Informa o peso máximo que o elevador consegue carregar.
     * 
     * @return Peso máximo.
     */
    public double getPesoMaximo() {
        return pesoMaximo;
    }

    /** 
     * Define o peso máximo que o  elevador consegue carregar.
     * 
     * @param pesoMaximo Novo peso máximo.
     */
    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }
    
    /**
     * Sobrescreve o método toString para mostrar ao usuário o estado em que o elevador selecionado se encontra.
     * 
     * @return Mensagem com o estado atual e tipo do elevador solicitado.
     */
    @Override
    public String toString() {
        if (estaOcupado) {
            return "O elevador " + idElevador + " do tipo " + tipoElevador + " esta ocupado."; 
        } else {
           return "O elevador " + idElevador + " do tipo " + tipoElevador + " esta livre.";  
        }
    }
    
}
