package control;
public class Main {
    public static void main(String[] args) {
        Parque parque = new Parque("Entrada principal");

        ConsolaUsuario consola = new ConsolaUsuario(parque);
        consola.iniciar();
    }
}
