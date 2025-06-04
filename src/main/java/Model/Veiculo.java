package Model;

/**
 * Representação dos veículos da oficina, apresentando: modelo  do veículo, placa do veículo, status do veículo e ano de sua fabricação.
 */

public class Veiculo {
    
    private int idCliente;
    private String modeloVeiculo;
    private String placaVeiculo;
    private String statusVeiculo;
    private int anoDeFabricacao;
    private double peso; 
    
/**
 * Construtor da classe Veúculo (@code Veículo).
 * Inicializa um novo veículo com suas informações básicas.  
 * 
 * @param modeloVeiculo
 * @param placaVeiculo
 * @param statusVeiculo
 * @param anoDeFabricacao 
 */
    
    public Veiculo(int idCliente,String modeloVeiculo, String placaVeiculo, String statusVeiculo, int anoDeFabricacao, double peso) {
        this.idCliente = idCliente; 
        this.anoDeFabricacao = anoDeFabricacao;
        this.modeloVeiculo = modeloVeiculo;
        this.placaVeiculo = placaVeiculo;
        this.anoDeFabricacao = anoDeFabricacao;
        this.peso = peso;
    }
    
    public int getIdCliente(){
        return idCliente;
    }
    
    public void setIdCliente(int idCliente){
        this.idCliente = idCliente;
    }
    
    /**
     * Pega o modelo do veículo
     * 
     * @return modelo do Veiculo
     */
    public String getModeloVeiculo(){
        return modeloVeiculo;
    }
    
    /**
     * Determina o modelo do Veículo
     * 
     * @param modeloVeiculo novo modelo de Veículo
     */
    
    public void setModeloVeiculo(String modeloVeiculo){
        this.modeloVeiculo = modeloVeiculo;
    }
    
    /**
     * Pega a placa do Veículo
     * 
     * @return placa do Veículo
     */
    public String getPlacaVeiculo(){
        return placaVeiculo;
    }
    
    /**
     * Determina a placa do Veículo
     * 
     * @param placaVeiculo nova placa de Veículo
     */
    public void setPlacaVeiculo(String placaVeiculo){
        this.placaVeiculo = placaVeiculo;
    }
    
    /**
     * Pega o status do Veículo
     * 
     * @return status do Veículo
     */
    public String getStatusVeiculo(){
        return statusVeiculo;
    }
    
    /** 
     * Determina o status do Veículo
     * 
     * @param statusVeiculo novo status do Veículo
     */
    public void setStatstusVeiculo(String statusVeiculo){
        this.statusVeiculo = statusVeiculo;
    }
    
    /**
     * Pega o ano de fabricação do Veículo
     * 
     * @return ano de fabricação do Veículo
     */
    public int getAnoDeFabricacao(){
        return anoDeFabricacao;
    }
    
    /**
     * Determina o ano de fabricação do Veículo
     * 
     * @param anoDeFabricacao  novo ano de fabricação do Veículo
     */
    public void setAnoDeFabricacao(int anoDeFabricacao){
        this.anoDeFabricacao = anoDeFabricacao;
    }
    
    /**
     * Pega o peso do Veículo
     * 
     * @return peso do Veículo
     */
    public double getPeso(){
        return peso;
    }
    
    /**
     * Determina o peso do Veículo
     * 
     * @param peso novo ano de fabricação do Veículo
     */
    public void setPeso(double peso){
        this.peso = peso;
    }
    
    /**
     * Valida a placa do Veículo 
     * Remove os caracteres especias da placa ("-"," ")
     * Verifica se não foi preenchido o número da placa 
     * Verifica se a placa do Veículo está no padrão antigo(três letras seguidas de 4 números aleatorios), ou no do Mercosul(três letras seguidas de um número, uma letra e mais dois números aleatorios) 
     *
     * @param placaVeiculo código para validação da placa do Veículo
     * @return false se não foi inserido o número da placa e o padrão da placa de acordo com o número inserido sendo paddraoMercosul e padraoAntigo
     */
    public static boolean validaPlaca(String placaVeiculo){
        String placa = placaVeiculo.replaceAll("-", "").trim().toUpperCase();
        
        if (placaVeiculo.isEmpty()) {
            return false;
        }
    
        boolean padraoMercosul = placa.matches("^[A-Z]{3}[0-9][A-Z][0-9]{2}$");
        boolean padraoAntigo = placa.matches("^[A-Z]{3}[0-9]{4}$");
        
        return padraoMercosul || padraoAntigo;
    
    }
    
    /**
     * Sobrescreve o método toString para retornar o modelo do veículo.
     * 
     * @return 
     */
    @Override
    public String toString(){
        return modeloVeiculo;
    }
    
}
