package controller;

import Model.Elevador;
import java.util.Calendar;
import java.util.List;

public class ElevadorController {
    
    private static List<Elevador> getListaElevadores = List.of(
        new Elevador(1, "ALINHAMENTO", false, 2000),
        new Elevador(2, "NORMAL", false, 3000),
        new Elevador(3, "NORMAL", false, 3000)
    );
    
    public static Elevador verificaDisponibilidade(Calendar horario){
        for (Elevador cadaElevador : getListaElevadores) {
            if (!cadaElevador.getEstaOcupado()){
                return cadaElevador;
            }
        }
        return null;
    }
    
    public static void ocupaElevador(int idElevador){
        for (Elevador cadaElevador : getListaElevadores) {
            if (cadaElevador.getIdElevador() == idElevador){
                cadaElevador.setEstaOcupado(true);
                return;
            }
        }
    }
    
    public static void desocupaElevador(int idElevador){
        for (Elevador cadaElevador : getListaElevadores) {
            if (cadaElevador.getIdElevador() == idElevador){
                cadaElevador.setEstaOcupado(false);
                return;
            }
        }
    }
    
    public static void mostraStatusElevador() {
        for (Elevador elevador : getListaElevadores){
            System.out.println(elevador.toString());
        }
    }
    
}
