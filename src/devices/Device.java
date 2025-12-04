package devices;

import interrupts.Interrupt;

public abstract class Device {
    private String name;
    private Priority priority;
    private long transferRate;
    private long lastInterruptTime;
    private long nextInterruptTime;
    protected java.util.Random random = new java.util.Random();

    public Device(String name, Priority priority, long transferRate) {
        this.name = name;
        this.priority = priority;
        this.transferRate = transferRate;
        this.lastInterruptTime = 0;
        this.nextInterruptTime = computeNextInterruptTime(0);
    }

    public boolean shouldInterrupt(long currentTime) {
        if (currentTime >= nextInterruptTime) {
            lastInterruptTime = currentTime;
            nextInterruptTime = computeNextInterruptTime(currentTime);
            return true;
        }
        return false;
    }

    // Cada dispositivo define quando será a próxima interrupção
    protected abstract long computeNextInterruptTime(long currentTime);


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
