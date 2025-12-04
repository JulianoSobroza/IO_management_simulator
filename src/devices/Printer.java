package devices;

public class Printer extends Device {
    public Printer() {
        super("Impressora", Priority.MEDIUM, 500);
    }

    @Override
    protected long computeNextInterruptTime(long currentTime) {
        // Gera uma interrupção aleatória entre 150 e 250 unidades de tempo a partir de agora
        return currentTime + 150 + random.nextInt(101);
    }
}
