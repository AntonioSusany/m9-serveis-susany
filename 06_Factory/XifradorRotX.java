public class XifradorRotX implements Xifrador {

    public static final char[] mins = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static final char[] mayus = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    // Constructor vacío
    public XifradorRotX() {}

    // Método de la interfaz para cifrar usando un desplazamiento
    @Override
    public String xifrar(String frase, int desplaçament) {
        return xifraRotX(frase, desplaçament);
    }

    // Método de la interfaz para descifrar usando un desplazamiento
    @Override
    public String desxifrar(String xifrat, int desplaçament) {
        return desxifraRotX(xifrat, desplaçament);
    }

    // Método que cifra el texto con un desplazamiento ROTX
    public static String xifraRotX(String frase, int desplaçament) {
        StringBuilder xifrat = new StringBuilder();
        desplaçament = desplaçament % 26;

        for (char i : frase.toCharArray()) {
            if (Character.isLowerCase(i)) {
                int posicio = (i - 'a' + desplaçament) % 26;
                xifrat.append(mins[posicio]);
            } else if (Character.isUpperCase(i)) {
                int posicio = (i - 'A' + desplaçament) % 26;
                xifrat.append(mayus[posicio]);
            } else {
                xifrat.append(i);
            }
        }
        return xifrat.toString();
    }

    // Método que descifra el texto con un desplazamiento ROTX
    public static String desxifraRotX(String xifrat, int desplaçament) {
        StringBuilder desxifrat = new StringBuilder();
        desplaçament = desplaçament % 26;

        for (char i : xifrat.toCharArray()) {
            if (Character.isLowerCase(i)) {
                int posicio = (i - 'a' - desplaçament + 26) % 26;
                desxifrat.append(mins[posicio]);
            } else if (Character.isUpperCase(i)) {
                int posicio = (i - 'A' - desplaçament + 26) % 26;
                desxifrat.append(mayus[posicio]);
            } else {
                desxifrat.append(i);
            }
        }
        return desxifrat.toString();
    }


    public static void main(String[] args) {
        XifradorRotX xifrador = new XifradorRotX();
        String frase = "Juan Manuel Sanchez";
        String xifrat = xifrador.xifrar(frase, 25);
        String desxifrat = xifrador.desxifrar(xifrat, 25);

        System.out.println("Texto cifrado: " + xifrat);
        System.out.println("Texto descifrado: " + desxifrat);
        
    }
}
