public class RotX {

    public static final char[] mins = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
    public static final char[] mayus = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public static void xifraRotX (String frase, int desplaçament){
        
        String xifrat = "";

        desplaçament = desplaçament % 26;

        for (char i : frase.toCharArray()) {
            if (Character.isLowerCase(i)) {
                int posicio = (i - 'a' + desplaçament) % 26;
                xifrat += mins[posicio];

            } else if (Character.isUpperCase(i)) {
                int posicio = (i - 'A' + desplaçament) % 26;
                xifrat += mayus[posicio];
                
            } else {
                xifrat += i;
            }
        }

        System.out.println(xifrat); 
    }

    public static void desxifraRotX (String xifrat, int desplaçament){

        String desxifrat = "";

        desplaçament = desplaçament % 26;

        for (char i : xifrat.toCharArray()) {
            if (Character.isLowerCase(i)) {
                int posicio = (i - 'a' - desplaçament + 26) % 26;
                desxifrat += mins[posicio];
            } else if (Character.isUpperCase(i)) {
                int posicio = (i - 'A' - desplaçament + 26) % 26;
                desxifrat += mayus[posicio];
            } else {
                desxifrat += i;
            }
        }

        System.out.println(desxifrat);

    }

    public static void forcaBrutaRotX (String xifrat){

        int longitud = mins.length;

        for (int i = 0; i <= longitud; i++ ) {

            String desxifrat = "";

            for (char j : xifrat.toCharArray()) {

                if (Character.isLowerCase(j)) {
                    int posicio = (j - 'a' - i + 26) % 26;
                    desxifrat += mins[posicio];
                } else if (Character.isUpperCase(j)) {
                    int posicio = (j - 'A' - i + 26) % 26;
                    desxifrat += mayus[posicio];
                } else {
                    desxifrat += j;
                }
            }
    
        System.out.println("Rot" + i + ": " + desxifrat);

        }

    }

    public static void main (String[] args){
        String frase = "Juan Manuel Sanchez";
        String xifrat = "Itzm Lzmtdk Rzmbgdy";
        int desplaçament = 25;

        //xifraRotX(frase, desplaçament);
        //desxifraRotX(xifrat, desplaçament);
        forcaBrutaRotX(xifrat);
    }
 
 
}