package devices;

import interrupts.Interrupt;

public abstract class Device {

    private String name;
    private Priority priority;
    private long transferRate;
    private long lastInterruptTime;

    public Device(String name, Priority priority, long transferRate) {
        this.name = name;
        this.priority = priority;
        this.transferRate = transferRate;
        this.lastInterruptTime = 0;
    }

    // Lógica extremamente simples baseada no tempo
    public boolean shouldInterrupt(long currentTime) {
        long interval = computeInterruptInterval();
        if (currentTime - lastInterruptTime >= interval) {
            lastInterruptTime = currentTime;
            return true;
        }
        return false;
    }

    // Cada tipo de dispositivo define seu próprio ritmo
    protected abstract long computeInterruptInterval();


    public Interrupt generateInterrupt(long time) {
        return new Interrupt(this, priority, time);
    }


    // Abaixo somente os Getters e Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public long getTransferRate() {
        return transferRate;
    }

    public void setTransferRate(long transferRate) {
        this.transferRate = transferRate;
    }

    public long getLastInterruptTime() {
        return lastInterruptTime;
    }

    public void setLastInterruptTime(long lastInterruptTime) {
        this.lastInterruptTime = lastInterruptTime;
    }
}
