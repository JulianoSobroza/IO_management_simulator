package interrupts;
import devices.Device;
import devices.Priority;

public class Interrupt {
    private final Device device;
    private final Priority priority;
    private final long timestamp;

    public Interrupt(Device device, Priority priority, long timestamp) {
        this.device = device;
        this.priority = priority;
        this.timestamp = timestamp;
    }

    public Device getDevice() { return device; }
    public Priority getPriority() { return priority; }
    public long getTimestamp() { return timestamp; }
}
