package devices;

public class Keyboard extends Device {
    public Keyboard() {
        super("Teclado", Priority.HIGH, 10);
    }

    @Override
    protected long computeInterruptInterval() {
        return 100; // uma interrupção a cada 100 unidades de tempo
    }
}
