package simulation;

import devices.Device;
import interrupts.Interrupt;
import interrupts.InterruptController;
import java.util.ArrayList;
import java.util.List;
import log.Logger;

public class Simulador {

    private InterruptController controller = new InterruptController();
    private Logger logger = new Logger("log.txt");
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

            try {
                Thread.sleep(500); // Delay de 500ms para visualização
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 1. Processo executa se nada o interromper
            processo.executar();
            logger.logInfo("[Tempo " + tempo + "] Processo principal em execução. " + processo.getContexto());

            // 2. Verifica dispositivos que geram interrupção nesta unidade
            verificarDispositivos(tempo);

            // 3. Se houver interrupções, tratar TODAS antes de voltar ao processo
            while (controller.hasInterrupts()) {
                tratarInterrupcao();
            }
        }
        logger.close();
    }

    private void verificarDispositivos(long currentTime) {
        for (Device dev : devices) {
            if (dev.shouldInterrupt(currentTime)) {
                Interrupt inter = dev.generateInterrupt(currentTime);
                controller.requestInterrupt(inter);

                logger.logWarning("[Tempo " + currentTime + "] Interrupção gerada: " +
                           dev.getName() + " (" + dev.getPriority() + ")");
            }
        }
    }

    private void tratarInterrupcao() {
        Interrupt inter = controller.nextInterrupt();

        logger.logInterrupt(">>> INICIANDO TRATAMENTO DE INTERRUPÇÃO <<<");
        logger.logSystem("[Tempo " + tempo + "] Interrupção de " + inter.getDevice().getName() + " detectada.");
        logger.logSystem("Estado do processo ANTES de salvar: " + processo.getContexto());
        
        logger.logSystem("Armazenando contexto na pilha...");
        processo.salvarContexto();
        logger.logSystem("Contexto salvo: " + processo.getContexto());

        logger.logInterrupt("CPU desviada para rotina de tratamento de: " +
                   inter.getDevice().getName() + " (Prioridade: " + inter.getPriority() + ")");

        // Simula tempo de tratamento (3 unidades de tempo)
        // Durante o tratamento, outros dispositivos podem gerar interrupções (que entram na fila)
        for (int i = 0; i < 3; i++) {
            try {
                Thread.sleep(300); 
            } catch (InterruptedException e) { e.printStackTrace(); }
            
            tempo++; // O tempo passa enquanto a CPU trata a interrupção
            verificarDispositivos(tempo); // Verifica se chegaram novas interrupções durante o tratamento
        }

        logger.logSystem("Interrupção tratada. Restaurando contexto...");
        processo.restaurarContexto();
        
        logger.logSystem("Contexto restaurado: " + processo.getContexto());
        logger.logInterrupt(">>> FIM DO TRATAMENTO <<<");
    }
}
