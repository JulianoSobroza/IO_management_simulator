package devices;

public class SATA extends Device {
    public SATA() {
        super("SATA ", Priority.LOW, 600_000_000);
    }

    @Override
    protected long computeInterruptInterval() {
        return 5; // muito frequente
    }
}
