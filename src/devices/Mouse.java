package devices;

public class Mouse extends Device {
    public Mouse() {
        super("Mouse", Priority.MEDIUM, 100);
    }

    @Override
    protected long computeInterruptInterval() {
        return 50; // interrupções mais frequentes
    }
}
