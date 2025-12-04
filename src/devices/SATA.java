package devices;

public class SATA extends Device {
    public SATA() {
        super("SATA ", Priority.LOW, 600_000_000);
    }

    @Override
    protected long computeNextInterruptTime(long currentTime) {
        // Gera uma interrupção aleatória entre 3 e 7 unidades de tempo a partir de agora
        return currentTime + 3 + random.nextInt(5);
    }
}
