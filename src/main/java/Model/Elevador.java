package Model;

public class Elevador {
    private String tipoElevador;
    private boolean estaOcupado;
    private double pesoMaximo;
    
    public Elevador(String tipoElevador, boolean estaOcupado, double pesoMaximo){
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
    
    
    
}
