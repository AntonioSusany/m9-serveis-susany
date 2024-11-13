import java.util.*;

public class XifradorMonoalfabetic implements Xifrador {
    public static final char[] alfabet = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'à', 'á', 'è', 'é', 'í', 'ï', 'ò', 'ó', 'ú', 'ü', 'ñ', 'ç',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'À', 'Á', 'È', 'É', 'Í', 'Ï', 'Ò', 'Ó', 'Ú', 'Ü', 'Ñ', 'Ç'
    };

    private static char[] alfabetPermutat;

    public static void main(String[] args) {
        XifradorMonoalfabetic xifrador = new XifradorMonoalfabetic();
        xifrador.init("contrasenya");

        String cadena = "Bon Dia!";
        String xifrat = xifrador.xifra(cadena);
        System.out.println("Xifrat: " + xifrat);

        String desxifrat = xifrador.desxifra(xifrat);
        System.out.println("Desxifrat: " + desxifrat + "  (A partir del xifrat: " + xifrat + ")");
    }

    @Override
    public void init(String clauSecreta) {
        alfabetPermutat = permutaAlfabet(alfabet);
    }

    @Override
    public String xifra(String cadena) {
        return xifraMonoAlfa(cadena);
    }

    @Override
    public String desxifra(String xifrat) {
        return desxifraMonoAlfa(xifrat);
    }

    public static char[] permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }
        Collections.shuffle(llista);
        char[] alfabetPermutat = new char[llista.size()];
        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
        return alfabetPermutat;
    }

    public static String xifraMonoAlfa(String cadena) {
        StringBuilder xifrat = new StringBuilder();
        for (char j : cadena.toCharArray()) {
            if (Character.isLetter(j)) {
                for (int i = 0; i < alfabet.length; i++) {
                    if (alfabet[i] == j) {
                        xifrat.append(alfabetPermutat[i]);
                        break;
                    }
                }
            } else {
                xifrat.append(j);
            }
        }
        return xifrat.toString();
    }

    public static String desxifraMonoAlfa(String xifrat) {
        StringBuilder desxifrat = new StringBuilder();
        for (char j : xifrat.toCharArray()) {
            if (Character.isLetter(j)) {
                for (int i = 0; i < alfabetPermutat.length; i++) {
                    if (alfabetPermutat[i] == j) {
                        desxifrat.append(alfabet[i]);
                        break;
                    }
                }
            } else {
                desxifrat.append(j);
            }
        }
        return desxifrat.toString();
    }
}
