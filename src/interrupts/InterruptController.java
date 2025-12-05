
package interrupts;
import java.util.PriorityQueue;
import interrupts.Interrupt;

/**
 * Controlador de interrupções (tipo um PIC - Programmable Interrupt Controller).
 * Gerencia a fila de interrupções baseada na prioridade.
 */
public class InterruptController {

    // Fila de prioridade: quem tem prioridade ALTA sai primeiro
    private PriorityQueue<Interrupt> fila =
        new PriorityQueue<>((a, b) -> a.getPrioridade().compareTo(b.getPrioridade()));

    public void solicitarInterrupcao(Interrupt interrupcao) {
        fila.add(interrupcao);
    }

    public Interrupt proximaInterrupcao() {
        return fila.poll();
    }

    public boolean temInterrupcoes() {
        return !fila.isEmpty();
    }
}
