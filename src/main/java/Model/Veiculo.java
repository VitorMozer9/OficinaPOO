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
        
//    public satatic boolean ValidaPlaca() {
//        String placa = placaVeiculo.trim().toUpperCase();
//        return placa.matches("^[A-Z]{3}[0-9][A-Z][0-9]{2}$");
//    }
        
    }
    
}
