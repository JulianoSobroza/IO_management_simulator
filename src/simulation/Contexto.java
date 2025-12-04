package simulation;

public class Contexto {

    private final int pc; // program counter fictício
    private final String estado;

    public Contexto(int pc, String estado) {
        this.pc = pc;
        this.estado = estado;
    }

    public int getPc() { return pc; }
    public String getEstado() { return estado; }

    @Override
    public String toString() {
        return String.format("{ PC=%d, Estado='%s' }", pc, estado);
    }
}
