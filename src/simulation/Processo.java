package simulation;

public class Processo {

    private Contexto contextoAtual;

    public Processo() {
        this.contextoAtual = new Contexto(0, "executando");
    }

    public void salvarContexto() {
        // Aqui você simula o armazenamento do estado.
        contextoAtual = new Contexto(contextoAtual.getPc(), "interrompido");
    }

    public void restaurarContexto() {
        // Simula retomada do processo
        contextoAtual = new Contexto(contextoAtual.getPc() + 1, "executando");
    }

    public Contexto getContexto() {
        return contextoAtual;
    }
}
