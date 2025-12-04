package devices;

public class Mouse extends Device {
    public Mouse() {
        super("Mouse", Priority.MEDIUM, 100);
    }

    @Override
    protected long computeNextInterruptTime(long currentTime) {
        // Gera uma interrupção aleatória entre 40 e 60 unidades de tempo a partir de agora
        return currentTime + 40 + random.nextInt(21);
    }
}
