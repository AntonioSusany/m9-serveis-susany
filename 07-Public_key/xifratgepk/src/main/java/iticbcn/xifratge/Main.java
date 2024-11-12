package iticbcn.xifratge;

import java.security.KeyPair;
import javax.xml.bind.DatatypeConverter;

public class Main {
    public static void main(String args[]) throws Exception {
        // Creació de l'objecte ClauPublica
        ClauPublica cp = new ClauPublica();
        
        // Missatge a xifrar
        String msg = "Missatge de prova per xifrar áéíóú àèìòù äëïöü";
        
        // Generació del parell de claus RSA
        KeyPair parellClaus = cp.generaParellClausRSA();
        
        // Xifrat del missatge
        byte[] msgXifrat = cp.xifraRSA(msg, parellClaus.getPublic());
        
        // Impressió del text xifrat
        System.out.println("=================================");
        System.out.print("Text xifrat: ");
        System.out.println(DatatypeConverter.printHexBinary(msgXifrat));
        
        // Desxifrat del missatge
        String msgDesxifrat = cp.desxifraRSA(msgXifrat, parellClaus.getPrivate());
        System.out.println("=================================");
        System.out.println("Text desxifrat: " + msgDesxifrat);
        
        // Codificació i impressió de la clau pública
        String strClauPub = DatatypeConverter.printHexBinary(parellClaus.getPublic().getEncoded());
        System.out.println("=================================");
        System.out.println("Clau pública: " + strClauPub);
        
        // Codificació i impressió de la clau privada
        String strClauPriv = DatatypeConverter.printHexBinary(parellClaus.getPrivate().getEncoded());
        System.out.println("=================================");
        System.out.println("Clau privada: " + strClauPriv);
    }
}
