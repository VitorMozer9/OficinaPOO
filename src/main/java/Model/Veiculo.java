package Model;

public class Veiculo {
    
    private String modeloVeiculo;
    private String placaVeiculo;
    private String statusVeiculo;
    private int anoDeFabricacao;
    
    public Veiculo(String modeloVeiculo, String placaVeiculo, String statusVeiculo, int anoDeFabricacao){
        this.anoDeFabricacao = anoDeFabricacao;
        this.modeloVeiculo = modeloVeiculo;
        this.placaVeiculo = placaVeiculo;
        this.anoDeFabricacao = anoDeFabricacao;
    }
    
    public String getModeloVeiculo(){
        return modeloVeiculo;
    }
    
    public void setModeloVeiculo(String modeloVeiculo){
        this.modeloVeiculo = modeloVeiculo;
    }
    
    public String getPlacaVeiculo(){
        return placaVeiculo;
    }
    
    public void setPlacaVeiculo(String placaVeiculo){
        this.placaVeiculo = placaVeiculo;
    }
    
    public String getStatusVeiculo(){
        return statusVeiculo;
    }
    
    public void setStatstusVeiculo(String statusVeiculo){
        this.statusVeiculo = statusVeiculo;
    }
    
    public int getAnoDeFabricacao(){
        return anoDeFabricacao;
    }
    
    public void setAnoDeFabricacao(int anoDeFabricacao){
        this.anoDeFabricacao = anoDeFabricacao;
    }
    
    public static boolean validaPlaca(String placaVeiculo){
        String placa = placaVeiculo.replaceAll("-", "").trim().toUpperCase();
        
        if (placaVeiculo.isEmpty()) {
            return false;
        }
    
        boolean padraoMercosul = placa.matches("^[A-Z]{3}[0-9][A-Z][0-9]{2}$");
        boolean padraoAntigo = placa.matches("^[A-Z]{3}[0-9]{4}$");
        
        return padraoMercosul || padraoAntigo;
    
    }
    
}
