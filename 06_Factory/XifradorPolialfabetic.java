import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private static final char[] ALFABET = 
        "abcdefghijklmnopqrstuvwxyzàáèéíòóúüïçñABCDEFGHIJKLMNOPQRSTUVWXYZÀÁÈÉÍÒÓÚÜÏÇÑ".toCharArray();
    private ArrayList<Character> alfabetPermutat;
    private Random random;

    // Constructor vacío
    public XifradorPolialfabetic() {}

    // Método de inicialización que usa una clave secreta

    public void init(String clauSecreta) {
        random = new Random(clauSecreta.hashCode());
        permutaAlfabet();
    }

    // Método para cifrar el mensaje (implementación de la interfaz)
    @Override
    public String xifrar(String msg, int desplaçament) {
        return xifraPoliAlfa(msg);
    }

    // Método para descifrar el mensaje (implementación de la interfaz)
    @Override
    public String desxifrar(String msgXifrat, int desplaçament) {
        return desxifraPoliAlfa(msgXifrat);
    }

    // Permuta el alfabeto utilizando el generador aleatorio
    private void permutaAlfabet() {
        alfabetPermutat = new ArrayList<>();
        for (char c : ALFABET) {
            alfabetPermutat.add(c);
        }
        Collections.shuffle(alfabetPermutat, random);
    }

    // Método para cifrar un mensaje
    private String xifraPoliAlfa(String msg) {
        StringBuilder msgXifrat = new StringBuilder();

        for (char lletra : msg.toCharArray()) {
            if (Character.isLetter(lletra) || ésCaracterEspecial(lletra)) {
                permutaAlfabet();
                int index = buscarIndexAlfabet(lletra);
                if (index != -1) {
                    msgXifrat.append(alfabetPermutat.get(index));
                } else {
                    msgXifrat.append(lletra); 
                }
            } else {
                msgXifrat.append(lletra);
            }
        }
        return msgXifrat.toString();
    }

    // Método para descifrar un mensaje
    private String desxifraPoliAlfa(String msgXifrat) {
        StringBuilder msgDesxifrat = new StringBuilder();

        for (char lletra : msgXifrat.toCharArray()) {
            if (Character.isLetter(lletra) || ésCaracterEspecial(lletra)) {
                permutaAlfabet();
                int index = buscarIndexPermutat(lletra);
                if (index != -1) {
                    msgDesxifrat.append(ALFABET[index]);
                } else {
                    msgDesxifrat.append(lletra); 
                }
            } else {
                msgDesxifrat.append(lletra); 
            }
        }
        return msgDesxifrat.toString();
    }

    // Buscar el índice de un carácter en el alfabeto original
    private int buscarIndexAlfabet(char lletra) {
        for (int i = 0; i < ALFABET.length; i++) {
            if (ALFABET[i] == lletra) {
                return i;
            }
        }
        return -1;
    }

    // Buscar el índice de un carácter en el alfabeto permutado
    private int buscarIndexPermutat(char lletra) {
        for (int i = 0; i < alfabetPermutat.size(); i++) {
            if (alfabetPermutat.get(i) == lletra) {
                return i;
            }
        }
        return -1;
    }

    // Verifica si un carácter es especial
    private boolean ésCaracterEspecial(char lletra) {
        String especials = "àáèéíòóúüïçñÀÁÈÉÍÒÓÚÜÏÇÑ";
        return especials.indexOf(lletra) != -1;
    }

    // Método principal para pruebas
    public static void main(String[] args) {
        XifradorPolialfabetic xifrador = new XifradorPolialfabetic();
        String clauSecreta = "contrasenya123";
        xifrador.init(clauSecreta);

        String[] msgs = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        System.out.println("Xifratge:\n--------");
        for (String msg : msgs) {
            String xifrat = xifrador.xifrar(msg, 0);
            System.out.printf("%-34s -> %s%n", msg, xifrat);
        }

        System.out.println("\nDesxifratge:\n-----------");
        for (String xifrat : msgs) {
            String desxifrat = xifrador.desxifrar(xifrat, 0);
            System.out.printf("%-34s -> %s%n", xifrat, desxifrat);
        }
    }
}
