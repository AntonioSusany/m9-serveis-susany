public class Rot13 {

    public static final char[] mins = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static final char[] mayus = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void xifraRot13 (String frase){
        
        String xifrat = "";

        for (char i : frase.toCharArray()) {
            if (Character.isLowerCase(i)) {
                int posicio = (i - 'a' + 13) % 26;
                xifrat += mins[posicio];

            } else if (Character.isUpperCase(i)) {
                int posicio = (i - 'A' + 13) % 26;
                xifrat += mayus[posicio];
                
            } else {
                xifrat += i;
            }
        }

        System.out.println(xifrat); 
    }

    public static void desxifraRot13 (String xifrat){

        String desxifrat = "";

        for (char i : xifrat.toCharArray()) {
            if (Character.isLowerCase(i)) {
                int posicio = (i - 'a' - 13 + 26) % 26;
                desxifrat += mins[posicio];
            } else if (Character.isUpperCase(i)) {
                int posicio = (i - 'A' - 13 + 26) % 26;
                desxifrat += mayus[posicio];
            } else {
                desxifrat += i;
            }
        }

        System.out.println(desxifrat);

    }

    public static void main (String[] args){
        String xfrase = "Ubyn";
        String frase = "Hola";

        xifraRot13(frase);
        desxifraRot13(xfrase);

    }
 
 
}
