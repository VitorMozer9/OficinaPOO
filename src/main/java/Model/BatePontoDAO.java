package Model;

import View.BatePontoView;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import java.util.ArrayList;

/**
 * Classe responsável por gerenciar os dados de batimento de ponto no sistema.
 * Estende a classe {@code GenericDAO<BatePonto>} e fornece operações para cadastrar, buscar, editar, remover
 * e exibir registros de batida de ponto.
 */
public class BatePontoDAO extends GenericDAO<BatePonto> {
    private static BatePontoDAO instancia;
    private BatePontoView viewBatePonto = new BatePontoView();

    /**
     * Construtor padrão da classe {@code BatePontoDAO}.
     * Inicializa o DAO com o caminho do arquivo de dados dos registros de ponto e o tipo da lista
     * utilizado pelo Gson.
     */
    private BatePontoDAO() {
        super("data/RegistroPontos.json", new TypeToken<List<BatePonto>>() {}.getType());
    }
    
    public static BatePontoDAO getInstancia(){
        if (instancia == null) {
            instancia = new BatePontoDAO();
        }
        return instancia;
    }

    /**
     * Obtém a chave identificadora de um registro de batida de ponto.
     * 
     * @param batePonto Objeto {@code BatePonto} a ser identificado.
     * @return ID do registro de ponto.
     */
    @Override
    protected Comparable<?> getChave(BatePonto batePonto) {
        return batePonto.getIdRegistro();
    }

    /**
     * Busca um registro de batida de ponto pelo ID.
     * 
     * @param id ID do registro a ser localizado.
     * @return Objeto {@code BatePonto} correspondente ao ID, ou {@code null} se não encontrado.
     */
    public BatePonto buscaRegistroPonto(int id) {
        return buscaPorChave(id);
    }

    /**
     * Gera um novo ID para registro de ponto, baseado no maior ID já registrado no sistema.
     * Isso garante que cada novo registro tenha um ID único.
     * 
     * @return O novo ID do registro, incrementado em relação ao maior ID atual.
     */
    public int geraIdRegistro() {
        int maiorID = 0;
        for (BatePonto cadaRegistro : getLista()) {
            if (cadaRegistro.getIdRegistro() > maiorID) {
                maiorID = cadaRegistro.getIdRegistro();
            }
        }
        return maiorID + 1;
    }

    /**
     * Registra uma nova batida de ponto no sistema.
     * Coleta os dados necessários através da interface de visualização.
     */
    public void registrarBatidaPonto() {
        int idFuncionario = viewBatePonto.getIdFuncionario();
        BatidaDePonto tipoBatida = viewBatePonto.getTipoBatida();

        BatePonto registroExistente = buscarRegistroIncompleto(idFuncionario);
        
        if (registroExistente != null && tipoBatida == BatidaDePonto.SAIDA) {
            registroExistente.registrarBatida(tipoBatida);
            salvarDados();
            System.out.println("Saída registrada com sucesso! Total de horas: " + 
                                               String.format("%.2f", registroExistente.getTotalHorasTrabalhadas()) + "h");
        } else if (tipoBatida == BatidaDePonto.ENTRADA) {
            int novoId = geraIdRegistro();
            BatePonto novoRegistro = new BatePonto(novoId, idFuncionario);
            novoRegistro.registrarBatida(tipoBatida);
            
            adicionaDados(novoRegistro);
            System.out.println("Entrada registrada com sucesso! ID do registro: " + novoId);
        } else {
            System.out.println("Não há registro de entrada para este funcionário hoje!");
        }
    }

    /**
     * Busca um registro incompleto (sem saída) para o funcionário.
     * 
     * @param idFuncionario ID do funcionário.
     * @return Registro incompleto encontrado ou null se não existir.
     */
    private BatePonto buscarRegistroIncompleto(int idFuncionario) {
        for (BatePonto registro : getLista()) {
            if (registro.getIdFuncionario() == idFuncionario && !registro.registroCompleto()) {
                return registro;
            }
        }
        return null;
    }

