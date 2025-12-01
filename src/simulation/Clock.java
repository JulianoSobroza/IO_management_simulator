package simulation;

public class Clock {

    private long tempoAtual = 0;

    public void tick() {
        tempoAtual++;
    }

    public long getTempo() {
        return tempoAtual;
    }

    public void adiantar(long unidades) {
        tempoAtual += unidades;
    }
}
