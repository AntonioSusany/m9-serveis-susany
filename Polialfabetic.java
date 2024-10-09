import java.util.*;

public class Polialfabetic {
    
    public static final char[] alfabet = {
        'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
        'à', 'á', 'è', 'é', 'í', 'ï', 'ò', 'ó', 'ú', 'ü', 'ñ', 'ç',
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
        'À', 'Á', 'È', 'É', 'Í', 'Ï', 'Ò', 'Ó', 'Ú', 'Ü', 'Ñ', 'Ç'
    };

    public static char[] alfabetPermutat = new char[alfabet.length];

    public static void main ( String [] args ) {
        
        String msgs[] = { "Test 01 àrbritre, coixí, Perímetre" , "Test 02 Taüll, DÍA, año" ,
            "Test 03 Peça, Òrrius, Bòvila" };
            
        String msgsXifrats [] = new String [ msgs.length ];

        System.out.println ( "Xifratge: \n --------" );

        for ( int i = 0; i < msgs. length ; i ++) {
            
            initRandom(clauSecreta);

            msgsXifrats[i] = xifraPoliAlf( msgs[i]);
''
            System.out.printf( "%-34s -> %s%n", msgs[i], msgsXifrats[i]);
        }

        System.out.println ( "Desxifratge: \n -----------" );
        
        for ( int i = 0; i < msgs. length ; i ++) {
            
            initRandom(clauSecreta);

            String msg = desxifraPoliAlfa( msgsXifrats [ i ]);
            System.out.printf( "%-34s -> %s%n", msgsXifrats[i], msg);
        }
    }

    public static void permutaAlfabet(char[] alfabet) {
        List<Character> llista = new ArrayList<>();
        for (char c : alfabet) {
            llista.add(c);
        }

        Collections.shuffle(llista);

        for (int i = 0; i < llista.size(); i++) {
            alfabetPermutat[i] = llista.get(i);
        }
        
    }

    public static String xifraPoliAlfa( String msg ){



    }
}
