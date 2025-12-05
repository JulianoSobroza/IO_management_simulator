package simulation;

import devices.Device;
import interrupts.Interrupt;
import interrupts.InterruptController;
import java.util.ArrayList;
import java.util.List;
import log.Logger;

/**
 * O coração da simulação.
 * Funciona como o loop principal do Sistema Operacional.
 */
public class Simulador {

    private InterruptController controlador;
    private Logger logger;
    private Processo processo;
    private final List<Device> dispositivos = new ArrayList<>();

    private long tempo = 0;

    public Simulador(InterruptController controlador, Logger logger, Processo processo) {
        this.controlador = controlador;
        this.logger = logger;
        this.processo = processo;
    }

    public void adicionarDispositivo(Device dispositivo) {
        dispositivos.add(dispositivo);
    }

    public void executar(long tempoMaximo) {
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
            while (controlador.temInterrupcoes()) {
                tratarInterrupcao();
            }
        }
    }

    private void verificarDispositivos(long tempoAtual) {
        for (Device d : dispositivos) {
            if (d.deveInterromper(tempoAtual)) {
                logger.logAviso("Dispositivo " + d.getNome() + " gerou interrupção!");
                controlador.solicitarInterrupcao(d.gerarInterrupcao(tempoAtual));
            }
        }
    }

    private void tratarInterrupcao() {
        Interrupt interrupcao = controlador.proximaInterrupcao();
        if (interrupcao != null) {
            logger.logSistema("Tratando interrupção de: " + interrupcao.getDispositivo().getNome() + 
                              " (Prioridade: " + interrupcao.getPrioridade() + ")");
            
            // Salva contexto
            processo.salvarContexto();
            logger.logSistema("Contexto salvo: " + processo.getContexto());

            // Simula o tratamento da interrupção (ISR)
            logger.logInterrupcao("Executando rotina de tratamento para " + interrupcao.getDispositivo().getNome() + "...");
            try {
                Thread.sleep(1000); // Simula tempo de processamento da interrupção
            } catch (InterruptedException e) { }

            // Restaura contexto
            processo.restaurarContexto();
            logger.logSistema("Contexto restaurado: " + processo.getContexto());
        }
    }
}
