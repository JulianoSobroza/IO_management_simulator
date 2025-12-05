import devices.Keyboard;
import devices.Printer;
import devices.SATA;
import interrupts.InterruptController;
import java.io.File;
import java.util.Scanner;
import log.Logger;
import simulation.Processo;
import simulation.Simulador;

public class Main {
    public static void main(String[] args) {
        // Limpa o log da execução anterior para começar limpo
        new File("eventos.log").delete();

        Scanner scanner = new Scanner(System.in);
        boolean executando = true;
        
        while (executando) {
            System.out.println("\n=========================================");
            System.out.println("   SIMULADOR DE GERENCIAMENTO DE E/S");
            System.out.println("=========================================");
            System.out.println("Escolha o cenário de simulação:");
            System.out.println("1. Simulação Padrão (Interrupções aleatórias)");
            System.out.println("2. Teste de Prioridade (Colisão forçada no Tempo 10)");
            System.out.println("0. Sair");
            System.out.print("Digite a opção desejada: ");

            int opcao = -1;
            try {
                if (scanner.hasNextInt()) {
                    opcao = scanner.nextInt();
                } else {
                    scanner.next(); // Limpa entrada inválida
                }
            } catch (Exception e) {
                System.out.println("Erro na leitura da opção.");
            }

            if (opcao == 0) {
                executando = false;
                System.out.println("Encerrando simulador...");
                continue;
            }

            // Cria os componentes (reiniciando o estado a cada simulação)
            InterruptController controlador = new InterruptController();
            Logger logger = new Logger("eventos.log");
            Processo processo = new Processo();
            Simulador simulador = new Simulador(controlador, logger, processo);

            if (opcao == 1) {
                System.out.println("\n>>> Iniciando Simulação Padrão (50 unidades de tempo) <<<");
                System.out.println("Dispositivos geram interrupções em intervalos aleatórios.");
                
                // Registra os dispositivos com comportamento padrão
                simulador.adicionarDispositivo(new Keyboard());
                simulador.adicionarDispositivo(new Printer());
                simulador.adicionarDispositivo(new SATA());

                // Inicia a simulação
                simulador.executar(50);

            } else if (opcao == 2) {
                System.out.println("\n>>> Iniciando Teste de Prioridade (20 unidades de tempo) <<<");
                System.out.println("Cenário: Teclado (Alta) e SATA (Baixa) configurados para interromper JUNTOS no tempo 10.");
                System.out.println("Resultado esperado: O Teclado deve ser atendido antes do SATA.");

                // Registra Teclado modificado para interromper no tempo 10
                simulador.adicionarDispositivo(new Keyboard() {
                    @Override
                    protected long calcularProximaInterrupcao(long tempoAtual) {
                        if (tempoAtual == 0) return 10; // Primeira interrupção no tempo 10
                        return tempoAtual + 1000; // Próximas muito distantes para não atrapalhar
                    }
                });

                // Registra SATA modificado para interromper TAMBÉM no tempo 10
                simulador.adicionarDispositivo(new SATA() {
                    @Override
                    protected long calcularProximaInterrupcao(long tempoAtual) {
                        if (tempoAtual == 0) return 10; // Primeira interrupção no tempo 10
                        return tempoAtual + 1000; 
                    }
                });

                // Inicia a simulação
                simulador.executar(20);
            } else {
                if (opcao != -1) System.out.println("Opção inválida.");
            }
        }

        scanner.close();
        System.out.println("\nSimulação finalizada. Verifique o arquivo 'eventos.log'.");
    }
}
