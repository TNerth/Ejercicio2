package Ejercicio2;
import javax.swing.JFrame;

public class app {
    public static void main(String[] args) {
        MarcoCompraPasaje marco = new MarcoCompraPasaje();
        marco.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marco.setSize(540, 520);
        marco.setLocationRelativeTo(null);
        marco.setVisible(true);
    }
}