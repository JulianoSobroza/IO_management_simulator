package simulation;

/**
 * Simula um processo rodando na CPU.
 * Ele tem um contexto que muda conforme executa.
 */
public class Processo {

    private Contexto contextoAtual;

    public Processo() {
        this.contextoAtual = new Contexto(0, "executando");
    }

    /**
     * Executa uma instrução do processo.
     */
    public void executar() {
        // Simula o processamento apenas incrementando o PC
        int novoPc = contextoAtual.getPc() + 1;
        this.contextoAtual = new Contexto(novoPc, "executando");
    }

    /**
     * Salva o estado atual (muda status para interrompido).
     */
    public void salvarContexto() {
        // Aqui você simula o armazenamento do estado.
        contextoAtual = new Contexto(contextoAtual.getPc(), "interrompido");
    }

    /**
     * Restaura o estado anterior (volta para executando).
     */
    public void restaurarContexto() {
        // Simula retomada do processo
        contextoAtual = new Contexto(contextoAtual.getPc(), "executando");
    }

    public Contexto getContexto() {
        return contextoAtual;
    }
}
