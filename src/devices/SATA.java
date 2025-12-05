package devices;

/**
 * Representa o disco rígido (SATA).
 * Baixa prioridade, mas muito rápido na transferência de dados (teoricamente).
 */
public class SATA extends Device {
    public SATA() {
        super("SATA ", Priority.BAIXA, 600_000_000);
    }

    @Override
    protected long calcularProximaInterrupcao(long tempoAtual) {
        // Gera uma interrupção aleatória entre 3 e 7 unidades de tempo a partir de agora
        return tempoAtual + 3 + aleatorio.nextInt(5);
    }
}
