package collections;
import java.util.*;

public class QueueBasics {
    public static void main(String[] args) {
        // ========== DECLARAÇÃO E CRIAÇÃO ==========
        System.out.println("=== QUEUES (FILAS) ===");

        // LinkedList implementa Queue
        Queue<String> fila = new LinkedList<>();
        System.out.println("Fila vazia: " + fila);
        // ArrayDeque - mais eficiente que LinkedList
        Queue<String> filaDeque = new ArrayDeque<>();

        // PriorityQueue - fila com prioridade
        Queue<Integer> filaPrioridade = new PriorityQueue<>();

        // ---------------- MÉTODOS BÁSICOS ----------------
        System.out.println("\n--- MÉTODOS BÁSICOS ---");
        Queue<String> atendimento = new LinkedList<>();

        // OFFER - adiciona elemento no final da fila (retorna boolean)
        System.out.println("=== OFFER (adicionar) ===");
        boolean adicionou1 = atendimento.offer("Cliente 1");
        boolean adicionou2 = atendimento.offer("Cliente 2");
        boolean adicionou3 = atendimento.offer("Cliente 3");
        System.out.println("Adicionou Cliente 1? " + adicionou1);  // true
        System.out.println("Fila após offer: " + atendimento);

        // POLL - remove e retorna o primeiro elemento (FIFO), ou null se vazia
        System.out.println("\n=== POLL (remover) ===");
        String proximo = atendimento.poll();  // Cliente 1
        System.out.println("Próximo atendido: " + proximo);
        System.out.println("Fila após poll: " + atendimento);

        // PEEK - visualiza o primeiro elemento sem remover, ou null se vazia
        System.out.println("\n=== PEEK (espiar) ===");
        String proximoNaFila = atendimento.peek();  // Cliente 2
        System.out.println("Próximo na fila: " + proximoNaFila);
        System.out.println("Fila após peek: " + atendimento);  // não mudou

        // SIZE - tamanho da fila
        System.out.println("Tamanho da fila: " + atendimento.size());

        // ISEMPTY - verificar se está vazia
        System.out.println("Fila vazia? " + atendimento.isEmpty());

        // CLEAR - limpar toda a fila
        Queue<String> copia = new LinkedList<>(atendimento);
        copia.clear();
        System.out.println("Fila após clear: " + copia);

        // ---------------- QUEUE COMO FILA (FIFO) ----------------
        System.out.println("\n--- QUEUE COMO FILA (FIFO) ===");
        Queue<String> bancoCentral = new LinkedList<>();

        // Simulando chegada de clientes
        bancoCentral.offer("Ana");
        bancoCentral.offer("Bruno");
        bancoCentral.offer("Carlos");
        bancoCentral.offer("Diana");
        System.out.println("Fila do banco: " + bancoCentral);

        // Atendimento por ordem de chegada (FIFO)
        System.out.println("\n=== ATENDIMENTO (FIFO) ===");
        while (!bancoCentral.isEmpty()) {
            String cliente = bancoCentral.poll();
            System.out.println("🏛️ Atendendo: " + cliente + " | Restam: " + bancoCentral);
        }

        // ---------------- DEQUE COMO PILHA (LIFO) ----------------
        System.out.println("\n--- DEQUE COMO PILHA (LIFO) ===");
        Deque<String> pilha = new ArrayDeque<>();

        // PUSH - adiciona no topo da pilha
        pilha.push("Prato 1");
        pilha.push("Prato 2");
        pilha.push("Prato 3");
        System.out.println("Pilha de pratos: " + pilha);

        // POP - remove do topo da pilha (LIFO)
        System.out.println("\n=== LAVAGEM DE PRATOS (LIFO) ===");
        while (!pilha.isEmpty()) {
            String prato = pilha.pop();
            System.out.println("🍽️ Lavando: " + prato + " | Restam: " + pilha);
        }

        // ---------------- DEQUE COMO FILA DUPLA ----------------
        System.out.println("\n--- DEQUE COMO FILA DUPLA ===");
        Deque<String> filaDupla = new ArrayDeque<>();

        // Adicionar nas duas pontas
        filaDupla.offerFirst("Primeiro");    // adiciona no início
        filaDupla.offerLast("Último");       // adiciona no final
        filaDupla.offerFirst("Novo Primeiro");
        filaDupla.offerLast("Novo Último");
        System.out.println("Fila dupla: " + filaDupla);

        // Remover das duas pontas
        String doInicio = filaDupla.pollFirst();  // remove do início
        String doFinal = filaDupla.pollLast();    // remove do final
        System.out.println("Removido do início: " + doInicio);
        System.out.println("Removido do final: " + doFinal);
        System.out.println("Fila dupla final: " + filaDupla);

        // Espiar nas duas pontas
        System.out.println("Primeiro elemento: " + filaDupla.peekFirst());
        System.out.println("Último elemento: " + filaDupla.peekLast());

        // ---------------- PRIORITYQUEUE ----------------
        System.out.println("\n--- PRIORITY QUEUE ===");

        // NÚMEROS - ordem natural (menor primeiro)
        System.out.println("=== NÚMEROS (menor primeiro) ===");
        PriorityQueue<Integer> filaNumeros = new PriorityQueue<>();
        filaNumeros.offer(30);
        filaNumeros.offer(10);
        filaNumeros.offer(50);
        filaNumeros.offer(20);
        System.out.println("PriorityQueue: " + filaNumeros);

        System.out.println("Processamento por prioridade:");
        while (!filaNumeros.isEmpty()) {
            System.out.println("Processando: " + filaNumeros.poll());
        }

        // STRINGS - ordem alfabética
        System.out.println("\n=== STRINGS (ordem alfabética) ===");
        PriorityQueue<String> filaNomes = new PriorityQueue<>();
        filaNomes.offer("Carlos");
        filaNomes.offer("Ana");
        filaNomes.offer("Bruno");
        filaNomes.offer("Diana");
        System.out.println("Fila nomes: " + filaNomes);

        System.out.println("Ordem alfabética:");
        while (!filaNomes.isEmpty()) {
            System.out.println("Chamando: " + filaNomes.poll());
        }

        // PRIORIDADE CUSTOMIZADA - maior primeiro
        System.out.println("\n=== PRIORIDADE CUSTOMIZADA ===");
        PriorityQueue<Integer> filaMaiorPrimeiro = new PriorityQueue<>(Collections.reverseOrder());
        filaMaiorPrimeiro.offer(30);
        filaMaiorPrimeiro.offer(10);
        filaMaiorPrimeiro.offer(50);
        filaMaiorPrimeiro.offer(20);

        System.out.println("Maior primeiro:");
        while (!filaMaiorPrimeiro.isEmpty()) {
            System.out.println("Processando: " + filaMaiorPrimeiro.poll());
        }

        // ---------------- COMPARAÇÃO DE PERFORMANCE ----------------
        System.out.println("\n--- PERFORMANCE ===");
        System.out.println("LinkedList Queue:  Boa para inserção/remoção, mas usa mais memória");
        System.out.println("ArrayDeque:        Mais eficiente que LinkedList, recomendada para Queue/Deque");
        System.out.println("PriorityQueue:     O(log n) para insert/remove, sempre mantém ordem");

        // ---------------- EXEMPLOS PRÁTICOS ----------------
        System.out.println("\n--- EXEMPLOS PRÁTICOS ---");

        // EXEMPLO 1: SISTEMA DE IMPRESSÃO
        System.out.println("=== SISTEMA DE IMPRESSÃO ===");
        Queue<String> filaImpressao = new LinkedList<>();
        filaImpressao.offer("documento1.pdf");
        filaImpressao.offer("planilha.xlsx");
        filaImpressao.offer("apresentacao.pptx");
        System.out.println("Fila de impressão: " + filaImpressao);

        System.out.println("Processando impressões:");
        int numeroImpressao = 1;
        while (!filaImpressao.isEmpty()) {
            String documento = filaImpressao.poll();
            System.out.println("🖨️ Imprimindo (" + numeroImpressao + "): " + documento);
            numeroImpressao++;
        }

        // EXEMPLO 2: NAVEGADOR - HISTÓRICO COM PILHA
        System.out.println("\n=== NAVEGADOR - HISTÓRICO ===");
        Deque<String> historico = new ArrayDeque<>();

        // Navegação (push = visitar nova página)
        historico.push("google.com");
        historico.push("github.com");
        historico.push("stackoverflow.com");
        historico.push("oracle.com/java");
        System.out.println("Histórico: " + historico);
        System.out.println("Página atual: " + historico.peek());

        // Botão "Voltar" (pop)
        System.out.println("\nUsando botão VOLTAR:");
        String paginaAnterior = historico.pop();
        System.out.println("Saindo de: " + paginaAnterior);
        System.out.println("Voltou para: " + historico.peek());

        // EXEMPLO 3: ATENDIMENTO HOSPITALAR COM PRIORIDADE
        System.out.println("\n=== HOSPITAL - TRIAGEM ===");

        // Criando comparador personalizado para urgência
        PriorityQueue<PacienteExp> triagem = new PriorityQueue<>((p1, p2) ->
                Integer.compare(p1.urgencia, p2.urgencia)); // menor número = mais urgente

        // Adicionando pacientes
        triagem.offer(new PacienteExp("Ana", 3));      // urgência baixa
        triagem.offer(new PacienteExp("Bruno", 1));    // emergência!
        triagem.offer(new PacienteExp("Carlos", 2));   // urgência média
        triagem.offer(new PacienteExp("Diana", 1));    // emergência!

        System.out.println("Ordem de atendimento:");
        while (!triagem.isEmpty()) {
            PacienteExp paciente = triagem.poll();
            String nivelUrgencia = paciente.urgencia == 1 ? "🚨 EMERGÊNCIA" :
                    paciente.urgencia == 2 ? "⚠️ URGENTE" : "✅ NORMAL";
            System.out.println("Atendendo: " + paciente.nome + " - " + nivelUrgencia);
        }

        // EXEMPLO 4: PROCESSAMENTO DE TAREFAS
        System.out.println("\n=== PROCESSAMENTO DE TAREFAS ===");
        Queue<String> filaProcessamento = new LinkedList<>();

        // Adicionando tarefas
        filaProcessamento.offer("Processar emails");
        filaProcessamento.offer("Gerar relatório");
        filaProcessamento.offer("Backup banco de dados");
        filaProcessamento.offer("Limpar logs antigos");

        System.out.println("=== PROCESSADOR INICIADO ===");
        int tarefaNumero = 1;
        while (!filaProcessamento.isEmpty()) {
            String tarefa = filaProcessamento.poll();
            System.out.println("⚙️ [" + tarefaNumero + "/4] Executando: " + tarefa);

            // Simular processamento
            if (tarefa.contains("Backup")) {
                System.out.println("   💾 Backup pode demorar mais...");
            }
            tarefaNumero++;
        }
        System.out.println("✅ Todas as tarefas foram concluídas!");

        // EXEMPLO 5: EDITOR DE TEXTO - UNDO/REDO
        System.out.println("\n=== EDITOR - UNDO/REDO ===");
        Deque<String> acoes = new ArrayDeque<>();
        Deque<String> undoStack = new ArrayDeque<>();

        // Simulando ações do usuário
        String[] comandos = {"Escrever 'Olá'", "Escrever ' Mundo'", "Apagar 'Mundo'", "Escrever ' Java'"};

        for (String comando : comandos) {
            acoes.push(comando);
            System.out.println("✏️ Ação: " + comando);
        }

        System.out.println("\nHistórico de ações: " + acoes);

        // Simulando UNDO (Ctrl+Z)
        System.out.println("\n=== FAZENDO UNDO ===");
        for (int i = 0; i < 2; i++) {
            if (!acoes.isEmpty()) {
                String ultimaAcao = acoes.pop();
                undoStack.push(ultimaAcao);
                System.out.println("↶ Desfazendo: " + ultimaAcao);
                System.out.println("  Estado atual: " + acoes);
            }
        }

        // Simulando REDO (Ctrl+Y)
        System.out.println("\n=== FAZENDO REDO ===");
        if (!undoStack.isEmpty()) {
            String acaoRedo = undoStack.pop();
            acoes.push(acaoRedo);
            System.out.println("↷ Refazendo: " + acaoRedo);
            System.out.println("  Estado atual: " + acoes);
        }
    }

    // Classe auxiliar para exemplo do hospital
    static class PacienteExp {
        String nome;
        int urgencia; // 1 = emergência, 2 = urgente, 3 = normal

        PacienteExp(String nome, int urgencia) {
            this.nome = nome;
            this.urgencia = urgencia;
        }
    }
}