import java.security.MessageDigest;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.util.HexFormat;

public class Hashes {
    public int npass = 0; // Variable per comptar el nombre de contrasenyes provades

    // Mètode per generar un hash SHA-512 amb salt
    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(salt.getBytes()); // Afegim el salt al digest
        byte[] bytes = md.digest(pw.getBytes());
        HexFormat hex = HexFormat.of();
        return hex.formatHex(bytes); // Convertim el byte[] a String
    }

    // Mètode per generar un hash PBKDF2 amb salt
    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iterations = 10000;
        PBEKeySpec spec = new PBEKeySpec(pw.toCharArray(), salt.getBytes(), iterations, 256);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash); // Convertim el byte[] a String
    }

    // Mètode per realitzar força bruta per trobar la contrasenya
    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        char[] charset = "abcdefABCDEF1234567890!".toCharArray(); // Conjunt de caràcters
        for (int length = 1; length <= 6; length++) {
            npass = 0; // Inicialitzem el comptador
            if (forceRecursive(charset, new char[length], 0, alg, hash, salt)) {
                return new String(currentPass);
            }
        }
        return null; // Retornem null si no es troba cap contrasenya
    }

    // Mètode auxiliar per iterar recursivament sobre totes les combinacions
    private char[] currentPass;
    private boolean forceRecursive(char[] charset, char[] current, int pos, String alg, String hash, String salt) throws Exception {
        if (pos == current.length) {
            String generatedHash = alg.equals("SHA-512") ? getSHA512AmbSalt(new String(current), salt) : getPBKDF2AmbSalt(new String(current), salt);
            npass++; // Incrementem el comptador
            if (generatedHash.equals(hash)) {
                currentPass = current.clone();
                return true;
            }
            return false;
        }

        for (char c : charset) {
            current[pos] = c;
            if (forceRecursive(charset, current, pos + 1, alg, hash, salt)) {
                return true;
            }
        }
        return false;
    }

    // Mètode per calcular l'interval de temps entre dos instants
    public String getInterval(long t1, long t2) {
        long diff = t2 - t1;
        long millis = diff % 1000;
        diff /= 1000;
        long seconds = diff % 60;
        diff /= 60;
        long minutes = diff % 60;
        diff /= 60;
        long hours = diff % 24;
        long days = diff / 24;
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", days, hours, minutes, seconds, millis);
    }

    // Mètode main per provar els mètodes de la classe
    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "a";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt), h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = { "SHA-512", "PBKDF2" };

        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}
