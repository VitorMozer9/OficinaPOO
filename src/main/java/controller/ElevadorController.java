package controller;

import Model.Elevador;
import java.util.Calendar;
import java.util.List;

/**
 * Classe responsável pelo controle dos elevadores da oficina.
 * Gerencia a disponibilidade, ocupação e visualização do status dos elevadores.
 * Essa classe simula um sistema com 3 elevadores disponíveis para os serviços da oficina.
 */
public class ElevadorController {
    
    /**
     * Lista fixa de elevadores disponíveis na oficina.
     * Cada elevador possui um ID, tipo (ex: NORMAL, ALINHAMENTO), status de ocupação e capacidade máxima. 
     */
    private static Elevador[] getListaElevadores = new Elevador[]{
        new Elevador(1, "ALINHAMENTO", false, 2000),
        new Elevador(2, "NORMAL", false, 3000),
        new Elevador(3, "NORMAL", false, 3000)
    };
    
    /**
     * Verifica a disponibilidade de um elevador para o horário informado.
     * Retorna o primeiro elevador não ocupado.
     * 
     * @param horario Horário solicitado para o uso do elevador (ainda não utilizado internamente.)
     * @return Um elevador disponível, ou {@code null} se todos estiverem ocupados.
     */
    public static Elevador verificaDisponibilidade(Calendar horario){
        for (Elevador cadaElevador : getListaElevadores) {
            if (!cadaElevador.getEstaOcupado()){
                return cadaElevador;
            }
        }
        return null;
    }
    
    /**
     * Marca um elevador como ocupado, de acordo com o ID fornecido.
     * 
     * @param idElevador ID do elevador a ser ocupado. 
     */
    public static void ocupaElevador(int idElevador){
        for (Elevador cadaElevador : getListaElevadores) {
            if (cadaElevador.getIdElevador() == idElevador){
                cadaElevador.setEstaOcupado(true);
                return;
            }
        }
    }
    
    /**
     * Marca um elevador como desocupado, de acordo com o ID fornecido.
     * 
     * @param idElevador ID do elevador a ser liberado.
     */
    public static void desocupaElevador(int idElevador){
        for (Elevador cadaElevador : getListaElevadores) {
            if (cadaElevador.getIdElevador() == idElevador){
                cadaElevador.setEstaOcupado(false);
                return;
            }
        }
    }
    
    /**
     * Exibe no console o status atual de todos os elevadores da oficina.
     * Inclui ID, tipo, capacidade e se está ocupado ou não.
     */
    public static void mostraStatusElevador() {
        for (Elevador elevador : getListaElevadores){
            System.out.println(elevador.toString());
        }
    }
    
}
