package simulation;

import devices.Device;
import interrupts.Interrupt;
import interrupts.InterruptController;
import java.util.ArrayList;
import java.util.List;
import log.Logger;

public class Simulador {

    private InterruptController controller = new InterruptController();
    private Logger logger = new Logger("asd.txt");
    private Processo processo = new Processo();
    private final List<Device> devices = new ArrayList<>();

    private long tempo = 0;

    public Simulador(InterruptController controller, Logger logger, Processo processo) {
        this.controller = controller;
        this.logger = logger;
        this.processo = processo;
    }


    public void addDevice(Device device) {
        devices.add(device);
    }

    public void run(long tempoMaximo) {
        for (tempo = 0; tempo <= tempoMaximo; tempo++) {

            // 1. Processo executa se nada o interromper
            logger.log("[Tempo " + tempo + "] Processo principal em execução.");

            // 2. Verifica dispositivos que geram interrupção nesta unidade
            for (Device dev : devices) {
                if (dev.shouldInterrupt(tempo)) {
                    Interrupt inter = dev.generateInterrupt(tempo);
                    controller.requestInterrupt(inter);

                    logger.log("[Tempo " + tempo + "] Interrupção gerada: " +
                               dev.getName() + " (" + dev.getPriority() + ")");
                }
            }

            // 3. Se houver interrupções, tratar agora
            if (controller.hasInterrupts()) {
                tratarInterrupcao();
            }
        }
        logger.close();
    }

    private void tratarInterrupcao() {
        Interrupt inter = controller.nextInterrupt();

        logger.log("[Tempo " + tempo + "] Armazenando contexto...");
        processo.salvarContexto();

        logger.log("[Tempo " + tempo + "] Tratando interrupção de " +
                   inter.getDevice().getName());

        // Um pequeno delay de tratamento para fins de simulação
        tempo += 3;

        logger.log("[Tempo " + tempo + "] Interrupção tratada. Restaurando contexto...");
        processo.restaurarContexto();
    }
}
