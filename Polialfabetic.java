import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class Polialfabetic {

   
    private static final char[] ALFABET = 
        "abcdefghijklmnopqrstuvwxyzàáèéíòóúüïçñABCDEFGHIJKLMNOPQRSTUVWXYZÀÁÈÉÍÒÓÚÜÏÇÑ".toCharArray();
    private static ArrayList<Character> alfabetPermutat;
    private static Random random;

   
    public static void initRandom(String clauSecreta) {
        random = new Random(clauSecreta.hashCode());
    }

   
    public static void permutaAlfabet() {
        alfabetPermutat = new ArrayList<>();
        for (char c : ALFABET) {
            alfabetPermutat.add(c);
        }
        Collections.shuffle(alfabetPermutat, random);
    }

    
    public static String xifraPoliAlfa(String msg) {
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

    
    public static String desxifraPoliAlfa(String msgXifrat) {
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

    
    private static int buscarIndexAlfabet(char lletra) {
        for (int i = 0; i < ALFABET.length; i++) {
            if (ALFABET[i] == lletra) {
                return i;
            }
        }
        return -1;
    }

    
    private static int buscarIndexPermutat(char lletra) {
        for (int i = 0; i < alfabetPermutat.size(); i++) {
            if (alfabetPermutat.get(i) == lletra) {
                return i;
            }
        }
        return -1;
    }

    
    private static boolean ésCaracterEspecial(char lletra) {
        String especials = "àáèéíòóúüïçñÀÁÈÉÍÒÓÚÜÏÇÑ";
        return especials.indexOf(lletra) != -1;
    }

    // Mètode principal
    public static void main(String[] args) {
        String clauSecreta = "contrasenya123";
        String msgs[] = {
            "Test 01 àrbitre, coixí, Perímetre",
            "Test 02 Taüll, DÍA, año",
            "Test 03 Peça, Òrrius, Bòvila"
        };

        String msgsXifrats[] = new String[msgs.length];

        System.out.println("Xifratge:\n--------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            msgsXifrats[i] = xifraPoliAlfa(msgs[i]);
            System.out.printf("%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println("\nDesxifratge:\n-----------");
        for (int i = 0; i < msgs.length; i++) {
            initRandom(clauSecreta);
            String msgDesxifrat = desxifraPoliAlfa(msgsXifrats[i]);
            System.out.printf("%-34s -> %s%n", msgsXifrats[i], msgDesxifrat);
        }
    }
}
