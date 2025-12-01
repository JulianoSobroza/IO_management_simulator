
import devices.Keyboard;
import devices.Mouse;
import devices.SATA;
import interrupts.InterruptController;
import log.Logger;
import simulation.Processo;
import simulation.Simulador;

public class Main {
    public static void main(String[] args) {

        // Cria o controlador de interrupções
        InterruptController controller = new InterruptController();

        // Cria o logger
        Logger logger = new Logger("eventos.log");

        // Cria o processo principal
        Processo processo = new Processo();
        
        // Cria o simulador
        Simulador simulador = new Simulador(controller, logger, processo);

        // Registra os dispositivos
        simulador.addDevice(new Keyboard());
        simulador.addDevice(new Mouse());
        simulador.addDevice(new SATA());

        // Inicia a simulação
        simulador.run(500);  // tempo total de simulação
    }
}
