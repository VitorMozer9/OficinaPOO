package Model;

public class Elevador {
    private int idElevador;
    private String tipoElevador;
    private boolean estaOcupado;
    private double pesoMaximo;
    
    public Elevador(int idElevador, String tipoElevador, boolean estaOcupado, double pesoMaximo){
        this.idElevador = idElevador;
        this.estaOcupado = estaOcupado;
        this.pesoMaximo = pesoMaximo;
        this.tipoElevador = tipoElevador;
    }

    public String getTipoElevador() {
        return tipoElevador;
    }
    
    public void setTipoElevador(String tipoElevador) {
        this.tipoElevador = tipoElevador;
    }

    public boolean getEstaOcupado() {
        return estaOcupado;
    }
    
    public void setEstaOcupado(boolean estaOcupado) {
        this.estaOcupado = estaOcupado;
    }

    public double getPesoMaximo() {
        return pesoMaximo;
    }

    public void setPesoMaximo(double pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }
    
    @Override
    public String toString() {
        if (estaOcupado = true) {
            return "O elevador " + idElevador + " do tipo " + tipoElevador + " esta ocupado."; 
        } else {
           return "O elevador " + idElevador + " do tipo " + tipoElevador + " esta livre.";  
        }
    }
    
}
