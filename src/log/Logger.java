package log;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Logger {

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
        writer.flush(); // garante que o log seja salvo imediatamente
        System.out.println(mensagem);
    }

    public void close() {
        writer.flush();
        writer.close();
    }
}
