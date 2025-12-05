package devices;

/**
 * Representa o teclado.
 * Gera interrupções frequentes (alta prioridade).
 */
public class Keyboard extends Device {
    // Construtor do Teclado
    public Keyboard() {
        super("Teclado", Priority.ALTA, 10);
    }

    @Override
    protected long calcularProximaInterrupcao(long tempoAtual) {
        // Gera uma interrupção aleatória entre 80 e 120 unidades de tempo a partir de agora
        return tempoAtual + 80 + aleatorio.nextInt(41);
    }
}
