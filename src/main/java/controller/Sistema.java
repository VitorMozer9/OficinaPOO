package controller;

import Main.OficinaPOO;
import View.TelaInicial;

public class Sistema {
    
    private TelaInicial telaInicial = new TelaInicial();
    private ClienteController clienteController = new ClienteController();
    
    public void iniciaSistema() {
        boolean rodando = true;
        OficinaPOO.carregaDados();
        
        while (rodando) {
            int opcaoDisponivel = telaInicial.mostraOpcoesDisponiveis();
            
            switch (opcaoDisponivel) {
                case 1 -> {
                    clienteController.executaMenuCliente();
                }
                
//                case 2 -> {
//                    funcionarioController.executaMenuFuncionario();
//                }
//                
//                case 3 -> {
//                    veiculoController.executaMenuVeiculo();
//                }
//                
//                case 4 -> {
//                    pecasController.executaMenuPecas();
//                }
                
//                case 5 -> {
//                    financeiroController.executaMenuFinanceiro();
//                }
                
                case 6 -> {
                    System.out.println("Encerrando sistema...");
                    OficinaPOO.salvarDados(OficinaPOO.getInstancia());
                    rodando = false;
                }
                
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
}