    /**
     * Mostra os dados de um registro de ponto com base no ID informado pela view.
     */
    public void mostrarRegistroPonto() {
        mostraDados(viewBatePonto::getIdRegistro, viewBatePonto::mostrarBatePonto);
    }

    /**
     * Remove um registro de ponto do sistema após confirmação do usuário.
     * Exibe mensagem de sucesso, falha ou cancelamento da operação.
     */
    public void removeRegistroPonto() {
        int id = viewBatePonto.getIdRegistro();
        BatePonto registro = buscaRegistroPonto(id);
        
        if (registro == null) {
            System.out.println("Registro não encontrado!");
            return;
        }
        
        viewBatePonto.mostrarBatePonto(registro);
        
        String opcaoConfirmacao = viewBatePonto.confirmaExclusaoRegistro();
        if (!opcaoConfirmacao.equalsIgnoreCase("S")) {
            System.out.println("Operação abortada!!");
            return;
        }
        
        if (removeDados(registro)) {
            System.out.println("Registro removido com sucesso!");
        } else {
            System.out.println("Falha ao remover registro! :(");
        }
    }

    /**
     * Edita os dados de um registro de ponto existente.
     * Permite alterar entrada, saída ou ID do funcionário com base na opção escolhida.
     */
    public void editaRegistroPonto() {
        editaDados(viewBatePonto::getIdRegistro, registro -> {
            viewBatePonto.mostrarBatePonto(registro);
            int opcao = viewBatePonto.editaRegistroPonto();
            switch (opcao) {
                case 1 -> registro.registrarBatida(BatidaDePonto.ENTRADA);
                case 2 -> registro.registrarBatida(BatidaDePonto.SAIDA);
                case 3 -> registro.setIdFuncionario(viewBatePonto.getIdFuncionario());
            }
        });
    }

    /**
     * Exibe um relatório de todos os registros de ponto de um funcionário específico.
     */
    public void exibirRelatorioFuncionario() {
        int idFuncionario = viewBatePonto.getIdFuncionario();
        List<BatePonto> registrosFuncionario = buscarRegistrosPorFuncionario(idFuncionario);
        
        if (registrosFuncionario.isEmpty()) {
            System.out.println("Nenhum registro encontrado para este funcionário!");
            return;
        }
        
        System.out.println("Funcionário ID: " + idFuncionario);
        System.out.println("Total de registros: " + registrosFuncionario.size());
        System.out.println();
        
        double totalHoras = 0.0;
        for (BatePonto registro : registrosFuncionario) {
            viewBatePonto.mostrarResumoRegistro(registro);
            totalHoras += registro.getTotalHorasTrabalhadas();
        }
        
        System.out.println();
        System.out.println("TOTAL DE HORAS TRABALHADAS: " + String.format("%.2f", totalHoras) + "h");
    }

    /**
     * Busca todos os registros de ponto de um funcionário específico.
     * 
     * @param idFuncionario ID do funcionário.
     * @return Lista de registros do funcionário.
     */
    public List<BatePonto> buscarRegistrosPorFuncionario(int idFuncionario) {
        List<BatePonto> registrosFuncionario = new ArrayList<>();
        for (BatePonto registro : getLista()) {
            if (registro.getIdFuncionario() == idFuncionario) {
                registrosFuncionario.add(registro);
            }
        }
        return registrosFuncionario;
    }

    /**
     * Exibe todos os registros de ponto do sistema.
     */
    public void listarTodosRegistros() {
        List<BatePonto> todosRegistros = getLista();
        
        if (todosRegistros.isEmpty()) {
            System.out.println("Nenhum registro de ponto encontrado!");
            return;
        }

        System.out.println("TODOS OS REGISTROS DE PONTO");
        System.out.println("Total de registros: " + todosRegistros.size());
        System.out.println();
        
        for (BatePonto registro : todosRegistros) {
            viewBatePonto.mostrarResumoRegistro(registro);
        }
        
    }
    
    /**
     * Sobrescreve o método toString para retornar informações do registro.
     * 
     * @return String com informações básicas do registro.
     */
    @Override
    public String toString() {
        return "BatePontoDAO | Total de pontos: " + getLista().size();
    }
}