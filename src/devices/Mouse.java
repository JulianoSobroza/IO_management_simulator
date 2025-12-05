package devices;

/**
 * Representa o mouse.
 * Prioridade média.
 */
public class Mouse extends Device {
    public Mouse() {
        super("Mouse", Priority.MEDIA, 100);
    }

    @Override
    protected long calcularProximaInterrupcao(long tempoAtual) {
        // Gera uma interrupção aleatória entre 40 e 60 unidades de tempo a partir de agora
        return tempoAtual + 40 + aleatorio.nextInt(21);
    }
}
