package devices;

/**
 * Representa a impressora.
 * Dispositivo mais lento, prioridade média.
 */
public class Printer extends Device {
    public Printer() {
        super("Impressora", Priority.MEDIA, 500);
    }

    @Override
    protected long calcularProximaInterrupcao(long tempoAtual) {
        // Gera uma interrupção aleatória entre 150 e 250 unidades de tempo a partir de agora
        return tempoAtual + 150 + aleatorio.nextInt(101);
    }
}
