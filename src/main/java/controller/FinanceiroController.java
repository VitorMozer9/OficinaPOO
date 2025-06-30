package controller;

import Model.FinanceiroDAO;
import View.FinanceiroView;

public class FinanceiroController {
    private FinanceiroView viewFinanceiro = new FinanceiroView();
    private FinanceiroDAO financeiroDao = FinanceiroDAO.getInstancia();
     
    /**
     * Exibe o menu de opções para as vendas e executa a ação solicitada pelo usuário.
     * O menu permanece ativo até que o usuário selecione a opção de sair.
     */
    public void executaMenuFinanceiro(){
        int opcao = 0; 
        
        while(opcao != 8){
            opcao = viewFinanceiro.mostraOpcoesFinanceiro();
            
            if (opcao == 8) {
                System.out.println("Saindo do menu financeiro...");
                break; 
            }
            
            switch (opcao){
                case 1 -> {
                    financeiroDao.adicionarConta();
                }
                case 2 -> {
                    financeiroDao.removerConta();
                }
                case 3 -> {
                    financeiroDao.editarConta();
                }
                case 4 -> {
                    financeiroDao.mostrarDadosConta();
                }
                case 5 -> {
                    financeiroDao.gerarRelatorioVendas();
                }
                case 6 -> {
                    financeiroDao.gerarBalancoMensal();
                }
                case 7 -> {
                    financeiroDao.listarContas();
                }
                default -> {
                    System.out.println("Opção inválida! Tente novamente.");
                }
            }
        }
    }
    
    @Override
    public String toString(){
        return "FinanceiroController: gerencia o fluxo de contas financeiras e relatórios de vendas.";
    }
    
}
