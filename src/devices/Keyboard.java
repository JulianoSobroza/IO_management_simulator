package devices;

public class Keyboard extends Device {
    public Keyboard() {
        super("Teclado", Priority.HIGH, 10);
    }

    @Override
    protected long computeNextInterruptTime(long currentTime) {
        // Gera uma interrupção aleatória entre 80 e 120 unidades de tempo a partir de agora
        return currentTime + 80 + random.nextInt(41);
    }
}
