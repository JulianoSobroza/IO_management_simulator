package simulation;

public class Processo {

    private Contexto contextoAtual;
    // private java.util.Random random = new java.util.Random(); // Não é mais necessário se não geramos valores aleatórios para registradores

    public Processo() {
        this.contextoAtual = new Contexto(0, "executando");
    }

    public void executar() {
        // Simula o processamento apenas incrementando o PC
        int novoPc = contextoAtual.getPc() + 1;
        this.contextoAtual = new Contexto(novoPc, "executando");
    }

    public void salvarContexto() {
        // Aqui você simula o armazenamento do estado.
        contextoAtual = new Contexto(contextoAtual.getPc(), "interrompido");
    }

    public void restaurarContexto() {
        // Simula retomada do processo
        contextoAtual = new Contexto(contextoAtual.getPc(), "executando");
    }

    public Contexto getContexto() {
        return contextoAtual;
    }
}
