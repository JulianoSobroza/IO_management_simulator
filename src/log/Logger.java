package log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

    public static final String RESET = "\u001B[0m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String CYAN = "\u001B[36m";

    private PrintWriter writer;

    public Logger(String fileName) {
        try {
            writer = new PrintWriter(new FileWriter(fileName, true)); // append = true
        } catch (IOException e) {
            throw new RuntimeException("Erro ao criar arquivo de log: " + fileName);
        }
    }

    public void log(String mensagem) {
        writer.println(mensagem);
        writer.flush();
        System.out.println(mensagem);
    }

    public void logInfo(String mensagem) {
        log(GREEN + "[INFO] " + mensagem + RESET);
    }

    public void logWarning(String mensagem) {
        log(YELLOW + "[WARN] " + mensagem + RESET);
    }

    public void logInterrupt(String mensagem) {
        log(RED + "[INTERRUPT] " + mensagem + RESET);
    }

    public void logSystem(String mensagem) {
        log(CYAN + "[SYSTEM] " + mensagem + RESET);
    }

    public void close() {
        writer.flush();
        writer.close();
    }
}
