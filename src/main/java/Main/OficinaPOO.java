package Main;

import Model.Cliente;
import Model.ClienteDAO;
import ModelComparator.ComparatorClientID;
import ModelComparator.ComparatorClientNome;
import controller.Sistema;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * A classe OficinaPOO representa o ponto de entrada e o gerenciador central de
 * dados da aplicação da oficina. Implementa o padrão Singleton para garantir
 * que exista apenas uma instância da oficina em toda a aplicação. É responsável
 * por armazenar as listas de clientes, funcionários, veículos e produtos, além
 * de gerenciar o armazenamento desses dados em um arquivo JSON.
 */
public class OficinaPOO {

    /**
     * Método find para clientes utilizando Iterator e Comparator. Percorre a
     * lista de clientes usando Iterator e compara cada elemento com o cliente
     * alvo usando o Comparator fornecido.
     *
     * @param clientes Lista de clientes para buscar
     * @param clienteAlvo Cliente que está sendo procurado
     * @param comparator Comparator para fazer a comparação entre clientes
     * @return Cliente encontrado ou null se não encontrado
     */
    public static Cliente findCliente(List<Cliente> clientes, Cliente clienteAlvo, Comparator<Cliente> comparator) {
        Iterator<Cliente> iterator = clientes.iterator();

        while (iterator.hasNext()) {
            Cliente clienteAtual = iterator.next();
            if (comparator.compare(clienteAtual, clienteAlvo) == 0) {
                return clienteAtual;
            }
        }
        return null;

    }

