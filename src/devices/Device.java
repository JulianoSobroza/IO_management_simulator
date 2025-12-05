package devices;

import interrupts.Interrupt;

/**
 * Classe abstrata que representa um dispositivo de hardware genérico.
 * Todos os dispositivos (Teclado, Mouse, etc.) herdam daqui.
 */
public abstract class Device {
    private String nome;
    private Priority prioridade;
    private long taxaTransferencia; // Não usado muito na simulação, mas tá aí
    private long ultimoTempoInterrupcao;
    private long proximoTempoInterrupcao;
    protected java.util.Random aleatorio = new java.util.Random();

    public Device(String nome, Priority prioridade, long taxaTransferencia) {
        this.nome = nome;
        this.prioridade = prioridade;
        this.taxaTransferencia = taxaTransferencia;
        this.ultimoTempoInterrupcao = 0;
        this.proximoTempoInterrupcao = calcularProximaInterrupcao(0);
    }

    /**
     * Verifica se o dispositivo deve gerar uma interrupção no tempo atual.
     */
    public boolean deveInterromper(long tempoAtual) {
        if (tempoAtual >= proximoTempoInterrupcao) {
            ultimoTempoInterrupcao = tempoAtual;
            proximoTempoInterrupcao = calcularProximaInterrupcao(tempoAtual);
            return true;
        }
        return false;
    }

    // Cada dispositivo define quando será a próxima interrupção
    protected abstract long calcularProximaInterrupcao(long tempoAtual);

    /**
     * Cria um objeto de interrupção para ser enviado ao controlador.
     */
    public Interrupt gerarInterrupcao(long tempo) {
        return new Interrupt(this, prioridade, tempo);
    }

    // Abaixo somente os Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Priority getPrioridade() {
        return prioridade;
    }
}
