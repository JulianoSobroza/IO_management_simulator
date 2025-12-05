package interrupts;
import devices.Device;
import devices.Priority;

/**
 * Representa um evento de interrupção gerado por um dispositivo.
 * Contém quem gerou, a prioridade e quando aconteceu.
 */
public class Interrupt {
    private final Device dispositivo;
    private final Priority prioridade;
    private final long tempo;

    public Interrupt(Device dispositivo, Priority prioridade, long tempo) {
        this.dispositivo = dispositivo;
        this.prioridade = prioridade;
        this.tempo = tempo;
    }

    public Device getDispositivo() { return dispositivo; }
    public Priority getPrioridade() { return prioridade; }
    public long getTempo() { return tempo; }
}
