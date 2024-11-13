import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.security.SecureRandom;

public class XifradorAES {

    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final int MIDA_IV = 16;
    private static byte[] iv = new byte[MIDA_IV];
    private static final String CLAU = "LaClauSecretaQueVulguis";

    public static void main(String[] args) {
        String msgs[] = {"Lorem ipsum dicet",
                        "Hola Andrés cómo está tu cuñado",
                        "Àgora ïlla Ôtto"};

        for (String msg : msgs) {
            byte[] bXifrats = null;
            String desxifrat = "";

            try {
                bXifrats = xifraAES(msg, CLAU);
                desxifrat = desxifraAES(bXifrats, CLAU);
            } catch (Exception e) {
                System.err.println("Error de xifrat: " + e.getLocalizedMessage());
            }
            System.out.println("--");
            System.out.println("Missatge inicial: " + msg);
            System.out.println("Missatge xifrat: " + Base64.getEncoder().encodeToString(bXifrats));
            System.out.println("Missatge desxifrat: " + desxifrat);
        }
    }

    public static byte[] xifraAES(String msg, String clau) throws Exception {
        // Generar l'IV de manera segura
        SecureRandom random = new SecureRandom();
        random.nextBytes(iv);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Generar hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes("UTF-8"));
        SecretKeySpec clauSecreta = new SecretKeySpec(Arrays.copyOf(clauHash, 32), ALGORISME_XIFRAT);

        // Inicialitzar el xifrador AES
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.ENCRYPT_MODE, clauSecreta, ivSpec);

        // Xifrar el missatge
        byte[] msgXifrat = cipher.doFinal(msg.getBytes("UTF-8"));

        // Combinar IV i missatge xifrat
        byte[] bIvIMsgXifrat = new byte[iv.length + msgXifrat.length];
        System.arraycopy(iv, 0, bIvIMsgXifrat, 0, iv.length);
        System.arraycopy(msgXifrat, 0, bIvIMsgXifrat, iv.length, msgXifrat.length);

        return bIvIMsgXifrat;
    }

    public static String desxifraAES(byte[] bIvIMsgXifrat, String clau) throws Exception {
        // Extreure l'IV
        byte[] iv = Arrays.copyOfRange(bIvIMsgXifrat, 0, MIDA_IV);
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        // Extreure la part xifrada
        byte[] msgXifrat = Arrays.copyOfRange(bIvIMsgXifrat, MIDA_IV, bIvIMsgXifrat.length);

        // Fer hash de la clau
        MessageDigest digest = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] clauHash = digest.digest(clau.getBytes("UTF-8"));
        SecretKeySpec clauSecreta = new SecretKeySpec(Arrays.copyOf(clauHash, 32), ALGORISME_XIFRAT);

        // Inicialitzar el xifrador AES
        Cipher cipher = Cipher.getInstance(FORMAT_AES);
        cipher.init(Cipher.DECRYPT_MODE, clauSecreta, ivSpec);

        // Desxifrar el missatge
        byte[] msgDesxifrat = cipher.doFinal(msgXifrat);

        return new String(msgDesxifrat, "UTF-8");
    }
}
