
package interrupts;
import java.util.PriorityQueue;
import interrupts.Interrupt;

public class InterruptController {

    private PriorityQueue<Interrupt> queue =
        new PriorityQueue<>((a, b) -> a.getPriority().compareTo(b.getPriority()));

    public void requestInterrupt(Interrupt interrupt) {
        queue.add(interrupt);
    }

    public Interrupt nextInterrupt() {
        return queue.poll();
    }

    public boolean hasInterrupts() {
        return !queue.isEmpty();
    }
}