    /**
     * Ponto de entrada principal da aplicação da oficina. Configura a
     * codificação de saída do console para UTF-8 e inicia o sistema principal
     * através da classe (@link Sistema).
     *
     * @param args Argumentos da linha de comando (não utilizados nesta
     * aplicação).
     */
    public static void main(String[] args) {
        // Configura a saída do console para UTF-8, garantindo a exibição correta de caracteres especiais
        System.setOut(new PrintStream(System.out, true, StandardCharsets.UTF_8));

        // Cria uma nova instância do controlador do sistema
        Sistema sistemaOficina = new Sistema();

        // Inicia o fluxo principal do sistema
        sistemaOficina.iniciaSistema();

        // Questão 1
        {
            // Feito
        }

        // Questão 2
        {
            // Feito, a interface para o Funcionário e gerente é diferente, 
            //gerente tem acesso ao cadastro de funcionarios e area financeira
        }

        // Questão 3
        {
            // Feito
        }

        // Questão 4
        {
            // Feito, foi usado herança para as classes: 
            // Classes Funcionario e Cliente com Pessoa
            // AgendamentoDAO/BatePontoDAO/ClienteDAO/FinanceiroDAO/FuncionarioDAO/
            // OrdemDeServicoDAO/ProdutoEstoqueDAO/VeiculoDAO/VendaDAO com GenericDAO
            // (Todos os exemplos utilizam utilizam o Super para implementar seus construtores)
        }

        // Questão 5
        {
            // Feito - Classe: ElevadorController / Linha: 18
            //  private static Elevador[] getListaElevadores = new Elevador[]{
            //  new Elevador(1, "ALINHAMENTO", false, 2000),
            //  new Elevador(2, "NORMAL", false, 3000),
            //  new Elevador(3, "NORMAL", false, 3000)
            //};
        }

        //Questão 6
        {
            // Feito
            // Todas as classes quer herdam GenericDAO(CRUD genérico) utilizam as funcionalidade de (Create, Read , Update, Delete)
            // Classe Funcionario DAO, é possível:
            // AdicionarFuncionario / RemoverFuncionario / BuscaFuncionario(ID) / EditaFuncionario / MostraFuncionario 
        }

        //Questão 7
        {
            // Feito
            // Todas as classes quer herdam GenericDAO(CRUD genérico) utilizam as funcionalidade de (Create, Read , Update, Delete)
            // Classe ClienteDAO, é possível:
            // AdicionarCliente / RemoverCliente / BuscaCliente(ID) / EditaCliente / MostraCliente 
        }

        //Questão 8
        {
            // Feito
            // Classe OrdemDeServicoDAO
            //
            // Metodo buscarPorCliente | Linha 307 : Filtra a lista completa de Ordens de Serviço e 
            // retorna as Ordens de serviço referentes ao ID do cliente.
            // Os dados são exibidos pela | mostraOsIdCliente | Linha : 310 

        }

        //Questão 9
        {
            // Feito
            // A classe GenericDAO possuí o metodo SalvarDados(), este salva os dados de forma dinâmica
            // Existe uma chamada para esse método após cada ação de Adicionar/Editar/Remover
            // Classes: OrdemDeServiçoDAO Linha: 99 | ProdutoEstoqueDAO Linha: 86
            //
            // (O sistema responde de forma dinamica a cada venda ou mudança no estoque)
        }

        //Questão 10
        {
            // Feito
            // Classe VendaDAO, após confirmar uma venda é gerado a nota fiscal da venda, (antes da confirmação, é mostrado um resumo da venda )
            // com as informações do cliente (Nome e CPF pseudo-anonimizado) 
        }

        //Questão 11
        {
            // 11 - A) Feito,  private static int quantVeiculosPrivado = 0;     na classe Sistema
            // 11 - B) Feito,  protected static int quantClientesProtegido = 0; na classe Sistema
            // 11 - C)
            //  A utilização de variáveis 'private' é mais robusta, porém impacta a performance.           
            // É mais robusta por permitir que o acesso aos atributos seja mediado por métodos (getters).
            // O impacto na performance ocorre devido à sobrecarga da chamada de método, que consome mais ciclos de CPU.

            // A utilização de variáveis 'protected' é mais performática, contudo, menos controlada.
            // É menos controlada pois qualquer classe filha (subclasse) pode acessar o atributo diretamente.
            // A performance é superior pois o acesso direto ao campo exige menos recursos do processador.
        }

        //Questão 12 
        {
            //  Feito, metodos getQuantClientesProtegido e getQuantVeiculosPrivado na classe Sistema. 
        }

        //Questão 13
        {
            //  Feito
            //  Classes: ComparatorAgendDataHora | ComparatorAgendID | ComparatorClientID | ComparatorClientNome
        }

        //Questão 14
        {
            //  Feito 
            //  Todos os dados são salvos em Jsons ao encerrar o sistema pelo metodo SalvarDados() da Classe GenericDAO
            //  O metodo escreve a coleção de acordo com seu tipo generico em seu respectivo arquivo JSON
            //  Todos os dados são carregados ao iniciar o sistema pelo metodo CarregaDados() da classe GenericDAO
            //  JSONs na presentes na pasta data

        }

        //Questão 15
        {
            //  Feito
            //  JavaDoc gerado

            // Questão 15.2 - Iterator e foreach
            ClienteDAO clienteDAO = ClienteDAO.getInstancia();
            List<Cliente> clientes = clienteDAO.getLista();
            Iterator<Cliente> clientesIterator = clientes.iterator();

            System.out.println("Usando Iterator para iterar");
            while (clientesIterator.hasNext()) {
                System.out.println(clientesIterator.next());
            }
            System.out.println();

            // A interface Iterator define um protocolo para atravessar os elementos sequencialmente:
            // - hasNext(): Funciona como uma pergunta: "Existe um próximo elemento?". Retorna um booleano.
            // - next(): Devolve o elemento seguinte na iteração e move o ponteiro para a próxima posição.
            // O laço 'for-each' (enhanced for) é uma abstração que simplifica a navegação em coleções.
            // Seu propósito é reduzir a verbosidade do código quando comparado ao uso manual de um Iterator.
            // Em tempo de compilação, o 'foreach' é convertido para um código que, de fato, utiliza um Iterator.
            System.out.println("Usando foreach para iterar");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
            System.out.println();
        }

        // Questão 16
        {
            ClienteDAO clienteDAO = ClienteDAO.getInstancia();
            List<Cliente> clientes = new ArrayList<>(clienteDAO.getLista());

            Collections.sort(clientes, new ComparatorClientNome()); 

            System.out.println("Ordenado por Nome");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
            System.out.println();

            Collections.sort(clientes, new ComparatorClientID()); 

            System.out.println("Ordenado por ID");
            for (Cliente cliente : clientes) {
                System.out.println(cliente);
            }
            System.out.println();
        }

        //Questão 17
        {
            ClienteDAO clienteDAO = ClienteDAO.getInstancia();
            List<Cliente> clientes = new ArrayList<>(clienteDAO.getLista());

            Comparator<Cliente> compIdCresc = (c1, c2) -> Integer.compare(c1.getIdCliente(), c2.getIdCliente());
            Collections.sort(clientes, compIdCresc);

            Cliente clienteAlvo = null;
            if (!clientes.isEmpty()) {
                clienteAlvo = clientes.get(0);
            }

            if (clienteAlvo != null) {
                double startTime = System.nanoTime();
                int resultado = Collections.binarySearch(clientes, clienteAlvo, compIdCresc);
                double endTime = System.nanoTime();

                System.out.printf("Demorou %f nano segundos para encontrar por meio da busca binaria%n", endTime - startTime);
                System.out.println("Resultado binarySearch: " + (resultado >= 0 ? "Encontrado na posição " + resultado : "Não encontrado"));

                startTime = System.nanoTime();
                Cliente encontrado = findCliente(clientes, clienteAlvo, compIdCresc);
                endTime = System.nanoTime();

                System.out.printf("Demorou %f nano segundos para encontrar por meio da busca implementada%n", endTime - startTime);
                System.out.println("Resultado find implementado: " + (encontrado != null ? "Cliente encontrado: " + encontrado.getNome() : "Não encontrado"));
                System.out.println("");
            } else {
                System.out.println("Nenhum cliente disponível para teste de busca.");
            }
        }
    }
}
