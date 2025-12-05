package log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Classe simples para logar mensagens no console e em arquivo.
 * Usa cores ANSI para ficar bonitinho no terminal.
 */
public class Logger {

    // Cores para o terminal
    public static final String RESET = "\u001B[0m";
    public static final String VERMELHO = "\u001B[31m";
    public static final String VERDE = "\u001B[32m";
    public static final String AMARELO = "\u001B[33m";
    public static final String AZUL = "\u001B[34m";
    public static final String CIANO = "\u001B[36m";

    private PrintWriter escritor;

    public Logger(String nomeArquivo) {
        try {
            escritor = new PrintWriter(new FileWriter(nomeArquivo, true)); // append = true
        } catch (IOException e) {
            throw new RuntimeException("Deu ruim ao criar arquivo de log: " + nomeArquivo);
        }
    }

    public void registrar(String mensagem) {
        escritor.println(mensagem);
        escritor.flush();
        System.out.println(mensagem);
    }

    public void logInfo(String mensagem) {
        registrar(VERDE + "[INFO] " + mensagem + RESET);
    }

    public void logAviso(String mensagem) {
        registrar(AMARELO + "[AVISO] " + mensagem + RESET);
    }

    public void logInterrupcao(String mensagem) {
        registrar(VERMELHO + "[INTERRUPCAO] " + mensagem + RESET);
    }

    public void logSistema(String mensagem) {
        registrar(CIANO + "[SISTEMA] " + mensagem + RESET);
    }

    public void fechar() {
        escritor.flush();
        escritor.close();
    }
}
